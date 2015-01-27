package chase.mods.ac.init;

import chase.mods.ac.block.*;
import chase.mods.ac.references.Names;
import cpw.mods.fml.common.registry.GameRegistry;

public class ACBlocks
{
	public static final BlockAC TestingBlock = new BlockTesting();
	public static final BlockAC blockLiquidIO = new BlockLiquidIO();
	public static final BlockAC blockItemIO = new BlockItemIO();
	public static final BlockAC blockMachineCasing = new BlockMachineCasing();
	public static final BlockAC blockMachineGlassCasing = new BlockMachineGlassCasing();
	
	
	public static void init()
	{
		GameRegistry.registerBlock(TestingBlock, "TestBlock");
		GameRegistry.registerBlock(blockLiquidIO, Names.Blocks.LiquidIO);
		GameRegistry.registerBlock(blockItemIO, Names.Blocks.ItemIO);
		GameRegistry.registerBlock(blockMachineCasing, Names.Blocks.MachineCasing);
		GameRegistry.registerBlock(blockMachineGlassCasing, Names.Blocks.MachineGlassCasing);
	}
}
