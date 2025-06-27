package imgui.flag;


/**
 * User fill ImGuiIO.KeyMap[] array with indices into the ImGuiIO.KeysDown[512] array
 */
public final class ImGuiKey {
    private ImGuiKey() {
    }

    /**
     * Keyboard
     *
     * <p>Definition: {@code 0}
     */
    public static final int _None = 0;

    /**
     * == ImGuiKey_NamedKey_BEGIN
     *
     * <p>Definition: {@code 512}
     */
    public static final int _Tab = 512;

    public static final int _LeftArrow = 513;

    public static final int _RightArrow = 514;

    public static final int _UpArrow = 515;

    public static final int _DownArrow = 516;

    public static final int _PageUp = 517;

    public static final int _PageDown = 518;

    public static final int _Home = 519;

    public static final int _End = 520;

    public static final int _Insert = 521;

    public static final int _Delete = 522;

    public static final int _Backspace = 523;

    public static final int _Space = 524;

    public static final int _Enter = 525;

    public static final int _Escape = 526;

    public static final int _LeftCtrl = 527;

    public static final int _LeftShift = 528;

    public static final int _LeftAlt = 529;

    public static final int _LeftSuper = 530;

    public static final int _RightCtrl = 531;

    public static final int _RightShift = 532;

    public static final int _RightAlt = 533;

    public static final int _RightSuper = 534;

    public static final int _Menu = 535;

    public static final int _0 = 536;

    public static final int _1 = 537;

    public static final int _2 = 538;

    public static final int _3 = 539;

    public static final int _4 = 540;

    public static final int _5 = 541;

    public static final int _6 = 542;

    public static final int _7 = 543;

    public static final int _8 = 544;

    public static final int _9 = 545;

    public static final int _A = 546;

    public static final int _B = 547;

    public static final int _C = 548;

    public static final int _D = 549;

    public static final int _E = 550;

    public static final int _F = 551;

    public static final int _G = 552;

    public static final int _H = 553;

    public static final int _I = 554;

    public static final int _J = 555;

    public static final int _K = 556;

    public static final int _L = 557;

    public static final int _M = 558;

    public static final int _N = 559;

    public static final int _O = 560;

    public static final int _P = 561;

    public static final int _Q = 562;

    public static final int _R = 563;

    public static final int _S = 564;

    public static final int _T = 565;

    public static final int _U = 566;

    public static final int _V = 567;

    public static final int _W = 568;

    public static final int _X = 569;

    public static final int _Y = 570;

    public static final int _Z = 571;

    public static final int _F1 = 572;

    public static final int _F2 = 573;

    public static final int _F3 = 574;

    public static final int _F4 = 575;

    public static final int _F5 = 576;

    public static final int _F6 = 577;

    public static final int _F7 = 578;

    public static final int _F8 = 579;

    public static final int _F9 = 580;

    public static final int _F10 = 581;

    public static final int _F11 = 582;

    public static final int _F12 = 583;

    public static final int _F13 = 584;

    public static final int _F14 = 585;

    public static final int _F15 = 586;

    public static final int _F16 = 587;

    public static final int _F17 = 588;

    public static final int _F18 = 589;

    public static final int _F19 = 590;

    public static final int _F20 = 591;

    public static final int _F21 = 592;

    public static final int _F22 = 593;

    public static final int _F23 = 594;

    public static final int _F24 = 595;

    /**
     * '
     */
    public static final int _Apostrophe = 596;

    /**
     * ,
     */
    public static final int _Comma = 597;

    /**
     * -
     */
    public static final int _Minus = 598;

    /**
     * .
     */
    public static final int _Period = 599;

    /**
     * /
     */
    public static final int _Slash = 600;

    /**
     * ;
     */
    public static final int _Semicolon = 601;

    /**
     * =
     */
    public static final int _Equal = 602;

    /**
     * [
     */
    public static final int _LeftBracket = 603;

    /**
     * \ (this text inhibit multiline comment caused by backslash)
     */
    public static final int _Backslash = 604;

    /**
     * ]
     */
    public static final int _RightBracket = 605;

    /**
     * `
     */
    public static final int _GraveAccent = 606;

    public static final int _CapsLock = 607;

    public static final int _ScrollLock = 608;

    public static final int _NumLock = 609;

    public static final int _PrintScreen = 610;

    public static final int _Pause = 611;

    public static final int _Keypad0 = 612;

    public static final int _Keypad1 = 613;

    public static final int _Keypad2 = 614;

    public static final int _Keypad3 = 615;

    public static final int _Keypad4 = 616;

    public static final int _Keypad5 = 617;

    public static final int _Keypad6 = 618;

    public static final int _Keypad7 = 619;

    public static final int _Keypad8 = 620;

    public static final int _Keypad9 = 621;

    public static final int _KeypadDecimal = 622;

    public static final int _KeypadDivide = 623;

    public static final int _KeypadMultiply = 624;

    public static final int _KeypadSubtract = 625;

    public static final int _KeypadAdd = 626;

    public static final int _KeypadEnter = 627;

    public static final int _KeypadEqual = 628;

    /**
     * Available on some keyboard/mouses. Often referred as "Browser Back"
     */
    public static final int _AppBack = 629;

    public static final int _AppForward = 630;

    /**
     * Menu (Xbox)      + (Switch)   Start/Options (PS)
     */
    public static final int _GamepadStart = 631;

    /**
     * View (Xbox)      - (Switch)   Share (PS)
     */
    public static final int _GamepadBack = 632;

    /**
     * X (Xbox)         Y (Switch)   Square (PS)        // Tap: Toggle Menu. Hold: Windowing mode (Focus/Move/Resize windows)
     */
    public static final int _GamepadFaceLeft = 633;

    /**
     * B (Xbox)         A (Switch)   Circle (PS)        // Cancel / Close / Exit
     */
    public static final int _GamepadFaceRight = 634;

    /**
     * Y (Xbox)         X (Switch)   Triangle (PS)      // Text Input / On-screen Keyboard
     */
    public static final int _GamepadFaceUp = 635;

    /**
     * A (Xbox)         B (Switch)   Cross (PS)         // Activate / Open / Toggle / Tweak
     */
    public static final int _GamepadFaceDown = 636;

    /**
     * D-pad Left                                       // Move / Tweak / Resize Window (in Windowing mode)
     */
    public static final int _GamepadDpadLeft = 637;

    /**
     * D-pad Right                                      // Move / Tweak / Resize Window (in Windowing mode)
     */
    public static final int _GamepadDpadRight = 638;

    /**
     * D-pad Up                                         // Move / Tweak / Resize Window (in Windowing mode)
     */
    public static final int _GamepadDpadUp = 639;

    /**
     * D-pad Down                                       // Move / Tweak / Resize Window (in Windowing mode)
     */
    public static final int _GamepadDpadDown = 640;

    /**
     * L Bumper (Xbox)  L (Switch)   L1 (PS)            // Tweak Slower / Focus Previous (in Windowing mode)
     */
    public static final int _GamepadL1 = 641;

    /**
     * R Bumper (Xbox)  R (Switch)   R1 (PS)            // Tweak Faster / Focus Next (in Windowing mode)
     */
    public static final int _GamepadR1 = 642;

    /**
     * L Trig. (Xbox)   ZL (Switch)  L2 (PS) [Analog]
     */
    public static final int _GamepadL2 = 643;

    /**
     * R Trig. (Xbox)   ZR (Switch)  R2 (PS) [Analog]
     */
    public static final int _GamepadR2 = 644;

    /**
     * L Stick (Xbox)   L3 (Switch)  L3 (PS)
     */
    public static final int _GamepadL3 = 645;

    /**
     * R Stick (Xbox)   R3 (Switch)  R3 (PS)
     */
    public static final int _GamepadR3 = 646;

    /**
     * [Analog]                                         // Move Window (in Windowing mode)
     */
    public static final int _GamepadLStickLeft = 647;

    /**
     * [Analog]                                         // Move Window (in Windowing mode)
     */
    public static final int _GamepadLStickRight = 648;

    /**
     * [Analog]                                         // Move Window (in Windowing mode)
     */
    public static final int _GamepadLStickUp = 649;

    /**
     * [Analog]                                         // Move Window (in Windowing mode)
     */
    public static final int _GamepadLStickDown = 650;

    /**
     * [Analog]
     */
    public static final int _GamepadRStickLeft = 651;

    /**
     * [Analog]
     */
    public static final int _GamepadRStickRight = 652;

    /**
     * [Analog]
     */
    public static final int _GamepadRStickUp = 653;

    /**
     * [Analog]
     */
    public static final int _GamepadRStickDown = 654;

    /**
     * Aliases: Mouse Buttons (auto-submitted from AddMouseButtonEvent() calls) - This is mirroring the data also written to io.MouseDown[], io.MouseWheel, in a format allowing them to be accessed via standard key API.
     */
    public static final int _MouseLeft = 655;

    /**
     * Aliases: Mouse Buttons (auto-submitted from AddMouseButtonEvent() calls) - This is mirroring the data also written to io.MouseDown[], io.MouseWheel, in a format allowing them to be accessed via standard key API.
     */
    public static final int _MouseRight = 656;

    /**
     * Aliases: Mouse Buttons (auto-submitted from AddMouseButtonEvent() calls) - This is mirroring the data also written to io.MouseDown[], io.MouseWheel, in a format allowing them to be accessed via standard key API.
     */
    public static final int _MouseMiddle = 657;

    /**
     * Aliases: Mouse Buttons (auto-submitted from AddMouseButtonEvent() calls) - This is mirroring the data also written to io.MouseDown[], io.MouseWheel, in a format allowing them to be accessed via standard key API.
     */
    public static final int _MouseX1 = 658;

    /**
     * Aliases: Mouse Buttons (auto-submitted from AddMouseButtonEvent() calls) - This is mirroring the data also written to io.MouseDown[], io.MouseWheel, in a format allowing them to be accessed via standard key API.
     */
    public static final int _MouseX2 = 659;

    /**
     * Aliases: Mouse Buttons (auto-submitted from AddMouseButtonEvent() calls) - This is mirroring the data also written to io.MouseDown[], io.MouseWheel, in a format allowing them to be accessed via standard key API.
     */
    public static final int _MouseWheelX = 660;

    /**
     * Aliases: Mouse Buttons (auto-submitted from AddMouseButtonEvent() calls) - This is mirroring the data also written to io.MouseDown[], io.MouseWheel, in a format allowing them to be accessed via standard key API.
     */
    public static final int _MouseWheelY = 661;

    /**
     * [Internal] Reserved for mod storage
     */
    public static final int _ReservedForModCtrl = 662;

    /**
     * [Internal] Reserved for mod storage
     */
    public static final int _ReservedForModShift = 663;

    /**
     * [Internal] Reserved for mod storage
     */
    public static final int _ReservedForModAlt = 664;

    /**
     * [Internal] Reserved for mod storage
     */
    public static final int _ReservedForModSuper = 665;

    /**
     * [Internal] Reserved for mod storage
     */
    public static final int _COUNT = 666;

    /**
     * Keyboard Modifiers (explicitly submitted by backend via AddKeyEvent() calls) - This is mirroring the data also written to io.KeyCtrl, io.KeyShift, io.KeyAlt, io.KeySuper, in a format allowing them to be accessed via standard key API, allowing calls such as IsKeyPressed(), IsKeyReleased(), querying duration etc. - Code polling every key (e.g. an interface to detect a key press for input mapping) might want to ignore those and prefer using the real keys (e.g. ImGuiKey_LeftCtrl, ImGuiKey_RightCtrl instead of ImGuiMod_Ctrl). - In theory the value of keyboard modifiers should be roughly equivalent to a logical or of the equivalent left/right keys. In practice: it's complicated; mods are often provided from different sources. Keyboard layout, IME, sticky keys and backends tend to interfere and break that equivalence. The safer decision is to relay that ambiguity down to the end-user... - On macOS, we swap Cmd(Super) and Ctrl keys at the time of the io.AddKeyEvent() call.
     *
     * <p>Definition: {@code 0}
     */
    public static final int ImGuiMod_None = 0;

    /**
     * Ctrl (non-macOS), Cmd (macOS)
     *
     * <p>Definition: {@code 1 << 12}
     */
    public static final int ImGuiMod_Ctrl = 4096;

    /**
     * Shift
     *
     * <p>Definition: {@code 1 << 13}
     */
    public static final int ImGuiMod_Shift = 8192;

    /**
     * Option/Menu
     *
     * <p>Definition: {@code 1 << 14}
     */
    public static final int ImGuiMod_Alt = 16384;

    /**
     * Windows/Super (non-macOS), Ctrl (macOS)
     *
     * <p>Definition: {@code 1 << 15}
     */
    public static final int ImGuiMod_Super = 32768;

    /**
     * 4-bits
     *
     * <p>Definition: {@code 0xF000}
     */
    public static final int ImGuiMod_Mask_ = 61440;

    /**
     * [Internal] Prior to 1.87 we required user to fill io.KeysDown[512] using their own native index + the io.KeyMap[] array. We are ditching this method but keeping a legacy path for user code doing e.g. IsKeyPressed(MY_NATIVE_KEY_CODE) If you need to iterate all keys (for e.g. an input mapper) you may use ImGuiKey_NamedKey_BEGIN..ImGuiKey_NamedKey_END.
     *
     * <p>Definition: {@code 512}
     */
    public static final int _NamedKey_BEGIN = 512;

    /**
     * [Internal] Prior to 1.87 we required user to fill io.KeysDown[512] using their own native index + the io.KeyMap[] array. We are ditching this method but keeping a legacy path for user code doing e.g. IsKeyPressed(MY_NATIVE_KEY_CODE) If you need to iterate all keys (for e.g. an input mapper) you may use ImGuiKey_NamedKey_BEGIN..ImGuiKey_NamedKey_END.
     *
     * <p>Definition: {@code ImGuiKey_COUNT}
     */
    public static final int _NamedKey_END = 666;

    /**
     * [Internal] Prior to 1.87 we required user to fill io.KeysDown[512] using their own native index + the io.KeyMap[] array. We are ditching this method but keeping a legacy path for user code doing e.g. IsKeyPressed(MY_NATIVE_KEY_CODE) If you need to iterate all keys (for e.g. an input mapper) you may use ImGuiKey_NamedKey_BEGIN..ImGuiKey_NamedKey_END.
     *
     * <p>Definition: {@code ImGuiKey_NamedKey_END - ImGuiKey_NamedKey_BEGIN}
     */
    public static final int _NamedKey_COUNT = 154;

    /**
     * Size of KeysData[]: hold legacy 0..512 keycodes + named keys
     *
     * <p>Definition: {@code ImGuiKey_COUNT}
     */
    public static final int _KeysData_SIZE = 666;

    /**
     * Accesses to io.KeysData[] must use (key - ImGuiKey_KeysData_OFFSET) index.
     *
     * <p>Definition: {@code 0}
     */
    public static final int _KeysData_OFFSET = 0;

    /**
     * Removed in 1.90.7, you can now simply use ImGuiMod_Ctrl
     *
     * <p>Definition: {@code ImGuiMod_Ctrl}
     */
    public static final int ImGuiMod_Shortcut = 4096;

    /**
     * Renamed in 1.89
     *
     * <p>Definition: {@code ImGuiMod_Ctrl}
     */
    public static final int _ModCtrl = 4096;

    /**
     * Renamed in 1.89
     *
     * <p>Definition: {@code ImGuiMod_Shift}
     */
    public static final int _ModShift = 8192;

    /**
     * Renamed in 1.89
     *
     * <p>Definition: {@code ImGuiMod_Alt}
     */
    public static final int _ModAlt = 16384;

    /**
     * Renamed in 1.89
     *
     * <p>Definition: {@code ImGuiMod_Super}
     */
    public static final int _ModSuper = 32768;
}
