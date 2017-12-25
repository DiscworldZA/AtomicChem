package disc.mods.ac.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class TETestingBlock extends TEBlockAM
{
	
	@Override
	public void updateEntity()
	{
		TileEntity tile = this.worldObj.getTileEntity(this.xCoord, this.yCoord + 1, this.zCoord);
		if (tile instanceof IFluidHandler)
		{
			FluidTankInfo[] info = ((IFluidHandler) tile).getTankInfo(ForgeDirection.UP);
			if (info != null)
			{
				for (FluidTankInfo tankInfo : info)
				{
					System.out.println(tankInfo.toString());
				}
			}
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound NBTTagCompound)
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void writeToNBT(NBTTagCompound NBTTagCompound)
	{
		// TODO Auto-generated method stub
		
	}
	
}
