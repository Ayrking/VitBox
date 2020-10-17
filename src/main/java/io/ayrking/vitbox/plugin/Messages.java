package io.ayrking.vitbox.plugin;

import org.jetbrains.annotations.NotNull;

import net.md_5.bungee.api.ChatColor;
/**
 * Contain all the messages sent by the plugin
 * @author Meltwin
 * @since 1.0.0
 */
public final class Messages {

    private Messages() {}
    
    // LootTable Parser
    public static final String LOAD_LOOT_TABLE = "Loading loot tables.";
    public static final String lootTableParseFail(final @NotNull String filename) {
        return "Can't parse the loot table "+filename+". Make sure it really is one.";
    }
    public static final String lootTableLoaded(final @NotNull String filename) {
        return "Loot table "+filename+" is loaded !";
    }

    // Boxes Parser
    public static final String LOAD_LOOT_BOXES = "Loading loot boxes.";
    public static final String lootBoxParseFail(final @NotNull String filename) {
        return "Can't parse the lootbox "+filename+". Make sure it really is one.";
    }
    public static final String lootBoxLoaded(int loaded, int max) {
        return "Lootbox files loaded : "+Integer.toString(loaded)+" valid / "+Integer.toString(max)+" files in directory.";
    }

    // Command Handler
    public static final String TABLE_CMD_FAIL = ChatColor.RED+"Erreur de synthaxe ! Vouliez vous écrire /vitbox table [new/list] ?";

    // Table Messages
    public static final String LIST_HEADER = ChatColor.DARK_RED+"================================["+ChatColor.YELLOW+"Loot Tables"+ChatColor.DARK_RED+"]================================";
    public static final String LIST_FOOTER = ChatColor.DARK_RED+"============================================================================";
    public static final String listItem(@NotNull String name) {
        return ChatColor.YELLOW+"   - "+name;
    }
}
