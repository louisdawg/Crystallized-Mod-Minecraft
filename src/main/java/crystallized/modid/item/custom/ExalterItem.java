package crystallized.modid.item.custom;

import crystallized.modid.block.ModBlocks;
import crystallized.modid.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;

import java.util.Map;

public class ExalterItem extends Item {
private static final Map<Block, Block> EXALTER_MAP =
        Map.ofEntries(
                Map.entry(Blocks.NETHERITE_BLOCK, ModBlocks.ENDO_BLOCK)
        );

    public ExalterItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        Block clickBlock = world.getBlockState(context.getBlockPos()).getBlock();

        if (clickBlock == Blocks.NETHERITE_BLOCK) {
            if (!world.isClient()) {
                world.breakBlock(context.getBlockPos(), false);

                Item itemToDrop = ModItems.ENDO_INGOT;
                world.spawnEntity(new net.minecraft.entity.ItemEntity(
                        world,
                        context.getBlockPos().getX() + 0.5,
                        context.getBlockPos().getY() + 0.5,
                        context.getBlockPos().getZ() + 0.5,
                        new net.minecraft.item.ItemStack(itemToDrop)
                ));
                context.getStack().damage(1, ((ServerWorld) world), ((ServerPlayerEntity) context.getPlayer()),
                        item -> {
                            assert context.getPlayer() != null;
                            context.getPlayer().sendEquipmentBreakStatus(item, EquipmentSlot.MAINHAND);
                        });
                world.playSound(null, context.getBlockPos(), SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.BLOCKS);
            }
            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }
}
