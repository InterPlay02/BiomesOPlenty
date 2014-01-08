package biomesoplenty.common.world.features;

import java.lang.reflect.Field;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.common.world.decoration.IBOPDecoration;
import biomesoplenty.common.world.generation.WorldGeneratorBOP;

public class WorldGenBOPDoubleFlora extends WorldGeneratorBOP
{
    private Block bottomFlora;
    private Block topFlora;
    private int bottomFloraMeta;
    private int topFloraMeta;
    private int groupCount;

    public WorldGenBOPDoubleFlora(Block bottomFlora, Block topFlora, int bottomFloraMeta, int topFloraMeta)
    {
        this(bottomFlora, topFlora, bottomFloraMeta, topFloraMeta, 64);
    }
    
    public WorldGenBOPDoubleFlora(Block bottomFlora, Block topFlora, int bottomFloraMeta, int topFloraMeta, int groupCount)
    {
        this.bottomFlora = bottomFlora;
        this.topFlora = topFlora;
        
        this.bottomFloraMeta = bottomFloraMeta;
        this.topFloraMeta = topFloraMeta;
        
        this.groupCount = groupCount;
    }

    @Override
	public boolean generate(World world, Random random, int x, int y, int z)
    {
        for (int l = 0; l < groupCount; ++l)
        {
            int i1 = x + random.nextInt(8) - random.nextInt(8);
            int j1 = y + random.nextInt(4) - random.nextInt(4);
            int k1 = z + random.nextInt(8) - random.nextInt(8);

            //TODO:	  isAirBlock()																		 canReplace()
            if (world.func_147437_c(i1, j1, k1) && (!world.provider.hasNoSky || j1 < 255) && this.bottomFlora.func_149705_a(world, i1, j1, k1, 0, new ItemStack(bottomFlora, 1, bottomFloraMeta)))
            {
            	//TODO:	setBlock()
                world.func_147465_d(i1, j1, k1, this.bottomFlora, this.bottomFloraMeta, 2);
                //TODO: setBlock()
                world.func_147465_d(i1, j1 + 1, k1, this.topFlora, this.topFloraMeta, 2);
            }
        }

        return true;
    }
    
	@Override
	public void doGeneration(World world, Random random, Field worldGeneratorField, WorldGenerator worldGenerator, BiomeGenBase biome, IBOPDecoration bopDecoration, int x, int z) throws Exception
	{
		for (int i = 0; i < worldGeneratorField.getInt(bopDecoration.getWorldFeatures()); i++)
		{
			int randX = x + random.nextInt(16) + 8;
			int randZ = z + random.nextInt(16) + 8;
			int randY = random.nextInt(world.getHeightValue(randX, randZ) * 2);

			worldGenerator.generate(world, random, randX, randY, randZ);
		}
	}
}