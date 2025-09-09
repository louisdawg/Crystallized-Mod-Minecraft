package crystallized.modid.item;

import crystallized.modid.CrystallizedMod;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item CRYSTALLIZED_INGOT = registerItem("crystallized_ingot", new Item(
            new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(CrystallizedMod.MOD_ID, "crystallized_ingot")))));
    public static final Item RAW_CRYSTALLIZED = registerItem("raw_crystallized", new Item(
            new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(CrystallizedMod.MOD_ID, "raw_crystallized")))));


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(CrystallizedMod.MOD_ID, name), item);
    }
    public static void registerModItems() {
        CrystallizedMod.LOGGER.info("Registering ModItems for " + CrystallizedMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(CRYSTALLIZED_INGOT);
            entries.add(RAW_CRYSTALLIZED);
        });
    }
}
