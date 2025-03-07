package imgui.extension.testengine;

import imgui.binding.ImGuiStruct;
import imgui.extension.testengine.flag.TestVerboseLevel;

public class TestEngineIO extends ImGuiStruct {
    public TestEngineIO(long ptr) { super(ptr); }

    /*JNI
        #include "_common.h"
        #include "_imgui_te.h"
        #define THIS ((ImGuiTestEngineIO*)STRUCT_PTR)
     */

    public void setConfigVerboseLevel(TestVerboseLevel level) {
        nSetConfigVerboseLevel(level.value);
    }
    private native void nSetConfigVerboseLevel(int configVerboseLevel); /*
        THIS->ConfigVerboseLevel = static_cast<ImGuiTestVerboseLevel>(configVerboseLevel);
    */

    public TestVerboseLevel getConfigVerboseLevel() {
        return TestVerboseLevel.fromInt(nGetConfigVerboseLevel());
    }
    private native int nGetConfigVerboseLevel(); /*
        return THIS->ConfigVerboseLevel;
    */

    public void setConfigVerboseLevelOnError(TestVerboseLevel level) {
        nSetConfigVerboseLevelOnError(level.value);
    }
    private native void nSetConfigVerboseLevelOnError(int configVerboseLevel); /*
        THIS->ConfigVerboseLevelOnError = static_cast<ImGuiTestVerboseLevel>(configVerboseLevel);
    */

    public TestVerboseLevel getConfigVerboseLevelOnError() {
        return TestVerboseLevel.fromInt(nGetConfigVerboseLevelOnError());
    }
    private native int nGetConfigVerboseLevelOnError(); /*
        return THIS->ConfigVerboseLevelOnError;
    */
}
