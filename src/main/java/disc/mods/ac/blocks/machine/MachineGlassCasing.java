package disc.mods.ac.blocks.machine;

import disc.mods.ac.creativetab.Tabs;
import disc.mods.core.block.CoreBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MachineGlassCasing extends CoreBlock {

	public MachineGlassCasing() {
		super("glasscasing", "machine/glasscasing");
		this.setCreativeTab(Tabs.BlockTab);
		this.setSoundType(SoundType.GLASS);
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

}
