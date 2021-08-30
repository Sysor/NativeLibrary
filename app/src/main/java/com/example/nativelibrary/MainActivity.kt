package com.example.nativelibrary

import android.Manifest.permission.READ_PHONE_STATE
import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.nativelibrary.databinding.ActivityMainBinding
import android.widget.Toast
import androidx.core.app.ActivityCompat
import java.util.jar.Manifest
import android.R

import android.content.DialogInterface
import android.os.Build
import android.provider.Settings
import androidx.appcompat.app.AlertDialog
import android.telephony.TelephonyManager
import androidx.annotation.RequiresApi
import java.security.Security


class MainActivity : AppCompatActivity() {






    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)




        binding.button.setOnClickListener {
            binding.sampleText.text = androidID(this)
        }

        binding.button2.setOnClickListener {


        }




        // Example of a call to a native method

    }




    /**
     * A native method that is implemented by the 'nativelibrary' native library,
     * which is packaged with this application.
     */

    external fun androidID(context:Context):String


    companion object {
        // Used to load the 'nativelibrary' library on application startup.
        init {
            System.loadLibrary("nativelibrary")
        }
    }
}