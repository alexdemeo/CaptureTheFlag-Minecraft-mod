package ctf.blocks;

import java.util.Random;

import ctf.blocks.tileentity.FlagTileEntity;
import ctf.main.Main;
import ctf.main.Things;
import net.minecraft.block.BlockBeacon;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockFlag extends BlockContainer {

	public int index = new Random().nextInt(6);
	private EntityPlayer player;

	public BlockFlag(Material material) {
		super(material);
		this.setBlockName("flag");
		this.setCreativeTab(Main.creativeTab);
		this.setBlockUnbreakable();
		this.setResistance(100);
	}

	@Override
	public void registerBlockIcons(IIconRegister icon) {
		this.blockIcon = icon.registerIcon("ctf:flag");
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

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
		this.player = player;
		return false;
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		FlagTileEntity te = (FlagTileEntity)world.getTileEntity(x, y, z);
		te.setTextureIndex(this.index);
	}

	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int p_149660_5_, float p_149660_6_, float p_149660_7_, float p_149660_8_, int p_149660_9_) {
		return 0;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new FlagTileEntity();
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		return null;
	}
}
