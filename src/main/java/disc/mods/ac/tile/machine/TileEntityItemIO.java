package disc.mods.ac.tile.machine;

import disc.mods.ac.multiblock.IMultiBlockIO;
import disc.mods.ac.multiblock.MultiBlock.MultiBlockPartState;
import disc.mods.core.tile.CoreTileEntityInventory;
import disc.mods.core.util.EnumSide;
import disc.mods.core.util.InventoryHelper;
import disc.mods.core.util.ItemHandlerConverter;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class TileEntityItemIO extends CoreTileEntityInventory implements IMultiBlockIO {

	protected MultiBlockPartState partState;
	protected NonNullList<IItemHandler> internalInventories = NonNullList.<IItemHandler>create();

	public TileEntityItemIO() {
		this.setMultiBlockPartState(MultiBlockPartState.Invalid);
	}

	@Override
	public void update() {
		/*
		 * if (!isFormed()) return;
		 */

		formMultiBlock(MultiBlockPartState.Output);
		if (partState == MultiBlockPartState.Input) {
			if (!isEmpty()) setStackInSlot(0, tryPutInternal(getStackInSlot(0)));
		}
		else if (partState == MultiBlockPartState.Output) {

			if (!isInventoriesEmpty()) {
				InventorySlotContents contents = this.getFirstStackInInventories();
				ItemStack afterInsert = this.insertItem(0, contents.removeStack(), false);
				contents.replaceStack(afterInsert);
			}
		}
		clearMultiBlock();
	}

	private boolean isInventoryEmpty(IItemHandler inv) {
		for (int i = 0; i < inv.getSlots(); i++) {
			if (!inv.getStackInSlot(i).isEmpty()) return false;
		}
		return true;
	}

	private boolean isInventoriesEmpty() {
		return internalInventories.stream().allMatch(x -> isInventoryEmpty(x));
	}

	private InventorySlotContents getFirstStackInInventories() {
		IItemHandler inv = internalInventories.stream().filter(x -> !isInventoryEmpty(x)).findFirst().get();
		for (int i = 0; i < inv.getSlots(); i++) {
			ItemStack stack = inv.getStackInSlot(i);
			if (!stack.isEmpty()) return new InventorySlotContents(inv, i);
		}
		return null;
	}

	@Override
	public boolean formMultiBlock(MultiBlockPartState state) {
		this.setMultiBlockPartState(state);
		BlockPos behindBlock = state == MultiBlockPartState.Input ? EnumSide.Back.getBlockAtSide(this)
				: EnumSide.Front.getBlockAtSide(this);
		for (int i = 0; i < 5; i++) {
			TileEntity te = world.getTileEntity(behindBlock.add(0, i, 0));
			if (te instanceof IInventory) {
				internalInventories.add(new ItemHandlerConverter((IInventory) te));
			}
			else if (te != null) {
				if (te.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null)) {
					internalInventories.add(te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null));
				}
			}
		}
		return false;
	}

	@Override
	public boolean clearMultiBlock() {
		internalInventories.clear();
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

	private ItemStack tryPutInternal(ItemStack stack) {
		for (IItemHandler inventory : internalInventories) {
			stack = InventoryHelper.Add(inventory, stack);
			if (stack.isEmpty()) return stack;
		}
		return stack;
	}

	private ItemStack tryPutExternal(ItemStack stack) {
		return this.insertItem(0, stack, false);
	}

	@Override
	public int getSlots() {
		return 1;
	}

	@Override
	public NonNullList<EnumSide> getItemHandlingSides() {
		return NonNullList.<EnumSide>from(null, EnumSide.Front, EnumSide.Back);
	}

	private class InventorySlotContents {

		IItemHandler handler;
		int slot;

		public InventorySlotContents(IItemHandler handler, int slot) {
			this.handler = handler;
			this.slot = slot;
		}

		public ItemStack removeStack() {
			int amount = handler.getStackInSlot(slot).getCount();
			return handler.extractItem(slot, amount, false);
		}

		public ItemStack replaceStack(ItemStack stack) {
			if (!handler.getStackInSlot(slot).isEmpty()) removeStack();
			return handler.insertItem(slot, stack, false);
		}

	}
}
