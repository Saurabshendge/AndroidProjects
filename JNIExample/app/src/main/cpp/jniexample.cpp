#include <jni.h>

// Write C++ code here.
//
// Do not forget to dynamically load the C++ library into your application.
//
// For instance,
//
// In MainActivity.java:
//    static {
//       System.loadLibrary("jniexample");
//    }
//
// Or, in MainActivity.kt:
//    companion object {
//      init {
//         System.loadLibrary("jniexample")
//      }
//    }
extern "C"
JNIEXPORT jint JNICALL
Java_com_example_jniexample_MainActivity_addition(JNIEnv *env, jobject thiz, jint a, jint b) {
    return a+b;
}