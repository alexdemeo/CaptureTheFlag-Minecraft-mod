package ctf.items.render;

import org.lwjgl.opengl.GL11;

import ctf.models.ModelFlag;
import ctf.proxy.ClientProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

public class RenderItemFlag implements IItemRenderer {

	protected ModelFlag model;

	public RenderItemFlag() {
		this.model = new ModelFlag();
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		switch (type) {
		case EQUIPPED: return true;
		case EQUIPPED_FIRST_PERSON: return true;
		default: return false;
		}
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		switch (type) {
		case EQUIPPED:
			GL11.glPushMatrix();
			GL11.glScalef(1.0F, 1.0F, 1.0F);
			GL11.glTranslatef(-0.1F, 1.15F, -0.4F);
			GL11.glRotatef(-80, 0, 1, 0); //y
			GL11.glRotatef(200, 1, 0, 0); //z
			GL11.glRotatef(-20, 0, 0, 1);
			Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("ctf:render/flag_0.png"));
			model.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			GL11.glPopMatrix();
			break;
		case EQUIPPED_FIRST_PERSON:
			GL11.glPushMatrix();
			GL11.glScalef(1, 1, 1);
			GL11.glTranslatef(1F, 1.15F, 0.5F);
			GL11.glRotatef(180, 1, 0, 0);
			GL11.glRotatef(-10, 0, 1, 0);
			GL11.glRotatef(-7.5F, 0, 0, 1);
			Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("ctf:render/flag_0.png"));
			model.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			GL11.glPopMatrix();
			break;
		default: break;
		}
	}
}