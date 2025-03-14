package imgui.extension.testengine;

import imgui.binding.ImGuiStruct;
import imgui.extension.testengine.callback.TestEngineGuiFun;
import imgui.extension.testengine.callback.TestEngineTestFun;
import imgui.internal.ImGuiContext;

public class TestEngine extends ImGuiStruct {
    public TestEngine(long ptr) { super(ptr); }

    // JNI

    /*JNI
        #include "_common.h"
        #include "jni_testengine.h"

        #define THIS ((ImGuiTestEngine*)STRUCT_PTR)

        static auto guiCallback(JNIEnv* env, jobject fn) {
            static jobject cbg = NULL;
            if (cbg != NULL) { env->DeleteGlobalRef(cbg); }
            cbg = env->NewGlobalRef(fn);

            printf("Wrapping gui callback in C++ lambda\n"); fflush(stdout);

            return [](ImGuiTestContext* ctx) {
                if (cbg != NULL) {
                    printf("Attempting to callback java guiFun from C++ lambda\n"); fflush(stdout);
                    Jni::CallTestEngineGuiFun(Jni::GetEnv(), cbg, ctx);
                    printf("GuiFun callback succeeded\n"); fflush(stdout);
                }
            };
        }

        static auto testCallback(JNIEnv* env, jobject fn) {
            static jobject cbt = NULL;
            if (cbt != NULL) { env->DeleteGlobalRef(cbt); }
            cbt = env->NewGlobalRef(fn);

            printf("Wrapping test callback in C++ lambda\n"); fflush(stdout);

            return [](ImGuiTestContext* ctx) {
                if (cbt != NULL) {
                    printf("Attempting to callback java testFun from C++ lambda\n"); fflush(stdout);
                    Jni::CallTestEngineTestFun(Jni::GetEnv(), cbt, ctx);
                    printf("TestFun callback succeeded\n"); fflush(stdout);
                }
            };
        }
    */

    // Test Registering
    public static void RegisterTest(TestEngine engine, String category, String name, TestEngineGuiFun vGuiCb, TestEngineTestFun vTestCb) { ImGuiTestEngine_RegisterTest(engine.ptr, category, name, vGuiCb, vTestCb); }
    private static native void ImGuiTestEngine_RegisterTest(long engine, String category, String name, TestEngineGuiFun vGuiCb, TestEngineTestFun vTestCb); /*
        Jni::RegisterTest(env, reinterpret_cast<ImGuiTestEngine*>(engine), category, name, guiCallback(env, vGuiCb), testCallback(env, vTestCb));
    */


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
        printf("Engine pointer: %p\n", (void*)engine);
        fflush(stdout);
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

    // Macros

    // Bind for the IM_CHECK_EQ macro.
    public static void checkEq(int lhs, int rhs) {
        n_checkEq(lhs, rhs);
    }
    private static native void n_checkEq(int lhs, int rhs); /*
        IM_CHECK_EQ(lhs, rhs);
    */
}
