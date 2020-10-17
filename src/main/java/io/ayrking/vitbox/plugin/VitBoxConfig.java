package io.ayrking.vitbox.plugin;

import javax.ejb.Singleton;

import org.bukkit.ChatColor;

import fr.plum.plumlib.chat.config.PluginConfig;
import io.ayrking.vitbox.files.PathUtils;

/**
 * Chat Config for VitBox plugin
 * @author Meltwin
 * @since 1.0.0
 */
@Singleton
public final class VitBoxConfig extends PluginConfig {

    // Static properties
    public static final String PLUGIN_NAME = "VitBox";
    public static final ChatColor PLUGIN_COLOR = ChatColor.GOLD;
    public static final ChatColor BRACK_COLOR = ChatColor.GREEN;

    // Folders
    public static final String SERVER_PLUGIN_FOLDER = "plugins";
    public static final String PLUGIN_FOLDER = PathUtils.constructRecursDirPath(SERVER_PLUGIN_FOLDER,PLUGIN_NAME);
    public static final String LOOT_TABLE_FOLDER = PathUtils.constructRecursDirPath(SERVER_PLUGIN_FOLDER,PLUGIN_NAME,"loottables");
    public static final String CHEST_DATA_FOLDER = PathUtils.constructRecursDirPath(SERVER_PLUGIN_FOLDER,PLUGIN_NAME,"chestdata");

    // Instances
    protected VitBoxConfig() {super(PLUGIN_NAME, PLUGIN_COLOR, BRACK_COLOR);}
    private static final VitBoxConfig instance = new VitBoxConfig();
    public static final VitBoxConfig getInstance() {return instance;}
    
}
