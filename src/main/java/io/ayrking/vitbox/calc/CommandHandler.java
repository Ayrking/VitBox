package io.ayrking.vitbox.calc;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import fr.plum.plumlib.arch.ICmdHandler;
import fr.plum.plumlib.chat.config.ChatConfig;
import fr.plum.plumlib.chat.sender.IMessageSender;
import fr.plum.plumlib.io.yaml.ConfigFile;
import io.ayrking.vitbox.Main;
import io.ayrking.vitbox.arch.LootTable;
import io.ayrking.vitbox.arch.loots.LootElement;
import io.ayrking.vitbox.plugin.Messages;
import io.ayrking.vitbox.plugin.Permissions;
import io.ayrking.vitbox.plugin.VitBoxConfig;

/**
 * Command Manager for the Vitteria Lootbox plugin
 * 
 * @author Meltwin
 * @since 1.0.0
 */
public final class CommandHandler extends ICmdHandler implements IMessageSender {

    static final boolean ERROR = false;

    // =========================================================================
    // Controllers
    // =========================================================================

    @Override
    public boolean command(final @NotNull CommandSender sender, final @NotNull Command command, final @NotNull String label, final @NotNull String[] args) {
        switch (label) {
            case "vitbox":
                return vitbox(sender, args);
            case "testitem":
                return testItem(sender);
            default:
                return ERROR;
        }
    }
    
    /**
     * Conductor of vitbox command
     */
    private final boolean vitbox(final @NotNull CommandSender sender, final @NotNull String[] args) {
        if (args.length>=1) {
            switch (args[0]) {
                case "table":
                    return vitboxTable(sender, args);
                case "box":
                    break;
                default:
                    break;
            }
        }
        // TODO : error message
        return ERROR;
    }
    private final boolean vitboxTable(final @NotNull CommandSender sender, final @NotNull String[] args) {
        if (args.length>=2) {
            switch (args[1]) {
                case "new":
                    return newLootTable(sender, args);
                case "list":
                    return listLootTable(sender);
                case "test":
                    return testLootTable(sender, args);
                case "info":
                    return infoLootTable(sender, args);
                default:
                    break;
            }
        }
        sender.sendMessage(Messages.TABLE_CMD_FAIL);
        return ERROR; 
    }

    // =========================================================================
    // TABLES
    // =========================================================================

    private final boolean newLootTable(final @NotNull CommandSender sender, final @NotNull String[] args) {
        if (!sender.hasPermission(Permissions.TABLES.toString()) || args.length < 3)
            return ERROR;
        new ConfigFile(this.getClass(), VitBoxConfig.LOOT_TABLE_FOLDER, args[2], "lootTableExample");
        return !ERROR;
    }

    private final boolean listLootTable(final @NotNull CommandSender sender) {
        if (!sender.hasPermission(Permissions.TABLES.toString()))
            return ERROR;

        sender.sendMessage(Messages.LIST_HEADER);
        for (LootTable table : Main.LOOT_TABLES)
            sender.sendMessage(Messages.listElement(table.getName()));
        sender.sendMessage(Messages.DOWN_HEADER);
        return !ERROR;
    }

    private final boolean testLootTable(final @NotNull CommandSender sender, final @NotNull String[] args) {
        if (args.length<3 || !(sender instanceof Player))
            return ERROR;
        LootTable table = Main.getLootTable(args[2]);
        if (table == null)
            return ERROR;
        VitBoxListener.playerInteractedStatic((Player) sender, table, true);
        return !ERROR;
    }
    
    private final boolean infoLootTable(final @NotNull CommandSender sender, final @NotNull String[] args) {
        if (args.length<3)
            return ERROR;
        LootTable table = Main.getLootTable(args[2]);
        sender.sendMessage(Messages.upHeader("Table Info"));
        for (LootElement item : table.getLoots())
            sender.sendMessage(item.lootInfo());
        sender.sendMessage(Messages.DOWN_HEADER);
        return !ERROR; 
    }

    // =========================================================================
    // Box
    // =========================================================================

    // =========================================================================
    // Test Item
    // =========================================================================

    private final boolean testItem(final @NotNull CommandSender sender) {
        if (!_isPlayer(sender) || sender.hasPermission(Permissions.ITEMS.toString()))
            return ERROR;
        ItemStack stack = Bukkit.getPlayer(sender.getName()).getInventory().getItemInMainHand();
        sender.sendMessage(Messages.DOWN_HEADER);
        sender.sendMessage("    Material : " + stack.getType().name());
        sender.sendMessage(Messages.DOWN_HEADER);
        return !ERROR;
    }

    // =========================================================================
    // Chat properties
    // =========================================================================

    @Override
    public @NotNull ChatConfig getChatConfig() {
        return VitBoxConfig.getInstance();
    }
    
}
