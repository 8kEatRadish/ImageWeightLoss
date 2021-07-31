package com.shawn.imageweightloss

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.shawn.imageweightloss.databinding.ActivityMainBinding
import com.shawn.imageweightloss.util.ImageWeightLossByLibJpeg

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Example of a call to a native method
        binding.sampleText.text = ImageWeightLossByLibJpeg.stringFromJNI()
    }
}