package imgui.internal;

import imgui.binding.ImGuiStruct;

public class ImGuiContext extends ImGuiStruct {
    public ImGuiContext(final long ptr) {
        super(ptr);
        ImGui.init();
    }

    /*JNI
        #include "_common.h"
        #include "_internal.h"
        #define THIS ((ImGuiContext*)STRUCT_PTR)
     */

    public ImGuiWindow getCurrentWindow() {
        return new ImGuiWindow(nGetCurrentWindow());
    }

    public void setCurrentWindow(final ImGuiWindow value) {
        nSetCurrentWindow(value.ptr);
    }

    private native long nGetCurrentWindow(); /*
        return (uintptr_t)THIS->CurrentWindow;
    */

    private native void nSetCurrentWindow(long value); /*
        THIS->CurrentWindow = reinterpret_cast<ImGuiWindow*>(value);
    */

    /*JNI
        #undef THIS
     */
}
