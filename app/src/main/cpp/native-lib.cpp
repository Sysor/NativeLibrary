#include <jni.h>
#include <string>
#include <android/log.h>
#include <sys/system_properties.h>


extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_nativelibrary_MainActivity_androidID(JNIEnv *env, jobject thiz, jobject context) {

    //контекст
    jclass contextClass = env->FindClass("android/content/Context");
    //доступ к сервису
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
extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_nativelibrary_MainActivity_applicationList(JNIEnv *env, jobject thiz,
                                                            jobject context) {

    jclass contextClass = env->FindClass("android/content/Context");
    jmethodID mid = env->GetMethodID(contextClass, "getSystemService",
                                     "(Ljava/lang/String;)Ljava/lang/Object;");
    jfieldID fieldId = env->GetStaticFieldID(contextClass, "TELEPHONY_SERVICE",
                                             "Ljava/lang/String;");
    jstring packageManagerType = (jstring) env->GetStaticObjectField(contextClass, fieldId);
    jobject package = env->CallObjectMethod(context, mid, packageManagerType);
    contextClass = env->FindClass("android/content/pm/PackageManager");


    //PackageManager.hasSystemFeature()
    jmethodID method_id_feature = env->GetMethodID(contextClass, "getInstalledPackages",
                                                   "(I)Ljava/util/List;");


    return reinterpret_cast<jstring>(method_id_feature);

}