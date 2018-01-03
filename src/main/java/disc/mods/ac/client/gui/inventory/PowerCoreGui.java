package disc.mods.ac.client.gui.inventory;

import disc.mods.core.client.gui.inventory.CoreGui;
import disc.mods.core.inventory.CoreContainer;
import disc.mods.core.tile.CoreTileEntityInventory;
import net.minecraft.entity.player.InventoryPlayer;

public class PowerCoreGui extends CoreGui {

	public PowerCoreGui(CoreContainer container) {
		super(container);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		
		super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
		
		this.drawSlot(10, 10);
		
	}

}
