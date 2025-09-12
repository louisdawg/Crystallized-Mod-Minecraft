package crystallized.modid.item;

import crystallized.modid.CrystallizedMod;
import crystallized.modid.block.ModBlocks;
import crystallized.modid.item.custom.ExalterItem;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {

    public static final ItemGroup CRYSTALLIZED_ITEMS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(CrystallizedMod.MOD_ID, "crystallized_items"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.CRYSTALLIZED_INGOT))
                    .displayName(Text.translatable("itemgroup.crystallized-mod.crystallized_items"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.CRYSTALLIZED_INGOT);
                        entries.add(ModItems.RAW_CRYSTALLIZED);

                        entries.add(ModItems.EXALTER);
                    })
                    .build());

    public static final ItemGroup CRYSTALLIZED_BLOCKS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(CrystallizedMod.MOD_ID, "crystallized_blocks"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModBlocks.CRYSTALLIZED_ORE))
                    .displayName(Text.translatable("itemgroup.crystallized-mod.crystallized_blocks"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModBlocks.CRYSTALLIZED_ORE);
                        entries.add(ModBlocks.CRYSTALLIZED_BLOCK);
                    })
                    .build());

    public static void registerItemGroups() {
        CrystallizedMod.LOGGER.info("Registering ItemGroups for " + CrystallizedMod.MOD_ID);
    }
}
