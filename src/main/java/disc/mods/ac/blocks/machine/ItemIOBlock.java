package disc.mods.ac.blocks.machine;

import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ItemIOBlock extends MachineBlock {

	public ItemIOBlock() {
		super("itemio", "machine/itemio");
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
