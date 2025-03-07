package imgui.extension.testengine;

import imgui.ImVec2;
import imgui.binding.ImGuiStruct;
import imgui.extension.testengine.flag.TestOpFlags;
import imgui.flag.ImGuiMouseButton;
import imgui.internal.ImGuiWindow;

public class TestContext extends ImGuiStruct {
    public TestContext(long ptr) {
        super(ptr);
    }

    /*JNI
        #include "_common.h"
        #include "_imgui_te.h"

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
    public void setRef(final String window) {
        nSetRef(window);
    }

    private native void nSetRef(String window); /*MANUAL
        auto window = obj_window == NULL ? NULL : (char*)env->GetStringUTFChars(obj_window, JNI_FALSE);
        THIS->SetRef(window);
        if (window != NULL) env->ReleaseStringUTFChars(obj_window, window);
    */

    //public native int GetRef();

    // Windows
    // - Use WindowInfo() to access path to child windows, since the paths are internally mangled.
    // - SetRef(WindowInfo("Parent/Child")->Window) --> set ref to child window.

    //    ImGuiTestItemInfo WindowInfo(ImGuiTestRef window_ref, ImGuiTestOpFlags flags = ImGuiTestOpFlags_None);

    public void windowClose(final String ref) {
        nWindowClose(ref);
    }

    private native void nWindowClose(String ref); /*MANUAL
        auto ref = obj_ref == NULL ? NULL : (char*)env->GetStringUTFChars(obj_ref, JNI_FALSE);
        THIS->WindowClose(ref);
        if (ref != NULL) env->ReleaseStringUTFChars(obj_ref, ref);
    */

    public void windowCollapse(final String ref, final boolean collapsed) {
        nWindowCollapse(ref, collapsed);
    }

    private native void nWindowCollapse(String ref, boolean collapsed); /*MANUAL
        auto ref = obj_ref == NULL ? NULL : (char*)env->GetStringUTFChars(obj_ref, JNI_FALSE);
        THIS->WindowCollapse(ref, collapsed);
        if (ref != NULL) env->ReleaseStringUTFChars(obj_ref, ref);
    */

    public void windowFocus(final String ref) {
        nWindowFocus(ref);
    }

    public void windowFocus(final String ref, final int flags) {
        nWindowFocus(ref, flags);
    }

    private native void nWindowFocus(String ref); /*MANUAL
        auto ref = obj_ref == NULL ? NULL : (char*)env->GetStringUTFChars(obj_ref, JNI_FALSE);
        THIS->WindowFocus(ref);
        if (ref != NULL) env->ReleaseStringUTFChars(obj_ref, ref);
    */

    private native void nWindowFocus(String ref, int flags); /*MANUAL
        auto ref = obj_ref == NULL ? NULL : (char*)env->GetStringUTFChars(obj_ref, JNI_FALSE);
        THIS->WindowFocus(ref, flags);
        if (ref != NULL) env->ReleaseStringUTFChars(obj_ref, ref);
    */

    public void windowBringToFront(final String ref) {
        nWindowBringToFront(ref);
    }

    public void windowBringToFront(final String ref, final int flags) {
        nWindowBringToFront(ref, flags);
    }

    private native void nWindowBringToFront(String ref); /*MANUAL
        auto ref = obj_ref == NULL ? NULL : (char*)env->GetStringUTFChars(obj_ref, JNI_FALSE);
        THIS->WindowBringToFront(ref);
        if (ref != NULL) env->ReleaseStringUTFChars(obj_ref, ref);
    */

    private native void nWindowBringToFront(String ref, int flags); /*MANUAL
        auto ref = obj_ref == NULL ? NULL : (char*)env->GetStringUTFChars(obj_ref, JNI_FALSE);
        THIS->WindowBringToFront(ref, flags);
        if (ref != NULL) env->ReleaseStringUTFChars(obj_ref, ref);
    */

    public void windowMove(final String ref, final ImVec2 pos) {
        nWindowMove(ref, pos.x, pos.y);
    }

    public void windowMove(final String ref, final float posX, final float posY) {
        nWindowMove(ref, posX, posY);
    }

    public void windowMove(final String ref, final ImVec2 pos, final ImVec2 pivot) {
        nWindowMove(ref, pos.x, pos.y, pivot.x, pivot.y);
    }

    public void windowMove(final String ref, final float posX, final float posY, final float pivotX, final float pivotY) {
        nWindowMove(ref, posX, posY, pivotX, pivotY);
    }

    public void windowMove(final String ref, final ImVec2 pos, final ImVec2 pivot, final int flags) {
        nWindowMove(ref, pos.x, pos.y, pivot.x, pivot.y, flags);
    }

    public void windowMove(final String ref, final float posX, final float posY, final float pivotX, final float pivotY, final int flags) {
        nWindowMove(ref, posX, posY, pivotX, pivotY, flags);
    }

    private native void nWindowMove(String ref, float posX, float posY); /*MANUAL
        auto ref = obj_ref == NULL ? NULL : (char*)env->GetStringUTFChars(obj_ref, JNI_FALSE);
        ImVec2 pos = ImVec2(posX, posY);
        THIS->WindowMove(ref, pos);
        if (ref != NULL) env->ReleaseStringUTFChars(obj_ref, ref);
    */

    private native void nWindowMove(String ref, float posX, float posY, float pivotX, float pivotY); /*MANUAL
        auto ref = obj_ref == NULL ? NULL : (char*)env->GetStringUTFChars(obj_ref, JNI_FALSE);
        ImVec2 pos = ImVec2(posX, posY);
        ImVec2 pivot = ImVec2(pivotX, pivotY);
        THIS->WindowMove(ref, pos, pivot);
        if (ref != NULL) env->ReleaseStringUTFChars(obj_ref, ref);
    */

    private native void nWindowMove(String ref, float posX, float posY, float pivotX, float pivotY, int flags); /*MANUAL
        auto ref = obj_ref == NULL ? NULL : (char*)env->GetStringUTFChars(obj_ref, JNI_FALSE);
        ImVec2 pos = ImVec2(posX, posY);
        ImVec2 pivot = ImVec2(pivotX, pivotY);
        THIS->WindowMove(ref, pos, pivot, flags);
        if (ref != NULL) env->ReleaseStringUTFChars(obj_ref, ref);
    */

    public void windowResize(final String ref, final ImVec2 size) {
        nWindowResize(ref, size.x, size.y);
    }

    public void windowResize(final String ref, final float sizeX, final float sizeY) {
        nWindowResize(ref, sizeX, sizeY);
    }

    private native void nWindowResize(String ref, float sizeX, float sizeY); /*MANUAL
        auto ref = obj_ref == NULL ? NULL : (char*)env->GetStringUTFChars(obj_ref, JNI_FALSE);
        ImVec2 size = ImVec2(sizeX, sizeY);
        THIS->WindowResize(ref, size);
        if (ref != NULL) env->ReleaseStringUTFChars(obj_ref, ref);
    */

    public void windowTeleportToMakePosVisible(final String ref, final ImVec2 posInWindow) {
        nWindowTeleportToMakePosVisible(ref, posInWindow.x, posInWindow.y);
    }

    public void windowTeleportToMakePosVisible(final String ref, final float posInWindowX, final float posInWindowY) {
        nWindowTeleportToMakePosVisible(ref, posInWindowX, posInWindowY);
    }

    private native void nWindowTeleportToMakePosVisible(String ref, float posInWindowX, float posInWindowY); /*MANUAL
        auto ref = obj_ref == NULL ? NULL : (char*)env->GetStringUTFChars(obj_ref, JNI_FALSE);
        ImVec2 posInWindow = ImVec2(posInWindowX, posInWindowY);
        THIS->WindowTeleportToMakePosVisible(ref, posInWindow);
        if (ref != NULL) env->ReleaseStringUTFChars(obj_ref, ref);
    */

    //    ImGuiWindow*GetWindowByRef(ImGuiTestRef window_ref);

    // Popups
    public void popupCloseOne() {
        nPopupCloseOne();
    }

    private native void nPopupCloseOne(); /*
        THIS->PopupCloseOne();
    */

    public void popupCloseAll() {
        nPopupCloseAll();
    }

    private native void nPopupCloseAll(); /*
        THIS->PopupCloseAll();
    */

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

    public void itemClick(final String ref) {
        nItemClick(ref);
    }

    public void itemClick(final String ref, final int button) {
        nItemClick(ref, button);
    }

    public void itemClick(final String ref, final int button, final int flags) {
        nItemClick(ref, button, flags);
    }

    private native void nItemClick(String ref); /*MANUAL
        auto ref = obj_ref == NULL ? NULL : (char*)env->GetStringUTFChars(obj_ref, JNI_FALSE);
        THIS->ItemClick(ref);
        if (ref != NULL) env->ReleaseStringUTFChars(obj_ref, ref);
    */

    private native void nItemClick(String ref, int button); /*MANUAL
        auto ref = obj_ref == NULL ? NULL : (char*)env->GetStringUTFChars(obj_ref, JNI_FALSE);
        THIS->ItemClick(ref, button);
        if (ref != NULL) env->ReleaseStringUTFChars(obj_ref, ref);
    */

    private native void nItemClick(String ref, int button, int flags); /*MANUAL
        auto ref = obj_ref == NULL ? NULL : (char*)env->GetStringUTFChars(obj_ref, JNI_FALSE);
        THIS->ItemClick(ref, button, flags);
        if (ref != NULL) env->ReleaseStringUTFChars(obj_ref, ref);
    */

    public void itemDoubleClick(final String ref) {
        nItemDoubleClick(ref);
    }

    public void itemDoubleClick(final String ref, final int button) {
        nItemDoubleClick(ref, button);
    }

    private native void nItemDoubleClick(String ref); /*MANUAL
        auto ref = obj_ref == NULL ? NULL : (char*)env->GetStringUTFChars(obj_ref, JNI_FALSE);
        THIS->ItemDoubleClick(ref);
        if (ref != NULL) env->ReleaseStringUTFChars(obj_ref, ref);
    */

    private native void nItemDoubleClick(String ref, int button); /*MANUAL
        auto ref = obj_ref == NULL ? NULL : (char*)env->GetStringUTFChars(obj_ref, JNI_FALSE);
        THIS->ItemDoubleClick(ref, button);
        if (ref != NULL) env->ReleaseStringUTFChars(obj_ref, ref);
    */

    public void itemCheck(final String ref) {
        nItemCheck(ref);
    }

    public void itemCheck(final String ref, final int button) {
        nItemCheck(ref, button);
    }

    private native void nItemCheck(String ref); /*MANUAL
        auto ref = obj_ref == NULL ? NULL : (char*)env->GetStringUTFChars(obj_ref, JNI_FALSE);
        THIS->ItemCheck(ref);
        if (ref != NULL) env->ReleaseStringUTFChars(obj_ref, ref);
    */

    private native void nItemCheck(String ref, int button); /*MANUAL
        auto ref = obj_ref == NULL ? NULL : (char*)env->GetStringUTFChars(obj_ref, JNI_FALSE);
        THIS->ItemCheck(ref, button);
        if (ref != NULL) env->ReleaseStringUTFChars(obj_ref, ref);
    */

    public void itemUncheck(final String ref) {
        nItemUncheck(ref);
    }

    public void itemUncheck(final String ref, final int button) {
        nItemUncheck(ref, button);
    }

    private native void nItemUncheck(String ref); /*MANUAL
        auto ref = obj_ref == NULL ? NULL : (char*)env->GetStringUTFChars(obj_ref, JNI_FALSE);
        THIS->ItemUncheck(ref);
        if (ref != NULL) env->ReleaseStringUTFChars(obj_ref, ref);
    */

    private native void nItemUncheck(String ref, int button); /*MANUAL
        auto ref = obj_ref == NULL ? NULL : (char*)env->GetStringUTFChars(obj_ref, JNI_FALSE);
        THIS->ItemUncheck(ref, button);
        if (ref != NULL) env->ReleaseStringUTFChars(obj_ref, ref);
    */

    public void itemOpen(final String ref) {
        nItemOpen(ref);
    }

    public void itemOpen(final String ref, final int button) {
        nItemOpen(ref, button);
    }

    private native void nItemOpen(String ref); /*MANUAL
        auto ref = obj_ref == NULL ? NULL : (char*)env->GetStringUTFChars(obj_ref, JNI_FALSE);
        THIS->ItemOpen(ref);
        if (ref != NULL) env->ReleaseStringUTFChars(obj_ref, ref);
    */

    private native void nItemOpen(String ref, int button); /*MANUAL
        auto ref = obj_ref == NULL ? NULL : (char*)env->GetStringUTFChars(obj_ref, JNI_FALSE);
        THIS->ItemOpen(ref, button);
        if (ref != NULL) env->ReleaseStringUTFChars(obj_ref, ref);
    */

    public void itemClose(final String ref) {
        nItemClose(ref);
    }

    public void itemClose(final String ref, final int button) {
        nItemClose(ref, button);
    }

    private native void nItemClose(String ref); /*MANUAL
        auto ref = obj_ref == NULL ? NULL : (char*)env->GetStringUTFChars(obj_ref, JNI_FALSE);
        THIS->ItemClose(ref);
        if (ref != NULL) env->ReleaseStringUTFChars(obj_ref, ref);
    */

    private native void nItemClose(String ref, int button); /*MANUAL
        auto ref = obj_ref == NULL ? NULL : (char*)env->GetStringUTFChars(obj_ref, JNI_FALSE);
        THIS->ItemClose(ref, button);
        if (ref != NULL) env->ReleaseStringUTFChars(obj_ref, ref);
    */

    public void itemInput(final String ref) {
        nItemInput(ref);
    }

    public void itemInput(final String ref, final int button) {
        nItemInput(ref, button);
    }

    private native void nItemInput(String ref); /*MANUAL
        auto ref = obj_ref == NULL ? NULL : (char*)env->GetStringUTFChars(obj_ref, JNI_FALSE);
        THIS->ItemInput(ref);
        if (ref != NULL) env->ReleaseStringUTFChars(obj_ref, ref);
    */

    private native void nItemInput(String ref, int button); /*MANUAL
        auto ref = obj_ref == NULL ? NULL : (char*)env->GetStringUTFChars(obj_ref, JNI_FALSE);
        THIS->ItemInput(ref, button);
        if (ref != NULL) env->ReleaseStringUTFChars(obj_ref, ref);
    */

    public void itemNavActivate(final String ref) {
        nItemNavActivate(ref);
    }

    public void itemNavActivate(final String ref, final int button) {
        nItemNavActivate(ref, button);
    }

    private native void nItemNavActivate(String ref); /*MANUAL
        auto ref = obj_ref == NULL ? NULL : (char*)env->GetStringUTFChars(obj_ref, JNI_FALSE);
        THIS->ItemNavActivate(ref);
        if (ref != NULL) env->ReleaseStringUTFChars(obj_ref, ref);
    */

    private native void nItemNavActivate(String ref, int button); /*MANUAL
        auto ref = obj_ref == NULL ? NULL : (char*)env->GetStringUTFChars(obj_ref, JNI_FALSE);
        THIS->ItemNavActivate(ref, button);
        if (ref != NULL) env->ReleaseStringUTFChars(obj_ref, ref);
    */

    //    bool        ItemOpenFullPath(ImGuiTestRef);

    // Item/Widgets: Batch actions over an entire scope
//    void        ItemActionAll(ImGuiTestAction action, ImGuiTestRef ref_parent, const ImGuiTestActionFilter* filter = NULL);
//    void        ItemOpenAll(ImGuiTestRef ref_parent, int depth = -1, int passes = -1);
//    void        ItemCloseAll(ImGuiTestRef ref_parent, int depth = -1, int passes = -1);

    // Item/Widgets: Helpers to easily set a value
    public void itemInputValue(final String ref, final int v) {
        nItemInputValue(ref, v);
    }

    private native void nItemInputValue(String ref, int v); /*MANUAL
        auto ref = obj_ref == NULL ? NULL : (char*)env->GetStringUTFChars(obj_ref, JNI_FALSE);
        THIS->ItemInputValue(ref, v);
        if (ref != NULL) env->ReleaseStringUTFChars(obj_ref, ref);
    */

    public void itemInputValue(final String ref, final float f) {
        nItemInputValue(ref, f);
    }

    private native void nItemInputValue(String ref, float f); /*MANUAL
        auto ref = obj_ref == NULL ? NULL : (char*)env->GetStringUTFChars(obj_ref, JNI_FALSE);
        THIS->ItemInputValue(ref, f);
        if (ref != NULL) env->ReleaseStringUTFChars(obj_ref, ref);
    */

    public void itemInputValue(final String ref, final String str) {
        nItemInputValue(ref, str);
    }

    private native void nItemInputValue(String ref, String str); /*MANUAL
        auto ref = obj_ref == NULL ? NULL : (char*)env->GetStringUTFChars(obj_ref, JNI_FALSE);
        auto str = obj_str == NULL ? NULL : (char*)env->GetStringUTFChars(obj_str, JNI_FALSE);
        THIS->ItemInputValue(ref, str);
        if (ref != NULL) env->ReleaseStringUTFChars(obj_ref, ref);
        if (str != NULL) env->ReleaseStringUTFChars(obj_str, str);
    */

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

    public void menuClick(final String ref) {
        nMenuClick(ref);
    }

    private native void nMenuClick(String ref); /*MANUAL
        auto ref = obj_ref == NULL ? NULL : (char*)env->GetStringUTFChars(obj_ref, JNI_FALSE);
        THIS->MenuClick(ref);
        if (ref != NULL) env->ReleaseStringUTFChars(obj_ref, ref);
    */

    public void menuCheck(final String ref) {
        nMenuCheck(ref);
    }

    private native void nMenuCheck(String ref); /*MANUAL
        auto ref = obj_ref == NULL ? NULL : (char*)env->GetStringUTFChars(obj_ref, JNI_FALSE);
        THIS->MenuCheck(ref);
        if (ref != NULL) env->ReleaseStringUTFChars(obj_ref, ref);
    */

    public void menuUncheck(final String ref) {
        nMenuUncheck(ref);
    }

    private native void nMenuUncheck(String ref); /*MANUAL
        auto ref = obj_ref == NULL ? NULL : (char*)env->GetStringUTFChars(obj_ref, JNI_FALSE);
        THIS->MenuUncheck(ref);
        if (ref != NULL) env->ReleaseStringUTFChars(obj_ref, ref);
    */

    public void menuCheckAll(final String ref_parent) {
        nMenuCheckAll(ref_parent);
    }

    private native void nMenuCheckAll(String ref_parent); /*MANUAL
        auto ref_parent = obj_ref_parent == NULL ? NULL : (char*)env->GetStringUTFChars(obj_ref_parent, JNI_FALSE);
        THIS->MenuCheckAll(ref_parent);
        if (ref_parent != NULL) env->ReleaseStringUTFChars(obj_ref_parent, ref_parent);
    */

    public void menuUncheckAll(final String ref_parent) {
        nMenuUncheckAll(ref_parent);
    }

    private native void nMenuUncheckAll(String ref_parent); /*MANUAL
        auto ref_parent = obj_ref_parent == NULL ? NULL : (char*)env->GetStringUTFChars(obj_ref_parent, JNI_FALSE);
        THIS->MenuUncheckAll(ref_parent);
        if (ref_parent != NULL) env->ReleaseStringUTFChars(obj_ref_parent, ref_parent);
    */

    // Helpers for Combo Boxes
//    void        ComboClick(ImGuiTestRef ref);
//    void        ComboClickAll(ImGuiTestRef ref);

    // Helpers for Tables
//    void                        TableOpenContextMenu(ImGuiTestRef ref, int column_n = -1);
//    ImGuiSortDirection          TableClickHeader(ImGuiTestRef ref, const char* label, ImGuiKeyChord key_mods = 0);
//    void                        TableSetColumnEnabled(ImGuiTestRef ref, const char* label, bool enabled);
//    void                        TableResizeColumn(ImGuiTestRef ref, int column_n, float width);
//    const ImGuiTableSortSpecs*  TableGetSortSpecs(ImGuiTestRef ref);
}
