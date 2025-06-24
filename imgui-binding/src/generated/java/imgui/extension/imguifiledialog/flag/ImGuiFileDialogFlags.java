package imgui.extension.imguifiledialog.flag;





public final class ImGuiFileDialogFlags {
    private ImGuiFileDialogFlags() {
    }

    /**
     * define none default flag
     *
     * <p>Definition: {@code 0}
     */
    public static final int None = 0;

    /**
     * show confirm to overwrite dialog
     *
     * <p>Definition: {@code (1 << 0)}
     */
    public static final int ConfirmOverwrite = 1;

    /**
     * dont show hidden file (file starting with a .)
     *
     * <p>Definition: {@code (1 << 1)}
     */
    public static final int DontShowHiddenFiles = 2;

    /**
     * disable the create directory button
     *
     * <p>Definition: {@code (1 << 2)}
     */
    public static final int DisableCreateDirectoryButton = 4;

    /**
     * hide column file type
     *
     * <p>Definition: {@code (1 << 3)}
     */
    public static final int HideColumnType = 8;

    /**
     * hide column file size
     *
     * <p>Definition: {@code (1 << 4)}
     */
    public static final int HideColumnSize = 16;

    /**
     * hide column file date
     *
     * <p>Definition: {@code (1 << 5)}
     */
    public static final int HideColumnDate = 32;

    /**
     * let the dialog embedded in your own imgui begin / end scope
     *
     * <p>Definition: {@code (1 << 6)}
     */
    public static final int NoDialog = 64;

    /**
     * don't let user type in filename field for file open style dialogs
     *
     * <p>Definition: {@code (1 << 7)}
     */
    public static final int ReadOnlyFileNameField = 128;

    /**
     * the file extentions filtering will not take into account the case
     *
     * <p>Definition: {@code (1 << 8)}
     */
    public static final int CaseInsensitiveExtentionFiltering = 256;

    /**
     * modal
     *
     * <p>Definition: {@code (1 << 9)}
     */
    public static final int Modal = 512;

    /**
     * disable the thumbnail mode
     *
     * <p>Definition: {@code (1 << 10)}
     */
    public static final int DisableThumbnailMode = 1024;

    /**
     * disable the place mode
     *
     * <p>Definition: {@code (1 << 11)}
     */
    public static final int DisablePlaceMode = 2048;

    /**
     * disable the quick path selection
     *
     * <p>Definition: {@code (1 << 12)}
     */
    public static final int DisableQuickPathSelection = 4096;

    /**
     * show the devices selection button
     *
     * <p>Definition: {@code (1 << 13)}
     */
    public static final int ShowDevicesButton = 8192;

    /**
     * enable the antural sorting for filenames and extentions, slower than standard sorting
     *
     * <p>Definition: {@code (1 << 14)}
     */
    public static final int NaturalSorting = 16384;

    /**
     * Definition: {@code ImGuiFileDialogFlags_ConfirmOverwrite |  //
     * ImGuiFileDialogFlags_Modal |             //
     * ImGuiFileDialogFlags_HideColumnType}
     */
    public static final int Default = 521;
}
