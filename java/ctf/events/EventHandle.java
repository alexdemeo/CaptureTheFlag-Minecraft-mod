package ctf.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import ctf.blocks.BlockFlag;
import ctf.blocks.BlockPedestal;
import ctf.blocks.BlockStand;
import ctf.blocks.tileentity.PedestalTileEntity;
import ctf.main.Main;
import ctf.main.Things;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.world.BlockEvent;

public class EventHandle {
	
	@SubscribeEvent
	public void onBlockPlaced(BlockEvent.PlaceEvent evt) {
		if ((evt.y < 50 || evt.y > 128) && (evt.block instanceof BlockPedestal || evt.block instanceof BlockStand)) {
			evt.setCanceled(true);
			Main.instance.sendPlayerMessage(evt.player, "That bock must be between y=50 and y=128");
		} else if (evt.block instanceof BlockFlag) {
			evt.setCanceled(true);
		}
		blockCheck:
		if (evt.block instanceof BlockStand) {
			for (int i = evt.x - 5; i <= evt.x + 5; i++) {
				for (int k = evt.z - 5; k <= evt.z + 5; k++) {
					if (evt.world.getBlock(i, evt.y, k) instanceof BlockPedestal) {
						break blockCheck;
					}
				}
			}
			evt.setCanceled(true);
			Main.instance.sendPlayerMessage(evt.player, "Stands must be within 5 blocks of a pedestal");
		}
	}
	
	@SubscribeEvent
	public void onBlockBroken(BlockEvent.BreakEvent evt) {
		if (evt.block instanceof BlockPedestal && !evt.getPlayer().capabilities.isCreativeMode) {
			PedestalTileEntity ent = (PedestalTileEntity)evt.world.getTileEntity(evt.x, evt.y, evt.z);
			evt.setCanceled(ent.isFrozen());
		} else if (evt.block instanceof BlockStand && evt.world.getBlock(evt.x, evt.y + 1, evt.z) instanceof BlockFlag) {
			evt.getPlayer().inventory.addItemStackToInventory(new ItemStack(Things.flag));
			evt.world.setBlockToAir(evt.x, evt.y + 1, evt.z);
		}
	}
	
	@SubscribeEvent
	public void onItemDropped(ItemTossEvent evt) {
		Item i = evt.entityItem.getEntityItem().getItem();
		if (i.equals(Item.getItemFromBlock(Things.flag)) && !evt.player.capabilities.isCreativeMode) {
			evt.setCanceled(true);
			evt.player.inventory.addItemStackToInventory(new ItemStack(i));
		}
	}
	
	private int announce = 0;
	@SubscribeEvent
	public void onEntityUpdate(LivingUpdateEvent evt) {
		if (evt.entityLiving instanceof EntityPlayer) {
			announce++;
			EntityPlayer player = (EntityPlayer)evt.entityLiving;
			if (announce >= 200 && player.inventory.hasItem(Item.getItemFromBlock(Things.flag))) {
				announce = 0;
				Main.instance.announceMessage(player.getDisplayName() + " has a flag at x=" + (int)player.posX + " z=" + (int)player.posZ);
			}
		}
	}
}
