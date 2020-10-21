package io.ayrking.vitbox.messages;

import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

/**
 * Messages about the iteminfo command
 * 
 * @author Meltwin
 * @since 1.0.0
 */
public final class ItemsMessages {
    
    private ItemsMessages() {}

    // #########################################################################
    // =========================================================================
    // 
    //                              Iteminfo CMD
    // 
    // =========================================================================
    // #########################################################################
    public static final String ITEM_INFO_HEADER = Messages.upHeader("Item info");
    public static final String materialInfo(final @NotNull Material mat) {
        return Messages.listElement("Material")+" : "+mat.name();
    }
}
