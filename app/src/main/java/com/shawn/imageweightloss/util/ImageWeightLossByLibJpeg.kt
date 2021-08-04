package com.shawn.imageweightloss.util

import android.graphics.Bitmap

object ImageWeightLossByLibJpeg {

    /**
     * 压缩图片
     * @param bitmap 图片
     * @param quality 0-100
     * @param outFilPath 输出地址
     * @param optimize TRUE=arithmetic coding, FALSE=Huffman
     * @return -1:失败；0:成功
     * @note 需要异步执行
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