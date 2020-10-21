package io.ayrking.vitbox.files;

import java.io.File;

import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

import io.ayrking.vitbox.plugin.VitBoxConfig;

/**
 * Little interface for path string manipulation
 * @author Meltwin
 * @since 1.0.0
 */
public interface PathUtils {
    static String constructRecursDirPath(String... dirs) {
        StringBuilder bld = new StringBuilder();
        bld.append(".");
        for (String directory : dirs)
            bld.append('/'+directory);
        return bld.toString();
    }

    static boolean chestNameExist(final @NotNull String name) {
        return (new File(VitBoxConfig.CHEST_DATA_FOLDER+"/"+name+".yml")).exists();
    }
    static String getFormattedChestName(final @NotNull Location loc) {
        StringBuilder bd = new StringBuilder();
        bd.append(loc.getWorld().getName()+"_");
        bd.append(loc.getBlockX()+"_");
        bd.append(loc.getBlockY()+"_");
        bd.append(loc.getBlockZ());
        return bd.toString();
    }
}
