package io.ayrking.vitbox.plugin;

import org.jetbrains.annotations.NotNull;

public enum Permissions {
    TABLES("vitbox.table"),
    BOXES("vitbox.box"),
    ITEMS("vitbox.testitem");

    private final String perm;
    private Permissions(final @NotNull String perm) {
        this.perm = perm;
    }

    @Override
    public final String toString() {return this.perm;}


}
