package net.minecraft.world.level.lighting;

import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import net.minecraft.core.BlockPosition;
import net.minecraft.core.SectionPosition;
import net.minecraft.world.level.EnumSkyBlock;
import net.minecraft.world.level.chunk.ILightAccess;
import net.minecraft.world.level.chunk.NibbleArray;

public class LightEngineStorageBlock extends LightEngineStorage<LightEngineStorageBlock.a> {

    protected LightEngineStorageBlock(ILightAccess ilightaccess) {
        super(EnumSkyBlock.BLOCK, ilightaccess, new LightEngineStorageBlock.a(new Long2ObjectOpenHashMap()));
    }

    @Override
    protected int d(long i) {
        long j = SectionPosition.e(i);
        NibbleArray nibblearray = this.a(j, false);

        return nibblearray == null ? 0 : nibblearray.a(SectionPosition.b(BlockPosition.b(i)), SectionPosition.b(BlockPosition.c(i)), SectionPosition.b(BlockPosition.d(i)));
    }

    public static final class a extends LightEngineStorageArray<LightEngineStorageBlock.a> {

        public a(Long2ObjectOpenHashMap<NibbleArray> long2objectopenhashmap) {
            super(long2objectopenhashmap);
        }

        @Override
        public LightEngineStorageBlock.a b() {
            return new LightEngineStorageBlock.a(this.a.clone());
        }
    }
}
