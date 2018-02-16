#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_growlist_caseylemon_growlist_1app_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
