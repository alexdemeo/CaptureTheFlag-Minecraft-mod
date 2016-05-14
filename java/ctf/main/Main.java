package ctf.main;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.Metadata;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import ctf.blocks.tileentity.FlagTileEntity;
import ctf.blocks.tileentity.PedestalTileEntity;
import ctf.blocks.tileentity.StandTileEntity;
import ctf.events.EventHandle;
import ctf.proxy.CommonProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = "ctf", version = "1.0", name="CTF")
public class Main {
	
	@SidedProxy(clientSide="ctf.proxy.ClientProxy", serverSide="ctf.proxy.ServerProxy", modId="ctf")
	public static CommonProxy proxy;
	
	@Metadata
	public static ModMetadata meta;
	
	@Instance(value="ctf")
	public static Main instance;

	public static CreativeTabs creativeTab = new CreativeTabs("CTF") {
		@Override
		public Item getTabIconItem() {
			return Item.getItemFromBlock(Things.flag);
		}
	};
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		GameRegistry.registerBlock(Things.pedestal, "pedestal");
		GameRegistry.registerBlock(Things.stand, "stand");
		GameRegistry.registerBlock(Things.flag, "flag");
		GameRegistry.registerTileEntity(PedestalTileEntity.class, "ctfpedestal");
		GameRegistry.registerTileEntity(FlagTileEntity.class, "ctfflag");
		GameRegistry.registerTileEntity(StandTileEntity.class, "ctfstand");
    	proxy.registerRenderers();
	}
	
    @EventHandler
    public void init(FMLInitializationEvent event) {
    	MinecraftForge.EVENT_BUS.register(new EventHandle());
    	GameRegistry.addRecipe(new ItemStack(Things.stand), new Object[] {
    			"IFI", "FIF", "OOO", 'I', Blocks.iron_block, 'F', Items.flint, 'O', Blocks.obsidian
    	});
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {}
    
    public void sendPlayerMessage(EntityPlayer player, String message) {
    	player.addChatMessage(new ChatComponentText(message));
    	System.out.println("To player " + player.getDisplayName() + ": " + message);
    }
}
