package ctf.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import ctf.blocks.BlockFlag;
import ctf.blocks.BlockPedestal;
import ctf.blocks.BlockStand;
import ctf.blocks.tileentity.PedestalTileEntity;
import ctf.main.Main;
import net.minecraftforge.event.world.BlockEvent;

public class EventHandle {
	
	@SubscribeEvent
	public void onBlockPlaced(BlockEvent.PlaceEvent evt) {
		if (evt.y < 50 || evt.y > 128) {
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
		}
	}
}
