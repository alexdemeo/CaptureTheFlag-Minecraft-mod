package ctf.blocks;


import java.util.ArrayList;
import java.util.Random;

import ctf.blocks.tileentity.PedestalTileEntity;
import ctf.blocks.tileentity.StandTileEntity;
import ctf.main.Main;
import ctf.main.Things;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockStand extends BlockContainer {

	private boolean canClick = true;

	EntityPlayer player;
	public BlockStand(Material material) {
		super(material);
		this.setCreativeTab(Main.creativeTab);
		this.setBlockName("stand");
		this.setHardness(0);

	}

	@Override
	public void registerBlockIcons(IIconRegister r) {
		this.blockIcon = r.registerIcon("ctf:stand");
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new StandTileEntity();
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
	public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int metadata) {
		world.scheduleBlockUpdate(x, y, z, this, 5);
		if (!canClick) {
			return;
		}
		
		canClick = false;
		if (world.getBlock(x, y + 1, z) instanceof BlockFlag) {
			System.out.println("CALLL");
			world.setBlockToAir(x, y + 1, z);
			this.player.inventory.addItemStackToInventory(new ItemStack(Things.flag));
		}
	}

	@Override
	public boolean hasTileEntity(int metadata) {
		return true;
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		canClick = true;
		if (world.getBlock(x, y + 1, z) instanceof BlockFlag) {
			this.player.inventory.addItemStackToInventory(new ItemStack(Things.flag));
			world.setBlock(x, y + 1, z, Blocks.air);
		} else if (this.player.inventory.hasItem(Item.getItemFromBlock(Things.flag))) {
			Main.instance.sendPlayerMessage(this.player, "You have returned the flag!");
			world.setBlock(x, y + 1, z, Things.flag);
			this.player.inventory.consumeInventoryItem(Item.getItemFromBlock(Things.flag));
		} else {
			Main.instance.sendPlayerMessage(this.player, "Get a flag to return it");
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
