package imgui.extension.imguiknobs.flag;


public final class ImGuiKnobFlags {
    private ImGuiKnobFlags() {
    }

    public static final int None = 0;

    /**
     * Definition: {@code 1 << 0}
     */
    public static final int NoTitle = 1;

    /**
     * Definition: {@code 1 << 1}
     */
    public static final int NoInput = 2;

    /**
     * Definition: {@code 1 << 2}
     */
    public static final int ValueTooltip = 4;

    /**
     * Definition: {@code 1 << 3}
     */
    public static final int DragHorizontal = 8;

    /**
     * Definition: {@code 1 << 4}
     */
    public static final int DragVertical = 16;

    /**
     * Definition: {@code 1 << 5}
     */
    public static final int Logarithmic = 32;

    /**
     * Definition: {@code 1 << 6}
     */
    public static final int AlwaysClamp = 64;
}
