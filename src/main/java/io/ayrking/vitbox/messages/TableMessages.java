package io.ayrking.vitbox.messages;

import net.md_5.bungee.api.ChatColor;

/**
 * Messages used about LootTables
 * @author Meltwin
 * @since 1.0.0
 */
public abstract class TableMessages {
    
    private TableMessages() {}

    // =========================================================================
    // Colors
    // =========================================================================
    static final ChatColor DR = ChatColor.DARK_RED;

    
    // =========================================================================
    // Messages
    // =========================================================================

    public static final String NO_TABLE = DR+"Aucune table n'existe avec ce nom";
}
