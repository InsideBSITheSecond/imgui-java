package imgui.extension.testengine;

import imgui.binding.ImGuiStruct;

public class ImGuiTestRef extends ImGuiStruct {
    public ImGuiTestRef(long ptr) {
        super(ptr);
    }

    /*JNI
        #include "_common.h"
        #include "jni_testengine.h"

        #define THIS ((ImGuiTestRef*)STRUCT_PTR)
     */
}
