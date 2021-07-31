#include <jni.h>
#include <string>
#include <jni.h>

extern "C" jstring
Java_com_shawn_imageweightloss_util_ImageWeightLossByLibJpeg_stringFromJNI(JNIEnv *env,
                                                                           jobject jobject) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
