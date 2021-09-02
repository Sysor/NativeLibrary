package com.example.nativelibrary

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.nativelibrary.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val PERMISSION_REQUEST = 0;

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        requestPermissions(arrayOf(Manifest.permission.READ_PHONE_STATE), PERMISSION_REQUEST);
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == PERMISSION_REQUEST) {
            if (grantResults[0].compareTo(PackageManager.PERMISSION_GRANTED) == 0 ){
                binding.button.setOnClickListener {
                    binding.sampleText.text = androidID(this)
                }

                binding.button2.setOnClickListener {

                    binding.sampleText.text = applicationList(this).toString()

                }
            } else {
                Toast.makeText(this, "не получено разрешение", Toast.LENGTH_SHORT).show()
            }
        }
    }

    /**
     * A native method that is implemented by the 'nativelibrary' native library,
     * which is packaged with this application.
     */

    external fun androidID(context: Context): String

    external fun applicationList(context: Context): String


    companion object {
        // Used to load the 'nativelibrary' library on application startup.
        init {
            System.loadLibrary("nativelibrary")
        }
    }
}

data class appList(
    var name: String,
    var size: Int,
    var isSystem: Boolean
)
