package disc.mods.ac.inventory;

import disc.mods.ac.tileentity.TEItemIO;
import disc.mods.ac.tileentity.TELiquidIO;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;

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
