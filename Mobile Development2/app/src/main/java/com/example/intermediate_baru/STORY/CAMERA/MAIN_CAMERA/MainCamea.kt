package com.example.intermediate_baru.STORY.CAMERA.MAIN_CAMERA

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import com.example.intermediate_baru.API.APIClient
import com.example.intermediate_baru.API.APIService
import com.example.intermediate_baru.Model.PredicResponse
import com.example.intermediate_baru.R
import com.example.intermediate_baru.STORY.NEW_CAMERA.CAMERA
import com.example.intermediate_baru.STORY.NEW_CAMERA.uriToFile
import com.example.intermediate_baru.databinding.ActivityMainCameaBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import java.io.File

class MainCamea : AppCompatActivity() {

    private lateinit var binding: ActivityMainCameaBinding
    private var getFile: File? = null
    var itemselected: String? = null

    companion object {
        const val CAMERA_RESULT = 100
        const val REQUEST_CODE = 3
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainCameaBinding.inflate(layoutInflater)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.previewImageView.setOnClickListener {
            startGallery()
        }

        binding.camera.setOnClickListener {
            launcercamera.launch(Intent(this, CAMERA::class.java))
        }

        binding.Gallery.setOnClickListener {
            startGallery()
        }

        val item = listOf("sayuran", "karbohidrat", "daging", "rempah", "buah-buahan", "kacang-kacangan", "daily product")
        val autofill : AutoCompleteTextView = findViewById(R.id.TextAuto)
        val adapter = ArrayAdapter(this, R.layout.list_item, item)
        autofill.setAdapter(adapter)

       binding.TextAuto.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, i, l ->

           itemselected = adapterView.getItemAtPosition(i) as String
           Toast.makeText(this, "item: $itemselected", Toast.LENGTH_SHORT).show()
       }

        binding.predictButton.setOnClickListener {
            PredicImage()
//            val botdialog = BottomSheetDialog(this)
//            val view = layoutInflater.inflate(R.layout.bottom_sheet, null)

//            if(itemselected == "karbohidrat"){
//                PredicImage()
//                botdialog.setCancelable(true)
//                botdialog.setContentView(view)
//                botdialog.show()
//
//            }
//            if (itemselected == "sayuran"){
//                PredicImage()
//            }
//            if(itemselected == "daging"){
//                PredicImage()
//            }
//            if(itemselected == "rempah"){
//                PredicImage()
//            }
//            if(itemselected == "buah-buahan"){
//                PredicImage()
//            }
//            if(itemselected == "daily product"){
//                PredicImage()
//            }
//            if(itemselected == "kacang-kacangan"){
//                PredicImage()
//            }

        }
    }



    private val launcercamera = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == CAMERA_RESULT){
            val data = it.data
            val myFile = data?.getStringExtra("picture")?.let { File(it) }
            val isBackCamera = data?.getBooleanExtra("isBackCamera", true) ?: true

            myFile?.let {
                val bitmap = BitmapFactory.decodeFile(it.path)
                getFile = it
                binding.previewImageView.setImageBitmap(bitmap)
            }
        }
    }

    private val launcherIntentGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val selectedImg = result.data?.data as Uri
            selectedImg.let { uri ->
                val myFile = uriToFile(uri, this@MainCamea)
                getFile = myFile
                binding.previewImageView.setImageURI(uri)
            }
        }
    }

    internal fun startGallery() {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "image/*"
        val chooser = Intent.createChooser(intent, "Choose a Picture")
        launcherIntentGallery.launch(chooser)
    }

    private fun PredicImage() {
        if (getFile !=null) {
            val file = getFile as File

            val requestImage = file.asRequestBody("image/jpeg".toMediaType())
            val imageMultiPart: MultipartBody.Part =
                MultipartBody.Part.createFormData("file", file.name, requestImage)
            val uploadreq = APIClient.getApiService().pred_karbo(imageMultiPart)
            uploadreq.enqueue(object : retrofit2.Callback<PredicResponse> {
                override fun onResponse(call: Call<PredicResponse>, response: Response<PredicResponse>) {
                    if(response != null) {
                        val responseBody = response.body()
                        if (responseBody != null) {

                            val nutrientInfoList = responseBody.nutrientInfo
                            val recommendedFoodsAndInfoList = responseBody.recommendedFoodsAndInfo
                            val prediction = responseBody.prediction
                            val recommendedRecipesList = responseBody.recommendedRecipes

                            val stringBuilder = StringBuilder()

                            for (nutrientInfoItem in nutrientInfoList) {
                                val protein = nutrientInfoItem.proteinG.toString()
                                val karbohidrat = nutrientInfoItem.karbohidratG.toString()
                                val lemak = nutrientInfoItem.lemakG.toString()
                                val kalori = nutrientInfoItem.kalori.toString()
                                val ukuran = nutrientInfoItem.ukuran
                                val keterangan = nutrientInfoItem.keterangan

                                val nutrientInfoText = "Protein: $protein g, Karbohidrat: $karbohidrat g, Lemak: $lemak g, Kalori: $kalori, Ukuran: $ukuran, Keterangan: $keterangan\n"
                                stringBuilder.append(nutrientInfoText)

                                runOnUiThread {
                                    binding.proteinTextView.text = stringBuilder.toString()
                                    binding.proteinTextView.visibility = View.VISIBLE
                                }
                            }

                            val nutrientTextView = findViewById<TextView>(R.id.proteinTextView)
                            nutrientTextView.text = stringBuilder.toString()

                            Log.e("api masuk", "$nutrientInfoList")

                        } else {
                            Log.e("api tidak masuk", "error")
                        }
                    }
                }

                override fun onFailure(call: Call<PredicResponse>, t: Throwable) {
                }

            })
        }

    }

}