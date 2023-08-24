package com.github.manasmods.unordinary_basics.data;

import com.github.manasmods.manascore.api.data.gen.EntityLoot;
import com.github.manasmods.unordinary_basics.item.Unordinary_BasicsItems;
import com.github.manasmods.unordinary_basics.registry.UBEntityTypes;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

public class Unordinary_BasicsEntityLoot extends EntityLoot {

    @Override
    protected void loadTables() {
        this.add(UBEntityTypes.MASTER_SWORD_WARDEN.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(Items.SCULK_CATALYST)))
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(Unordinary_BasicsItems.DECAYED_MASTER_SWORD))));
    }
}
