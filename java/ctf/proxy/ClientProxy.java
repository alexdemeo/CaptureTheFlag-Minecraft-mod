package ctf.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ctf.blocks.render.ModelFlag;
import ctf.blocks.render.FlagRenderHandle;
import ctf.blocks.render.PedestalRenderHandle;
import ctf.blocks.render.StandRenderHandle;
import ctf.blocks.tileentity.FlagTileEntity;
import ctf.blocks.tileentity.PedestalTileEntity;
import ctf.blocks.tileentity.StandTileEntity;
import net.minecraftforge.client.MinecraftForgeClient;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {

	@Override
	@SideOnly(Side.CLIENT)
	public void registerRenderers() {
		ClientRegistry.bindTileEntitySpecialRenderer(FlagTileEntity.class, new FlagRenderHandle());
		ClientRegistry.bindTileEntitySpecialRenderer(PedestalTileEntity.class, new PedestalRenderHandle());
		ClientRegistry.bindTileEntitySpecialRenderer(StandTileEntity.class, new StandRenderHandle());
	}
}
