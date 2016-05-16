package ctf.blocks.tileentity;

import java.util.Random;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class FlagTileEntity extends TileEntity {
	public static final Random rand = new Random();
	
	public static final int numFlags = 8;
	
	private String texture = "ctf:render/flag_"+ rand.nextInt(numFlags) + ".png";

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		tag.getString("texture");
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setString("texture", this.texture);
	}
	
	public String getTexture() {
		return this.texture;
	}
	
	public void setTextureIndex(int i) {
		this.texture = "ctf:render/flag_" + i + ".png";
	}
}
