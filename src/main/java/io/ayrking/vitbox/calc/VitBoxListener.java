package io.ayrking.vitbox.calc;

import javax.inject.Singleton;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;

import fr.plum.plumlib.chat.config.ChatConfig;
import fr.plum.plumlib.chat.sender.IMessageSender;
import io.ayrking.vitbox.Main;
import io.ayrking.vitbox.arch.LootTable;
import io.ayrking.vitbox.arch.box.LootBox;
import io.ayrking.vitbox.arch.price.PriceElement;
import io.ayrking.vitbox.messages.BoxesMessages;
import io.ayrking.vitbox.plugin.VitBoxConfig;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

/**
 * Listener for box opening
 * 
 * @author Meltwin
 * @since 1.0.0
 */
@Singleton
public class VitBoxListener implements Listener, IMessageSender {

    private static final VitBoxListener instance = new VitBoxListener();
    private VitBoxListener() {}
    public static VitBoxListener getInstance() {return instance;}
    

    // =========================================================================
    // Event Handlers
    // =========================================================================

    @EventHandler
    public void playerInteract(final @NotNull PlayerInteractEvent event) {
        // If player doesn't click on a chest
        if (!event.hasBlock() || event.getAction() != Action.RIGHT_CLICK_BLOCK
                || event.getClickedBlock().getType() != Material.CHEST)
            return;

        // Search for the box
        LootBox box = Main.BOX.searchAt(event.getClickedBlock().getWorld().getName(), event.getClickedBlock().getX(),
                event.getClickedBlock().getY(), event.getClickedBlock().getZ());
        if (box == null || box.getLootTable() == null)
            return;

        event.setCancelled(true);
        // Check if player can afford the box
        if (!box.getLootTable().playerCanOpen(event.getPlayer())) {
            // Actionbar
            event.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR,new TextComponent(BoxesMessages.CANT_AFFORD));
            // Chat
            sendMessage(event.getPlayer(), BoxesMessages.CANT_AFFORD);
            return;
        }

        //Everything is allright, proceding to the opening
        playerInteracted(event.getPlayer(),box.getLootTable(),false);
    }

    // =========================================================================
    // Calculations methods
    // =========================================================================
    /**
     * The player interacted with the lootbox so apply all the change
     * @param player : the player who opened the lootbox
     * @param table  : the table where the loot must be taken
     */
    public static final void playerInteractedStatic(final @NotNull Player player, final @NotNull LootTable table, boolean test) {
        instance.playerInteracted(player, table, test);
    }
    /**
     * The player interacted with the lootbox so apply all the change
     * @param player : the player who opened the lootbox
     * @param table  : the table where the loot must be taken
     * @param test   : true for testing (price will be free)
     */
    public final void playerInteracted(final @NotNull Player player, final @NotNull LootTable table, boolean test) {
        if (!test) {
            for (PriceElement price : table.getPrice())
                price.applyToPlayer(player);
        }
        table.pickOne().applyToPlayer(player);
        
        // Actionbar
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR,new TextComponent(BoxesMessages.boxOpen(table.getName())));
        // Chat
        sendMessage(player, BoxesMessages.boxOpen(table.getName()));
    }

    // =========================================================================
    // Chat Properties
    // =========================================================================
    @Override
    public @NotNull ChatConfig getChatConfig() {
        return VitBoxConfig.getInstance();
    }


}
