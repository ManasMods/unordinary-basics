package com.github.manasmods.unordinary_basics.client.block_entity_renderer;

import com.github.manasmods.unordinary_basics.block.entity.ItemSorterBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.util.Mth;

import java.util.List;

public class ItemSorterBlockEntityRenderer implements BlockEntityRenderer<ItemSorterBlockEntity> {

    public static final int MAX_LINE_WIDTH = 70;
    private static final int LINE_HEIGHT = 10;
    private static final int BLACK_TEXT_OUTLINE_COLOR = -988212;
    private static final int OUTLINE_RENDER_DISTANCE = Mth.square(16);
    private final Font font;

    public ItemSorterBlockEntityRenderer(BlockEntityRendererProvider.Context context){
        this.font = context.getFont();
    }

    public void render(ItemSorterBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        pPoseStack.pushPose();

        pPoseStack.pushPose();
        pPoseStack.scale(0.6666667F, -0.6666667F, -0.6666667F);
        pPoseStack.popPose();
        pPoseStack.translate(0.0D, (double)0.33333334F, (double)0.063F);
        pPoseStack.scale(0.010416667F, -0.010416667F, 0.010416667F);
        int i = 16777215;
        FormattedCharSequence[] aformattedcharsequence = pBlockEntity.getRenderMessages(Minecraft.getInstance().isTextFilteringEnabled(), (text) -> {
            if (text != null) {
                List<FormattedCharSequence> list = this.font.split(text, MAX_LINE_WIDTH);
                return list.isEmpty() ? FormattedCharSequence.EMPTY : list.get(0);
            }
            return null;
        });
        int l = pPackedLight;

        for(int i1 = 0; i1 < 4; ++i1) {
            if (aformattedcharsequence[i1] != null) {
                FormattedCharSequence formattedcharsequence = aformattedcharsequence[i1];
                float f1 = (float) (-this.font.width(formattedcharsequence) / 2 + 50);
                this.font.drawInBatch8xOutline(formattedcharsequence, f1, (float) (i1 * 10 - 40), i, 0, pPoseStack.last().pose(), pBufferSource, l);
                this.font.drawInBatch(formattedcharsequence, f1, (float) (i1 * 10 - 40), i, false, pPoseStack.last().pose(), pBufferSource, false, 0, l);
            }
        }

        pPoseStack.popPose();
    }
}
