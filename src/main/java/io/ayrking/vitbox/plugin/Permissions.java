package io.ayrking.vitbox.plugin;

import org.jetbrains.annotations.NotNull;

import fr.plum.plumlib.arch.IPermission;

public enum Permissions implements IPermission {
    TABLES("vitbox.table"),
    BOXES("vitbox.box"),
    ITEMS("vitbox.testitem");

    private final String perm;
    private Permissions(final @NotNull String perm) {
        this.perm = perm;
    }
    
    @Override
    public String path() {
        return this.perm;
    }


}
