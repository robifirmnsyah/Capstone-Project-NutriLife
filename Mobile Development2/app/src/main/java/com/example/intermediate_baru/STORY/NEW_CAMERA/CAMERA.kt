package com.example.intermediate_baru.STORY.NEW_CAMERA

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_GET_CONTENT
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.intermediate_baru.R
import com.example.intermediate_baru.STORY.CAMERA.MAIN_CAMERA.MainCamea
import com.example.intermediate_baru.STORY.MainPage
import com.example.intermediate_baru.databinding.ActivityCameraBinding
import com.google.android.material.tabs.TabLayout.TabIndicatorGravity
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class CAMERA : AppCompatActivity() {

    private lateinit var binding: ActivityCameraBinding
    private var imageCapture: ImageCapture? = null
    private var cameraSelector: CameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
    private lateinit var outputdirectory: File
    private val mainCamea: MainCamea = MainCamea()

    companion object{
        const val CAMERA_RESULT = 100
        private val PERMISSION = arrayOf(Manifest.permission.CAMERA)
        private const val CODE_PERMISSION = 10
        private const val DATE_FORMAT = "yy-mm-dd-hh-mm-ss-SS"
        const val TAG = "cameraX"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar?.hide()
        binding = ActivityCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        outputdirectory = getOutputdirectory()

        if(permissiongranted()) {
            StartCamera()
            Toast.makeText(this, "akses diterima", Toast.LENGTH_SHORT).show()
        } else {
            ActivityCompat.requestPermissions(this, PERMISSION, CODE_PERMISSION)
        }

        binding.capture.setOnClickListener {
            takePhoto()
        }

        binding.switchcamera.setOnClickListener {
            cameraSelector = if(cameraSelector.equals(CameraSelector.DEFAULT_BACK_CAMERA)) CameraSelector.DEFAULT_FRONT_CAMERA
            else CameraSelector.DEFAULT_BACK_CAMERA
            StartCamera()
        }

    }

    private fun takePhoto(){
        val imageCapture = imageCapture ?: return
        val Filephoto = MakeFile(application)

        val outputOption = ImageCapture.OutputFileOptions.Builder(Filephoto).build()

        imageCapture.takePicture(outputOption, ContextCompat.getMainExecutor(this),
        object : ImageCapture.OnImageSavedCallback {

            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                val intent = Intent()
                intent.putExtra("picture", Filephoto.absolutePath)
                intent.putExtra("isBackCamera", cameraSelector == CameraSelector.DEFAULT_BACK_CAMERA)
                rotateFile(Filephoto, cameraSelector == CameraSelector.DEFAULT_BACK_CAMERA)
                setResult(MainCamea.CAMERA_RESULT, intent)
                finish()
            }

            override fun onError(exception: ImageCaptureException) {
                Toast.makeText(this@CAMERA, "Gagal mengambil gambar", Toast.LENGTH_SHORT).show()
            }

        }
        )

    }

    private fun getOutputdirectory():File {
        val Dir = externalMediaDirs.firstOrNull().let {
            File(it, resources.getString(R.string.app_name)).apply {
                mkdirs()
            }
        }
        return if (Dir != null && Dir.exists())
            Dir else filesDir
    }

    private fun StartCamera(){
        val cameraProvide = ProcessCameraProvider.getInstance(this)

        cameraProvide.addListener({
            val cameraProvider: ProcessCameraProvider = cameraProvide.get()
            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(binding.campreview.surfaceProvider)
                }
            imageCapture = ImageCapture.Builder()
                .build()

            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageCapture)
            } catch (exc: Exception){
                Toast.makeText(this, "kamera tidak muncul", Toast.LENGTH_SHORT).show()
            }

        }, ContextCompat.getMainExecutor(this))
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == CODE_PERMISSION){
            if (permissiongranted()){
                StartCamera()
            }else {
                Toast.makeText(this, "anda tidak punya akses", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun permissiongranted() = PERMISSION.all {
        ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
    }

}