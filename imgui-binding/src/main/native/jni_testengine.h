#pragma once

#include "jni.h"
#include "imgui_te_context.h"
#include "imgui_te_coroutine.h"
#include "imgui_te_engine.h"
#include "imgui_te_exporters.h"
#include "imgui_te_imconfig.h"
#include "imgui_te_perftool.h"
#include "imgui_te_ui.h"
#include "imgui_te_utils.h"
#include "imgui_te_internal.h"

namespace Jni
{
    void RegisterTest(JNIEnv* env, ImGuiTestEngine* e, char* category, char* name, ImGuiTestGuiFunc* guiFunc, ImGuiTestTestFunc* testFunc);
}
