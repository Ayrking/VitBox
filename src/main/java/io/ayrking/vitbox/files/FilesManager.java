package io.ayrking.vitbox.files;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

import fr.plum.plumlib.chat.config.ChatConfig;
import fr.plum.plumlib.chat.sender.IMessageSender;
import fr.plum.plumlib.io.yaml.ConfigFile;
import io.ayrking.vitbox.Main;
import io.ayrking.vitbox.files.parser.LootTableParser;
import io.ayrking.vitbox.plugin.Messages;
import io.ayrking.vitbox.plugin.VitBoxConfig;

/**
 * Class charge of managing locals configuration files
 * 
 * @author Meltwin
 * @since 1.0.0
 */
public final class FilesManager implements IMessageSender {

    static final FilenameFilter YAML_FILTER = (f, name) -> name.endsWith(".yml");

    /**
     * Load the local files
     */
    public final void init() {
        loadLootTables();
    }

    /**
     * Will load tables in the dir plugins/VitBox/
     */
    private final void loadLootTables() {

        sendMessage(Bukkit.getConsoleSender(), Messages.LOAD_LOOT_TABLE);

        // Calc Path & retrieve yaml files' name list
        File lootTableDir = new File(VitBoxConfig.LOOT_TABLE_FOLDER);
        if (!lootTableDir.exists())
            lootTableDir.mkdirs();

        String[] files = new File(VitBoxConfig.LOOT_TABLE_FOLDER).list(YAML_FILTER);

        if (files.length == 0) {
            new ConfigFile(this.getClass(), VitBoxConfig.LOOT_TABLE_FOLDER, "example", "lootTableExample");
        }

        LootTableParser parser;
        for (String file : files) {
            parser = new LootTableParser(this.getClass(), file);
            if (parser.isValid())
                Main.LOOT_TABLES.add(parser.getLootTable());
        }
    }

    @Override
    public @NotNull ChatConfig getChatConfig() {
        return VitBoxConfig.getInstance();
    }
    
}
