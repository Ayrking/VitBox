package io.ayrking.vitbox.arch.loots;

import java.util.ArrayList;

import javax.validation.constraints.Null;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Class storing configured loottable
 * 
 * @author Meltwin
 * @since 1.0.0
 */
public class LootTable {
    
    private final String name;
    private final ArrayList<LootItem> items = new ArrayList<>();

    public LootTable(final @NotNull String tableName) {
        name = tableName;
    }

    // GETTERS
    public final String getLootTableName() {return name;}
    public final LootItem[] getLootItem() {return items.toArray(new LootItem[0]);}

    // Setters
    public final void addItem(final @NotNull String itemID, short qty, double proba) {
        items.add(new LootItem(itemID, qty, proba));
    }

    /**
     * Put all the probability between 0 & 1.
     * Make the sum of the probability to 1
     */
    public final void reUnitProbabilities() {
        double sum = 0;
        for (LootItem item : items)
            sum += item.getProbability();

        if (sum == 0)
            return;

        for (LootItem item : items)
            item.setProbability(item.getProbability()/sum);
    }
    /**
     * Transform the probability of each item to the sum of the probability of the previous in order to calculate more quickly the item later.
     */
    public final void transformProbaToSum() {
        if (items.isEmpty())
            return;

        double sum = 0;
        for (LootItem item : items) {
            sum += item.getProbability();
            item.setProbability(sum);
        }
    }

    /**
     * Choose randomly a {@link LootItem} of the loot table.
     * Return null if the table is empty.
     */
    @Nullable public final LootItem pickOne() {
        if (items.isEmpty())
            return null;

        // TODO: improve via dichotomy
        double rand = Math.random();
        for (int i = items.size()-2; i>=0; i--) {
            if (items.get(i).getProbability()<rand)
                return items.get(i+1);
        }
        return items.get(0);
    }

    /**
     * Return an {@link ItemStack} randomly taken for the table.
     * Can return null if the table is empty.
     */
    @Nullable public final ItemStack getRandomItemStack() {
        LootItem item = pickOne();
        if (item == null)
            return null;
        return new ItemStack(Material.getMaterial(item.getItemID()), item.getQuantity());
    }
    
}
