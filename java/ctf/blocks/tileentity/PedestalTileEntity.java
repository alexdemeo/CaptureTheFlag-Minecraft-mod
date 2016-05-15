package ctf.blocks.tileentity;

import java.util.ArrayList;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class PedestalTileEntity extends TileEntity {

	private ArrayList<String> members = new ArrayList<String>();
	private boolean frozen = false;
	
	public PedestalTileEntity() {}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		this.members = new ArrayList<String>();
		NBTTagList tags = (NBTTagList) tag.getTag("members");
		for (int i = 0; i < tags.tagCount(); i++) {
			NBTTagCompound comp = tags.getCompoundTagAt(i);
			members.add(comp.getString("member"));
		}
		this.frozen = tag.getBoolean("frozen");
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		NBTTagList tags = new NBTTagList();
		for (String m : this.members) {
			NBTTagCompound comp = new NBTTagCompound();
			comp.setString("member", m);
			tags.appendTag(comp);
		}
		tag.setTag("members", tags);
		tag.setBoolean("frozen", this.frozen);
	}

	public boolean addMember(String member) {
		if (!this.members.contains(member)) {
			this.members.add(member);
			return true;
		} else {
			return false;
		}
	}

	public void removeMember(String member) {
		this.members.remove(member);
	}

	public ArrayList<String> getMembers() {
		return this.members;
	}
	
	public boolean isFrozen() {
		return this.frozen;
	}
	
	public void setFrozen() {
		this.frozen = true;
	}
}
