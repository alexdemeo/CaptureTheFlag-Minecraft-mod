package ctf.events;

import java.util.Iterator;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import ctf.blocks.BlockFlag;
import ctf.blocks.BlockPedestal;
import ctf.blocks.BlockStand;
import ctf.blocks.tileentity.PedestalTileEntity;
import ctf.main.Main;
import ctf.main.Things;
import net.minecraft.block.BlockBed;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.event.entity.item.ItemExpireEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.world.BlockEvent;import net.minecraftforge.event.world.BlockEvent.PlaceEvent;

public class EventHandleStandard {
	private static final int radiusStand = 5;
	private static final int radiusBed = 20;
	@SubscribeEvent
	public void onBlockPlaced(BlockEvent.PlaceEvent evt) {
		if ((evt.y < 50 || evt.y > 128) && (evt.block instanceof BlockPedestal || evt.block instanceof BlockStand)) {
			evt.setCanceled(true);
			Main.instance.sendPlayerMessage(evt.player, "That bock must be between y=50 and y=128");
		} else if (evt.block instanceof BlockFlag) {
			evt.setCanceled(true);
		}
		standCheck:
			if (evt.block instanceof BlockStand) {
				for (int i = evt.x - radiusStand; i <= evt.x + radiusStand; i++) {
					for (int k = evt.z - radiusStand; k <= evt.z + radiusStand; k++) {
						if (evt.world.getBlock(i, evt.y, k) instanceof BlockPedestal) {
							break standCheck;
						}
					}
				}
				evt.setCanceled(true);
				Main.instance.sendPlayerMessage(evt.player, "Stands must be within " + radiusStand + " blocks of a pedestal");
			}
		bedCheck:
			if (evt.block instanceof BlockBed) {
				for (int j = evt.y - 2; j <= evt.y + 2; j++) {
					for (int i = evt.x - radiusBed; i <= evt.x + radiusBed; i++) {
						for (int k = evt.z - radiusBed; k <= evt.z + radiusBed; k++) {
							if (evt.world.getBlock(i, j, k) instanceof BlockPedestal) {
								break bedCheck;
							}
						}
					}
				}
				evt.setCanceled(true);
				Main.instance.sendPlayerMessage(evt.player, "Beds must be within a " + "radiusBed" + " block lateral radius and 2 block vertical radius of a pedestal");
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

	@SubscribeEvent
	public void onItemDespawn(ItemExpireEvent evt) {
		if (evt.entityItem.getEntityItem().getItem() == Item.getItemFromBlock(Things.flag)) {
			evt.setCanceled(true);
		}
	}
	
	@SubscribeEvent
	public void onPlayerDied(LivingDeathEvent evt) {
		if (evt.entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)evt.entity;
			if (player.inventory.consumeInventoryItem(Item.getItemFromBlock(Things.flag))) {
				player.worldObj.spawnEntityInWorld(new EntityItem(player.worldObj, player.posX, player.posY, player.posZ, new ItemStack(Things.flag)));
				System.out.println(player.getDisplayName() + " dropped a flag");
			}
		}
	}

}
