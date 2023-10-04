#include <jni.h>
#include <string>
#include <string.h>
extern "C"
JNIEXPORT jint JNICALL
Java_com_example_myapplication_MainActivity_getTxtLen(JNIEnv *env, jobject thisoj,jstring txt_) {
   const char *txt = env->GetStringUTFChars((jstring) txt, 0);
   int len= strlen(txt);
    return  len;
}