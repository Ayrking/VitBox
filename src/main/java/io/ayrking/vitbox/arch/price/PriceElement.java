package io.ayrking.vitbox.arch.price;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * Base parent class for price elements (item, exp ...)
 * 
 * @author Meltwin
 * @since 1.0.0
 */
public abstract class PriceElement {
    public abstract boolean playerHas(final @NotNull Player player);
    public abstract void applyToPlayer(final @NotNull Player player);
}
