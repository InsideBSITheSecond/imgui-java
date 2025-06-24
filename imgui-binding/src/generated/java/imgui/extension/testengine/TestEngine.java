package imgui.extension.testengine;

import imgui.ImGuiPlatformMonitor;
import imgui.ImGuiViewport;
import imgui.ImVec2;
import imgui.binding.ImGuiStruct;



import imgui.extension.testengine.callback.TestEngineGuiFun;
import imgui.extension.testengine.callback.TestEngineTestFun;
import imgui.internal.ImGuiContext;


public class TestEngine extends ImGuiStruct {
    private static final TestContext TMP_CTX = new TestContext(0);

    public TestEngine(long ptr) { super(ptr); }

    // JNI

    /*JNI
        #include "_common.h"
        #include "jni_testengine.h"

        #define THIS ((ImGuiTestEngine*)STRUCT_PTR)

        struct CallbackPair {
            jobject guiCallback;
            jobject testCallback;
        };

        static std::mutex           g_cbMutex;
        static std::unordered_map<ImGuiTest*, CallbackPair*>  g_callbacks;

        void testStubGuiCallback(ImGuiTestContext* tc);
        void testStubTestCallback(ImGuiTestContext* tc);
    */

    /*JNI
        void testStubGuiCallback(ImGuiTestContext* tc) {
            ImGuiTest* t = tc->Test;
            CallbackPair* cp = nullptr;
            {
                std::lock_guard<std::mutex> lock(g_cbMutex);
                auto it = g_callbacks.find(t);
                if (it != g_callbacks.end())
                    cp = it->second;
            }
            if (cp && cp->guiCallback) {
                JNIEnv* env = Jni::GetEnv();
                jclass ctxCls = env->FindClass("imgui/extension/testengine/TestContext");
                jmethodID ctor = env->GetMethodID(ctxCls, "<init>", "(J)V");
                if (!ctor)
                    return;
                jobject jCtx = env->NewObject(ctxCls, ctor, (jlong)(uintptr_t)tc);
                Jni::CallTestEngineGuiFun(env, cp->guiCallback, jCtx);
                env->DeleteLocalRef(jCtx);
            }
        }

        void testStubTestCallback(ImGuiTestContext* tc) {
            ImGuiTest* t = tc->Test;
            CallbackPair* cp = nullptr;
            {
                std::lock_guard<std::mutex> lock(g_cbMutex);
                auto it = g_callbacks.find(t);
                if (it != g_callbacks.end())
                    cp = it->second;
            }
            if (cp && cp->testCallback) {
                JNIEnv* env = Jni::GetEnv();
                jclass ctxCls = env->FindClass("imgui/extension/testengine/TestContext");
                jmethodID ctor = env->GetMethodID(ctxCls, "<init>", "(J)V");
                if (!ctor)
                    return;
                jobject jCtx = env->NewObject(ctxCls, ctor, (jlong)(uintptr_t)tc);
                Jni::CallTestEngineTestFun(env, cp->testCallback, jCtx);
                env->DeleteLocalRef(jCtx);
            }
        }

     */

    public native void registerTest(String category, String name, TestEngineGuiFun guiFunc, TestEngineTestFun testFunc); /*
        printf("Attempting to register test callback: %s - %s\n", category, name); fflush(stdout);
        printf("Callbacks: %d - %d\n",
                env->CallIntMethod(guiFunc, env->GetMethodID(env->GetObjectClass(guiFunc), "hashCode", "()I")),
                env->CallIntMethod(testFunc, env->GetMethodID(env->GetObjectClass(testFunc), "hashCode", "()I")));
        fflush(stdout);

        // Copy the strings to persistent memory. (this isn't yet freed anywhere)
        char* persistentCategory = strdup(category);
        char* persistentName = strdup(name);

        ImGuiTest* t = IM_REGISTER_TEST(THIS, persistentCategory, persistentName);
        auto* cp = new CallbackPair{
            env->NewGlobalRef(guiFunc),
            env->NewGlobalRef(testFunc)
        };

        {
            std::lock_guard<std::mutex> lock(g_cbMutex);
            // if someone re-registered the same test, clean up old refs
            if (auto it = g_callbacks.find(t); it != g_callbacks.end()) {
                env->DeleteGlobalRef(it->second->guiCallback);
                env->DeleteGlobalRef(it->second->testCallback);
                delete it->second;
            }
            g_callbacks[t] = cp;
        }

        // hook up our stubs
        t->GuiFunc  = testStubGuiCallback;
        t->TestFunc = testStubTestCallback;

        printf("Test %s - %s registered (multiple ok)\n", persistentCategory, persistentName);
        fflush(stdout);
    */


    // Test Registering
//    public static void RegisterTest(TestEngine engine, String category, String name, TestEngineGuiFun vGuiCb, TestEngineTestFun vTestCb) { ImGuiTestEngine_RegisterTest(engine.ptr, category, name, vGuiCb, vTestCb); }
//    private static native void ImGuiTestEngine_RegisterTest(long engine, String category, String name, TestEngineGuiFun vGuiCb, TestEngineTestFun vTestCb); /*
//        Jni::RegisterTest(env, reinterpret_cast<ImGuiTestEngine*>(engine), category, name, guiCallback(env, vGuiCb), testCallback(env, vTestCb));
//    */


    // Global Functions


    public static TestEngine CreateContext() {
        return new TestEngine(ImGuiTestEngine_CreateContext());
    }
    private static native long ImGuiTestEngine_CreateContext(); /*
        return (uintptr_t)ImGuiTestEngine_CreateContext();
    */

    public static TestEngineIO GetIO(TestEngine engine) {
        return new TestEngineIO(ImGuiTestEngine_GetIO(engine.ptr));
    }
    private static native long ImGuiTestEngine_GetIO(long engine); /*
        return (uintptr_t)&ImGuiTestEngine_GetIO(reinterpret_cast<ImGuiTestEngine*>(engine));
    */

    public static void Start(TestEngine engine, ImGuiContext context) {
        System.out.println(Long.toHexString(engine.ptr));
        ImGuiTestEngine_Start(engine.ptr, context.ptr);
    }
    private static native void ImGuiTestEngine_Start(long engine, long context); /*
        printf("Engine pointer: %p\n", (void*)engine); fflush(stdout);
        ImGuiTestEngine_Start(reinterpret_cast<ImGuiTestEngine*>(engine), reinterpret_cast<ImGuiContext*>(context));
    */

    public static void InstallDefaultCrashHandler() {
        ImGuiTestEngine_InstallDefaultCrashHandler();
    }
    private static native void ImGuiTestEngine_InstallDefaultCrashHandler(); /*
        ImGuiTestEngine_InstallDefaultCrashHandler();
    */

    public static void TryAbortEngine(TestEngine engine) {
        ImGuiTestEngine_TryAbortEngine(engine.ptr);
    }
    private static native boolean ImGuiTestEngine_TryAbortEngine(long engine); /*
        return ImGuiTestEngine_TryAbortEngine(reinterpret_cast<ImGuiTestEngine*>(engine));
    */

    public static void ShowTestEngineWindows(TestEngine engine) {
        ImGuiTestEngine_ShowTestEngineWindows(engine.ptr);
    }
    private static native void ImGuiTestEngine_ShowTestEngineWindows(long engine); /*
        ImGuiTestEngine_ShowTestEngineWindows(reinterpret_cast<ImGuiTestEngine*>(engine), NULL);
    */

    public static void PostSwap(TestEngine engine) {
        ImGuiTestEngine_PostSwap(engine.ptr);
    }
    private static native void ImGuiTestEngine_PostSwap(long engine); /*
        ImGuiTestEngine_PostSwap(reinterpret_cast<ImGuiTestEngine*>(engine));
    */


    public static void Stop(TestEngine engine) {
        ImGuiTestEngine_Stop(engine.ptr);
    }
    private static native void ImGuiTestEngine_Stop(long engine); /*
        ImGuiTestEngine_Stop(reinterpret_cast<ImGuiTestEngine*>(engine));
    */

    public static void DestroyContext(TestEngine engine) {
        ImGuiTestEngine_DestroyContext(engine.ptr);
    }
    private static native void ImGuiTestEngine_DestroyContext(long engine); /*
        ImGuiTestEngine_DestroyContext(reinterpret_cast<ImGuiTestEngine*>(engine));
    */

    // Fields

    private static final TestEngineIO _GETIO_1 = new TestEngineIO(0);

    public TestEngineIO getIO() {
        _GETIO_1.ptr = nGetIO();
        return _GETIO_1;
    }

    private native long nGetIO(); /*
        return (uintptr_t)&THIS->IO;
    */

    public ImGuiContext getUiContextTarget() {
        return new ImGuiContext(nGetUiContextTarget());
    }

    private native long nGetUiContextTarget(); /*
        return (uintptr_t)THIS->UiContextTarget;
    */

    public TestContext getTestContext() {
        return new TestContext(nGetTestContext());
    }

    private native long nGetTestContext(); /*
        return (uintptr_t)THIS->TestContext;
    */

    // Macros

    // Bind for the IM_CHECK_EQ macro.
    public static void checkEq(int lhs, int rhs) {
        n_checkEq(lhs, rhs);
    }
    private static native void n_checkEq(int lhs, int rhs); /*
        IM_CHECK_EQ(lhs, rhs);
    */
}
