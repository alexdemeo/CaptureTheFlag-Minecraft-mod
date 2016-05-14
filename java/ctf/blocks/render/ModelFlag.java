package ctf.blocks.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@SideOnly(Side.CLIENT)
public class ModelFlag extends ModelBase {
	//fields
	ModelRenderer poleTop;
	ModelRenderer poleBottom;
	ModelRenderer poleRight;
	ModelRenderer poleLeft;
	ModelRenderer flagTop;
	ModelRenderer flagFace1;
	ModelRenderer flagFace2;
	
	public ModelFlag()  {
		textureWidth = 64;
		textureHeight = 32;

		poleTop = new ModelRenderer(this, 8, 0);
		poleTop.addBox(0F, 0F, 0F, 2, 16, 2);
		poleTop.setRotationPoint(-1F, -8F, -1F);
		poleTop.setTextureSize(64, 32);
		poleTop.mirror = true;
		setRotation(poleTop, 0F, 0F, 0F);
		poleBottom = new ModelRenderer(this, 0, 0);
		poleBottom.addBox(0F, 8F, 0F, 2, 16, 2);
		poleBottom.setRotationPoint(-1F, 0F, -1F);
		poleBottom.setTextureSize(64, 32);
		poleBottom.mirror = true;
		setRotation(poleBottom, 0F, 0F, 0F);
		poleRight = new ModelRenderer(this, 17, 3);
		poleRight.addBox(0F, 0F, 0F, 7, 1, 2);
		poleRight.setRotationPoint(1F, -8F, -1F);
		poleRight.setTextureSize(64, 32);
		poleRight.mirror = true;
		setRotation(poleRight, 0F, 0F, 0F);
		poleLeft = new ModelRenderer(this, 17, 0);
		poleLeft.addBox(0F, 0F, 0F, 7, 1, 2);
		poleLeft.setRotationPoint(-8F, -8F, -1F);
		poleLeft.setTextureSize(64, 32);
		poleLeft.mirror = true;
		setRotation(poleLeft, 0F, 0F, 0F);
		flagTop = new ModelRenderer(this, 0, 19);
		flagTop.addBox(0F, 0F, 0F, 12, 0, 2);
		flagTop.setRotationPoint(-6F, -8F, -1F);
		flagTop.setTextureSize(64, 32);
		flagTop.mirror = true;
		setRotation(flagTop, 0F, 0F, 0F);
		flagFace1 = new ModelRenderer(this, 36, 0);
		flagFace1.addBox(0F, 0F, 0F, 12, 23, 0);
		flagFace1.setRotationPoint(-6F, -8F, 1F);
		flagFace1.setTextureSize(64, 32);
		flagFace1.mirror = true;
		setRotation(flagFace1, 0F, 0F, 0F);
		flagFace2 = new ModelRenderer(this, 36, 0);
		flagFace2.addBox(0F, 0F, 0F, 12, 23, 0);
		flagFace2.setRotationPoint(6F, -8F, -1F);
		flagFace2.setTextureSize(64, 32);
		flagFace2.mirror = true;
		setRotation(flagFace2, 0F, 3.141593F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		poleTop.render(f5);
		poleBottom.render(f5);
		poleRight.render(f5);
		poleLeft.render(f5);
		flagTop.render(f5);
		flagFace1.render(f5);
		flagFace2.render(f5);
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
