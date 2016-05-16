package ctf.main;

import java.util.Iterator;

import cpw.mods.fml.common.FMLCommonHandler;
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
import ctf.events.EventHandleFML;
import ctf.events.EventHandleStandard;
import ctf.items.ItemFlag;
import ctf.items.ItemPedestal;
import ctf.items.ItemStand;
import ctf.proxy.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = Res.MODID, version = Res.VERSION, name=Res.NAME)
public class Main {
	
	@SidedProxy(clientSide="ctf.proxy.ClientProxy", serverSide="ctf.proxy.ServerProxy", modId="ctf")
	public static CommonProxy proxy;
	
	@Metadata
	public static ModMetadata meta;
	
	@Instance(value=Res.MODID)
	public static Main instance;

	public static CreativeTabs creativeTab = new CreativeTabs("CTF") {
		@Override
		public Item getTabIconItem() {
			return Item.getItemFromBlock(Things.flag);
		}
	};
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		GameRegistry.registerBlock(Things.pedestal, ItemPedestal.class, "pedestal");
		GameRegistry.registerBlock(Things.stand, ItemStand.class, "stand");
		GameRegistry.registerBlock(Things.flag, ItemFlag.class, "flag");
		
		GameRegistry.registerTileEntity(PedestalTileEntity.class, "ctfpedestal");
		GameRegistry.registerTileEntity(FlagTileEntity.class, "ctfflag");
		GameRegistry.registerTileEntity(StandTileEntity.class, "ctfstand");
		
    	proxy.registerRenderers();
	}
	
    @EventHandler
    public void init(FMLInitializationEvent event) {
    	MinecraftForge.EVENT_BUS.register(new EventHandleStandard());
    	FMLCommonHandler.instance().bus().register(new EventHandleFML());
    	
    	GameRegistry.addRecipe(new ItemStack(Things.stand), new Object[] {
    			"IFI", "FIF", "OOO", 'I', Blocks.iron_block, 'F', Items.flint, 'O', Blocks.obsidian
    	});
    	
    	Iterator<IRecipe> iterator = CraftingManager.getInstance().getRecipeList().iterator();
    	while (iterator.hasNext()) {
    	    IRecipe recipe = iterator.next();
    	    if (recipe == null) continue;
    	    ItemStack output = recipe.getRecipeOutput();
    	    if (output != null && output.getItem() == Item.getItemFromBlock(Blocks.tnt)) iterator.remove();
    	}    	
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {}
    
    public void sendPlayerMessage(EntityPlayer player, String message) {
    	player.addChatMessage(new ChatComponentText(message));
    	System.out.println("To player " + player.getDisplayName() + ": " + message);
    }
    
    public void announceMessage(String message) {
    	Iterator i = MinecraftServer.getServer().getConfigurationManager().playerEntityList.iterator();
    	while (i.hasNext()) {
    		this.sendPlayerMessage((EntityPlayer)i.next(), message);
    	}
    }
}
