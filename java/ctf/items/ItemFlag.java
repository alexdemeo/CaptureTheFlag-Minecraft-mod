package ctf.items;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class ItemFlag extends ItemBlock {

	public ItemFlag(Block block) {
		super(block);
		this.setMaxStackSize(1);
	}
}
