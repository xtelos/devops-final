package net.minecraft.world.level.block;

import java.util.Random;
import net.minecraft.core.BaseBlockPosition;
import net.minecraft.core.BlockPosition;
import net.minecraft.core.EnumDirection;
import net.minecraft.server.level.WorldServer;
import net.minecraft.tags.TagsBlock;
import net.minecraft.world.item.context.BlockActionContext;
import net.minecraft.world.level.GeneratorAccess;
import net.minecraft.world.level.IBlockAccess;
import net.minecraft.world.level.World;
import net.minecraft.world.level.block.state.BlockBase;
import net.minecraft.world.level.block.state.BlockStateList;
import net.minecraft.world.level.block.state.IBlockData;
import net.minecraft.world.level.block.state.properties.BlockProperties;
import net.minecraft.world.level.block.state.properties.BlockStateBoolean;
import net.minecraft.world.level.block.state.properties.BlockStateInteger;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.VoxelShapes;

import org.bukkit.event.block.LeavesDecayEvent; // CraftBukkit

public class BlockLeaves extends Block {

    public static final BlockStateInteger DISTANCE = BlockProperties.an;
    public static final BlockStateBoolean PERSISTENT = BlockProperties.v;

    public BlockLeaves(BlockBase.Info blockbase_info) {
        super(blockbase_info);
        this.j((IBlockData) ((IBlockData) ((IBlockData) this.blockStateList.getBlockData()).set(BlockLeaves.DISTANCE, 7)).set(BlockLeaves.PERSISTENT, false));
    }

    @Override
    public VoxelShape e(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
        return VoxelShapes.a();
    }

    @Override
    public boolean isTicking(IBlockData iblockdata) {
        return (Integer) iblockdata.get(BlockLeaves.DISTANCE) == 7 && !(Boolean) iblockdata.get(BlockLeaves.PERSISTENT);
    }

    @Override
    public void tick(IBlockData iblockdata, WorldServer worldserver, BlockPosition blockposition, Random random) {
        if (!(Boolean) iblockdata.get(BlockLeaves.PERSISTENT) && (Integer) iblockdata.get(BlockLeaves.DISTANCE) == 7) {
            // CraftBukkit start
            LeavesDecayEvent event = new LeavesDecayEvent(worldserver.getWorld().getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ()));
            worldserver.getServer().getPluginManager().callEvent(event);

            if (event.isCancelled() || worldserver.getType(blockposition).getBlock() != this) {
                return;
            }
            // CraftBukkit end
            c(iblockdata, (World) worldserver, blockposition);
            worldserver.a(blockposition, false);
        }

    }

    @Override
    public void tickAlways(IBlockData iblockdata, WorldServer worldserver, BlockPosition blockposition, Random random) {
        worldserver.setTypeAndData(blockposition, a(iblockdata, (GeneratorAccess) worldserver, blockposition), 3);
    }

    @Override
    public int f(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
        return 1;
    }

    @Override
    public IBlockData updateState(IBlockData iblockdata, EnumDirection enumdirection, IBlockData iblockdata1, GeneratorAccess generatoraccess, BlockPosition blockposition, BlockPosition blockposition1) {
        int i = h(iblockdata1) + 1;

        if (i != 1 || (Integer) iblockdata.get(BlockLeaves.DISTANCE) != i) {
            generatoraccess.getBlockTickList().a(blockposition, this, 1);
        }

        return iblockdata;
    }

    private static IBlockData a(IBlockData iblockdata, GeneratorAccess generatoraccess, BlockPosition blockposition) {
        int i = 7;
        BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition();
        EnumDirection[] aenumdirection = EnumDirection.values();
        int j = aenumdirection.length;

        for (int k = 0; k < j; ++k) {
            EnumDirection enumdirection = aenumdirection[k];

            blockposition_mutableblockposition.a((BaseBlockPosition) blockposition, enumdirection);
            i = Math.min(i, h(generatoraccess.getType(blockposition_mutableblockposition)) + 1);
            if (i == 1) {
                break;
            }
        }

        return (IBlockData) iblockdata.set(BlockLeaves.DISTANCE, i);
    }

    private static int h(IBlockData iblockdata) {
        return TagsBlock.LOGS.isTagged(iblockdata.getBlock()) ? 0 : (iblockdata.getBlock() instanceof BlockLeaves ? (Integer) iblockdata.get(BlockLeaves.DISTANCE) : 7);
    }

    @Override
    protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
        blockstatelist_a.a(BlockLeaves.DISTANCE, BlockLeaves.PERSISTENT);
    }

    @Override
    public IBlockData getPlacedState(BlockActionContext blockactioncontext) {
        return a((IBlockData) this.getBlockData().set(BlockLeaves.PERSISTENT, true), (GeneratorAccess) blockactioncontext.getWorld(), blockactioncontext.getClickPosition());
    }
}
