package io.ayrking.vitbox.arch.loots;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import io.ayrking.vitbox.plugin.Messages;

/**
 * Simple class for storing a lootable item
 * 
 * @author Meltwin
 * @since 1.0.0
 */
public class LootItem extends LootElement {

    // =========================================================================
    // DATA
    // =========================================================================
    private short quantity;
    private final String itemID;

    // =========================================================================
    // Super
    // =========================================================================

    public LootItem(final @NotNull String itemID, short qty, double proba) {
        super(proba);
        this.itemID = itemID;
        this.quantity = qty;
    }

    // =========================================================================
    // Getters
    // =========================================================================

    @NotNull
    public final String getItemID() {
        return this.itemID;
    }

    public final short getQuantity() {
        return this.quantity;
    }

    // =========================================================================
    // Applicator
    // =========================================================================

    @Override
    public void applyToPlayer(@NotNull Player player) {
        Material mat = Material.getMaterial(this.itemID);
        // TODO : send message "no material by this name"
        if (mat == null)
            return;
        player.getInventory().addItem(new ItemStack(mat, this.quantity));
    }

    @Override
    public String lootInfo() {
        return Messages.itemInfo(this.itemID, this.proba);
    }
}
