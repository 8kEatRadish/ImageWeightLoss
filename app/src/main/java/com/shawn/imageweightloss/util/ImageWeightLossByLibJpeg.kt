package com.shawn.imageweightloss.util

object ImageWeightLossByLibJpeg {
    external fun stringFromJNI(): String

    init {
        System.loadLibrary("native-lib")
    }
}