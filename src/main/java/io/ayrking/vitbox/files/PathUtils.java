package io.ayrking.vitbox.files;

/**
 * Little interface for path string manipulation
 * @author Meltwin
 * @since 1.0.0
 */
public interface PathUtils {
    static String constructRecursDirPath(String... dirs) {
        StringBuilder bld = new StringBuilder();
        for (String directory : dirs)
            bld.append('/'+directory);
        return bld.toString();
    }
}
