package io.ayrking.vitbox.plugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.plum.plumlib.arch.ICmdHandler;
import fr.plum.plumlib.io.yaml.ConfigFile;
import io.ayrking.vitbox.Main;
import io.ayrking.vitbox.arch.loots.LootTable;
/**
 * Command Manager for the Vitteria Lootbox plugin
 * @author Meltwin
 * @since 1.0.0
 */
public final class CommandHandler extends ICmdHandler {

    static final boolean ERROR = false;

    @Override
    public boolean command(CommandSender sender, Command command, String label, String[] args) {
        if (label.equals("vitbox") && args.length>=1) {
            switch(args[0]) {
                case "table":
                    if (args.length>=2)
                        switch (args[1]) {
                            case "new":
                                return instantiateNewTable(sender, args);
                            case "list":
                                return getTableList(sender);
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
        }
        return ERROR;
    }


    /*
        ###########################################
                          TABLES
        ###########################################                 
    */

    private final boolean instantiateNewTable(CommandSender sender, String[] args) {
        if (this._isPlayer(sender) && !((Player) sender).hasPermission(VitBoxConfig.TABLE_PERM) || args.length < 3)
            return ERROR;
        new ConfigFile(this.getClass(), VitBoxConfig.LOOT_TABLE_FOLDER, args[2], "lootTableExample");
        return !ERROR;
    }
    private final boolean getTableList(CommandSender sender) {
        sender.sendMessage(Messages.LIST_HEADER);
        for (LootTable table : Main.LOOT_TABLES)
            sender.sendMessage(Messages.listItem(table.getLootTableName()));
        sender.sendMessage(Messages.LIST_FOOTER);
        return !ERROR;
    }
    
}
