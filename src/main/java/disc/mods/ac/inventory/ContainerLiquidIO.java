package disc.mods.ac.inventory;

import disc.mods.ac.network.PacketHandler;
import disc.mods.ac.network.packet.PacketLiquidIO;
import disc.mods.ac.tileentity.TELiquidIO;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraftforge.fluids.FluidStack;

public class ContainerLiquidIO extends ContainerAM
{
	TELiquidIO liquidIO;
	private FluidStack lastFluidStack = null;
	private int lastInternalCapacity = 0;
	private boolean cleared = false;
	
	public ContainerLiquidIO(InventoryPlayer inventory, TELiquidIO tile)
	{
		this.liquidIO = tile;
		this.drawInv(inventory, 0, 0);
		this.addSlot(tile, 0, 20, 32);
		this.addSlot(tile, 1, 60, 32);
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer p_75145_1_)
	{
		return true;
	}
	
	@Override
	public void detectAndSendChanges()
	{
		super.detectAndSendChanges();
		
		boolean changed = false;
		if (liquidIO.getInternalTank().getFluid() != null)
		{
			this.cleared = false;
			if (!liquidIO.getInternalTank().getFluid().isFluidStackIdentical(lastFluidStack))
			{
				changed = true;
				lastFluidStack = liquidIO.getInternalTank().getFluid().copy();
			}
			if (!changed && lastInternalCapacity != liquidIO.getInternalTank().getCapacity())
			{
				changed = true;
				lastInternalCapacity = liquidIO.getInternalTank().getCapacity();
			}
		}
		else
		{
			if(!cleared)
			{
				changed = true;
				cleared = true;
				lastFluidStack = null;
			}
		}
		
		if (changed)
		{
			for (int i = 0; i < this.crafters.size(); ++i)
			{				
				if (this.crafters.get(i) instanceof EntityPlayerMP)
				{
					PacketHandler.INSTANCE.sendTo(new PacketLiquidIO(liquidIO), (EntityPlayerMP) this.crafters.get(i));
				}
			}
		}
	}
	
}
