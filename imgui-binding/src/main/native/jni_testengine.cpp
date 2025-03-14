#include "jni_testengine.h"

namespace Jni
{
    void RegisterTest(JNIEnv* env, char* category, char* name, ImGuiTestGuiFunc* guiFunc, ImGuiTestTestFunc* testFunc) {
        printf("attempting to register test callback: %s - %s\n", category, name); fflush(stdout);
        printf("callbacks: %p - %p", guiFunc, testFunc);
    }
}
