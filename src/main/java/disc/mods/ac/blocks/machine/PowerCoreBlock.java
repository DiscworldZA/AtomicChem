package disc.mods.ac.blocks.machine;

import disc.mods.ac.client.gui.inventory.PowerCoreGui;
import disc.mods.ac.creativetab.Tabs;
import disc.mods.ac.inventory.PowerCoreContainer;
import disc.mods.ac.tile.machine.TileEntityPowerCore;
import disc.mods.core.block.CoreTileEntityBlock;
import disc.mods.core.util.PlayerUtils;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PowerCoreBlock extends CoreTileEntityBlock {

	public PowerCoreBlock() {
		super("powercore", "machine/powercore");
		this.setTileEntity(TileEntityPowerCore.class);
		this.setCreativeTab(Tabs.BlockTab);
		this.registerGUI(PowerCoreGui.class, PowerCoreContainer.class);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (worldIn.isRemote && hand == EnumHand.MAIN_HAND) {
			TileEntityPowerCore pc = (TileEntityPowerCore) worldIn.getTileEntity(pos);
			PlayerUtils.sendMessage(playerIn, "Power: " + pc.getEnergyStored());
		}
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
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
