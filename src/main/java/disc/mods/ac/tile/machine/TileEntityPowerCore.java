package disc.mods.ac.tile.machine;

import disc.mods.core.DiscCore;
import disc.mods.core.tile.CorePoweredTileEntity;
import disc.mods.core.util.EnumSide;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;

public class TileEntityPowerCore extends CorePoweredTileEntity {

	public TileEntityPowerCore() {
		this.setMaxEnergy(20000000);
		this.setInputRate(20000);
		this.setOutputRate(0);
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == CapabilityEnergy.ENERGY && EnumSide.Down.matches(facing, this)) {
			return true;
		}
		return super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityEnergy.ENERGY && EnumSide.Down.matches(facing, this)) {
			return CapabilityEnergy.ENERGY.cast(this);
		}
		return super.getCapability(capability, facing);
	}

	@Override
	public boolean canExtract() {
		return false;
	}

	@Override
	public boolean canReceive() {
		return true;
	}

	@Override
	public void update() {
	}

	@Override
	public int getSlots() {
		return 0;
	}

	@Override
	public NonNullList<EnumSide> getItemHandlingSides() {
		return NonNullList.<EnumSide>create();
	}

}
