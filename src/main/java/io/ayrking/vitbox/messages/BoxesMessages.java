package io.ayrking.vitbox.messages;

import org.jetbrains.annotations.NotNull;

import net.md_5.bungee.api.ChatColor;

/**
 * Messages about the lootboxes
 * @author Meltwin
 * @since 1.0.0
 */
public final class BoxesMessages {

    private BoxesMessages() {}

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
    public static final String LOAD_LOOT_BOXES = "Loading loot boxes.";
    public static final String lootBoxParseFail(final @NotNull String filename) {
        return "Can't parse the lootbox "+filename+". Make sure it really is one.";
    }
    public static final String lootBoxLoaded(int loaded, int max) {
        return "Lootbox files loaded : "+Integer.toString(loaded)+" valid / "+Integer.toString(max)+" files in directory.";
    }

        // =========================================================================
        // 
        //                                  Box Opening
        // 
        // =========================================================================
    public static final String CANT_AFFORD = DR+"Vous n'avez pas de quoi ouvrir cette boite.";
    public static final String boxOpen(final @NotNull String tableName) {
        return "Vous ouvrez une lootbox "+tableName+".";
    }

        // =====================================================================
        // 
        //                                  CMD Global
        // 
        // =====================================================================
    public static final String BOX_CMD_FAIL = R+"Erreur de synthaxe : /vitbox box [list/new/remove] ...";

        // =====================================================================
        // 
        //                                  CMD New
        // 
        // =====================================================================
    public static final String NEW_BOX_FAIL = ChatColor.DARK_RED+"Erreur de synthaxe : /vitbox box new [<name>] <loot table>";
    public static final String BOX_ALREADY_EXIST = "Cette lootbox existe déjà.";
    public static final String NAME_ALREADY_USED = "Ce nom a déjà été utilisé.";
    public static final String boxCreated(final @NotNull String boxName, final @NotNull String tableName) {
        return "La lootbox "+boxName+"["+tableName+"] a été créée.";
    }
    
}
