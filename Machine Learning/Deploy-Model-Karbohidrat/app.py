from flask import Flask, render_template,request

from keras_preprocessing.image import load_img
from keras_preprocessing.image import img_to_array
from keras_applications.mobilenet_v2 import preprocess_input
from keras_applications.mobilenet_v2 import decode_predictions
from keras_applications.mobilenet_v2 import MobileNetV2
from keras.models import load_model


import tensorflow as tf
from tensorflow import keras
import numpy as np
import os
from PIL import Image
import pandas as pd 
import matplotlib.pyplot as plt
import base64
from io import BytesIO

app=Flask(__name__)

#Load model
modelxception = load_model("my_model.h5")
modelrekom = load_model("Model Rekomendasi Karbohidrat.h5")
@app.route('/',methods=['GET'])

def hello_word():
    return render_template("index.html")

@app.route("/",methods=["POST"])
def predict():
    imagefile=request.files["imagefile"]
    image_path="./images/" + imagefile.filename
    imagefile.save(image_path)

    class_names = ["Jagung","Kentang","Mie & Pasta","Nasi","Oatmeal","Roti","Singkong","Ubi"]


    # Membaca dan mempersiapkan gambar untuk prediksi
    img = tf.keras.utils.load_img(image_path, target_size=(150, 150))
    x = tf.keras.utils.img_to_array(img)
    x = np.expand_dims(x, axis=0)
    x = x / 255.0

    # Mengubah gambar menjadi tensor
    x = tf.convert_to_tensor(x, dtype=tf.float32)

    # Melakukan prediksi
    predictions = modelxception(x)
    predicted_class_index = tf.argmax(predictions[0]).numpy()
    predicted_class = class_names[predicted_class_index]

    ##Membaca info gizi
    df = pd.read_csv("Informasi Gizi Karbohidrat.csv", sep = ',')
    # Menghilangkan spasi tambahan di kolom 'Bahan'
    df['Bahan'] = df['Bahan'].str.strip()
    # Nama makanan
    food_names =  df['Bahan'].values
    # Data Makanan
    data = df[['Kalori', 'Lemak(g)', 'Karbohidrat(g)', 'Protein(g)']].values
    # Normalisasi data
    data_norm = (data - np.min(data, axis=0)) / (np.max(data, axis=0) - np.min(data, axis=0))

    # Mengambil informasi nutrisi berdasarkan kelas prediksi
    predicted_class = class_names[predicted_class_index]
    nutrient_info = df.loc[df['Bahan'] == predicted_class, ['Kalori', 'Lemak(g)', 'Karbohidrat(g)', 'Protein(g)','Ukuran','Keterangan']]

    # Konversi nutrient_info menjadi dictionary
    nutrient_info_dict = nutrient_info.to_dict(orient='records')

    # Mencari makanan dengan info gizi terdekat
    detected_object = nutrient_info[['Kalori', 'Lemak(g)', 'Karbohidrat(g)', 'Protein(g)']].values[0]
    detected_object_norm = (detected_object - np.min(data, axis=0)) / (np.max(data, axis=0) - np.min(data, axis=0))
    predicted_calories = modelrekom.predict(np.expand_dims(detected_object_norm, axis=0))
    predicted_calories = predicted_calories * (np.max(data, axis=0)[0] - np.min(data, axis=0)[0]) + np.min(data, axis=0)[0]
    predicted_calories = np.squeeze(predicted_calories)

    # Mencari 2 makanan dengan gizi terdekat untuk setiap baris
    num_rows = nutrient_info.shape[0]
    recommended_foods_list = []
    recommended_sizes_list = []
    for i in range(num_rows):
        predicted_calories = nutrient_info.iloc[i]['Kalori']
        min_calories_diffs = []
        recommended_foods = []
        recommended_sizes = []
        
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
    
    recommended_foods_list.extend(recommended_foods)
    recommended_sizes_list.extend(recommended_sizes)


    return render_template("index.html", prediction=predicted_class, nutrient_info=nutrient_info_dict, recommended_foods_list=recommended_foods_list, recommended_sizes_list=recommended_sizes_list, df=df)


if __name__ =='__main__':
    app.run(port=3000, debug=True)
