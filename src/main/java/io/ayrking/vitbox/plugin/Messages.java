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

    // Table Messages
    public static final String TABLE_CMD_FAIL = ChatColor.RED+"Erreur de synthaxe ! Vouliez vous écrire /vitbox table [new/list/test/info] ?";
    public static final String LIST_HEADER = upHeader("Loot Tables");
    public static final String testTable(final @NotNull String table) {
        return "Vous ouvrez la table "+table+".";
    }

    public static final String itemInfo(final @NotNull String item, final double start, final double end) {
        return listItem(item)+" : "+ChatColor.DARK_RED+Double.toString(start)+" -> "+Double.toString(end);
    }

    // Player Message
    public static final String boxOpen(final @NotNull String tableName) {
        return "Vous ouvrez une lootbox "+tableName+".";
    }

    // Headers

    public static final byte HEADER_LENGTH = 53;
    public static final String DOWN_HEADER = equalLine(HEADER_LENGTH);

    public static final String upHeader(final @NotNull String title) {
        StringBuilder bd = new StringBuilder(ChatColor.DARK_RED+"");
        float m = (HEADER_LENGTH-title.length()-2)/2f;
        bd.append(equalLine((int) Math.floor(m)));
        bd.append("["+ChatColor.GOLD+title+ChatColor.DARK_RED+"]");
        bd.append(equalLine((int) Math.ceil(m)));
        return bd.toString();
    }

    /*
        #############################################################
        #############################################################
        ####                                                     ####
        ####                        UTILS                        ####
        ####                                                     ####
        #############################################################
        #############################################################
    */
    static final String equalLine(int size) {
        StringBuilder bd = new StringBuilder(ChatColor.DARK_RED+"");
        for (byte i = 0; i<size/2; i++)
            bd.append("==");
        if (size%2 == 1)
            bd.append('=');
        return bd.toString();
    }
    public static final String listItem(@NotNull String name) {
        return ChatColor.YELLOW+"   - "+name;
    }
}
