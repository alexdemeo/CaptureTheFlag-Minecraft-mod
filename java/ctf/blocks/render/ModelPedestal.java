package ctf.blocks.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@SideOnly(Side.CLIENT)
public class ModelPedestal extends ModelBase {
	//fields
	ModelRenderer Shape1;
	ModelRenderer Shape2;
	ModelRenderer baseSide2;

	public ModelPedestal() {
		textureWidth = 64;
		textureHeight = 32;
		Shape1 = new ModelRenderer(this, 0, 0);
		Shape1.addBox(0F, 0F, 0F, 4, 8, 4);
		Shape1.setRotationPoint(-2F, 16F, -2F);
		Shape1.setTextureSize(64, 32);
		Shape1.mirror = true;
		setRotation(Shape1, 0F, 0F, 0F);
		Shape2 = new ModelRenderer(this, 0, 13);
		Shape2.addBox(0.25F, 0F, 0F, 22, 8, 0);
		Shape2.setRotationPoint(-8F, 16F, 8F);
		Shape2.setTextureSize(64, 32);
		Shape2.mirror = true;
		setRotation(Shape2, 0F, 0.7853982F, 0F);
		baseSide2 = new ModelRenderer(this, 0, 13);
		baseSide2.addBox(0.3F, 0F, 0F, 22, 8, 0);
		baseSide2.setRotationPoint(-8F, 16F, -8F);
		baseSide2.setTextureSize(64, 32);
		baseSide2.mirror = true;
		setRotation(baseSide2, 0F, -0.7853982F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Shape1.render(f5);
		Shape2.render(f5);
		baseSide2.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
	}

}
