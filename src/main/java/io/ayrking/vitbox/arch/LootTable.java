package io.ayrking.vitbox.arch;

import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import io.ayrking.vitbox.arch.loots.LootElement;
import io.ayrking.vitbox.arch.loots.LootItem;
import io.ayrking.vitbox.arch.price.PriceElement;
import io.ayrking.vitbox.arch.price.PriceItem;

/**
 * Class storing configured loottable
 * 
 * @author Meltwin
 * @since 1.0.0
 */
public class LootTable {
    
    private final String name;
    private final ArrayList<LootElement> items = new ArrayList<>();
    private final ArrayList<PriceElement> prices = new ArrayList<>();

    public LootTable(final @NotNull String tableName) {
        name = tableName;
    }

    // =========================================================================
    // Lists management
    // =========================================================================

    // GETTERS
    public final String getName() {return name;}
    public final LootElement[] getLoots() {return items.toArray(new LootElement[0]);}
    public final PriceElement[] getPrice() {return prices.toArray(new PriceElement[0]);}


    /**
     * Add an item to the lootable item list
     */
    public final void addLootItem(final @NotNull String itemID, short qty, double proba) {
        items.add(new LootItem(itemID, qty, proba));
    }

    // Prices
    public final void addPrice(final @NotNull PriceElement price) {
        prices.add(price);
    }
    public final void addPriceItem(final @NotNull String itemID, short qty) {
        prices.add(new PriceItem(itemID, qty));
    }

    // =========================================================================
    // Calc Methods
    // =========================================================================

    /**
     * Put all the probability between 0 & 1.
     * Make the sum of the probability up to 1
     */
    public final void reUnitProbabilities() {
        double sum = 0;
        for (LootElement item : items)
            sum += item.getProbability();

        if (sum == 0)
            return;

        for (LootElement item : items)
            item.setProbability(item.getProbability()/sum);
    }
    /**
     * Transform the probability of each item to the sum of the probability of the previous in order to calculate more quickly the item later.
     */
    public final void transformProbaToSum() {
        if (items.isEmpty())
            return;

        double sum = 0;
        for (LootElement item : items) {
            sum += item.getProbability();
            item.setProbability(sum);
        }
    }

    // =========================================================================
    // Random pick up
    // =========================================================================

    /**
     * Choose randomly a {@link LootElement} of the loot table.
     * Return null if the table is empty.
     */
    @Nullable public final LootElement pickOne() {
        if (items.isEmpty())
            return null;

        // TODO: improve via dichotomy ?
        double rand = Math.random();
        for (int i = items.size()-2; i>=0; i--) {
            if (items.get(i).getProbability()<rand)
                return items.get(i+1);
        }
        return items.get(0);
    }

    // =========================================================================
    // Dynamic
    // =========================================================================
    public final boolean playerCanOpen(final @NotNull Player player) {
        boolean b = false;
        for (PriceElement price : prices) {
            b = b || price.playerHas(player);
            if (b) return true;
        }
        return b;
    }
    
}
