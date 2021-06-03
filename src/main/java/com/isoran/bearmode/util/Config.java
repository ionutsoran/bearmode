package com.isoran.bearmode.util;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;

import java.io.File;

public class Config {
    public static ForgeConfigSpec SERVER_CONFIG;
    public static ForgeConfigSpec CLIENT_CONFIG;

    public static ForgeConfigSpec.IntValue COPPER_GLOW_DURATION;

    static {
        ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();
        ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();

        glowingDuration(SERVER_BUILDER, CLIENT_BUILDER);

        SERVER_CONFIG = SERVER_BUILDER.build();
        CLIENT_CONFIG = CLIENT_BUILDER.build();
    }

    private static void glowingDuration(ForgeConfigSpec.Builder SERVER_BUILDER,
                                        ForgeConfigSpec.Builder CLIENT_BUILDER) {

        COPPER_GLOW_DURATION = CLIENT_BUILDER.comment("How long the glowing last for Coppered Apples (in ticks)")
                .defineInRange("glow_duration", 700, 100, 10000);
    }

    public static void loadConfigFile(ForgeConfigSpec config, String path)
    {
        final CommentedFileConfig file = CommentedFileConfig.builder(new File(path))
                .sync()
                .autosave()
                .writingMode(WritingMode.REPLACE)
                .build();

        file.load();
        config.setConfig(file);
    }
}
