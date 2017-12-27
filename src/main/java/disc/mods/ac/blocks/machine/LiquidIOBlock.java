package disc.mods.ac.blocks.machine;

import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class LiquidIOBlock extends MachineBlock {

	public LiquidIOBlock() {
		super("liquidio", "machine/liquidio");
	}

	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return null;
	}

	@Override
	public boolean canRotate() {
		return true;
	}

	@Override
	public boolean canRotateVertically() {
		return true;
	}
}
