#include <jni.h>
#include <string>
#include "Mathlib.cpp"
MathLib mathLib;
extern "C" JNIEXPORT jstring JNICALL
Java_com_example_calculatorlib_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
extern "C"
JNIEXPORT jint JNICALL
Java_com_example_calculatorlib_MainActivity_MathOperations(JNIEnv *env, jobject thiz, jchar op,
                                                           jint a, jint b) {
    // TODO: implement MathOperations()
    switch (op) {
        case '+':
            return mathLib.l_add(a,b);
            break;
        case '-':
            return mathLib.l_sub(a,b);
            break;
        case '*':
            return mathLib.l_mul(a,b);
            break;
        case '/':
            return mathLib.l_div(a,b);
            break;
        default:
            return 0;
            break;

    }

}