package chase.mods.ac.tileentity;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import chase.mods.ac.helpers.BlockHelper;
import chase.mods.ac.references.Names;

public class TEItemIO extends TEBlockAMInventory implements ISidedInventory
{
	private List<IInventory> externalInventory = new ArrayList<IInventory>();
	
	public TEItemIO()
	{
		this.setName(Names.Blocks.ItemIO);
		this.createInventory(1);
	}
	
	@Override
	public void updateEntity()
	{
		if (!this.worldObj.isRemote)
		{
			if (this.getStackInSlot(0) != null && this.hasExternalInventory())
			{
				ItemStack stack = this.getStackInSlot(0);
				boolean placed = false;
				int chestIndex = 0;
				while (!placed)
				{
					IInventory chest = this.externalInventory.get(chestIndex);
					if (chest.getSizeInventory() > 0)
					{
						for (int i = 0; i < chest.getSizeInventory(); i++)
						{
							ItemStack stackToPlace = this.getStackInSlot(0);
							ItemStack stackInChest = chest.getStackInSlot(i);
							if (chest.getStackInSlot(i) == null)
							{
								chest.setInventorySlotContents(i, stackToPlace);
								this.setInventorySlotContents(0, null);
								placed = true;
								break;
								
							}
							else if (chest.isItemValidForSlot(i, stack) && stackInChest.getItem() == stackToPlace.getItem())
							{
								int maxStackSize = stackInChest.getMaxStackSize();
								int stackSizeInChest = stackInChest.stackSize;
								int stackSizeToPlace = stackToPlace.stackSize;
								int stackLeft = (stackSizeInChest + stackSizeToPlace) > maxStackSize ? (stackSizeInChest + stackSizeToPlace - maxStackSize) : maxStackSize
								        - (stackSizeInChest + stackSizeToPlace);
								if (stackLeft > 0)
								{
									this.setInventorySlotContents(0, new ItemStack(stackInChest.getItem(), stackLeft));
									chest.setInventorySlotContents(i, new ItemStack(stackInChest.getItem(), maxStackSize));
								}
								else
								{
									this.setInventorySlotContents(0, null);
									chest.setInventorySlotContents(i, new ItemStack(stackInChest.getItem(), maxStackSize + stackLeft));
									placed = true;
									break;
								}
							}
						}
					}
					if(chestIndex + 1 != this.externalInventory.size())
					{
						chestIndex++;
					}
					else
					{
						break;
					}
				}
			}
		}
	}
	
	public boolean hasExternalInventory()
	{
		int meta = this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord);
		ForgeDirection facingDir = BlockHelper.getOrientation(meta);
		int x = this.xCoord + facingDir.getOpposite().offsetX;
		int y = this.yCoord + facingDir.getOpposite().offsetY;
		int z = this.zCoord + facingDir.getOpposite().offsetZ;
		TileEntity tile = this.worldObj.getTileEntity(x, y, z);
		if (tile != null && tile instanceof IInventory && !(tile instanceof TEItemIO))
		{
			int checkY = this.yCoord;
			while (this.worldObj.getTileEntity(x, checkY, z) instanceof IInventory)
			{
				IInventory chest = (IInventory) this.worldObj.getTileEntity(x, checkY, z);
				if (chest != null && chest.getSizeInventory() > 0)
				{
					this.externalInventory.add(chest);
				}
				checkY++;
			}
			return true;
			
		}
		return false;
	}
	
	@Override
	public int[] getAccessibleSlotsFromSide(int side)
	{
		int meta = this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord);
		ForgeDirection facingDir = BlockHelper.getOrientation(meta);
		if (facingDir.ordinal() == side || facingDir.getOpposite().ordinal() == side)
		{
			return new int[] { 0 };
		}
		return new int[0];
	}
	
	@Override
	public boolean canInsertItem(int slotIndex, ItemStack stack, int side)
	{
		int meta = this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord);
		ForgeDirection facingDir = BlockHelper.getOrientation(meta);
		if (facingDir.ordinal() == side)
		{
			if (this.getStackInSlot(slotIndex) == null || this.getStackInSlot(slotIndex).isItemEqual(stack))
			{
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean canExtractItem(int slotIndex, ItemStack stack, int side)
	{
		int meta = this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord);
		ForgeDirection facingDir = BlockHelper.getOrientation(meta);
		if (facingDir.ordinal() == side || facingDir.getOpposite().ordinal() == side)
		{
			if (this.getStackInSlot(slotIndex).isItemEqual(stack))
			{
				return true;
			}
		}
		return false;
	}
	
}
