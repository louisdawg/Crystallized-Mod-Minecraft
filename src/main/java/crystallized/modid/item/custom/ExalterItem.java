package crystallized.modid.item.custom;

import crystallized.modid.block.ModBlocks;
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

        if(EXALTER_MAP.containsKey(clickBlock))    {
            if(!world.isClient())    {
                world.setBlockState(context.getBlockPos(), EXALTER_MAP.get(clickBlock).getDefaultState());


                context.getStack().damage(1, ((ServerWorld) world), ((ServerPlayerEntity) context.getPlayer()),
                        item -> {
                            assert context.getPlayer() != null;
                            context.getPlayer().sendEquipmentBreakStatus(item, EquipmentSlot.MAINHAND);
                        });

                world.playSound(null, context.getBlockPos(), SoundEvents.BLOCK_BELL_USE, SoundCategory.BLOCKS);
            }
        }

        return ActionResult.SUCCESS;
    }
}
