package com.shawn.imageweightloss.util

import android.graphics.Bitmap

object ImageWeightLossByLibJpeg {

    /**
     * 压缩图片
     * @param bitmap 图片
     * @param quality 0-100
     * @param outFilPath 输出地址
     * @param optimize TRUE=arithmetic coding, FALSE=Huffman
     */
    external fun compressBitmap(
        bitmap: Bitmap, quality: Int,
        outFilPath: String,
        optimize: Boolean
    ): Int

    init {
        System.loadLibrary("native-lib")
    }
}