from flask import Flask, render_template, request
import pandas as pd
import difflib
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import cosine_similarity


app = Flask(__name__)

df = None  # DataFrame yang digunakan untuk menyimpan data bahan makanan
selected_foods = []  # List yang digunakan untuk menyimpan bahan makanan yang dipilih

def load_data():
    global df
    # Kode untuk memuat data bahan makanan ke dalam DataFrame
    df = pd.read_csv('calorie_infos final.csv', encoding='latin-1', sep=';')
    df = df.dropna()

def kalori(Bahan, Gram):
    try:
        kalori_bahan = df.loc[df['Bahan'].str.lower() == Bahan.lower(), 'Kalori'].iloc[0]
        Kalori = kalori_bahan * Gram
        return Kalori
    except IndexError:
        raise ValueError("Mohon maaf, bahan yang Anda masukkan belum tersedia di database kami")

def calculate_similarity(query, corpus):
    vectorizer = TfidfVectorizer()
    corpus_vector = vectorizer.fit_transform(corpus)
    query_vector = vectorizer.transform([query])
    similarities = cosine_similarity(query_vector, corpus_vector)
    return similarities[0]


def get_most_similar(query, corpus, n=5):
    similarities = calculate_similarity(query, corpus)
    indices = similarities.argsort()[::-1][:n]
    return [corpus[i] for i in indices]


@app.route('/', methods=['GET', 'POST'])
def index():
    global selected_foods
    load_data()

    if request.method == 'POST':
        bahan = request.form['bahan']
        berat = request.form['berat']
        repeat = request.form['repeat']
    
        if bahan not in df['Bahan'].str.lower().values:
            similarities = get_most_similar(bahan, df['Bahan'].str.lower().values, n=10)
            if similarities:
                match_options = []
                for i, similar in enumerate(similarities, start=1):
                    match_options.append((i, similar))


                return render_template('match.html', match_options=match_options, bahan=bahan)
            else:
                return render_template('error.html', error_message="Mohon maaf, bahan yang Anda masukkan belum tersedia di database kami")

        if repeat == "yes":
            try:
                Gram = int(berat)
            except ValueError:
                return render_template('error.html', error_message="Masukkan berat dalam bentuk angka")

            try:
                Kalori = kalori(bahan, Gram)
            except ValueError as e:
                return render_template('error.html', error_message=str(e))

            selected_foods.append((bahan, Kalori, Gram))
            print(f"Kalori dari {bahan} sebanyak {Gram} gr adalah {Kalori:.2f} kal")
            total_kalori = sum(food[1] for food in selected_foods)
            print(f"Total kalori sejauh ini adalah {total_kalori:.2f} kal")

            return render_template('index.html')

        elif repeat == "no":
            try:
                Gram = int(berat)
            except ValueError:
                return render_template('error.html', error_message="Masukkan berat dalam bentuk angka")

            try:
                Kalori = kalori(bahan, Gram)
            except ValueError as e:
                return render_template('error.html', error_message=str(e))

            selected_foods.append((bahan, Kalori, Gram))
            print(f"Kalori dari {bahan} sebanyak {Gram} gr adalah {Kalori:.2f} kal")
            total_kalori = sum(food[1] for food in selected_foods)
            print(f"Total kalori sejauh ini adalah {total_kalori:.2f} kal")

            return render_template('total.html', selected_foods=selected_foods, total_kalori=total_kalori, bahan=bahan, gram=berat, kalori=Kalori)
    else:
        selected_foods = []  # Mengatur ulang selected_foods menjadi list kosong

    return render_template('index.html')

@app.route('/similar', methods=['POST'])
def handle_match():
    selected_index = int(request.form['selected_index'])
    bahan = request.form['bahan']

    similarities = get_most_similar(bahan.lower(), df['Bahan'].str.lower().values, n=10)  # Mengambil 10 hasil terdekat
    Bahan = similarities[selected_index - 1]


    return render_template('input.html', bahan=Bahan)

@app.route('/input', methods=['POST'])
def handle_input():
    bahan = request.form['bahan']
    gram = request.form['gram']

    try:
        Gram = int(gram)
    except ValueError:
        return render_template('error.html', error_message="Masukkan berat dalam bentuk angka")

    try:
        Kalori = kalori(bahan, Gram)
    except ValueError as e:
        return render_template('error.html', error_message=str(e))

    selected_foods.append((bahan, Kalori, Gram))
    print(f"Kalori dari {bahan} sebanyak {Gram} gr adalah {Kalori:.2f} kal")
    total_kalori = sum(food[1] for food in selected_foods)
    print(f"Total kalori sejauh ini adalah {total_kalori:.2f} kal")

    return render_template('repeat.html', bahan=bahan)

@app.route('/repeat', methods=['POST'])
def handle_repeat():
    repeat = request.form['repeat']

    if repeat == "yes":
        return render_template('index.html')
    elif repeat == "no":
        total_kalori = sum(food[1] for food in selected_foods)
        return render_template('total.html', selected_foods=selected_foods, total_kalori=total_kalori)



if __name__ == '__main__':
    app.run()
