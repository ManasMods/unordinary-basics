package com.github.manasmods.unordinary_basics.core;

import com.github.manasmods.unordinary_basics.block.IColorable;
import com.github.manasmods.unordinary_basics.block.IFromItem;
import com.github.manasmods.unordinary_basics.block.IPatternable;
import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.BannerBlock;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import javax.annotation.Nullable;
import java.util.ConcurrentModificationException;
import java.util.List;


@Mixin(BedBlockEntity.class)
public class MixinBedBlockEntity extends BlockEntity implements IFromItem, IPatternable, IColorable {
    /** A list of all the banner patterns. */
    @Nullable
    private ListTag itemPatterns;
    /** A list of all patterns stored on this banner. */
    @Nullable
    private List<Pair<BannerPattern, DyeColor>> patterns;

    @Shadow
    private DyeColor color;

    public MixinBedBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(BlockEntityType.BED, pWorldPosition, pBlockState);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        try {
            super.saveAdditional(pTag);

            if (this.itemPatterns != null) {
                pTag.put("Patterns", this.itemPatterns);
            }

            pTag.putInt("Color", color.getId());
        } catch (ConcurrentModificationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void load(CompoundTag pTag) {
        try {
            super.load(pTag);

            this.itemPatterns = pTag.getList("Patterns", 10);
            this.patterns = null;
            this.color = DyeColor.byId(pTag.getInt("Color"));
        } catch (ConcurrentModificationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void fromItem(ItemStack pItem) {
        if (pItem == null) {
            this.itemPatterns = null;
            this.patterns = null;

            this.color = ((BedBlock) this.getBlockState().getBlock()).getColor();
        } else {
            this.itemPatterns = BannerBlockEntity.getItemPatterns(pItem);
            this.patterns = null;

            BannerBlock block = (BannerBlock) Block.byItem(pItem.getItem());
            this.color = block.getColor();
        }

        //Save changes to disk on server
        this.setChanged();

        //Send changes to clients
        this.getLevel().sendBlockUpdated(this.getBlockPos(), getBlockState(), getBlockState(), Block.UPDATE_CLIENTS);
    }

    @Override
    public CompoundTag getUpdateTag() {
        try {
            CompoundTag tag = super.getUpdateTag();

            if (this.itemPatterns != null) {
                tag.put("Patterns", this.itemPatterns);
            }

            tag.putInt("Color", color.getId());

            return tag;
        } catch (ConcurrentModificationException e) {
            e.printStackTrace();
            return new CompoundTag();
        }
    }

    @Override
    /**
     * @return the patterns for this bed.
     */
    public List<Pair<BannerPattern, DyeColor>> getPatterns() {
        if (this.patterns == null) {
            this.patterns = this.createPatterns(this.color, this.itemPatterns);
        }

        return this.patterns;
    }

    //TODO: Fix this. The patterns aren't loaded so this also doesn't work
    public List<Pair<BannerPattern, DyeColor>> createPatterns(DyeColor pColor, @Nullable ListTag pListTag) {
        List<Pair<BannerPattern, DyeColor>> list = Lists.newArrayList();
        if (pListTag != null) {
            for (int i = 0; i < pListTag.size(); ++i) {
                CompoundTag compoundtag = pListTag.getCompound(i);
                BannerPattern bannerpattern = BannerPattern.byHash(compoundtag.getString("Pattern")).get();
                if (bannerpattern != null) {
                    int j = compoundtag.getInt("Color");
                    list.add(Pair.of(bannerpattern, DyeColor.byId(j)));
                }
            }
        }

        return list;
    }

    @Override
    public ListTag getItemPatterns() {
        return this.itemPatterns;
    }

    @Override
    public DyeColor getColor() {
        return this.color;
    }

}
