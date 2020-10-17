package io.ayrking.vitbox;

import java.util.ArrayList;

import org.jetbrains.annotations.NotNull;

import fr.plum.plumlib.arch.PlumPlugin;
import fr.plum.plumlib.chat.config.ChatConfig;
import io.ayrking.vitbox.arch.LootTable;
import io.ayrking.vitbox.files.FilesManager;
import io.ayrking.vitbox.plugin.VitBoxConfig;

public class Main extends PlumPlugin {

    static final FilesManager FILES = new FilesManager();
    public static final ArrayList<LootTable> LOOT_TABLES = new ArrayList<>();

    @Override
    public void onEnable() {
        super.onEnable();

        // Initialisation of the plugin
        sendInitMsg();
        FILES.init(); // Load local files
    }


    @Override
    public @NotNull ChatConfig getChatConfig() {
        return VitBoxConfig.getInstance();
    }
}
