package com.nickm13.shulker.listener;

import net.minecraft.server.v1_16_R3.*;
import org.bukkit.Location;
import org.bukkit.block.ShulkerBox;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

/**
 * @author NickM13
 * @since 3/15/2021
 */
public class BreakListener implements Listener {

    /**
     * Attempts to cancel shulker box breaks and replace it with a creative-style break
     *
     * @param event Block Break Event
     */
    @EventHandler
    @SuppressWarnings("unused")
    public void onBlockBreak(BlockBreakEvent event) {
        if (event.getBlock().getState() instanceof ShulkerBox) {
            event.setDropItems(false);
            World world = ((CraftWorld) event.getBlock().getWorld()).getHandle();
            BlockPosition position = new BlockPosition(
                    event.getBlock().getLocation().getBlockX(),
                    event.getBlock().getLocation().getBlockY(),
                    event.getBlock().getLocation().getBlockZ());
            TileEntity tileEntity = world.getTileEntity(position);
            if (tileEntity instanceof TileEntityShulkerBox) {
                BlockShulkerBox blockShulkerBox = (BlockShulkerBox) world.getType(position).getBlock();
                TileEntityShulkerBox tileEntityShulkerBox = (TileEntityShulkerBox) tileEntity;
                NBTTagCompound tagCompound = tileEntityShulkerBox.e(new NBTTagCompound());

                net.minecraft.server.v1_16_R3.ItemStack itemStack = new net.minecraft.server.v1_16_R3.ItemStack(blockShulkerBox);

                if (!tagCompound.isEmpty()) {
                    itemStack.a("BlockEntityTag", tagCompound);
                }

                if (tileEntityShulkerBox.hasCustomName()) {
                    itemStack.a(tileEntityShulkerBox.getCustomName());
                }

                event.getBlock().getWorld().dropItem(new Location(event.getBlock().getWorld(),
                                position.getX() + 0.5,
                                position.getY() + 0.5,
                                position.getZ() + 0.5),
                        CraftItemStack.asBukkitCopy(itemStack));
            }
        }
    }

}
