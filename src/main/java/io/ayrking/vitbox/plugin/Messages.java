package io.ayrking.vitbox.plugin;

import org.jetbrains.annotations.NotNull;
/**
 * Contain all the messages sent by the plugin
 * @author Meltwin
 * @since 1.0.0
 */
public final class Messages {

    private Messages() {}
    
    // LootTable Parser
    public static final String LOAD_LOOT_TABLE = "Loading loot tables.";
    public static final String lootTableParseFail(final @NotNull String filename) {
        return "Can't parse the loot table "+filename+". Make sure it really is one.";
    }
    public static final String lootTableLoaded(final @NotNull String filename) {
        return "Loot table "+filename+" is loaded !";
    }

    // Boxes Parser
    public static final String LOAD_LOOT_BOXES = "Loading loot boxes.";
    public static final String lootBoxParseFail(final @NotNull String filename) {
        return "Can't parse the lootbox "+filename+". Make sure it really is one.";
    }
    public static final String lootBoxLoaded(int loaded, int max) {
        return "Lootbox files loaded : "+Integer.toString(loaded)+" valid / "+Integer.toString(max)+" files in directory.";
    }
}
