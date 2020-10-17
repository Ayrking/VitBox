package io.ayrking.vitbox.arch.loots;

import java.util.ArrayList;

import org.jetbrains.annotations.NotNull;

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
    public final void addItem(final @NotNull String itemID, double proba) {
        items.add(new LootItem(itemID, proba));
    }

    /**
     * Put all the probability between 0 & 1
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

    
}
