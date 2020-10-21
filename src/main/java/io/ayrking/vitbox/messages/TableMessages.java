package io.ayrking.vitbox.messages;

import org.jetbrains.annotations.NotNull;

import net.md_5.bungee.api.ChatColor;

/**
 * Messages used about LootTables
 * @author Meltwin
 * @since 1.0.0
 */
public final class TableMessages {
    
    private TableMessages() {}

    // #########################################################################
    // =========================================================================
    //
    //                                  Colors
    // 
    // =========================================================================
    // #########################################################################
    static final ChatColor DR = ChatColor.DARK_RED;
    static final ChatColor R = ChatColor.RED;

    // #########################################################################
    // =========================================================================
    //
    //                                  Messages
    // 
    // =========================================================================
    // #########################################################################

        // =========================================================================
        // 
        //                                  Loading
        // 
        // =========================================================================
    public static final String LOAD_LOOT_TABLE = "Loading loot tables.";
    public static final String lootTableParseFail(final @NotNull String filename) {
        return "Can't parse the loot table "+filename+". Make sure it really is one.";
    }
    public static final String lootTableLoaded(final @NotNull String filename) {
        return "Loot table "+filename+" is loaded !";
    }

        // =========================================================================
        // 
        //                                  CMD Global
        // 
        // =========================================================================
    public static final String NO_TABLE = DR+"Aucune table n'existe avec ce nom";
    public static final String TABLE_CMD_FAIL = R+"Erreur de synthaxe : /vitbox table [info/list/new/test] ... ";

        // =========================================================================
        // 
        //                                  CMD New
        // 
        // =========================================================================
    public static final String NEW_TABLE_FAIL = R+"Erreur de synthaxe : /vitbox table new <nom>";
    public static final String newTable(final @NotNull String name) {
        return "La table "+name+" a été créée";
    }

        // =========================================================================
        // 
        //                                  CMD Info
        // 
        // =========================================================================
    public static final String INFO_TABLE_HEADER = Messages.upHeader("Info table");
    public static final String INFO_TABLE_FAIL = R+"Erreur de synthaxe : /vitbox table info <loottable>";
    public static final String itemInfo(final @NotNull String item, final double probaSum) {
        return Messages.listElement(item)+" : "+ChatColor.DARK_RED+Double.toString(probaSum);
    }

        // =========================================================================
        // 
        //                                  CMD List
        // 
        // =========================================================================
    public static final String LIST_HEADER = Messages.upHeader("Loot Tables");

        // =========================================================================
        // 
        //                                  CMD Test
        // 
        // =========================================================================
    public static final String TEST_TABLE_FAIL = R+"Erreur de synthaxe : /vitbox table test <loottable>";
    public static final String testTable(final @NotNull String table) {
        return "Vous testez la table "+table+".";
    }
}
