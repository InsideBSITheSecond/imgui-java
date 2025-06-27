package imgui.internal;

import imgui.binding.ImGuiStruct;

public class ImGuiWindow extends ImGuiStruct {
    public ImGuiWindow(final long ptr) {
        super(ptr);
        ImGui.init();
    }

    /*JNI
        #include "_common.h"
        #include "_internal.h"
        #define THIS ((ImGuiWindow*)STRUCT_PTR)
     */

    public int getID(final String id) {
        return nGetID(id);
    }

    private native int nGetID(String obj_id); /*MANUAL
        auto id = obj_id == NULL ? NULL : (char*)env->GetStringUTFChars(obj_id, JNI_FALSE);
        auto _result = THIS->GetID(id);
        if (id != NULL) env->ReleaseStringUTFChars(obj_id, id);
        return _result;
    */

    /**
     * Is scrollbar visible?
     */
    public boolean getScrollbarX() {
        return nGetScrollbarX();
    }

    private native boolean nGetScrollbarX(); /*
        return THIS->ScrollbarX;
    */

    /**
     * Is scrollbar visible?
     */
    public boolean getScrollbarY() {
        return nGetScrollbarY();
    }

    private native boolean nGetScrollbarY(); /*
        return THIS->ScrollbarY;
    */

    /*JNI
        #undef THIS
     */
}
