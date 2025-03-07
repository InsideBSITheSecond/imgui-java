package imgui.extension.testengine.flag;

import imgui.binding.annotation.BindingAstEnum;
import imgui.binding.annotation.BindingSource;

@BindingSource(callPtr = "", callOperator = "")
public final class TestRunSpeed {
    private TestRunSpeed(){}

    @BindingAstEnum(file = "ast-imgui_te_engine.json", qualType = "ImGuiTestRunSpeed_")
    public Void __;
}
