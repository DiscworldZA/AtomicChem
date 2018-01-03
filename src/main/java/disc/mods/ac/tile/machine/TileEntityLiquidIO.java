package disc.mods.ac.tile.machine;

import disc.mods.ac.multiblock.IMultiBlockIO;
import disc.mods.ac.multiblock.MultiBlock.MultiBlockPartState;
import disc.mods.core.fluid.CoreFluidTank;
import disc.mods.core.tile.CoreTileEntity;
import disc.mods.core.util.EnumSide;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.FluidTankProperties;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;

public class TileEntityLiquidIO extends CoreTileEntity implements IMultiBlockIO, IFluidHandler {

	protected MultiBlockPartState partState;
	protected NonNullList<IFluidHandler> internalTanks = NonNullList.<IFluidHandler>create();

	protected CoreFluidTank internalTank = new CoreFluidTank() {

		@Override
		public int getCapacity() {
			return 4000;
		}
	};

	public TileEntityLiquidIO() {
		partState = MultiBlockPartState.Invalid;
	}

	@Override
	public void update() {
		/*
		 * if (!isFormed()) return;
		 */
		formMultiBlock(MultiBlockPartState.Output);
		if (partState == MultiBlockPartState.Input) {
			if (!this.internalTank.isEmpty()) {
				int drained = tryFillInternal(this.internalTank.getFluid());
				this.internalTank.drain(drained, true);
			}
		}
		else if (partState == MultiBlockPartState.Output) {
			if (!isInternalTanksEmpty()) {
				IFluidHandler tankToDrain = getFirstTank();
				FluidStack fluidStack = tankToDrain.drain(4000, true);
				int filled = internalTank.fill(fluidStack, true);
				tankToDrain.fill(new FluidStack(fluidStack.getFluid(), fluidStack.amount - filled), true);
			}
		}
		clearMultiBlock();
	}

	private boolean isTankEmpty(IFluidHandler handler) {
		for (int i = 0; i < handler.getTankProperties().length; i++) {
			FluidStack stack = handler.getTankProperties()[i].getContents();
			if (stack != null && stack.amount != 0) return false;
		}
		return true;
	}

	public boolean isInternalTanksEmpty() {
		return internalTanks.stream().allMatch(x -> isTankEmpty(x));
	}

	public IFluidHandler getFirstTank() {
		return internalTanks.stream().filter(x -> !isTankEmpty(x)).findFirst().get();
	}

	private int tryFillInternal(FluidStack fluid) {
		int totalFilled = 0;
		for (IFluidHandler fluidHandler : internalTanks) {
			int filled = fluidHandler.fill(fluid, true);
			totalFilled += filled;
			fluid.amount -= filled;
			if (fluid.amount == 0) return totalFilled;
		}
		return totalFilled;
	}

	@Override
	public boolean formMultiBlock(MultiBlockPartState state) {
		this.setMultiBlockPartState(state);
		BlockPos behindBlock = state == MultiBlockPartState.Input ? EnumSide.Back.getBlockAtSide(this)
				: EnumSide.Front.getBlockAtSide(this);
		for (int i = 0; i < 5; i++) {
			TileEntity te = world.getTileEntity(behindBlock.add(0, i, 0));
			if (te instanceof IFluidHandler) {
				internalTanks.add((IFluidHandler) te);
			}
			else if (te != null) {
				if (te.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)) {
					internalTanks.add(te.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null));
				}
			}
		}
		this.partState = state;
		return false;
	}

	@Override
	public boolean clearMultiBlock() {
		internalTanks.clear();
		this.partState = MultiBlockPartState.Invalid;
		return false;
	}

	@Override
	public void setMultiBlockPartState(MultiBlockPartState state) {
		this.partState = state;
	}

	@Override
	public MultiBlockPartState getMultiBlockPartState() {
		return partState;
	}

	@Override
	public IFluidTankProperties[] getTankProperties() {
		return new IFluidTankProperties[] {
				new FluidTankProperties(internalTank.getFluid(), internalTank.getCapacity(), true, true) };
	}

	@Override
	public int fill(FluidStack resource, boolean doFill) {
		return internalTank.fill(resource, doFill);
	}

	@Override
	public FluidStack drain(FluidStack resource, boolean doDrain) {
		return internalTank.drain(resource.amount, doDrain);
	}

	@Override
	public FluidStack drain(int maxDrain, boolean doDrain) {
		return internalTank.drain(maxDrain, doDrain);
	}

	protected NonNullList<EnumSide> getHandlingSides() {
		return NonNullList.<EnumSide>from(null, EnumSide.Front, EnumSide.Back);
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY
				&& getHandlingSides().stream().anyMatch(x -> x.matches(facing, this))) {
			return true;
		}
		return super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY
				&& getHandlingSides().stream().anyMatch(x -> x.matches(facing, this))) {
			return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(this);
		}
		return super.getCapability(capability, facing);
	}
}
