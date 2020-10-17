package io.ayrking.vitbox;

import java.util.ArrayList;

import org.jetbrains.annotations.NotNull;

import fr.plum.plumlib.arch.PlumPlugin;
import fr.plum.plumlib.chat.config.ChatConfig;
import io.ayrking.vitbox.arch.box.BoxTable;
import io.ayrking.vitbox.arch.loots.LootTable;
import io.ayrking.vitbox.files.FilesManager;
import io.ayrking.vitbox.plugin.CommandHandler;
import io.ayrking.vitbox.plugin.VitBoxConfig;
/**
 * Plugin for creating lootbox (compatible with modded item)
 * @author Meltwin
 * @since 1.0.0
 */
public class Main extends PlumPlugin {

    static FilesManager FILES = new FilesManager();
    public static ArrayList<LootTable> LOOT_TABLES = new ArrayList<>();
    public static BoxTable BOX = new BoxTable();

    public static final LootTable getLootTable(final @NotNull String name) {
        for (LootTable t : LOOT_TABLES)
            if (t.getLootTableName().equals(name)) return t;
        return null;
    }

    @Override
    public void onEnable() {
        super.onEnable();

        // Initialisation of the plugin
        sendInitMsg();
        FILES.init(); // Load local files
        this.setCommandHandler(new CommandHandler());
    }


    @Override
    public @NotNull ChatConfig getChatConfig() {
        return VitBoxConfig.getInstance();
    }
}
