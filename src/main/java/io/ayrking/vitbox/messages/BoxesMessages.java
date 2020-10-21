package io.ayrking.vitbox.messages;

import org.jetbrains.annotations.NotNull;

import net.md_5.bungee.api.ChatColor;

/**
 * Messages about the lootboxes
 * @author Meltwin
 * @since 1.0.0
 */
public abstract class BoxesMessages {

    private BoxesMessages() {}

    // =========================================================================
    // Messages
    // =========================================================================

    public static final String NEW_BOX_FAIL = ChatColor.DARK_RED+"Erreur de synthaxe : /vitbox box new [<name>] <loot table>";
    public static final String BOX_ALREADY_EXIST = "Cette lootbox existe déjà.";
    public static final String NAME_ALREADY_USED = "Ce nom a déjà été utilisé.";
    public static final String boxCreated(final @NotNull String boxName, final @NotNull String tableName) {
        return "La lootbox "+boxName+"["+tableName+"] a été créée.";
    }
    
}
