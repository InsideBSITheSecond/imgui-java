package imgui.extension.testengine.flag;

import imgui.binding.annotation.BindingSource;

@BindingSource
public enum TestVerboseLevel {
    Silent(0),
    Error(1),
    Warning(2),
    Info(3),
    Debug(4),
    Trace(5);

    public final int value;

    TestVerboseLevel(int value) {
        this.value = value;
    }

    public static TestVerboseLevel fromInt(int i) {
        for (TestVerboseLevel level : values()) {
            if (level.value == i) {
                return level;
            }
        }
        throw new IllegalArgumentException("Unknown ImGuiTestVerboseLevel value: " + i);
    }
}
