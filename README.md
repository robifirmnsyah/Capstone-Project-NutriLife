# NutriLife - Final Capstone Project 2023
Food is one thing that cannot be separated from people’s lives. But, food can affect everything such as the immune system and heart health, and many more. Eating food  that has imbalanced nutrition or isn't nutritious will cause problems like malnutrition or being overweight. So, The food we ate must have balanced nutrition and we as humans should know the nutrition we have eaten. 
So, we decided to make an application to control daily nutrition with some new features to help maintain health and food.

## Our Team

|          Nama         | Bangkit-ID |       Path       |
|:---------------------:|:----------:|:----------------:|
|  Tiara Nurillatiffah  |  M295DSY0057  | Machine Learning |
|  Illa Rosyidah  |  M295DSY0085  | Machine Learning |
|   Retna Ayundara Wulandari    |  M295DSY0138  |   Machine Learning |
|  M. Aksyal Bambang Suseno  |  A309DSX1124  |  Mobile Development |
|    Robi Firmansyah     |  C109DSX1670  |      Cloud Computing     |
|    Asyrafbilal Fadhila Bhinar Jaya      |  C360DSX2422  |      Cloud Computing    |

## Mobile Development
* Feature
- login and register, for authentification user
- Pick image from gallery, you can select an image from the gallery in preparation for uploading an image to detect food.
- Take an image from camera, you can take image from camera in preparation for uploading an image to detect food
- Calculator, for calculate the calories of the food we are looking for.
- Send image to the cloud to get prediction, after you prepare the image, you can click the process button to send the image and detect the image food and issue nutrition and food recommendations

* Dependencies
- Lifecycle & Livedata
- kotlinx-coroutines
- Retrofit 2
- Material Show Case
- Ok Http 3
- recyclerView
- sharepreference & datastore
- camera2
- viewpager

* Tools
- Android Studio Electric Eel | 2022.1.1 Patch 1
- JRE (Java Runtime Environment) or JDK (Java Development Kit).
  
## Machine Learning
The project is based from Google Colab (due to limited system requirements of our laptop/PC). Using Machine Learning with Tensorflow as framework to Classify the skin disease.

Link to Colab:
https://colab.research.google.com/drive/1pHLtCHAldd8zZMVqZaRTvqLWafxANQo3?usp=sharing   (Fruit Model)
https://colab.research.google.com/drive/1wJSoAUnUOinyY-7diDMJDISrgdDETVrY?usp=sharing   (Carbohydrate Model)
https://colab.research.google.com/drive/1fw35dPgCm6mlleXHRSojqU3Tq02Dg0Tm?usp=sharing   (Daily Product Model)
https://colab.research.google.com/drive/1qKXjk4gj_lFaHb_xHyBKSqquGrVWhQ0b?usp=sharing   (Meat Model)
https://colab.research.google.com/drive/1emfG6Tsbs5FfUAn_3hmcP1Ogy-cIVyI3?usp=sharing   (Vegetable Model)
https://colab.research.google.com/drive/1ITzcSH1e8-Qj5vV4lwPUvQmyPBNCmze7?usp=sharing   (Spice Model)
https://colab.research.google.com/drive/1IEKNf2kcxQNn0fhFGSGBntivv6Mjnc4a?usp=sharing    (Nuts Model)
https://colab.research.google.com/drive/1yQDUpU6aog8Yw-LE7AiKoOLiNAjm88pB?usp=sharing    (Calculator Calories)

1. Load Datasets
Load datasets from modified dataset that we host to Google Drive, here is the link:
[https://drive.google.com/file/d/1-dOK_6g-Bkf8_SKcZKUwiCIj8TSqC7O5/view?usp=sharing](https://drive.google.com/folderview?id=12D2Ox36nD14qBxWmKuA9g7v1y2B6PTE1 )
2. Training
Using transfer learning Xception to make model accuracy better, Using CategoricalCrossentropy as loss, Using Adam as optimizer, Using RMSprop as optimizer, Added more layer too to model.Sequential to make model accuracy more better: Added AveragePooling2D layer, Added Flatten layer, Set the callbacks EarlyStopping to stop training when accuracy doesn't improve
3. Saved the Model to Google Drive
Then, saved the model (*.h5 format) to Google Drive (saved only the best model to Google Drive):
[https://drive.google.com/file/d/1-0ODyEWBJcERmvXXM5Ejk_VoxV7gI6SW/view](https://drive.google.com/drive/folders/1MuLsfXG4PiaCaxHCw_fy9oDzUIHT1XuL?usp=sharing)

## Cloud Computing
### Featured Technologies
* [Node js](https://nodejs.org/en/) It is suitable for those who need real time communication between client and server and The single-threaded event system is very fast when handling many requests at once from clients
* [Framework Express Js](https://expressjs.com/)  there is no need to use the default http module from NodeJS. This framework offers several features such as routing, view rendering, and supports middleware in other words it will save a lot of time in Node.js application development.
* [Google Cloud Platform (GCP)](https://cloud.google.com/gcp/)  Grow from prototype to production without having to think about capacity, reliability, or performance, Easily capture, manage, process, and visualize data with Google Cloud data analytics products.
