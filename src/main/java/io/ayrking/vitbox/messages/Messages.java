package io.ayrking.vitbox.messages;

import org.jetbrains.annotations.NotNull;

import net.md_5.bungee.api.ChatColor;
/**
 * Class for generating utils for the messages
 * @author Meltwin
 * @since 1.0.0
 */
public final class Messages {

    private Messages() {}

    // #########################################################################
    // =========================================================================
    // 
    //                              HEADERS
    // 
    // =========================================================================
    // #########################################################################

    public static final byte HEADER_LENGTH = 53;
    public static final String LIST_HEADER = upHeader("Loot Tables");
    public static final String DOWN_HEADER = equalLine(HEADER_LENGTH);

    public static final String upHeader(final @NotNull String title) {
        StringBuilder bd = new StringBuilder(ChatColor.DARK_RED+"");
        float m = (HEADER_LENGTH-title.length()-2)/2f;
        bd.append(equalLine((int) Math.floor(m)));
        bd.append("["+ChatColor.GOLD+title+ChatColor.DARK_RED+"]");
        bd.append(equalLine((int) Math.ceil(m)));
        return bd.toString();
    }
    static final String equalLine(int size) {
        StringBuilder bd = new StringBuilder(ChatColor.DARK_RED+"");
        for (byte i = 0; i<size/2; i++)
            bd.append("==");
        if (size%2 == 1)
            bd.append('=');
        return bd.toString();
    }
    public static final String listElement(@NotNull String element) {
        return ChatColor.YELLOW+"   - "+element;
    }
}
