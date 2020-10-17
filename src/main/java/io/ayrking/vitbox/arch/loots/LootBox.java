package io.ayrking.vitbox.arch.loots;

import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
/**
 * Data of the lootbox 
 * @author Meltwin
 * @since 1.0.0
 */
public class LootBox {
    
    final Location loc;
    final LootTable table;

    public LootBox(@NotNull Location loc, @NotNull LootTable table) {
        this.loc = loc;
        this.table = table;
    }

    public final Location getLoc() {return loc;}
    public final String getWorldName() {return loc.getWorld().getName();}
    public final LootTable getLootTable() {return table;}
}
