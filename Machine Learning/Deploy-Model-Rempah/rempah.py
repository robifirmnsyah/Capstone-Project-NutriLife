from flask import Flask, request, jsonify
from PIL import Image
import numpy as np
import matplotlib.pyplot as plt
from tensorflow.keras.preprocessing.image import ImageDataGenerator, load_img, img_to_array
from tensorflow import keras
import tensorflow as tf
import pandas as pd
import csv
import io
import os
os.environ['TF_CPP_MIN_LOG_LEVEL'] = '2'

# Import model
model = keras.models.load_model("model_rempah.h5")
modelrekom = keras.models.load_model("rekomendasi_rempah.h5")


def load_recipe_data():
    recipe_data = []
    with open('Resep - Rempah.csv', 'r') as csvfile:
        reader = csv.DictReader(csvfile)
        for row in reader:
            kategori = row['Bahan']
            dosis = row['Dosis']
            bagian = row['Bagian yang digunakan']
            cara_membuat = row['Cara Membuat']
            larangan = row['Larangan']
            manfaat = row['Manfaat']
            recipe_data.append({
                'Bahan': kategori,
                'Dosis': dosis,
                'Bagian yang digunakan': bagian,
                'Cara Membuat': cara_membuat,
                'Larangan': larangan,
                'Manfaat': manfaat
            })
    return recipe_data


def transform_image(img):
    img = img.resize((150, 150))
    img = img_to_array(img)
    img = img.astype(np.float32) / 255
    img = tf.image.resize(img, [150, 150])
    img = np.expand_dims(img, axis=0)
    return img


def predict(x):
    predictions = model.predict(x)
    return predictions


app = Flask(__name__)


@app.route("/", methods=["GET", "POST"])
def index():
    if request.method == "POST":
        file = request.files.get('file')
        if file is None or file.filename == "":
            return jsonify({"error": "no file"})
        try:
            class_names = ["Asam Jawa", "Bawang Bombai", "Bawang Daun", "Bawang Merah", "Bawang Putih", "Cabai", "Daun Salam",
                           "Jahe", "Kemiri", "Kencur", "Ketumbar", "Kunyit", "Lengkuas", "Merica", "Paprika", "Seledri", "Serai"]
            image_bytes = file.read()
            pillow_img = Image.open(io.BytesIO(image_bytes))
            predictions = predict(transform_image(pillow_img))
            predicted_class_index = tf.argmax(predictions[0]).numpy()
            predicted_class = class_names[predicted_class_index]

            # Membaca info gizi
            df = pd.read_csv("Informasi Gizi Rempah.csv", sep=',')
            # Menghilangkan spasi tambahan di kolom 'Bahan'
            df['Bahan'] = df['Bahan'].str.strip()
            # Nama makanan
            food_names = df['Bahan'].values
            # Data Makanan
            data = df[[
                'Kalori', 'Lemak(g)', 'Karbohidrat(g)', 'Protein(g)']].values
            # Normalisasi data
            data_norm = (data - np.min(data, axis=0)) / \
                (np.max(data, axis=0) - np.min(data, axis=0))

            # Mengambil informasi nutrisi berdasarkan kelas prediksi
            predicted_class = class_names[predicted_class_index]
            nutrient_info = df.loc[df['Bahan'] == predicted_class, [
                'Kalori', 'Lemak(g)', 'Karbohidrat(g)', 'Protein(g)', 'Ukuran',  'Keterangan']]

            # Konversi nutrient_info menjadi dictionary
            nutrient_info_dict = nutrient_info.to_dict(orient='records')

            # Mencari makanan dengan info gizi terdekat
            detected_nutrients = nutrient_info[[
                'Kalori', 'Lemak(g)', 'Karbohidrat(g)', 'Protein(g)']]
            mean_nutrients = detected_nutrients.mean()
            # Normalisasi nutrisi yang dideteksi
            detected_object = mean_nutrients.values
            detected_object_norm = (detected_object - np.min(data, axis=0)) / \
                (np.max(data, axis=0) - np.min(data, axis=0))
            predicted_calories = modelrekom.predict(
                np.expand_dims(detected_object_norm, axis=0))
            predicted_calories = predicted_calories * \
                (np.max(data, axis=0)[0] - np.min(data, axis=0)
                 [0]) + np.min(data, axis=0)[0]
            predicted_calories = np.squeeze(predicted_calories)

            # Mencari 3 makanan dengan kalori terdekat
            min_calories_diffs = []
            recommended_foods = []
            recommended_sizes = []
            recommended_nutrient_info = []

            for i in range(len(data)):
                calories_diff = abs(predicted_calories - data[i][0])
                if food_names[i] == predicted_class:
                    continue
                if len(min_calories_diffs) < 3:
                    if food_names[i] not in recommended_foods:
                        min_calories_diffs.append(calories_diff)
                        recommended_foods.append(food_names[i])
                        recommended_sizes.append(df.loc[i, 'Ukuran'])
                else:
                    max_diff_index = np.argmax(min_calories_diffs)
                    if calories_diff < min_calories_diffs[max_diff_index]:
                        if food_names[i] not in recommended_foods:
                            min_calories_diffs[max_diff_index] = calories_diff
                            recommended_foods[max_diff_index] = food_names[i]
                            recommended_sizes[max_diff_index] = df.loc[i, 'Ukuran']

            for recommended_food in recommended_foods:
                nutrient_info = df.loc[df['Bahan'] == recommended_food, [
                    'Kalori', 'Lemak(g)', 'Karbohidrat(g)', 'Protein(g)', 'Ukuran', 'Keterangan']]
                recommended_nutrient_info.append(
                    nutrient_info.to_dict(orient='records')[0])

                # Menggabungkan recommended_foods_list dan recommended_nutrient_info_list
                combined_list = [{"food": food, "nutrient_info": nutrient_info} for food, nutrient_info in zip(
                    recommended_foods, recommended_nutrient_info)]

            # Find the recommended recipes based on the predicted class
            recipe_data = load_recipe_data()
            recommended_recipes = []

            for data in recipe_data:
                if predicted_class == data['Bahan']:
                    recommended_recipes.append(data)

            # Display the details of each recommended recipe
            recommended_recipe_details = []
            for i, recipe in enumerate(recommended_recipes[:3]):
                recipe_details = {}
                recipe_details['name'] = recipe['Manfaat']
                recipe_details['dosis'] = recipe['Dosis']
                recipe_details['larangan'] = recipe['Larangan']
                recipe_details['bagian'] = recipe['Bagian yang digunakan']
                recipe_details['steps'] = [step.strip()
                                           for step in recipe['Cara Membuat'].split(';')]
                recommended_recipe_details.append(recipe_details)

            response = {
                "prediction": predicted_class,
                "nutrient_info": nutrient_info_dict,
                "recommended_foods_and_info": combined_list,
                "recommended_recipes": recommended_recipe_details
            }
            return jsonify(response)
        except Exception as e:
            return jsonify({"error": str(e)})
    return "OK"


if __name__ == "__main__":
    app.run(debug=True)
