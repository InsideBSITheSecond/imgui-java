package imgui.extension.testengine;

import imgui.binding.ImGuiStruct;
import imgui.extension.testengine.callback.TestCallback;
import imgui.internal.ImGuiContext;

public class TestEngine extends ImGuiStruct {
    public TestEngine(long ptr) { super(ptr); }

    // JNI

    /*JNI
        #include "_common.h"
        #include "_imgui_te.h"
        #include <string>
        #include <jni.h>
        #define THIS ((ImGuiTestEngine*)STRUCT_PTR)

        // Global variable to store the JavaVM pointer.
        static JavaVM* gJavaVM = nullptr;

        // Called when the native library is loaded.
        // Save the JavaVM pointer so it can be used later.
        JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM* vm, void* reserved) {
            gJavaVM = vm;
            return JNI_VERSION_1_6;
        }

        // Helper function to retrieve the stored JavaVM pointer.
        JavaVM* getJavaVM() {
            return gJavaVM;
        }

        struct CallbackContainer {
            std::string     Category;
            std::string     Name;
            jobject           GuiFunc;
            jobject           TestFunc;
            void*           UserData;
        };

        // Shared helper: a static, non-capturing function that does the common work.
        static void callJavaCallback(jobject callback, ImGuiTestContext* ctx) {
            if (!callback)
                return;

            // Retrieve the JavaVM pointer.
            JavaVM* jvm = getJavaVM();
            JNIEnv* env = nullptr;
            bool attached = false;

            // Attach the current thread if necessary.
            if (jvm->GetEnv(reinterpret_cast<void**>(&env), JNI_VERSION_1_6) != JNI_OK) {
                jvm->AttachCurrentThread(reinterpret_cast<void**>(&env), nullptr);
                attached = true;
            }

            // Get the callback object's class.
            jclass callbackClass = env->GetObjectClass(callback);
            // Look up the "run" method with signature (J)V.
            jmethodID runMethod = env->GetMethodID(callbackClass, "run", "(J)V");
            if (runMethod) {
                // Call the Java callback, passing the test context pointer as a long.
                env->CallVoidMethod(callback, runMethod, reinterpret_cast<jlong>(ctx));
            }
            if (env->ExceptionCheck()) {
                env->ExceptionDescribe();
                env->ExceptionClear();
            }
            // Detach the thread if we attached it here.
            if (attached) {
                jvm->DetachCurrentThread();
            }
        }

        // Function for GUI callbacks.
        static void callJavaGuiCallback(ImGuiTestContext* ctx) {
            // Retrieve the Java callback from the test's user data.
            CallbackContainer* container = reinterpret_cast<CallbackContainer*>(ctx->Test->UserData);
            if (!container || !container->GuiFunc)
                return;

            callJavaCallback(container->GuiFunc, ctx);
        }

        // Function for Test callbacks.
        static void callJavaTestCallback(ImGuiTestContext* ctx) {
            // Retrieve the Java callback from the test's user data.
            CallbackContainer* container = reinterpret_cast<CallbackContainer*>(ctx->Test->UserData);
            if (!container || !container->TestFunc)
                return;

            callJavaCallback(container->TestFunc, ctx);
        }
    */

    // Test Registering

    /**
     * Registers a test by passing the callback to native C++ code.
     */
    public static void registerTest(TestEngine engine, String category, String testName, TestCallback guiCallback, TestCallback testCallback) {
        registerTestNative(engine.ptr, category, testName, guiCallback, testCallback);
    }

    // Declare the native method
    private static native void registerTestNative(long enginePtr, String categoryChars, String testNameChars, TestCallback guiCallback, TestCallback testCallback); /*
        std::string category(categoryChars);
        std::string testName(testNameChars);

        // Convert engine pointer to the proper native type.
        ImGuiTestEngine* engine = reinterpret_cast<ImGuiTestEngine*>(enginePtr);

        // Create a global reference for the Java callback so it remains valid beyond this function call.
        CallbackContainer* container = new CallbackContainer();
        container->GuiFunc = env->NewGlobalRef(guiCallback);
        container->TestFunc = env->NewGlobalRef(testCallback);
        container->Category = category;
        container->Name = testName;

        // Register the test using your native macro.
        ImGuiTest* t = IM_REGISTER_TEST(engine, container->Category.c_str(), container->Name.c_str());
        printf("Got strings: %s %s\n", container->Category, container->Name);
        fflush(stdout);

        // Store the global callback pointer in the test's user variables.
        t->UserData = container;

        // Assign the GuiFunc to our non-capturing static function.
        t->GuiFunc = callJavaGuiCallback;
        t->TestFunc = callJavaTestCallback;

        // Optionally, you could store globalCallback elsewhere for cleanup when the test is unregistered.
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
