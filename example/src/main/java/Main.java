import imgui.ImFontConfig;
import imgui.ImFontGlyphRangesBuilder;
import imgui.ImGui;
import imgui.ImGuiIO;
import imgui.app.Application;
import imgui.app.Configuration;
import imgui.extension.testengine.TestContext;
import imgui.extension.testengine.TestEngine;
import imgui.extension.testengine.TestEngineIO;
import imgui.extension.testengine.callback.TestEngineGuiFun;
import imgui.extension.testengine.callback.TestEngineTestFun;
import imgui.extension.testengine.flag.TestVerboseLevel;
import imgui.flag.ImGuiConfigFlags;
import imgui.flag.ImGuiInputTextFlags;
import imgui.flag.ImGuiWindowFlags;
import imgui.type.ImBoolean;
import imgui.type.ImInt;
import imgui.type.ImString;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main extends Application {
    private final ImString str = new ImString(5);
    private final float[] flt = new float[1];
    private int count = 0;

    @Override
    protected void configure(final Configuration config) {
        config.setTitle("Example Application");
    }

    @Override
    protected void initImGui(final Configuration config) {
        super.initImGui(config);

        final ImGuiIO io = ImGui.getIO();
        io.setIniFilename(null);                                // We don't want to save .ini file
        io.addConfigFlags(ImGuiConfigFlags.NavEnableKeyboard);  // Enable Keyboard Controls
        io.addConfigFlags(ImGuiConfigFlags.DockingEnable);      // Enable Docking
        io.addConfigFlags(ImGuiConfigFlags.ViewportsEnable);    // Enable Multi-Viewport / Platform Windows
        io.setConfigViewportsNoTaskBarIcon(true);

        initFonts(io);

        final TestEngineIO testIo = testEngine.getIO();
        //final TestEngineIO testIo = ImTestEngine.GetIO(testEngine);
        System.out.println(testIo.getConfigVerboseLevel());
        testIo.setConfigVerboseLevel(TestVerboseLevel.Info);
        System.out.println(testIo.getConfigVerboseLevel());

        System.out.println(testIo.getConfigVerboseLevelOnError());
        testIo.setConfigVerboseLevelOnError(TestVerboseLevel.Debug);
        System.out.println(testIo.getConfigVerboseLevelOnError());

        System.out.println(Long.toHexString(testEngine.ptr));
        TestEngine.Start(testEngine, ImGui.getCurrentContext());

        final ImBoolean b = new ImBoolean(false);
        testEngine.registerTest("demo_tests", "test1", new TestEngineGuiFun() {
            @Override
            public void run(TestContext ctx) {
                // GUI Function
                ImGui.begin("Test Window", null, ImGuiWindowFlags.NoSavedSettings);
                ImGui.text("Hello, automation world");
                ImGui.button("Click Me");
                if (ImGui.treeNode("Node")) {
                    ImGui.checkbox("Checkbox", b);
                    ImGui.treePop();
                }
                ImGui.end();
            }
        }, new TestEngineTestFun() {
            @Override
            public void run(TestContext ctx) {
                // Test Function
                ctx.setRef("Test Window");
                ctx.itemClick("Click Me");
                ctx.itemOpen("Node");
                ctx.itemCheck("Node/Checkbox");
                ctx.itemUncheck("Node/Checkbox");
            }
        } );

        final int[] myInt = {42};
        testEngine.registerTest("demo_tests", "test2", new TestEngineGuiFun() {
            @Override
            public void run(TestContext ctx) {
                ImGui.begin("Test Window", null, ImGuiWindowFlags.NoSavedSettings);
                ImGui.sliderInt("Slider", myInt, 0, 1000);
            }
        }, new TestEngineTestFun() {
            @Override
            public void run(TestContext ctx) {
                ctx.setRef("Test Window");

                TestEngine.checkEq(myInt[0], 42);
                ctx.itemInputValue("Slider", 123);
                TestEngine.checkEq(myInt[0], 123);
            }
        } );

        testEngine.registerTest("demo_tests", "open_metrics", new TestEngineGuiFun() {
            @Override
            public void run(TestContext ctx) {

            }
        }, new TestEngineTestFun() {
            @Override
            public void run(TestContext ctx) {
                ctx.setRef("Dear ImGui Demo");
                ctx.menuCheck("Tools/Metrics\\/Debugger");
            }
        } );

        testEngine.registerTest("demo_tests", "capture_screenshots", new TestEngineGuiFun() {
            @Override
            public void run(TestContext ctx) {

            }
        }, new TestEngineTestFun() {
            @Override
            public void run(TestContext ctx) {
                TestEngine.checkEq(42, 69);
            }
        } );

        testEngine.registerTest("demo_tests", "capture_video", new TestEngineGuiFun() {
            @Override
            public void run(TestContext ctx) {

            }
        }, new TestEngineTestFun() {
            @Override
            public void run(TestContext ctx) {
                TestEngine.checkEq(69, 42);
            }
        });

        //TestEngine.InstallDefaultCrashHandler();
    }

    /**
     * Example of fonts configuration
     * For more information read: https://github.com/ocornut/imgui/blob/33cdbe97b8fd233c6c12ca216e76398c2e89b0d8/docs/FONTS.md
     */
    private void initFonts(final ImGuiIO io) {
        // This enables FreeType font renderer, which is disabled by default.
        io.getFonts().setFreeTypeRenderer(true);

        // Add default font for latin glyphs
        io.getFonts().addFontDefault();

        // You can use the ImFontGlyphRangesBuilder helper to create glyph ranges based on text input.
        // For example: for a game where your script is known, if you can feed your entire script to it (using addText) and only build the characters the game needs.
        // Here we are using it just to combine all required glyphs in one place
        final ImFontGlyphRangesBuilder rangesBuilder = new ImFontGlyphRangesBuilder(); // Glyphs ranges provide
        rangesBuilder.addRanges(io.getFonts().getGlyphRangesDefault());
        rangesBuilder.addRanges(io.getFonts().getGlyphRangesCyrillic());
        rangesBuilder.addRanges(io.getFonts().getGlyphRangesJapanese());
        rangesBuilder.addRanges(FontAwesomeIcons._IconRange);

        // Font config for additional fonts
        // This is a natively allocated struct so don't forget to call destroy after atlas is built
        final ImFontConfig fontConfig = new ImFontConfig();
        fontConfig.setMergeMode(true);  // Enable merge mode to merge cyrillic, japanese and icons with default font

        final short[] glyphRanges = rangesBuilder.buildRanges();
        io.getFonts().addFontFromMemoryTTF(loadFromResources("Tahoma.ttf"), 14, fontConfig, glyphRanges); // cyrillic glyphs
        io.getFonts().addFontFromMemoryTTF(loadFromResources("NotoSansCJKjp-Medium.otf"), 14, fontConfig, glyphRanges); // japanese glyphs
        io.getFonts().addFontFromMemoryTTF(loadFromResources("fa-regular-400.ttf"), 14, fontConfig, glyphRanges); // font awesome
        io.getFonts().addFontFromMemoryTTF(loadFromResources("fa-solid-900.ttf"), 14, fontConfig, glyphRanges); // font awesome
        io.getFonts().build();

        fontConfig.destroy();
    }

    @Override
    public void process() {
        if (ImGui.begin("Demo", ImGuiWindowFlags.AlwaysAutoResize)) {
            ImGui.text("OS: [" + System.getProperty("os.name") + "] Arch: [" + System.getProperty("os.arch") + "]");
            ImGui.text("Hello, World! " + FontAwesomeIcons.Smile); // :)

            if (ImGui.button(FontAwesomeIcons.Save + " Save")) {
                count++; } ImGui.sameLine();

            if (ImGui.button("GC now")) {
                System.gc(); }

            ImGui.sameLine();
            ImGui.text(String.valueOf(count));
            ImGui.inputText("string", str, ImGuiInputTextFlags.CallbackResize);
            ImGui.text("Result: " + str.get());
            ImGui.sliderFloat("float", flt, 0, 1);
            ImGui.separator();
            ImGui.text("Extra");
            Extra.show(this);
        }
        ImGui.end();
    }

    private static byte[] loadFromResources(String name) {
        try {
            return Files.readAllBytes(Paths.get(Main.class.getResource(name).toURI()));
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(final String[] args) {
        launch(new Main());
        System.exit(0);
    }
}
