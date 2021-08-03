package com.shawn.imageweightloss.util

import android.graphics.Bitmap

object ImageWeightLossByLibJpeg {
    external fun compressBitmap(
        bitmap: Bitmap, quality: Int,
        outFilPath: String?,
        optimize: Boolean
    ): Int

    init {
        System.loadLibrary("native-lib")
    }
}