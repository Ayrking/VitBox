package io.ayrking.vitbox.arch.loots;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * Parent class for loots elements
 * 
 * @author Meltwin
 * @since 1.0.0
 */
public abstract class LootElement {

    // =============================================================================
    // DATA
    // =============================================================================
    protected double proba;

    // =========================================================================
    // Super
    // =========================================================================

    public LootElement(double probability) {
        this.proba = probability;
    }

    // =========================================================================
    // Getters
    // =========================================================================
    /**
     * Get the probability of obtaining this element
     */
    public final double getProbability() {return this.proba;}
    /**
     * Set the new probability for the element
     */
    public final void setProbability(double newProba) {this.proba = newProba;}

    // =========================================================================
    // Methods
    // =========================================================================
    public abstract void applyToPlayer(final @NotNull Player player);
    public abstract String lootInfo();
}
