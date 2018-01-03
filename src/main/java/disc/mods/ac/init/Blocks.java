package disc.mods.ac.init;

import disc.mods.ac.blocks.machine.*;
import disc.mods.core.block.CoreBlock;
import disc.mods.core.init.IDiscBlocks;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public enum Blocks implements IDiscBlocks {
	ItemIO(ItemIOBlock.class), LiquidIO(LiquidIOBlock.class), Casing(MachineCasing.class), GlassCasing(
			MachineGlassCasing.class), PowerCore(PowerCoreBlock.class);

	private final Class<? extends CoreBlock> blockClass;
	private final Class<? extends ItemBlock> itemBlockClass;
	private Block block;

	Blocks(Class<? extends CoreBlock> blockClass) {
		this(blockClass, ItemBlock.class);
	}

	Blocks(Class<? extends CoreBlock> blockClass, Class<? extends ItemBlock> itemBlockClass) {
		this.blockClass = blockClass;
		this.itemBlockClass = itemBlockClass;
	}

	@Override
	public Class<? extends CoreBlock> getBlockClass() {
		return blockClass;
	}

	@Override
	public Class<? extends ItemBlock> getItemBlockClass() {
		return itemBlockClass;
	}

	@Override
	public void setBlock(Block block) {
		this.block = block;
	}

	@Override
	public Block getBlock() {
		return this.block;
	}

}
