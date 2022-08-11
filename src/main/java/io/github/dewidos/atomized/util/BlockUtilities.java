package io.github.dewidos.atomized.util;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import java.util.function.ToIntFunction;

public class BlockUtilities {
    public static ToIntFunction<BlockState> litBlockEmission(int pLightValue) {
        return (pBlockState) -> {
            return pBlockState.getValue(BlockStateProperties.LIT) ? pLightValue : 0;
        };
    }
}
