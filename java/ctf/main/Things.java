package ctf.main;

import ctf.blocks.BlockFlag;
import ctf.blocks.BlockPedestal;
import ctf.blocks.BlockStand;
import ctf.items.ItemFlag;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;

public class Things {
	public static Block pedestal = new BlockPedestal(Material.grass);
	public static Block flag = new BlockFlag(Material.cloth);
	public static Block stand = new BlockStand(Material.grass);
	
	public static ItemBlock itemFlag = new ItemFlag(Things.flag);
}
