package ctf.blocks;


import java.util.ArrayList;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ctf.blocks.tileentity.FlagTileEntity;
import ctf.blocks.tileentity.PedestalTileEntity;
import ctf.main.Main;
import ctf.main.Things;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockRedstoneWire;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPedestal extends BlockContainer {

	private boolean canClick = true;
	private EntityPlayer player;

	public BlockPedestal(Material material) {
		super(material);
		this.setCreativeTab(Main.creativeTab);
		this.setBlockName("pedestal");
		this.setHardness(0);
		this.setResistance(100);
	}

	@Override
	public void registerBlockIcons(IIconRegister r) {
		this.blockIcon = r.registerIcon("ctf:pedestal");
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new PedestalTileEntity();
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
		this.player = player;
		world.scheduleBlockUpdate(x, y, z, this, 5);
		if (!canClick) {
			return false;
		}
		canClick = false;
		return true;
	}

	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int p_149660_5_, float p_149660_6_, float p_149660_7_, float p_149660_8_, int metadata) {
		return 0;
	}

	@Override
	public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int metadata) {}

	@Override
	public boolean hasTileEntity(int metadata) {
		return true;
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		canClick = true;
		PedestalTileEntity t = (PedestalTileEntity) world.getTileEntity(x, y, z);
		if (t == null) return;
		t.setFrozen();
		ArrayList<String> currentMembers = t.getMembers();
		if (currentMembers.contains(this.player.getDisplayName())) {									//check if player is on team
			if (this.player.inventory.hasItem(Item.getItemFromBlock(Things.flag)) && !(world.getBlock(x, y + 1, z) instanceof BlockFlag)) {
				this.player.inventory.consumeInventoryItem(Item.getItemFromBlock(Things.flag));
				world.setBlock(x, y + 1, z, Things.flag);
				Main.instance.announceMessage(this.player.getDisplayName() + " has returned their flag!");
			} else Main.instance.sendPlayerMessage(this.player, "You're already on team " + t.getMembers());
			return;
		} else if (currentMembers.size() == 2) {														//if player isn't on team, but team is full
			if (world.getBlock(x, y + 1, z) instanceof BlockFlag) { 									//if there's a flag, take it
				Main.instance.announceMessage(this.player.getDisplayName() + " has stolen the flag of " + t.getMembers());
				BlockFlag flag = (BlockFlag)world.getBlock(x, y + 1, z);
				world.setBlock(x, y + 1, z, Blocks.air);
				this.player.inventory.addItemStackToInventory(new ItemStack(flag));
				this.player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 1200, 0, false));
				return;
			} else {																					//there must be no flag to take
				Main.instance.sendPlayerMessage(this.player, "There's no flag here to steal...");
				return;
			}
		} else {																						//Must be called to add a player to the team
			if (t.getMembers().isEmpty()) {																//If there isn't a team, active the block
				Main.instance.sendPlayerMessage(this.player, "You have founded a team at (" + x + " "+ y + " " + z + ")");
				world.setBlock(x, y + 1, z, Things.flag);
			}
			if (t.addMember(this.player.getDisplayName())) {											//try to add player to team the team
				Main.instance.sendPlayerMessage(this.player, "Members of this team are " + t.getMembers() + " at " + "(" + x + " "+ y + " " + z + ")");
			} else {																					//this should never run, but i felt obligated to put it here
				Main.instance.sendPlayerMessage(this.player, "You're already on this team bud");
			}
		}
	}

	@Override
	public int getRenderType() {
		return -1;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
}
