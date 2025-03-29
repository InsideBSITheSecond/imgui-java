#include "jni_jvm.h"

#include <stdexcept>

static JavaVM* jvm;

namespace Jni
{
    void InitJvm(JNIEnv* env) {
        env->GetJavaVM(&jvm);
    }

    JNIEnv* GetEnv() {
        JNIEnv* env;
        jint res = jvm->GetEnv(reinterpret_cast<void**>(&env), JNI_VERSION_1_8);
        printf("> env ");
        if (res == JNI_EDETACHED) {
            // Thread is not attached. Try to attach it.
            res = jvm->AttachCurrentThread(reinterpret_cast<void**>(&env), nullptr);
            if (res != JNI_OK) {
                // Handle the error as needed (throw exception, log, etc.)
                // For example, you could throw a std::runtime_error:
                throw std::runtime_error("Failed to attach current thread to JVM");
            }
            printf("(reattached) ");
        } else if (res == JNI_EVERSION) {
            // The requested JNI version is not supported.
            throw std::runtime_error("JNI version not supported");
        }
        // If res is JNI_OK, env is already valid.
        printf("aquired.\n"); fflush(stdout);
        return env;
    }
}
