package imgui.internal;

import imgui.binding.ImGuiStruct;
import imgui.binding.annotation.BindingField;
import imgui.binding.annotation.BindingSource;

@BindingSource
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

    @BindingField
    public ImGuiWindow CurrentWindow;

    /*JNI
        #undef THIS
     */
}
