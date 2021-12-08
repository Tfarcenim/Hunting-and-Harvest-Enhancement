package tfar.huntingandharvestenhancement.world.generation;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.FeatureSpread;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.foliageplacer.FoliagePlacer;
import net.minecraft.world.gen.foliageplacer.FoliagePlacerType;
import tfar.huntingandharvestenhancement.init.ModFoliagePlacerTypes;

import java.util.Random;
import java.util.Set;

public class PalmFoliagePlacer extends FoliagePlacer {

    public static final Codec<PalmFoliagePlacer> CODEC = RecordCodecBuilder
            .create((placerInstance) -> func_242830_b(placerInstance).and(FeatureSpread.createCodec(0, 16, 8)
                    .fieldOf("trunk_height").forGetter((p_242835_0_) -> p_242835_0_.trunkHeight))
                    .apply(placerInstance, PalmFoliagePlacer::new));

    private final FeatureSpread trunkHeight;

    public PalmFoliagePlacer(FeatureSpread radius, FeatureSpread offset, FeatureSpread trunkHeight) {
        super(radius, offset);
        this.trunkHeight = trunkHeight;
    }

    @Override
    protected FoliagePlacerType<?> getPlacerType() {
        return ModFoliagePlacerTypes.PALM;
    }

    //createFoliage
    @Override
    protected void func_230372_a_(IWorldGenerationReader world, Random rand, BaseTreeFeatureConfig config, int trunkHeight, Foliage foliage, int foliageHeight, int radius, Set<BlockPos> leaves, int offset, MutableBoundingBox box) {
        BlockPos blockpos = foliage.func_236763_a_();//foliagePos

        buildLayers(world, rand, leaves, blockpos, config);

        /*for(int j = blockpos.getY() - foliageHeight + offset; j <= blockpos.getY() + offset; ++j) {
            int k = blockpos.getY() - j;
            int l = radius + foliage.func_236764_b_() + MathHelper.floor((float)k / (float)foliageHeight * 3.5F);
            int i1;
            if (k > 0 && l == i && (j & 1) == 0) {
                i1 = l + 1;
            } else {
                i1 = l;
            }

            //placeLeavesRow
            this.func_236753_a_(world, rand, config, new BlockPos(blockpos.getX(), j, blockpos.getZ()), i1, leaves, 0, foliage.func_236765_c_()/*doubleTrunk, box);
            i = l;
        }*/
    }


    //0,0,0 is right above the trunk
    protected void buildLayers(IWorldGenerationReader world, Random rand, Set<BlockPos> leaves, BlockPos start, BaseTreeFeatureConfig config) {
        Vector3i[] offsets = new Vector3i[]{
                new Vector3i(-7,-2,-2),
new Vector3i(-7,-1,-2),
new Vector3i(-5,-1,0),
new Vector3i(-4,-1,0),
new Vector3i(-4,0,0),
new Vector3i(-3,0,0),
new Vector3i(-2,0,0),
new Vector3i(-1,-1,0),
new Vector3i(-1,0,0),
new Vector3i(0,-1,-5),
new Vector3i(0,-1,-4),
new Vector3i(0,-1,-1),
new Vector3i(0,-1,1),
new Vector3i(0,-1,4),
new Vector3i(0,-1,5),
new Vector3i(0,0,-4),
new Vector3i(0,0,-3),
new Vector3i(0,0,-2),
new Vector3i(0,0,-1),
new Vector3i(0,0,0),
new Vector3i(0,0,1),
new Vector3i(0,0,2),
new Vector3i(0,0,3),
new Vector3i(0,0,4),
new Vector3i(1,-1,0),
new Vector3i(1,0,0),
new Vector3i(2,0,0),
new Vector3i(3,0,0),
new Vector3i(4,-1,0),
new Vector3i(4,0,0),
new Vector3i(5,-1,0),
        };

        BlockPos.Mutable mutable = new BlockPos.Mutable();

        for (Vector3i vec : offsets) {
            mutable.setAndOffset(start, vec.getX(), vec.getY(), vec.getZ());
            if (TreeFeature.isReplaceableAt(world, mutable)) {
                BlockState state = config.leavesProvider.getBlockState(rand, mutable);
                world.setBlockState(mutable, state, 19);
                leaves.add(mutable.toImmutable());
            }
        }
    }

    //foliageHeight
    @Override
    public int func_230374_a_(Random p_230374_1_, int p_230374_2_, BaseTreeFeatureConfig p_230374_3_) {
        return 0;
    }

    //should skip location
    @Override
    protected boolean func_230373_a_(Random p_230373_1_, int p_230373_2_, int p_230373_3_, int p_230373_4_, int p_230373_5_, boolean p_230373_6_) {
        return false;
    }

    /*
    Foliage

    BlockPos foliagePos
    int radiusOffset
    boolean doubleTrunk
     */
}
