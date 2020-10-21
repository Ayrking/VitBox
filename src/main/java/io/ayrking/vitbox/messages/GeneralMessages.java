package io.ayrking.vitbox.messages;

import net.md_5.bungee.api.ChatColor;

/**
 * General Messages used across the plugin
 * @author Meltwin
 * @since 1.0.0
 */
public abstract class GeneralMessages {

    private GeneralMessages() {}

    // =========================================================================
    // Colors
    // =========================================================================
    static final ChatColor DR = ChatColor.DARK_RED;
    static final ChatColor R = ChatColor.RED;


    // =========================================================================
    // Messages
    // =========================================================================

    public static final String PLAYER_ONLY = DR+"Seul les joueurs ont accès à cette commande.";
    public static final String NO_PERM = DR+"Vous n'avez pas la permission d'effectuer cette commande.";

    public static final String VITBOX_CMD_ERROR = R+"Erreur de synthaxe : /vitbox [box/table] ... ";

}
