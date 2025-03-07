package imgui.extension.testengine.flag;

import imgui.binding.annotation.BindingAstEnum;
import imgui.binding.annotation.BindingSource;

@BindingSource
public class TestOpFlags {
    private TestOpFlags(){}

    @BindingAstEnum(file = "ast-imgui_te_engine.json", qualType = "ImGuiTestOpFlags_")
    public Void __;
}
