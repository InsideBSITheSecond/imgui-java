package imgui.extension.implot.flag;





public class ImPlotColormapScaleFlags {
    private ImPlotColormapScaleFlags() {

    }

    /**
     * default
     *
     * <p>Definition: {@code 0}
     */
    public static final int None = 0;

    /**
     * the colormap axis label will not be displayed
     *
     * <p>Definition: {@code 1 << 0}
     */
    public static final int NoLabel = 1;

    /**
     * render the colormap label and tick labels on the opposite side
     *
     * <p>Definition: {@code 1 << 1}
     */
    public static final int Opposite = 2;

    /**
     * invert the colormap bar and axis scale (this only affects rendering; if you only want to reverse the scale mapping, make scale_min{@code >}scale_max)
     *
     * <p>Definition: {@code 1 << 2}
     */
    public static final int Invert = 4;
}
