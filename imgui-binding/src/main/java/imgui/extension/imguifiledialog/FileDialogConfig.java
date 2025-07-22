package imgui.extension.imguifiledialog;

import imgui.binding.ImGuiStruct;
import imgui.binding.ImGuiStructDestroyable;
import imgui.binding.annotation.BindingField;
import imgui.binding.annotation.BindingSource;
import imgui.binding.annotation.ReturnValue;
import imgui.binding.annotation.TypeStdString;

import java.io.File;

// I think I need to change source so it can be made as a ImGuiStructDestroyable
// tho I'd like to test that into a project that use the filedialog lib in cpp
// and the example app is designed to be included in a project that include imgui/etc...
// not designed to just include the lib and run a test app

@BindingSource
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

    @BindingField
    @TypeStdString
    @ReturnValue(callSuffix = ".c_str()")
    public String path;

    @BindingField
    @TypeStdString
    @ReturnValue(callSuffix = ".c_str()")
    public String fileName;

    @BindingField
    @TypeStdString
    @ReturnValue(callSuffix = ".c_str()")
    public String filePathName;

    @BindingField
    public int countSelectionMax;

    @BindingField(isFlag = true)
    public int flags;

    @BindingField
    public float sidePaneWidth;

    /*JNI
        #undef THIS
     */
}
