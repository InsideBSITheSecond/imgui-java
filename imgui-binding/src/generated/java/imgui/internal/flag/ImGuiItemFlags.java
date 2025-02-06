package imgui.internal.flag;


/**
 * Transient per-window flags, reset at the beginning of the frame. For child window, inherited from parent on first Begin().
 * This is going to be exposed in imgui.h when stabilized enough.
 */
public final class ImGuiItemFlags {
    private ImGuiItemFlags() {
    }

    /**
     * (Default)
     *
     * <p>Definition: {@code i}
     */
    public static final int None = 0;

    /**
     * false    // Disable keyboard tabbing. This is a "lighter" version of ImGuiItemFlags_NoNav.
     *
     * <p>Definition: {@code DrawLi}
     */
    public static final int NoTabStop = 1;

    /**
     * false    // Disable any form of focusing (keyboard/gamepad directional navigation and SetKeyboardFocusHere() calls).
     *
     * <p>Definition: {@code ve = P}
     */
    public static final int NoNav = 2;

    /**
     * false    // Disable item being a candidate for default focus (e.g. used by title bar items).
     *
     * <p>Definition: {@code curren}
     */
    public static final int NoNavDefaultFocus = 4;

    /**
     * false    // Any button-like behavior will have repeat mode enabled (based on io.KeyRepeatDelay and io.KeyRepeatRate values). Note that you can also call IsItemActive() after any button to tell if it is being held.
     *
     * <p>Definition: {@code off_mi}
     */
    public static final int ButtonRepeat = 8;

    /**
     * true     // MenuItem()/Selectable() automatically close their parent popup window.
     *
     * <p>Definition: {@code s(Work}
     */
    public static final int AutoClosePopups = 16;
}
