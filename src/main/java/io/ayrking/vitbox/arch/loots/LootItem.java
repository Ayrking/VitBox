package io.ayrking.vitbox.arch.loots;

import org.jetbrains.annotations.NotNull;

/**
 * Simple class for storing a loottable item
 * 
 * @author Meltwin
 * @since 1.0.0
 */
public class LootItem {

    private short quantity;
    private final String itemID;
    private double proba;
    
    public LootItem(final @NotNull String itemID, short qty, double proba) {
        this.itemID = itemID;
        this.quantity = qty;
        this.proba=  proba;
    }

    // Getters
    @NotNull
    public final String getItemID() {return this.itemID;}
    public final short getQuantity() {return this.quantity;}
    public final double getProbability() {return this.proba;}
    public final void setProbability(double newProba) {this.proba = newProba;}
}
