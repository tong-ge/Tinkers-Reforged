package mrthomas20121.tinkers_reforged.datagen;

import mrthomas20121.tinkers_reforged.TinkersReforged;
import mrthomas20121.tinkers_reforged.init.TinkersReforgedBlocks;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.stream.Collectors;

public class ReforgedBlocksLoot extends BlockLoot {

    @Nonnull
    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ForgeRegistries.BLOCKS.getValues().stream()
                .filter((block) -> TinkersReforged.MOD_ID.equals(Objects.requireNonNull(block.getRegistryName()).getNamespace()))
                .collect(Collectors.toList());
    }

    @Override
    protected void addTables() {
        this.dropSelf(TinkersReforgedBlocks.aluminum_ore.get());
        this.dropSelf(TinkersReforgedBlocks.aluminum_block.get());
        this.dropSelf(TinkersReforgedBlocks.duralumin_block.get());
        this.dropSelf(TinkersReforgedBlocks.electrical_copper_block.get());
        this.dropSelf(TinkersReforgedBlocks.lavium_block.get());
        this.dropSelf(TinkersReforgedBlocks.qivium_block.get());
        this.dropSelf(TinkersReforgedBlocks.gausum_block.get());
    }
}
