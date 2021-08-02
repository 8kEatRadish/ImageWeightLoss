package com.shawn.imageweightloss

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.lifecycle.lifecycleScope
import com.shawn.imageweightloss.databinding.ActivityMainBinding
import com.shawn.imageweightloss.util.ImageWeightLossByLibJpeg
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.InputStream
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.N)
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch(Dispatchers.IO) {
            val inputStream = resources.assets.open("img")
            val bitmap = BitmapFactory.decodeStream(inputStream)
//            val w = bitmap.width
//            val h = bitmap.height
//            val size = bitmap.byteCount
            withContext(Dispatchers.Main){
//                binding.textView.text = "w = $w ; h = $h ; size = $size"
            }
            val res = ImageWeightLossByLibJpeg.compressBitmap(bitmap,50,"${application.dataDir.absolutePath}/test.jpeg",true)

            Log.d("suihw ","压缩是否成功 = $res")

            withContext(Dispatchers.Main){
                binding.textView2.text = "$res"
            }

        }
    }
}