package ctf.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ctf.blocks.render.RenderBlockFlag;
import ctf.blocks.render.RenderBlockPedestal;
import ctf.blocks.render.RenderBlockStand;
import ctf.blocks.tileentity.FlagTileEntity;
import ctf.blocks.tileentity.PedestalTileEntity;
import ctf.blocks.tileentity.StandTileEntity;
import ctf.items.render.RenderItemFlag;
import ctf.items.render.RenderItemPedestal;
import ctf.items.render.RenderItemStand;
import ctf.main.Things;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {

	@Override
	@SideOnly(Side.CLIENT)
	public void registerRenderers() {
		ClientRegistry.bindTileEntitySpecialRenderer(FlagTileEntity.class, new RenderBlockFlag());
		ClientRegistry.bindTileEntitySpecialRenderer(PedestalTileEntity.class, new RenderBlockPedestal());
		ClientRegistry.bindTileEntitySpecialRenderer(StandTileEntity.class, new RenderBlockStand());
		
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(Things.flag), new RenderItemFlag());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(Things.pedestal), new RenderItemPedestal());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(Things.stand), new RenderItemStand());
	}
}
