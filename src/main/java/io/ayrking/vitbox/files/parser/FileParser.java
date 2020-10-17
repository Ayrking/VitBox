package io.ayrking.vitbox.files.parser;

import org.jetbrains.annotations.NotNull;

import fr.plum.plumlib.io.yaml.ConfigFile;

/**
 * Basic Class for loading local yaml files
 * @author Meltwin
 * @since 1.0.0
 */
public class FileParser extends ConfigFile {

    protected boolean valid = false;

    public FileParser(final @NotNull Class<?> c, final @NotNull String configDir, final @NotNull String outFile, final String defaultFile) {
        super(c, configDir, outFile, defaultFile);
    }
    /**
     * Return whether the file was parsed correctly or not
     */
    public final boolean isValid() {return valid;}
}
