package imgui.extension.testengine;

import imgui.binding.ImGuiStruct;
import imgui.binding.annotation.BindingField;
import imgui.binding.annotation.BindingMethod;
import imgui.binding.annotation.BindingSource;
import imgui.binding.annotation.ReturnValue;
import imgui.binding.annotation.ArgValue;
import imgui.extension.testengine.callback.TestEngineGuiFun;
import imgui.extension.testengine.callback.TestEngineTestFun;
import imgui.internal.ImGuiContext;

@BindingSource
public class TestEngine extends ImGuiStruct {
    public TestEngine(long ptr) { super(ptr); }

    // JNI

    /*JNI
        #include "_common.h"
        #include "jni_testengine.h"

        #define THIS ((ImGuiTestEngine*)STRUCT_PTR)

        static auto guiCallback(JNIEnv* env, jobject fn) {
            static jobject cb = NULL;
            if (cb != NULL) { env->DeleteGlobalRef(cb); }
            cb = env->NewGlobalRef(fn);

            printf("Wrapping gui callback in C++ lambda\n"); fflush(stdout);

            return [](ImGuiTestContext* ctx) {
                if (cb != NULL) {
                    Jni::CallTestEngineGuiFun(Jni::GetEnv(), cb, ctx);
                }
            };
        }

        static auto testCallback(JNIEnv* env, jobject fn) {
            static jobject cb = NULL;
            if (cb != NULL) { env->DeleteGlobalRef(cb); }
            cb = env->NewGlobalRef(fn);

            printf("Wrapping test callback in C++ lambda\n"); fflush(stdout);

            return [](ImGuiTestContext* ctx) {
                if (cb != NULL) {
                    Jni::CallTestEngineTestFun(Jni::GetEnv(), cb, ctx);
                }
            };
        }
    */

    // Test Registering
    public static void RegisterTest(String category, TestEngine engine, String name, TestEngineGuiFun vGuiCb, TestEngineTestFun vTestCb) { ImGuiTestEngine_RegisterTest(category, engine, name, vGuiCb, vTestCb); }
    private static native void ImGuiTestEngine_RegisterTest(String category, TestEngine engine, String name, TestEngineGuiFun vGuiCb, TestEngineTestFun vTestCb); /*
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

    @BindingField(accessors = BindingField.Accessor.GETTER)
    @ReturnValue(isStatic = true, callPrefix = "&")
    public TestEngineIO IO;

    @BindingField(accessors = BindingField.Accessor.GETTER)
    public ImGuiContext UiContextTarget;

    // Macros

    // Bind for the IM_CHECK_EQ macro.
    public static void checkEq(int lhs, int rhs) {
        n_checkEq(lhs, rhs);
    }
    private static native void n_checkEq(int lhs, int rhs); /*
        IM_CHECK_EQ(lhs, rhs);
    */
}
