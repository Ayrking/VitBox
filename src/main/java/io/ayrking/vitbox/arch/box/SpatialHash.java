package io.ayrking.vitbox.arch.box;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

import fr.plum.plumlib.container.Pair;

/**
 * HashTable based on the position of the box.
 * Divide the search on region
 * @author Meltwin
 * @since 1.0.0
 */
public class SpatialHash {

    // HashMap<region x, HashMap<region z, Lootbox>>
    private HashMap<Integer, HashMap<Integer, ArrayList<LootBox>>> data = new HashMap<>();

    public final void addLootBox(final @NotNull LootBox lootbox) {
        Pair<Integer> reduced = reduceLoc(lootbox.getLoc());

        // Hash on X
        if (!data.containsKey(reduced.get(true))) 
            data.put(reduced.get(true), new HashMap<>());
        
        // Hash on Z
        if (!data.get(reduced.get(true)).containsKey(reduced.get(false)))
            data.get(reduced.get(true)).put(reduced.get(false), new ArrayList<>());
        
        // Add lootbox
        data.get(reduced.get(true)).get(reduced.get(false)).add(lootbox);
    }

    /**
     * Search a lotboox at precise coordonates
     * @return a {@link LootBox} instance or null if none was found
     */
    public final LootBox searchAt(int x, int y, int z) {
        Pair<Integer> pos = new Pair<>(reduce(x),reduce(z));

        // Check if regions contain a lootbox
        if (!data.containsKey(pos.get(true)))
            return null;
        if (!data.get(pos.get(true)).containsKey(pos.get(false)))
            return null;
        
        // Search on box of the region
        for (LootBox box : data.get(pos.get(true)).get(pos.get(false))) {
            Location loc = box.getLoc();
            if (loc.getBlockX() == x && loc.getBlockY() == y && loc.getBlockZ() == z)
                return box;
        }
        return null;
    }

    /**
     * Return a {@link Pair} of the reduce x & z
     */
    private final Pair<Integer> reduceLoc(final Location loc) {
        if (loc == null)
            return new Pair<>(0,0);
        return new Pair<>(reduce(loc.getX()),reduce(loc.getZ()));
    }
    private final int reduce(double toCast) { return (int) toCast/512;}

}
