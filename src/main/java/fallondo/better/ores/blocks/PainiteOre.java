package fallondo.better.ores.blocks;

import net.minecraft.block.*;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

import java.util.Random;

public class PainiteOre extends Block {
    public PainiteOre() {
        super(FabricBlockSettings.of(Material.STONE)
            .strength(7f, 9f)
            .sounds(BlockSoundGroup.STONE)
            .requiresTool()
            .breakByTool(FabricToolTags.PICKAXES, 3));
    }

    private int getExperienceWhenMined(Random random) {
        return MathHelper.nextInt(random, 2, 15);
    }

    public void onStacksDropped(BlockState state, ServerWorld world, BlockPos pos, ItemStack stack) {
        super.onStacksDropped(state, world, pos, stack);
        if (EnchantmentHelper.getLevel(Enchantments.SILK_TOUCH, stack) == 0) {
            int i = getExperienceWhenMined(world.random);
            if (i > 0) {
                dropExperience(world, pos, i);
            }
        }
    }
}
