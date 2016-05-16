package ctf.items;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class ItemPedestal extends ItemBlock {

	public ItemPedestal(Block block) {
		super(block);
		this.setMaxStackSize(1);
	}
	
	@Override
	public boolean shouldRotateAroundWhenRendering() {
		return true;
	}
}
