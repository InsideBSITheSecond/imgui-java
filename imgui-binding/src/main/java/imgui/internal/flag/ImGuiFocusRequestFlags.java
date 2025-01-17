package imgui.internal.flag;

import imgui.binding.annotation.BindingAstEnum;
import imgui.binding.annotation.BindingSource;

@BindingSource
public class ImGuiFocusRequestFlags {
    private ImGuiFocusRequestFlags() {
    }

    @BindingAstEnum(file = "ast-imgui_internal.json", qualType = "ImGuiFocusRequestFlags_")
    public Void ___;
}
