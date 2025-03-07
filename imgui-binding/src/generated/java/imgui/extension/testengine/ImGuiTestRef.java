package imgui.extension.testengine;

import imgui.binding.ImGuiStruct;

public class ImGuiTestRef extends ImGuiStruct {
    public ImGuiTestRef(long ptr) {
        super(ptr);
    }

    /*JNI
        #include "_common.h"
        #include "_imgui_te.h"

        #define THIS ((ImGuiTestRef*)STRUCT_PTR)
     */
}
