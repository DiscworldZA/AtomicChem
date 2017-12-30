package disc.mods.ac.blocks.machine;

import disc.mods.ac.creativetab.Tabs;
import disc.mods.ac.tile.machine.TileEntityLiquidIO;
import disc.mods.core.block.CoreTileEntityBlock;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class LiquidIOBlock extends CoreTileEntityBlock {

	public LiquidIOBlock() {
		super("liquidio", "machine/liquidio");
		this.setTileEntity(TileEntityLiquidIO.class);
		this.setCreativeTab(Tabs.BlockTab);
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
