package chase.mods.ac.tileentity;

import net.minecraft.init.Items;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.fluids.FluidRegistry.FluidRegisterEvent;
import chase.mods.ac.helpers.BlockHelper;
import chase.mods.ac.references.Names;

public class TELiquidIO extends TEBlockAMInventory implements IFluidHandler
{
	private int internalCapacity = 2000;
	private int internalAmount = 0;
	private int externalCapacity;
	private int externalAmount;
	private FluidTank internalTank = new FluidTank(1000);
	private IFluidHandler externalTank;
	
	public TELiquidIO()
	{
		this.setName(Names.Blocks.LiquidIO);
		this.createInventory(2);
		if (this.internalCapacity != 0)
		{
			internalTank.setCapacity(internalCapacity);
		}
		if (this.internalAmount != 0 && this.internalTank.getFluid() != null)
		{
			this.internalTank.setFluid(new FluidStack(this.internalTank.getFluid().getFluid(), this.internalAmount));
		}
	}
	
	@Override
	public void updateEntity()
	{
		if (!this.worldObj.isRemote)
		{
			if (internalTank.getFluidAmount() > 0 && this.hasExternalTank() && externalTank.canFill(ForgeDirection.DOWN, this.internalTank.getFluid().getFluid()))
			{
				FluidStack fluidStack = this.internalTank.getFluid();
				int sended = this.externalTank.fill(ForgeDirection.DOWN, new FluidStack(internalTank.getFluid(), internalTank.getFluidAmount()), true);
				this.internalTank.drain(sended, true);
			}
			
			if (this.hasExternalTank())
			{
				FluidTankInfo[] array = this.externalTank.getTankInfo(ForgeDirection.UP);
				if (array != null && array[0] != null)
				{
					FluidTankInfo info = array[0];
					if (info.capacity != 0)
					{
						externalCapacity = info.capacity;
					}
					if (info.fluid != null)
					{
						externalAmount = info.fluid.amount;
					}
				}
			}
			if (this.getStackInSlot(0) != null)
			{
				ItemStack item = this.getStackInSlot(0);
				ItemStack item2 = this.getStackInSlot(1);
				if (FluidContainerRegistry.isBucket(item) && FluidContainerRegistry.isFilledContainer(item))
				{
					if (this.canFill(ForgeDirection.UP, FluidContainerRegistry.getFluidForFilledItem(item).getFluid()) && item2 == null)
					{
						this.internalTank.fill(FluidContainerRegistry.getFluidForFilledItem(item), true);
						this.setInventorySlotContents(0, null);
						this.setInventorySlotContents(1, new ItemStack(Items.bucket));
					}
				}
				else if (FluidContainerRegistry.isEmptyContainer(item) && internalTank.getFluidAmount() > 0)
				{
					FluidStack drained = this.internalTank.drain(FluidContainerRegistry.getContainerCapacity(internalTank.getFluid(), item), false);
					if (drained.amount == FluidContainerRegistry.getContainerCapacity(internalTank.getFluid(), item))
					{
						ItemStack filledItem = FluidContainerRegistry.fillFluidContainer(this.internalTank.getFluid(), item);
						this.internalTank.drain(FluidContainerRegistry.getContainerCapacity(internalTank.getFluid(), item), true);
						this.setInventorySlotContents(1, filledItem);
						this.setInventorySlotContents(0, null);
					}
				}
			}
		}
	}
	
	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack)
	{
		if (index == 0 && stack != null)
		{
			if (FluidContainerRegistry.isBucket(stack) || FluidContainerRegistry.isFilledContainer(stack))
			{
				return true;
			}
		}
		return false;
	}
	
	public boolean hasExternalTank()
	{
		int meta = this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord);
		ForgeDirection facingDir = BlockHelper.getOrientation(meta);
		int x = this.xCoord + facingDir.offsetX;
		int y = this.yCoord + facingDir.offsetY;
		int z = this.zCoord + facingDir.offsetZ;
		TileEntity tile = this.worldObj.getTileEntity(x, y, z);
		if (tile != null && tile instanceof IFluidHandler && !(tile instanceof TELiquidIO))
		{
			int checkY = this.yCoord;
			while (this.worldObj.getTileEntity(x, checkY, z) instanceof IFluidHandler)
			{
				checkY++;
			}
			IFluidHandler fluidHandler = (IFluidHandler) this.worldObj.getTileEntity(x, checkY - 1, z);
			FluidTankInfo[] tankInfo = fluidHandler.getTankInfo(ForgeDirection.UP);
			if (tankInfo != null && tankInfo.length > 0)
			{
				this.externalTank = fluidHandler;
				return true;
			}
		}
		return false;
	}
	
	public IFluidHandler getExternalTank()
	{
		return this.externalTank;
	}
	
	public FluidTank getInternalTank()
	{
		return this.internalTank;
	}
	
	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill)
	{
		int meta = this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord);
		ForgeDirection facingDir = BlockHelper.getOrientation(meta).getOpposite();
		if (facingDir == from)
		{
			if (resource != null)
			{
				int test = this.internalTank.fill(resource, doFill);
				return test;
			}
		}
		return 0;
	}
	
	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain)
	{
		int meta = this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord);
		ForgeDirection facingDir = BlockHelper.getOrientation(meta).getOpposite();
		if (facingDir.getOpposite() == from || facingDir == from)
		{
			if (resource != null)
			{
				return this.internalTank.drain(resource.amount, doDrain);
			}
		}
		return null;
	}
	
	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain)
	{
		int meta = this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord);
		ForgeDirection facingDir = BlockHelper.getOrientation(meta);
		if (facingDir.getOpposite() == from || facingDir == from)
		{
			return this.internalTank.drain(maxDrain, doDrain);
		}
		return null;
	}
	
	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid)
	{
		int meta = this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord);
		ForgeDirection facingDir = ForgeDirection.UP;
		if (facingDir == from)
		{
			if (internalTank.getFluid() == null || internalTank.getFluid().getFluid() == null)
			{
				return true;
			}
			else
			{
				return internalTank.getFluid().getFluid() == fluid && this.internalTank.getCapacity() > this.internalTank.getFluidAmount();
			}
		}
		
		return false;
	}
	
	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid)
	{
		int meta = this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord);
		ForgeDirection facingDir = BlockHelper.getOrientation(meta);
		if (facingDir.getOpposite() == from || facingDir == from)
		{
			return true;
		}
		return false;
	}
	
	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from)
	{
		int meta = this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord);
		ForgeDirection facingDir = BlockHelper.getOrientation(meta);
		if (facingDir == from || facingDir == from.getOpposite())
		{
			FluidTankInfo[] info = new FluidTankInfo[1];
			info[0] = this.internalTank.getInfo();
			return info;
		}
		return new FluidTankInfo[0];
	}
	
	@Override
	public void readFromNBT(NBTTagCompound NBTTag)
	{
		super.readFromNBT(NBTTag);
		
		int fluidID = 0;
		if (NBTTag != null)
		{
			if (NBTTag.hasKey("internalcapacity")) this.internalCapacity = NBTTag.getInteger("internalcapacity");
			if (NBTTag.hasKey("currentinternalcapacity")) this.internalAmount = NBTTag.getInteger("currentinternalcapacity");
			if (NBTTag.hasKey("fluidID")) fluidID = NBTTag.getInteger("fluidID");
		}
		this.internalTank.setCapacity(internalCapacity);
		if (NBTTag.hasKey("fluidID"))
		{
			this.internalTank.setFluid(new FluidStack(fluidID, this.internalAmount));
		}
		else
		{
			this.internalTank.setFluid(null);
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound NBTTag)
	{
		super.writeToNBT(NBTTag);
		
		if (NBTTag == null)
		{
			NBTTag = new NBTTagCompound();
		}
		NBTTag.setInteger("internalcapacity", this.internalTank.getCapacity());
		if (this.internalTank.getFluid() != null)
		{
			NBTTag.setInteger("currentinternalcapacity", this.internalTank.getFluidAmount());
			NBTTag.setInteger("fluidID", this.internalTank.getFluid().getFluid().getID());
		}
		else
		{
			NBTTag.setInteger("currentinternalcapacity", 0);
		}
	}
	
}
