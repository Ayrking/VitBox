package io.ayrking.vitbox.files.parser;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import fr.plum.plumlib.chat.config.ChatConfig;
import fr.plum.plumlib.chat.sender.IMessageSender;
import io.ayrking.vitbox.arch.LootTable;
import io.ayrking.vitbox.messages.TableMessages;
import io.ayrking.vitbox.plugin.VitBoxConfig;

/**
 * Parser for the plugin's LootTable YAML files
 * 
 * @author Meltwin
 * @since 1.0.0
 */
public final class LootTableParser extends FileParser implements IMessageSender {

    private LootTable table;

    public LootTableParser(final @NotNull Class<?> c, final @NotNull String file) {
        super(c, VitBoxConfig.LOOT_TABLE_FOLDER, file, null);
        loadData();
    }

    // =========================================================================
    // Parser
    // =========================================================================

    private ConfigurationSection cs;

    /**
     * Parse the data in the file
     */
    private void loadData() {
        // Collecting Loots
        cs = this.getConfig().getConfigurationSection("loots");
        if (cs == null) {
            sendMessage(Bukkit.getConsoleSender(), TableMessages.lootTableParseFail(this.fileName));
            return;
        }

        // If loot path exist => extract the loots
        this.table = new LootTable(this.fileName);

        // Extract the loots
        this.extractLootsItem();
        // TODO : Exp, Money, ...

        // Extract the prices
        this.extractPriceItem(); 
        // TODO : Exp, Money, ...       
            
        // Norm the table 
        this.table.reUnitProbabilities();
        this.table.transformProbaToSum();

        // END
        sendMessage(Bukkit.getConsoleSender(), TableMessages.lootTableLoaded(this.fileName));
        this.valid = true;
    }
    /**
     * Extract the possible loots of item 
     */
    private final void extractLootsItem() {
        cs = this.getConfig().getConfigurationSection("loots.items");
        if (cs == null)
            return;

        for (String child : cs.getKeys(false)) {
            short qty = (short) this.getConfig().getInt("loots.items."+child+".amount", 1);
            double proba = this.getConfig().getDouble("loots.items."+ child+".proba");
            this.table.addLootItem(child, qty, proba);
        }
    }
    /**
     * Extract the cost in items
     */
    private final void extractPriceItem() {
        cs = this.getConfig().getConfigurationSection("price.items");
        if (cs == null)
            return;

        for (String child : cs.getKeys(false))
            this.table.addPriceItem(child, (short) this.getConfig().getInt("price.items."+child, 1));
    }

    // =========================================================================
    // Getters
    // =========================================================================
    /**
     * Return the generated LootTable.
     * Can return a null value if the file is invalid (no node loots)
     */
    @Nullable public final LootTable getLootTable() {return this.table;}

    // =========================================================================
    // Utils
    // =========================================================================

    @Override
    public @NotNull ChatConfig getChatConfig() {
        return VitBoxConfig.getInstance();
    }
}
