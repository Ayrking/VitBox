package io.ayrking.vitbox.plugin;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import fr.plum.plumlib.chat.config.ChatConfig;
import fr.plum.plumlib.chat.sender.IMessageSender;
import io.ayrking.vitbox.Main;
import io.ayrking.vitbox.arch.loots.LootBox;

/**
 * Listener for box opening
 * 
 * @author Meltwin
 * @since 1.0.0
 */
public class VitBoxListener implements Listener, IMessageSender {

    @EventHandler
    public void playerInteract(final @NotNull PlayerInteractEvent event) {
        // If player doesn't click on a chest
        if (!event.hasBlock() || event.getAction() != Action.RIGHT_CLICK_BLOCK
                || event.getClickedBlock().getType() != Material.CHEST)
            return;

        // Get won item
        LootBox box = Main.BOX.searchAt(event.getClickedBlock().getWorld().getName(), event.getClickedBlock().getX(),
                event.getClickedBlock().getY(), event.getClickedBlock().getZ());
        if (box == null)
            return;
        ItemStack stack = box.getLootTable().getRandomItemStack();
        if (stack == null)
            return;

        // If everything is alright, do callback & affect player
        event.getPlayer().getInventory().addItem(stack);
        event.setCancelled(true);
        sendMessage(event.getPlayer(), Messages.boxOpen(box.getLootTable().getLootTableName()));
    }

    @Override
    public @NotNull ChatConfig getChatConfig() {
        return VitBoxConfig.getInstance();
    }


}
