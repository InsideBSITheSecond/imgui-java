#include "jni_testengine.h"

namespace Jni
{
    void RegisterTest(JNIEnv* env, ImGuiTestEngine* e, char* category, char* name, ImGuiTestGuiFunc* guiFunc, ImGuiTestTestFunc* testFunc) {
        printf("Attempting to register test callback: %s - %s\n", category, name); fflush(stdout);
        printf("Callbacks: %p - %p\n", guiFunc, testFunc); fflush(stdout);

        ImGuiTest* t = NULL;
        t = IM_REGISTER_TEST(e, category, name);
        t->GuiFunc = guiFunc;
        //t->TestFunc = testFunc;

        printf("Test %s - %s registered\n", category, name); fflush(stdout);
    }
}
