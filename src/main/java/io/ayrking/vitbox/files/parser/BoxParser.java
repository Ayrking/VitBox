package io.ayrking.vitbox.files.parser;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.NotNull;

import fr.plum.plumlib.chat.config.ChatConfig;
import fr.plum.plumlib.chat.sender.IMessageSender;
import io.ayrking.vitbox.Main;
import io.ayrking.vitbox.arch.LootTable;
import io.ayrking.vitbox.arch.box.LootBox;
import io.ayrking.vitbox.messages.BoxesMessages;
import io.ayrking.vitbox.plugin.VitBoxConfig;

/**
 * Parse chest data yaml files
 */
public class BoxParser extends FileParser implements IMessageSender {

    private final String name;
    private LootBox box;

    public BoxParser(@NotNull Class<?> c, @NotNull String outFile) {
        super(c, VitBoxConfig.CHEST_DATA_FOLDER, outFile, null);
        this.name = outFile;
        loadData();
    }

    private final void loadData() {
        ConfigurationSection cs = this.getConfig().getConfigurationSection("chest");
        if (cs == null) {
            sendMessage(Bukkit.getConsoleSender(), BoxesMessages.lootBoxParseFail(this.fileName));
            return;
        }

        // If is chest configured
        String world = this.getConfig().getString("chest.world");
        double x = this.getConfig().getDouble("chest.x");
        double y = this.getConfig().getDouble("chest.y");
        double z = this.getConfig().getDouble("chest.z");
        LootTable table = Main.getLootTable(this.getConfig().getString("chest.table"));

        if (table == null || world == null) {
            sendMessage(Bukkit.getConsoleSender(), BoxesMessages.lootBoxParseFail(this.fileName));
            return;
        }

        this.box = new LootBox(this.name, new Location(Bukkit.getWorld(world), x, y, z), table);
        this.valid = true;
    }

    public final LootBox getLootBox() {
        return this.box;
    }

    @Override
    public @NotNull ChatConfig getChatConfig() {
        return VitBoxConfig.getInstance();
    }
    
}
