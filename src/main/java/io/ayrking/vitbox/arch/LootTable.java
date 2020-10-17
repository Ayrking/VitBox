package io.ayrking.vitbox.arch;

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

    
}
