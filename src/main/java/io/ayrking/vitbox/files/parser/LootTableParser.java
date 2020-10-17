package io.ayrking.vitbox.files.parser;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.NotNull;

import fr.plum.plumlib.chat.config.ChatConfig;
import fr.plum.plumlib.chat.sender.IMessageSender;
import io.ayrking.vitbox.arch.LootTable;
import io.ayrking.vitbox.plugin.Messages;
import io.ayrking.vitbox.plugin.VitBoxConfig;

/**
 * Parser for the plugin's LootTable YAML files
 * 
 * @author Meltwin
 * @since 1.0.0
 */
public final class LootTableParser extends FileParser implements IMessageSender {

    private LootTable table;
    private final String tableName;

    public LootTableParser(final @NotNull Class<?> c, final @NotNull String file) {
        super(c, VitBoxConfig.LOOT_TABLE_FOLDER, file, null);
        this.tableName = file;
        loadData();
    }

    // Getters

    /**
     * Parse the data in the file
     */
    private void loadData() {
        ConfigurationSection cs = this.getConfig().getConfigurationSection("loots");
        if (cs == null) {
            sendMessage(Bukkit.getConsoleSender(), Messages.lootTableParseFail(this.tableName));
            return;
        }

        // If loot path exist => extract the loots
        this.table = new LootTable(this.tableName);

        for (String child : cs.getKeys(false)) // Add Items
            this.table.addItem(child, this.getConfig().getDouble("loots." + child));

        sendMessage(Bukkit.getConsoleSender(), Messages.lootTableLoaded(this.tableName));
        this.valid = true;
    }

    public final LootTable getLootTable() {return this.table;}

    @Override
    public @NotNull ChatConfig getChatConfig() {
        return VitBoxConfig.getInstance();
    }
}
