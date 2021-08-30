#include <jni.h>
#include <string>
#include <android/log.h>
#include <sys/system_properties.h>


extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_nativelibrary_MainActivity_androidID(JNIEnv *env, jobject thiz, jobject context) {


    jclass contextClass = env->FindClass("android/content/Context");
    jmethodID mid = env->GetMethodID(contextClass, "getSystemService",
                                     "(Ljava/lang/String;)Ljava/lang/Object;");
    jfieldID fieldId = env->GetStaticFieldID(contextClass, "TELEPHONY_SERVICE",
                                             "Ljava/lang/String;");
    jstring telephonyManagerType = (jstring) env->GetStaticObjectField(contextClass, fieldId);
    jobject telephony = env->CallObjectMethod(context, mid, telephonyManagerType);
    contextClass = env->FindClass("android/telephony/TelephonyManager");
    mid = env->GetMethodID(contextClass, "getDeviceId", "(I)Ljava/lang/String;");
    telephonyManagerType = (jstring) env->CallObjectMethod(telephony, mid);
    return telephonyManagerType;


}