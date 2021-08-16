package com.ds.projectdawn.objects.entities.projectiles;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Matrix3f;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MagicNoteRenderer extends EntityRenderer<MagicNoteEntity>
{
    private static final ResourceLocation MAGIC_NOTE_TEXTURE = new ResourceLocation("projectdawn:textures/particle/magic_note1.png");
    private static final RenderType magic_note_render = RenderType.entityCutoutNoCull(MAGIC_NOTE_TEXTURE);

    public MagicNoteRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn);
    }

    public void render(MagicNoteEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        matrixStackIn.pushPose();
        matrixStackIn.scale(0.25F, 0.25F, 0.25F);
        matrixStackIn.translate(0, 0.3D, 0);
        matrixStackIn.mulPose(this.entityRenderDispatcher.cameraOrientation());
        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(180.0F));
        MatrixStack.Entry matrixstack = matrixStackIn.last();
        Matrix4f matrix4f = matrixstack.pose();
        Matrix3f matrix3f = matrixstack.normal();
        IVertexBuilder ivertexbuilder = bufferIn.getBuffer(magic_note_render);
        render(ivertexbuilder, matrix4f, matrix3f, packedLightIn, 0.0F, 0, 0, 1);
        render(ivertexbuilder, matrix4f, matrix3f, packedLightIn, 1.0F, 0, 1, 1);
        render(ivertexbuilder, matrix4f, matrix3f, packedLightIn, 1.0F, 1, 1, 0);
        render(ivertexbuilder, matrix4f, matrix3f, packedLightIn, 0.0F, 1, 0, 0);
        matrixStackIn.popPose();
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    private static void render(IVertexBuilder vertexBuilder, Matrix4f matrix4F, Matrix3f matrix3F, int lightmap, float x, int y, int p_229045_6_, int p_229045_7_) {

        vertexBuilder.vertex(matrix4F, x - 0.5F, (float)y - 0.25F, 0.0F).color(1, 255, 1, 255).uv((float)p_229045_6_, (float)p_229045_7_).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(lightmap).normal(matrix3F, 0.0F, 1.0F, 0.0F).endVertex();
    }

    @Override
    public ResourceLocation getTextureLocation(MagicNoteEntity entity) { return MAGIC_NOTE_TEXTURE; }

}
