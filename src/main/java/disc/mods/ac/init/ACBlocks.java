package disc.mods.ac.init;

import cpw.mods.fml.common.registry.GameRegistry;
import disc.mods.ac.block.*;
import disc.mods.ac.references.Names;

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
