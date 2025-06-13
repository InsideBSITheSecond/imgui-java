package imgui.extension.testengine;

import imgui.binding.ImGuiStruct;
import imgui.binding.ImGuiStructDestroyable;
import imgui.binding.annotation.BindingField;
import imgui.binding.annotation.BindingMethod;
import imgui.binding.annotation.BindingSource;
import imgui.binding.annotation.ReturnValue;
import imgui.extension.testengine.callback.TestEngineGuiFun;
import imgui.extension.testengine.callback.TestEngineTestFun;
import imgui.internal.ImGuiContext;

@BindingSource
public class ImGuiTestEngine extends ImGuiStructDestroyable {
    private static final ImGuiTestContext TMP_CTX = new ImGuiTestContext(0);

    public ImGuiTestEngine() { super(); }
    public ImGuiTestEngine(long ptr) { super(ptr); }

    @Override
    protected long create() {
        return nCreate();
    }

    /*JNI
        #include "_common.h"
        #include "jni_testengine.h"

        #define THIS ((ImGuiTestEngine*)STRUCT_PTR)

        jmethodID jTestEngineGuiFunMID;
        jmethodID jTestEngineTestFunMID;

        struct TestEngineCallbackUserData {
            JNIEnv* env;
            jobject* guiHandler;
            jobject* testHandler;
        };

        void TestEngineGuiCallbackStub(TestEngineCallbackUserData* data) {

        }
     */

    private native long nCreate(); /*
        return (uintptr_t)(ImGuiTestEngine_CreateContext());
    */

    public native void registerTest(String category, String name); /*
        TestEngineCallbackUserData userData;
        userData.env = env;
        //userData.guiHandler = &guiFunc;
        //userData.testHandler = &testFunc;

        ImGuiTest* t = nullptr;

        t = IM_REGISTER_TEST(THIS, "demo_tests", "test1");
        //t->GuiFunc = NULL;
        //t->TestFunc = NULL;

//        t->GuiFunc = [](ImGuiTestContext* ctx) // Optionally provide a GUI function in addition to your application GUI
//        {
//            ImGui::Begin("Test Window", NULL, ImGuiWindowFlags_NoSavedSettings);
//            ImGui::Text("Hello, automation world");
//            ImGui::Button("Click Me");
//            if (ImGui::TreeNode("Node"))
//            {
//                static bool b = false;
//                ImGui::Checkbox("Checkbox", &b);
//                ImGui::TreePop();
//            }
//            ImGui::End();
//        };
//        t->TestFunc = [](ImGuiTestContext* ctx) // Generally provide a Test function which will drive the test.
//        {
//            ctx->SetRef("Test Window");
//            ctx->ItemClick("Click Me");
//            ctx->ItemOpen("Node"); // Optional as ItemCheck("Node/Checkbox") can do it automatically
//            ctx->ItemCheck("Node/Checkbox");
//            ctx->ItemUncheck("Node/Checkbox");
//        };
    */

//    public static ImGuiTestEngine CreateContext() {
//        return new ImGuiTestEngine(ImGuiTestEngine_CreateContext());
//    }
//    private static native long ImGuiTestEngine_CreateContext(); /*
//        return (uintptr_t)ImGuiTestEngine_CreateContext();
//    */

//    public static ImGuiTestEngineIO GetIO(ImGuiTestEngine engine) {
//        return new ImGuiTestEngineIO(ImGuiTestEngine_GetIO(engine.ptr));
//    }
//    private static native long ImGuiTestEngine_GetIO(long engine); /*
//        return (uintptr_t)&ImGuiTestEngine_GetIO(reinterpret_cast<ImGuiTestEngine*>(engine));
//    */

    public native void Start(long context); /*
        ImGuiTestEngine_Start(THIS, reinterpret_cast<ImGuiContext*>(context));
    */

    public static void InstallDefaultCrashHandler() {
        nInstallDefaultCrashHandler();
    }
    private static native void nInstallDefaultCrashHandler(); /*
        ImGuiTestEngine_InstallDefaultCrashHandler();
    */

    public static void TryAbortEngine(ImGuiTestEngine engine) {
        ImGuiTestEngine_TryAbortEngine(engine.ptr);
    }
    private static native boolean ImGuiTestEngine_TryAbortEngine(long engine); /*
        return ImGuiTestEngine_TryAbortEngine(reinterpret_cast<ImGuiTestEngine*>(engine));
    */

    public static void ShowTestEngineWindows(ImGuiTestEngine engine) {
        ImGuiTestEngine_ShowTestEngineWindows(engine.ptr);
    }
    private static native void ImGuiTestEngine_ShowTestEngineWindows(long engine); /*
        ImGuiTestEngine_ShowTestEngineWindows(reinterpret_cast<ImGuiTestEngine*>(engine), NULL);
    */

    public static void PostSwap(ImGuiTestEngine engine) {
        ImGuiTestEngine_PostSwap(engine.ptr);
    }
    private static native void ImGuiTestEngine_PostSwap(long engine); /*
        ImGuiTestEngine_PostSwap(reinterpret_cast<ImGuiTestEngine*>(engine));
    */


    public static void Stop(ImGuiTestEngine engine) {
        ImGuiTestEngine_Stop(engine.ptr);
    }
    private static native void ImGuiTestEngine_Stop(long engine); /*
        ImGuiTestEngine_Stop(reinterpret_cast<ImGuiTestEngine*>(engine));
    */

    public static void DestroyContext(ImGuiTestEngine engine) {
        ImGuiTestEngine_DestroyContext(engine.ptr);
    }
    private static native void ImGuiTestEngine_DestroyContext(long engine); /*
        ImGuiTestEngine_DestroyContext(reinterpret_cast<ImGuiTestEngine*>(engine));
    */

    // Fields

    @BindingField(accessors = BindingField.Accessor.GETTER)
    @ReturnValue(isStatic = true, callPrefix = "&")
    public ImGuiTestEngineIO IO;

    @BindingField(accessors = BindingField.Accessor.GETTER)
    public ImGuiContext UiContextTarget;

    @BindingField(accessors = BindingField.Accessor.GETTER)
    public ImGuiTestContext TestContext;

    // Macros

    // Bind for the IM_CHECK_EQ macro.
    public static void checkEq(int lhs, int rhs) {
        n_checkEq(lhs, rhs);
    }
    private static native void n_checkEq(int lhs, int rhs); /*
        IM_CHECK_EQ(lhs, rhs);
    */

    /*JNI
        #undef THIS
     */
}
