package chase.mods.ac.client.gui.inventory;

import net.minecraft.entity.player.InventoryPlayer;
import chase.mods.ac.inventory.ContainerItemIO;
import chase.mods.ac.inventory.ContainerLiquidIO;
import chase.mods.ac.tileentity.TEItemIO;
import chase.mods.ac.tileentity.TELiquidIO;

public class GuiItemIO extends GuiAM
{
	private TEItemIO itemIO;
	
	public GuiItemIO(InventoryPlayer player, TEItemIO tile)
	{
		super(new ContainerItemIO(player, tile));
		this.itemIO = tile;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
	{
		this.drawTitle(itemIO.getName());		
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
		super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
		this.drawSlot(78, 32);
	}
	
}
