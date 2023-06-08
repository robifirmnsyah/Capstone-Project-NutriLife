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
model = keras.models.load_model("model_buah.h5")
modelrekom = keras.models.load_model("rekomendasi_buah.h5")


def load_recipe_data():
    recipe_data = []
    with open('Resep - Buah.csv', 'r') as csvfile:
        reader = csv.DictReader(csvfile)
        for row in reader:
            kategori = row['Bahan']
            recipe_name = row['Nama Resep']
            bahan = row['Bahan yang dibutuhkan']
            cara_membuat = row['Cara Membuat']
            kalori = row['Kalori']
            recipe_data.append({
                'Bahan': kategori,
                'Nama Resep': recipe_name,
                'Bahan yang dibutuhkan': bahan,
                'Cara Membuat': cara_membuat,
                'Kalori': kalori
            })
    return recipe_data


def transform_image(img):
    img = img.resize((224, 224))
    img = img_to_array(img)
    img = img.astype(np.float32) / 255
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
            class_names = ["Alpukat", "Anggur", "Apel", "Belimbing", "Buah Naga", "Durian", "Jambu", "Jeruk", "Kiwi", "Kurma", "Leci", "Mangga", "Manggis", "Melon", "Nanas",
                           "Nangka", "Pepaya", "Pir", "Pisang", "Rambutan", "Salak", "Sawo", "Semangka", "Sirsak", "Stroberi", "Tomat"]
            image_bytes = file.read()
            pillow_img = Image.open(io.BytesIO(image_bytes))
            predictions = predict(transform_image(pillow_img))
            predicted_class_index = tf.argmax(predictions[0]).numpy()
            predicted_class = class_names[predicted_class_index]

            # Membaca info gizi
            df = pd.read_csv("Informasi Gizi Buah.csv", sep=',')
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
            nutrient_info = df.loc[df['Bahan'] == predicted_class, [
                'Kalori', 'Lemak(g)', 'Karbohidrat(g)', 'Protein(g)', 'Ukuran',  'Keterangan']]

            # Konversi nutrient_info menjadi dictionary
            nutrient_info_dict = nutrient_info.to_dict(orient='records')

            # Mencari 2 makanan dengan gizi terdekat untuk setiap baris
            num_rows = min(nutrient_info.shape[0], 2)
            recommended_foods_list = []
            recommended_sizes_list = []
            recommended_nutrient_info_list = []

            for i in range(num_rows):
                recommended_foods_list = []
                recommended_sizes_list = []
                recommended_nutrient_info_list = []

                predicted_calories = nutrient_info.iloc[i]['Kalori']
                min_calories_diffs = []
                recommended_foods = []
                recommended_sizes = []
                recommended_nutrient_info = []

                for j in range(len(data)):
                    calories_diff = abs(predicted_calories - data[j][0])
                    # Skip food if it is the same as the detected food
                    if food_names[j] == predicted_class:
                        continue
                    if len(min_calories_diffs) < 2:
                        min_calories_diffs.append(calories_diff)
                        recommended_foods.append(food_names[j])
                        recommended_sizes.append(df.loc[j, 'Ukuran'])
                    else:
                        max_diff_index = np.argmax(min_calories_diffs)
                        if calories_diff < min_calories_diffs[max_diff_index]:
                            min_calories_diffs[max_diff_index] = calories_diff
                            recommended_foods[max_diff_index] = food_names[j]
                            recommended_sizes[max_diff_index] = df.loc[j, 'Ukuran']

            for recommended_food in recommended_foods:
                nutrient_info = df.loc[df['Bahan'] == recommended_food, [
                    'Kalori', 'Lemak(g)', 'Karbohidrat(g)', 'Protein(g)', 'Ukuran', 'Keterangan']]
                recommended_nutrient_info.append(
                    nutrient_info.to_dict(orient='records')[0])

                # Check if recommended_food already exists in recommended_foods_list
                if recommended_food not in recommended_foods_list:
                    recommended_foods_list.append(recommended_food)
                    recommended_sizes_list.append(
                        recommended_sizes[recommended_foods.index(recommended_food)])

                    recommended_nutrient_info_list.append(
                        recommended_nutrient_info[-1])
                # Menggabungkan recommended_foods_list dan recommended_nutrient_info_list
                combined_list = [{"food": food, "nutrient_info": nutrient_info} for food, nutrient_info in zip(
                    recommended_foods_list, recommended_nutrient_info_list)]

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
                recipe_details['name'] = recipe['Nama Resep']
                recipe_details['ingredients'] = [ingredient.strip(
                ) for ingredient in recipe['Bahan yang dibutuhkan'].split(';')]
                recipe_details['steps'] = [step.strip()
                                           for step in recipe['Cara Membuat'].split(';')]
                recipe_details['kalori'] = recipe['Kalori']
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
    app.run(port=4000, debug=True)
