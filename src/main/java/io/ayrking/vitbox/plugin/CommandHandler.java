package io.ayrking.vitbox.plugin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import fr.plum.plumlib.arch.ICmdHandler;
import fr.plum.plumlib.chat.config.ChatConfig;
import fr.plum.plumlib.chat.sender.IMessageSender;
import fr.plum.plumlib.io.yaml.ConfigFile;
import io.ayrking.vitbox.Main;
import io.ayrking.vitbox.arch.loots.LootItem;
import io.ayrking.vitbox.arch.loots.LootTable;

/**
 * Command Manager for the Vitteria Lootbox plugin
 * 
 * @author Meltwin
 * @since 1.0.0
 */
public final class CommandHandler extends ICmdHandler implements IMessageSender {

    static final boolean ERROR = false;

    @Override
    public boolean command(final @NotNull CommandSender sender, final @NotNull Command command,
            final @NotNull String label, final @NotNull String[] args) {
        if (label.equals("vitbox") && args.length >= 1) {
            switch (args[0]) {
                case "table":
                    if (args.length >= 2)
                        switch (args[1]) {
                            case "new":
                                return instantiateNewTable(sender, args);
                            case "list":
                                return getTableList(sender);
                            case "test":
                                return testTable(sender, args);
                            case "info":
                                return infoTable(sender, args);
                            default:
                                break;
                        }
                    sender.sendMessage(Messages.TABLE_CMD_FAIL);
                    return !ERROR;
                case "box":
                    break;
                default:
                    return ERROR;
            }
        } else if (label.equals("testitem")) {
            return testItem(sender);
        }
        return ERROR;
    }

    /*
     * ########################################### TABLES
     * ###########################################
     */

    private final boolean instantiateNewTable(final @NotNull CommandSender sender, final @NotNull String[] args) {
        if (!sender.hasPermission(Permissions.TABLES.toString()) || args.length < 3)
            return ERROR;
        new ConfigFile(this.getClass(), VitBoxConfig.LOOT_TABLE_FOLDER, args[2], "lootTableExample");
        return !ERROR;
    }

    private final boolean getTableList(final @NotNull CommandSender sender) {
        if (!sender.hasPermission(Permissions.TABLES.toString()))
            return ERROR;

        sender.sendMessage(Messages.LIST_HEADER);
        for (LootTable table : Main.LOOT_TABLES)
            sender.sendMessage(Messages.listItem(table.getLootTableName()));
        sender.sendMessage(Messages.DOWN_HEADER);
        return !ERROR;
    }

    private final boolean testTable(final @NotNull CommandSender sender, final @NotNull String[] args) {
        if (args.length<3)
            return ERROR;
        sendMessage(sender, Messages.testTable(args[2]));
        LootTable table = Main.getLootTable(args[2]);
        if (table == null)
            return ERROR;
        Bukkit.getPlayer(sender.getName()).getInventory().addItem(table.getRandomItemStack());
        return !ERROR;
    }
    
    private final boolean infoTable(final @NotNull CommandSender sender, final @NotNull String[] args) {
        if (args.length<3)
            return ERROR;
        LootTable table = Main.getLootTable(args[2]);
        sender.sendMessage(Messages.upHeader("Table Info"));
        for (LootItem item : table.getLootItem())
            sender.sendMessage(Messages.itemInfo(item.getItemID(), item.getProbability() ,0));
        sender.sendMessage(Messages.DOWN_HEADER);
        return !ERROR; 
    }

    /*
     * ########################################### 
     *                     Items
     * ###########################################
     */

    private final boolean testItem(final @NotNull CommandSender sender) {
        if (!_isPlayer(sender) || sender.hasPermission(Permissions.ITEMS.toString()))
            return ERROR;
        ItemStack stack = Bukkit.getPlayer(sender.getName()).getInventory().getItemInMainHand();
        sender.sendMessage(Messages.DOWN_HEADER);
        sender.sendMessage("    Material : " + stack.getType().name());
        sender.sendMessage(Messages.DOWN_HEADER);
        return !ERROR;
    }

    @Override
    public @NotNull ChatConfig getChatConfig() {
        return VitBoxConfig.getInstance();
    }
    
}
