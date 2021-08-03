package com.shawn.imageweightloss

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.lifecycleScope
import com.shawn.imageweightloss.databinding.ActivityMainBinding
import com.shawn.imageweightloss.util.ImageWeightLossByLibJpeg
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.N)
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch(Dispatchers.IO) {

            var inputStream: InputStream? = null
            var bitmap: Bitmap? = null
            var fos: OutputStream? = null

            //压缩前
            var w: Int = 0
            var h: Int = 0
            var size: Long = 0
            var file: File? = null


            //压缩结果 -1 不成功 ; 0 成功
            var res: Int = -1


            try {
                inputStream = resources.assets.open("img")
                file = File(getExternalFilesDir(null), "test")
                fos = FileOutputStream(file)
                val buffer = ByteArray(4096)
                var len: Int = inputStream.read(buffer)
                while (len > 0) {
                    fos.write(buffer, 0, len)
                    len = inputStream.read(buffer)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                fos?.close()
                inputStream?.close()
            }

            bitmap = BitmapFactory.decodeFile("${getExternalFilesDir(null)}/test")

            bitmap?.apply {
                w = width
                h = height

                //开始压缩
                res = ImageWeightLossByLibJpeg.compressBitmap(
                    this,
                    30,
                    "${this@MainActivity.getExternalFilesDir(null)!!.absolutePath}/test.jpeg",
                    true
                )
            }


            size = File("${getExternalFilesDir(null)!!.absolutePath}/test").length()



            withContext(Dispatchers.Main) {
                binding.imageView.setImageBitmap(bitmap)
                binding.textView.text = "压缩前 w = $w ; h = $h ; size = $size"
            }

            Log.d(
                "suihw ",
                "压缩是否成功 = $res ; path : ${getExternalFilesDir(null)!!.absolutePath}/test.jpeg"
            )

            val compressBitmap =
                BitmapFactory.decodeFile("${getExternalFilesDir(null)!!.absolutePath}/test.jpeg")
            val compressFile = File("${getExternalFilesDir(null)!!.absolutePath}/test.jpeg")
            withContext(Dispatchers.Main) {
                binding.textView2.text =
                    "压缩后 w = ${compressBitmap.width} ; h = ${compressBitmap.height} ; size = ${compressFile.length()}"
                binding.imageView2.setImageBitmap(compressBitmap)
            }
        }
    }
}