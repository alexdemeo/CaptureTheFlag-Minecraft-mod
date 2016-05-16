package ctf.events;

import java.util.Iterator;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import ctf.main.Main;
import ctf.main.Things;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;

public class EventHandleFML {
	@SubscribeEvent
	public void onPlayerJoined(PlayerLoggedInEvent evt) {
		Iterator i = MinecraftServer.getServer().getConfigurationManager().playerEntityList.iterator();
		while (i.hasNext()) {
			EntityPlayer p = (EntityPlayer)i.next();
			if (p.inventory.hasItem(Item.getItemFromBlock(Things.flag))) {
				Main.instance.sendPlayerMessage(evt.player, p.getDisplayName() + " has a flag");
			}
		}
	}

	@SubscribeEvent
	public void onPlayerLeft(PlayerLoggedOutEvent evt) {
		if (evt.player.inventory.hasItem(Item.getItemFromBlock(Things.flag))) {
			evt.player.inventory.consumeInventoryItem(Item.getItemFromBlock(Things.flag));
			evt.player.worldObj.spawnEntityInWorld(new EntityItem(evt.player.worldObj, evt.player.posX, evt.player.posY, evt.player.posZ, new ItemStack(Things.flag)));
			Main.instance.announceMessage(evt.player.getDisplayName() + " has logged out with a flag, it has dropped at (" 
					+ (int)evt.player.posX 
					+ " " + (int)evt.player.posY 
					+ " " + (int)evt.player.posZ + ")");
		}
	}
}
