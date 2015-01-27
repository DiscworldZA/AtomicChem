package chase.mods.ac.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import chase.mods.ac.tileentity.TEItemIO;
import chase.mods.ac.tileentity.TELiquidIO;

public class ContainerItemIO extends ContainerAM
{
	TEItemIO itemIO;
		
	public ContainerItemIO(InventoryPlayer inventory, TEItemIO tile)
	{
		this.itemIO = tile;
		this.drawInv(inventory, 0, 0);
		this.addSlot(tile, 0, 78, 32);
	}
}
