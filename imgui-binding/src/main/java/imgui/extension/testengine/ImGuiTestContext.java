package imgui.extension.testengine;

import imgui.ImVec2;
import imgui.binding.ImGuiStruct;
import imgui.binding.annotation.BindingMethod;
import imgui.binding.annotation.BindingSource;
import imgui.binding.annotation.OptArg;

@BindingSource
public final class ImGuiTestContext extends ImGuiStruct {
    public ImGuiTestContext(long ptr) {
        super(ptr);
    }

    /*JNI
        #include "_common.h"
        #include "jni_testengine.h"

        #define THIS ((ImGuiTestContext*)STRUCT_PTR)
     */

    // Base Reference
    // - ItemClick("Window/Button")                --> click "Window/Button"
    // - SetRef("Window"), ItemClick("Button")     --> click "Window/Button"
    // - SetRef("Window"), ItemClick("/Button")    --> click "Window/Button"
    // - SetRef("Window"), ItemClick("//Button")   --> click "/Button"
    // - SetRef("//$FOCUSED"), ItemClick("Button") --> click "Button" in focused window.
    // See https://github.com/ocornut/imgui_test_engine/wiki/Named-References about using ImGuiTestRef in all ImGuiTestContext functions.
    // Note: SetRef() may take multiple frames to complete if specified ref is an item id.
    // Note: SetRef() ignores current reference, so they are always absolute path.
    @BindingMethod
    public native void SetRef(String window);

    //@BindingMethod
    //public native int GetRef();

    // Windows
    // - Use WindowInfo() to access path to child windows, since the paths are internally mangled.
    // - SetRef(WindowInfo("Parent/Child")->Window) --> set ref to child window.

    //    ImGuiTestItemInfo WindowInfo(ImGuiTestRef window_ref, ImGuiTestOpFlags flags = ImGuiTestOpFlags_None);

    @BindingMethod
    public native void WindowClose(String ref);

    @BindingMethod
    public native void WindowCollapse(String ref, boolean collapsed);

    @BindingMethod
    public native void WindowFocus(String ref, @OptArg int flags);

    @BindingMethod
    public native void WindowBringToFront(String ref, @OptArg int flags);

    @BindingMethod
    public native void WindowMove(String ref, ImVec2 pos, @OptArg ImVec2 pivot, @OptArg int flags);

    @BindingMethod
    public native void WindowResize(String ref, ImVec2 size);

    @BindingMethod
    public native void WindowTeleportToMakePosVisible(String ref, ImVec2 posInWindow);

    //    ImGuiWindow*GetWindowByRef(ImGuiTestRef window_ref);

    // Popups
    @BindingMethod
    public native void PopupCloseOne();

    @BindingMethod
    public native void PopupCloseAll();

    // ImGuiID     PopupGetWindowID(ImGuiTestRef ref);

    // Get hash for a decorated ID Path.
    // Note: for windows you may use WindowInfo()
    //ImGuiID     GetID(ImGuiTestRef ref);
    //ImGuiID     GetID(ImGuiTestRef ref, ImGuiTestRef seed_ref);

    // Miscellaneous helpers
//    ImVec2      GetPosOnVoid(ImGuiViewport* viewport);                              // Find a point that has no windows // FIXME: This needs error return and flag to enable/disable forcefully finding void.
//    ImVec2      GetWindowTitlebarPoint(ImGuiTestRef window_ref);                    // Return a clickable point on window title-bar (window tab for docked windows).
//    ImVec2      GetMainMonitorWorkPos();                                            // Work pos and size of main viewport when viewports are disabled, or work pos and size of monitor containing main viewport when viewports are enabled.
//    ImVec2      GetMainMonitorWorkSize();

    // Screenshot/Video Captures
//    void        CaptureReset();                                                     // Reset state (use when doing multiple captures)
//    void        CaptureSetExtension(const char* ext);                               // Set capture file format (otherwise for video this default to EngineIO->VideoCaptureExtension)
//    bool        CaptureAddWindow(ImGuiTestRef ref);                                 // Add window to be captured (default to capture everything)
//    void        CaptureScreenshotWindow(ImGuiTestRef ref, int capture_flags = 0);   // Trigger a screen capture of a single window (== CaptureAddWindow() + CaptureScreenshot())
//    bool        CaptureScreenshot(int capture_flags = 0);                           // Trigger a screen capture
//    bool        CaptureBeginVideo();                                                // Start a video capture
//    bool        CaptureEndVideo();

    // Mouse inputs
//    void        MouseMove(ImGuiTestRef ref, ImGuiTestOpFlags flags = ImGuiTestOpFlags_None);
//    void        MouseMoveToPos(ImVec2 pos);
//    void        MouseTeleportToPos(ImVec2 pos, ImGuiTestOpFlags flags = ImGuiTestOpFlags_None);
//    void        MouseClick(ImGuiMouseButton button = 0);
//    void        MouseClickMulti(ImGuiMouseButton button, int count);
//    void        MouseDoubleClick(ImGuiMouseButton button = 0);
//    void        MouseDown(ImGuiMouseButton button = 0);
//    void        MouseUp(ImGuiMouseButton button = 0);
//    void        MouseLiftDragThreshold(ImGuiMouseButton button = 0);
//    void        MouseDragWithDelta(ImVec2 delta, ImGuiMouseButton button = 0);
//    void        MouseWheel(ImVec2 delta);
//    void        MouseWheelX(float dx) { MouseWheel(ImVec2(dx, 0.0f)); }
//    void        MouseWheelY(float dy) { MouseWheel(ImVec2(0.0f, dy)); }
//    void        MouseMoveToVoid(ImGuiViewport* viewport = NULL);
//    void        MouseClickOnVoid(ImGuiMouseButton button = 0, ImGuiViewport* viewport = NULL);
//    ImGuiWindow*FindHoveredWindowAtPos(const ImVec2& pos);
//    bool        FindExistingVoidPosOnViewport(ImGuiViewport* viewport, ImVec2* out);

    // Mouse inputs: Viewports
    // - This is automatically called by SetRef() and any mouse action taking an item reference (e.g. ItemClick("button"), MouseClick("button"))
    // - But when using raw position directy e.g. MouseMoveToPos() / MouseTeleportToPos() without referring to the parent window before, this needs to be set.
//    void        MouseSetViewport(ImGuiWindow* window);
//    void        MouseSetViewportID(ImGuiID viewport_id);

    // Keyboard inputs
//    void        KeyDown(ImGuiKeyChord key_chord);
//    void        KeyUp(ImGuiKeyChord key_chord);
//    void        KeyPress(ImGuiKeyChord key_chord, int count = 1);
//    void        KeyHold(ImGuiKeyChord key_chord, float time);
//    void        KeySetEx(ImGuiKeyChord key_chord, bool is_down, float time);
//    void        KeyChars(const char* chars);                // Input characters
//    void        KeyCharsAppend(const char* chars);          // Input characters at end of field
//    void        KeyCharsAppendEnter(const char* chars);     // Input characters at end of field, press Enter
//    void        KeyCharsReplace(const char* chars);         // Delete existing field then input characters
//    void        KeyCharsReplaceEnter(const char* chars);    // Delete existing field then input characters, press Enter

    // Navigation inputs
    // FIXME: Need some redesign/refactoring:
    // - This was initially intended to: replace mouse action with keyboard/gamepad
    // - Abstract keyboard vs gamepad actions
    // However this is widely inconsistent and unfinished at this point.
//    void        SetInputMode(ImGuiInputSource input_mode);  // Mouse or Keyboard or Gamepad. In Keyboard or Gamepad mode, actions such as ItemClick or ItemInput are using nav facilities instead of Mouse.
//    void        NavMoveTo(ImGuiTestRef ref);
//    void        NavActivate();                              // Activate current selected item: activate button, tweak sliders/drags. Equivalent of pressing Space on keyboard, ImGuiKey_GamepadFaceUp on a gamepad.
//    void        NavInput();                                 // Input into select item: input sliders/drags. Equivalent of pressing Enter on keyboard, ImGuiKey_GamepadFaceDown on a gamepad.

    // Scrolling
//    void        ScrollTo(ImGuiTestRef ref, ImGuiAxis axis, float scroll_v, ImGuiTestOpFlags flags = ImGuiTestOpFlags_None);
//    void        ScrollToX(ImGuiTestRef ref, float scroll_x) { ScrollTo(ref, ImGuiAxis_X, scroll_x); }
//    void        ScrollToY(ImGuiTestRef ref, float scroll_y) { ScrollTo(ref, ImGuiAxis_Y, scroll_y); }
//    void        ScrollToTop(ImGuiTestRef ref);
//    void        ScrollToBottom(ImGuiTestRef ref);
//    void        ScrollToItem(ImGuiTestRef ref, ImGuiAxis axis, ImGuiTestOpFlags flags = ImGuiTestOpFlags_None);
//    void        ScrollToItemX(ImGuiTestRef ref);
//    void        ScrollToItemY(ImGuiTestRef ref);
//    void        ScrollToTabItem(ImGuiTabBar* tab_bar, ImGuiID tab_id);
//    bool        ScrollErrorCheck(ImGuiAxis axis, float expected, float actual, int* remaining_attempts);
//    void        ScrollVerifyScrollMax(ImGuiTestRef ref);

    // Low-level queries
    // - ItemInfo queries never returns a NULL pointer, instead they return an empty instance (info->IsEmpty(), info->ID == 0) and set contexted as errored.
    // - You can use ImGuiTestOpFlags_NoError to do a query without marking context as errored. This is what ItemExists() does.
//    ImGuiTestItemInfo   ItemInfo(ImGuiTestRef ref, ImGuiTestOpFlags flags = ImGuiTestOpFlags_None);
//    ImGuiTestItemInfo   ItemInfoOpenFullPath(ImGuiTestRef ref, ImGuiTestOpFlags flags = ImGuiTestOpFlags_None);
//    ImGuiID             ItemInfoHandleWildcardSearch(const char* wildcard_prefix_start, const char* wildcard_prefix_end, const char* wildcard_suffix_start);
//    ImGuiTestItemInfo   ItemInfoNull() { return ImGuiTestItemInfo(); }
//    void                GatherItems(ImGuiTestItemList* out_list, ImGuiTestRef parent, int depth = -1);

    // Item/Widgets manipulation

    //void        ItemAction(ImGuiTestAction action, ImGuiTestRef ref, ImGuiTestOpFlags flags = 0, void* action_arg = NULL);

    @BindingMethod
    public native void ItemClick(String ref, @OptArg int button, @OptArg int flags);

    @BindingMethod
    public native void ItemDoubleClick(String ref, @OptArg int button);

    @BindingMethod
    public native void ItemCheck(String ref, @OptArg int button);

    @BindingMethod
    public native void ItemUncheck(String ref, @OptArg int button);

    @BindingMethod
    public native void ItemOpen(String ref, @OptArg int button);

    @BindingMethod
    public native void ItemClose(String ref, @OptArg int button);

    @BindingMethod
    public native void ItemInput(String ref, @OptArg int button);

    @BindingMethod
    public native void ItemNavActivate(String ref, @OptArg int button);

    //    bool        ItemOpenFullPath(ImGuiTestRef);

    // Item/Widgets: Batch actions over an entire scope
//    void        ItemActionAll(ImGuiTestAction action, ImGuiTestRef ref_parent, const ImGuiTestActionFilter* filter = NULL);
//    void        ItemOpenAll(ImGuiTestRef ref_parent, int depth = -1, int passes = -1);
//    void        ItemCloseAll(ImGuiTestRef ref_parent, int depth = -1, int passes = -1);

    // Item/Widgets: Helpers to easily set a value
    @BindingMethod
    public native void ItemInputValue(String ref, int v);

    @BindingMethod
    public native void ItemInputValue(String ref, float f);

    @BindingMethod
    public native void ItemInputValue(String ref, String str);

    // Item/Widgets: Helpers to easily read a value by selecting Slider/Drag/Input text, copying into clipboard and parsing it.
    // - This requires the item to be selectable (we will later provide helpers that works in more general manner)
    // - Original clipboard value is restored afterward.
//    bool        ItemSelectAndReadValue(ImGuiTestRef ref, ImGuiDataType data_type, void* out_data, ImGuiTestOpFlags flags = ImGuiTestOpFlags_None);
//    void        ItemSelectAndReadValue(ImGuiTestRef ref, int* out_v);
//    void        ItemSelectAndReadValue(ImGuiTestRef ref, float* out_v);

    // Item/Widgets: Status query
//    bool        ItemExists(ImGuiTestRef ref);
//    bool        ItemIsChecked(ImGuiTestRef ref);
//    bool        ItemIsOpened(ImGuiTestRef ref);
//    void        ItemVerifyCheckedIfAlive(ImGuiTestRef ref, bool checked);

    // Item/Widgets: Drag and Mouse operations
//    void        ItemHold(ImGuiTestRef ref, float time);
//    void        ItemHoldForFrames(ImGuiTestRef ref, int frames);
//    void        ItemDragOverAndHold(ImGuiTestRef ref_src, ImGuiTestRef ref_dst);
//    void        ItemDragAndDrop(ImGuiTestRef ref_src, ImGuiTestRef ref_dst, ImGuiMouseButton button = 0);
//    void        ItemDragWithDelta(ImGuiTestRef ref_src, ImVec2 pos_delta);

    // Helpers for Tab Bars widgets
//    void        TabClose(ImGuiTestRef ref);
//    bool        TabBarCompareOrder(ImGuiTabBar* tab_bar, const char** tab_order);

    // Helpers for MenuBar and Menus widgets
    // - e.g. MenuCheck("File/Options/Enable grid");
    //    void        MenuAction(ImGuiTestAction action, ImGuiTestRef ref);
    //    void        MenuActionAll(ImGuiTestAction action, ImGuiTestRef ref_parent);

    @BindingMethod
    public native void MenuClick(String ref);                 //{ MenuAction(ImGuiTestAction_Click, ref); }

    @BindingMethod
    public native void MenuCheck(String ref);                 //{ MenuAction(ImGuiTestAction_Check, ref); }

    @BindingMethod
    public native void MenuUncheck(String ref);               //{ MenuAction(ImGuiTestAction_Uncheck, ref); }

    @BindingMethod
    public native void MenuCheckAll(String ref_parent);       //{ MenuActionAll(ImGuiTestAction_Check, ref_parent); }

    @BindingMethod
    public native void MenuUncheckAll(String ref_parent);     //{ MenuActionAll(ImGuiTestAction_Uncheck, ref_parent); }

    // Helpers for Combo Boxes
//    void        ComboClick(ImGuiTestRef ref);
//    void        ComboClickAll(ImGuiTestRef ref);

    // Helpers for Tables
//    void                        TableOpenContextMenu(ImGuiTestRef ref, int column_n = -1);
//    ImGuiSortDirection          TableClickHeader(ImGuiTestRef ref, const char* label, ImGuiKeyChord key_mods = 0);
//    void                        TableSetColumnEnabled(ImGuiTestRef ref, const char* label, bool enabled);
//    void                        TableResizeColumn(ImGuiTestRef ref, int column_n, float width);
//    const ImGuiTableSortSpecs*  TableGetSortSpecs(ImGuiTestRef ref);

    /*JNI
        #undef THIS
     */
}
