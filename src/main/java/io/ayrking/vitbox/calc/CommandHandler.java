package io.ayrking.vitbox.calc;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
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
import io.ayrking.vitbox.arch.box.LootBox;
import io.ayrking.vitbox.arch.loots.LootElement;
import io.ayrking.vitbox.files.PathUtils;
import io.ayrking.vitbox.messages.BoxesMessages;
import io.ayrking.vitbox.messages.GeneralMessages;
import io.ayrking.vitbox.messages.ItemsMessages;
import io.ayrking.vitbox.messages.TableMessages;
import io.ayrking.vitbox.messages.Messages;
import io.ayrking.vitbox.plugin.Permissions;
import io.ayrking.vitbox.plugin.VitBoxConfig;
import net.md_5.bungee.api.ChatColor;

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
    public boolean command(final @NotNull CommandSender sender, final @NotNull Command command,
            final @NotNull String label, final @NotNull String[] args) {
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
        if (args.length >= 1) {
            switch (args[0]) {
                case "table":
                    return vitboxTable(sender, args);
                case "box":
                    return vitboxBoxes(sender, args);
                default:
                    break;
            }
        }
        sender.sendMessage(GeneralMessages.VITBOX_CMD_ERROR);
        return !ERROR;
    }

    private final boolean vitboxTable(final @NotNull CommandSender sender, final @NotNull String[] args) {
        if (args.length >= 2) {
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
        sender.sendMessage(TableMessages.TABLE_CMD_FAIL);
        return !ERROR;
    }

    private final boolean vitboxBoxes(final @NotNull CommandSender sender, final @NotNull String[] args) {
        if (args.length >= 2) {
            switch (args[1]) {
                case "new":
                    return newBox(sender, args);
                case "list":
                    break;
                case "remove":
                    break;
                default:
                    break;
            }
        }
        sender.sendMessage(BoxesMessages.BOX_CMD_FAIL);
        return !ERROR;
    }

    // =========================================================================
    // TABLES
    // =========================================================================

    private final boolean newLootTable(final @NotNull CommandSender sender, final @NotNull String[] args) {
        if (!sender.hasPermission(Permissions.TABLES.toString())) {
            sendMessage(sender, GeneralMessages.NO_PERM);
            return !ERROR;
        } 
        if (args.length < 3) {
            sender.sendMessage(TableMessages.NEW_TABLE_FAIL);
            return !ERROR;
        }
            
        new ConfigFile(this.getClass(), VitBoxConfig.LOOT_TABLE_FOLDER, args[2], "lootTableExample");
        sendMessage(sender, TableMessages.newTable(args[2]));
        return !ERROR;
    }

    private final boolean listLootTable(final @NotNull CommandSender sender) {
        if (!sender.hasPermission(Permissions.TABLES.toString())) {
            sendMessage(sender, GeneralMessages.NO_PERM);
            return ERROR;
        }

        sender.sendMessage(Messages.LIST_HEADER);
        for (LootTable table : Main.LOOT_TABLES)
            sender.sendMessage(Messages.listElement(table.getName()));
        sender.sendMessage(Messages.DOWN_HEADER);
        return !ERROR;
    }

    private final boolean testLootTable(final @NotNull CommandSender sender, final @NotNull String[] args) {
        if (!_isPlayer(sender)) {
            sendMessage(sender, GeneralMessages.PLAYER_ONLY);
            return !ERROR;
        }
        if (args.length < 3) {
            sender.sendMessage(TableMessages.TEST_TABLE_FAIL);
            return !ERROR;
        }
        LootTable table = Main.getLootTable(args[2]);
        if (table == null) {
            sendMessage(sender, TableMessages.NO_TABLE);
            return !ERROR;
        }
        VitBoxListener.playerInteractedStatic((Player) sender, table, true);
        return !ERROR;
    }

    private final boolean infoLootTable(final @NotNull CommandSender sender, final @NotNull String[] args) {
        if (args.length < 3) {
            sender.sendMessage(TableMessages.INFO_TABLE_FAIL);
            return !ERROR;
        }
            
        LootTable table = Main.getLootTable(args[2]);
        sender.sendMessage(TableMessages.INFO_TABLE_HEADER);
        for (LootElement item : table.getLoots())
            sender.sendMessage(item.lootInfo());
        sender.sendMessage(Messages.DOWN_HEADER);
        return !ERROR;
    }

    // =========================================================================
    // Boxes
    // =========================================================================
    private final boolean newBox(final @NotNull CommandSender sender, final @NotNull String[] args) {
        // Player only
        if (!_isPlayer(sender)) {
            sender.sendMessage(ChatColor.DARK_RED + GeneralMessages.PLAYER_ONLY);
            return !ERROR;
        }
        // Has Perm
        if (!sender.hasPermission(Permissions.BOXES.toString())) {
            sender.sendMessage(ChatColor.DARK_RED+GeneralMessages.NO_PERM);
            return !ERROR;
        }
        // Need at least 3 args
        if (args.length < 3) {
            sender.sendMessage(ChatColor.DARK_RED + BoxesMessages.NEW_BOX_FAIL);
            return !ERROR;
        }

        // Get the pos of the new box & test if it already exist
        Player player = (Player) sender;
        Location loc = player.getTargetBlock(null, 5).getLocation();
        if (Main.BOX.searchAt(loc.getWorld().getName(), loc.getBlockX(), loc.getBlockY(), loc.getBlockZ()) != null) {
            sendMessage(sender, ChatColor.DARK_RED + BoxesMessages.BOX_ALREADY_EXIST);
            return !ERROR;
        }

        // Choose the name of the box
        String name;
        String loottable;
        if (args.length == 4) {
            if (PathUtils.chestNameExist(args[2])) {
                sendMessage(sender, ChatColor.DARK_RED + BoxesMessages.NAME_ALREADY_USED);
                return !ERROR;
            }
            if (Main.getLootTable(args[3]) == null) {
                sendMessage(sender, ChatColor.DARK_RED + TableMessages.NO_TABLE);
                return !ERROR;
            }
            name = args[2];
            loottable = args[3];
        } else {
            if (Main.getLootTable(args[2]) == null) {
                sendMessage(sender, ChatColor.DARK_RED + TableMessages.NO_TABLE);
                return !ERROR;
            }
            name = PathUtils.getFormattedChestName(loc);
            loottable = args[2];
        }

        // Create the box
        ConfigFile cf = new ConfigFile(this.getClass(), VitBoxConfig.CHEST_DATA_FOLDER, name, null);
        cf.getConfig().set("chest.world", loc.getWorld().getName());
        cf.getConfig().set("chest.x", loc.getBlockX());
        cf.getConfig().set("chest.y", loc.getBlockY());
        cf.getConfig().set("chest.z", loc.getBlockZ());
        cf.getConfig().set("chest.table", loottable);

        try {
            cf.getConfig().save(VitBoxConfig.CHEST_DATA_FOLDER +"/"+ name + ".yml");
        } catch (IOException e) {
            e.printStackTrace();
            return ERROR;
        }
        // Load the box
        LootBox box = new LootBox(name, loc, Main.getLootTable(loottable));
        Main.BOX.addLootBox(box);

        sendMessage(sender, BoxesMessages.boxCreated(name, loottable));
        return !ERROR;
    }

    // =========================================================================
    // Test Item
    // =========================================================================

    private final boolean testItem(final @NotNull CommandSender sender) {
        if (!_isPlayer(sender)) {
            sendMessage(sender, GeneralMessages.PLAYER_ONLY);
            return !ERROR;
        }
        if (!sender.hasPermission(Permissions.ITEMS.toString())) {
            sendMessage(sender, GeneralMessages.NO_PERM);
            return !ERROR;
        }
        ItemStack stack = Bukkit.getPlayer(sender.getName()).getInventory().getItemInMainHand();
        sender.sendMessage(ItemsMessages.ITEM_INFO_HEADER);
        sender.sendMessage(ItemsMessages.materialInfo(stack.getType()));
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
