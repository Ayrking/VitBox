package io.ayrking.vitbox.arch.loots;

import org.jetbrains.annotations.NotNull;

/**
 * Simple class for storing a loottable item
 * 
 * @author Meltwin
 * @since 1.0.0
 */
public class LootItem {

    private final String itemID;
    private double proba;
    
    public LootItem(final @NotNull String itemID, double proba) {
        this.itemID = itemID;
        this.proba=  proba;
    }

    // Getters
    @NotNull
    public final String getItemID() {return this.itemID;}
    public final double getProbability() {return this.proba;}
    public final void setProbability(double newProba) {this.proba = newProba;}
}
