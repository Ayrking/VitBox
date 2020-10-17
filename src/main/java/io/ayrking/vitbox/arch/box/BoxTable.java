package io.ayrking.vitbox.arch.box;

import java.util.HashMap;

import org.jetbrains.annotations.NotNull;

import io.ayrking.vitbox.arch.loots.LootBox;

/**
 * Manager of the lootbox
 * @author Meltwin
 * @since 1.0.0
 */
public final class BoxTable {

    private final HashMap<String, SpatialHash> data = new HashMap<>();
    
    /**
     * Register a new lootbox
     */
    public final void addLootBox(final @NotNull LootBox lootbox) {
        if (!data.containsKey(lootbox.getWorldName()))
            data.put(lootbox.getWorldName(), new SpatialHash());
        data.get(lootbox.getWorldName()).addLootBox(lootbox);
    }

    /**
     * Search a lotboox at precise coordonates in a given world
     * @return a {@link LootBox} instance or null if none was found
     */
    public final LootBox searchAt(String wname, double x, double y, double z) {
        if (wname == null || !data.containsKey(wname))
            return null;   
        return data.get(wname).searchAt(x, y, z);
    }
}
