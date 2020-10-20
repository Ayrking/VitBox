package io.ayrking.vitbox.arch.price;

import java.util.Map;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class PriceItem extends PriceElement {
    final Material mat;
    final String itemID;
    final short qty;

    public PriceItem(final @NotNull String itemID, final short qty) {
        this.itemID = itemID;
        this.mat = Material.getMaterial(this.itemID);
        this.qty = qty;
    }

    @Override
    public boolean playerHas(@NotNull Player player) {
        return player.getInventory().contains(mat, qty);
    }

    /**
     * Remove the cost in item to the player inventory
     */
    @Override
    public void applyToPlayer(@NotNull Player player) {
        short toRm = this.qty;
        for (Map.Entry<Integer,? extends ItemStack> entry : player.getInventory().all(mat).entrySet()) {
            if (toRm > entry.getValue().getAmount()) {
                toRm -= entry.getValue().getAmount();
                player.getInventory().clear(entry.getKey());
            }
            else {
                entry.getValue().setAmount(entry.getValue().getAmount()-toRm);
                break;
            }
        }
    }
}
