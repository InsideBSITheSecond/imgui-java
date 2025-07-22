package imgui.extension.imguifiledialog;

import imgui.binding.ImGuiStruct;
import imgui.binding.ImGuiStructDestroyable;

import java.io.File;

// I think I need to change source so it can be made as a ImGuiStructDestroyable
// tho I'd like to test that into a project that use the filedialog lib in cpp
// and the example app is designed to be included in a project that include imgui/etc...
// not designed to just include the lib and run a test app

public final class FileDialogConfig extends ImGuiStructDestroyable {
    @Override
    protected long create() {
        return nCreate();
    }

    /*JNI
        #include "_imguifiledialog.h"
        #define THIS ((IGFD::FileDialogConfig*)STRUCT_PTR)
     */

    private native long nCreate(); /*
        return (uintptr_t)(new IGFD::FileDialogConfig());
    */

    public String getpath() {
        return nGetpath();
    }

    public void setpath(final String value) {
        nSetpath(value);
    }

    private native String nGetpath(); /*
        return env->NewStringUTF(THIS->path.c_str());
    */

    private native void nSetpath(String value); /*MANUAL
        auto value = obj_value == NULL ? NULL : (char*)env->GetStringUTFChars(obj_value, JNI_FALSE);
        THIS->path = value;
        if (value != NULL) env->ReleaseStringUTFChars(obj_value, value);
    */

    public String getfileName() {
        return nGetfileName();
    }

    public void setfileName(final String value) {
        nSetfileName(value);
    }

    private native String nGetfileName(); /*
        return env->NewStringUTF(THIS->fileName.c_str());
    */

    private native void nSetfileName(String value); /*MANUAL
        auto value = obj_value == NULL ? NULL : (char*)env->GetStringUTFChars(obj_value, JNI_FALSE);
        THIS->fileName = value;
        if (value != NULL) env->ReleaseStringUTFChars(obj_value, value);
    */

    public String getfilePathName() {
        return nGetfilePathName();
    }

    public void setfilePathName(final String value) {
        nSetfilePathName(value);
    }

    private native String nGetfilePathName(); /*
        return env->NewStringUTF(THIS->filePathName.c_str());
    */

    private native void nSetfilePathName(String value); /*MANUAL
        auto value = obj_value == NULL ? NULL : (char*)env->GetStringUTFChars(obj_value, JNI_FALSE);
        THIS->filePathName = value;
        if (value != NULL) env->ReleaseStringUTFChars(obj_value, value);
    */

    public int getcountSelectionMax() {
        return nGetcountSelectionMax();
    }

    public void setcountSelectionMax(final int value) {
        nSetcountSelectionMax(value);
    }

    private native int nGetcountSelectionMax(); /*
        return THIS->countSelectionMax;
    */

    private native void nSetcountSelectionMax(int value); /*
        THIS->countSelectionMax = value;
    */

    public int getflags() {
        return nGetflags();
    }

    public void setflags(final int value) {
        nSetflags(value);
    }

    /**
     */
    public void addflags(final int flags) {
        setflags(getflags() | flags);
    }

    /**
     */
    public void removeflags(final int flags) {
        setflags(getflags() & ~(flags));
    }

    /**
     */
    public boolean hasflags(final int flags) {
        return (getflags() & flags) != 0;
    }

    private native int nGetflags(); /*
        return THIS->flags;
    */

    private native void nSetflags(int value); /*
        THIS->flags = value;
    */

    public float getsidePaneWidth() {
        return nGetsidePaneWidth();
    }

    public void setsidePaneWidth(final float value) {
        nSetsidePaneWidth(value);
    }

    private native float nGetsidePaneWidth(); /*
        return THIS->sidePaneWidth;
    */

    private native void nSetsidePaneWidth(float value); /*
        THIS->sidePaneWidth = value;
    */

    /*JNI
        #undef THIS
     */
}
