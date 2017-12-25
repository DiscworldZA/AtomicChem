package disc.mods.ac.client.gui.inventory;

import java.awt.Color;
import java.awt.Point;

import org.lwjgl.opengl.GL11;

import disc.mods.ac.inventory.ContainerLiquidIO;
import disc.mods.ac.references.Colors;
import disc.mods.ac.references.Names;
import disc.mods.ac.references.Textures;
import disc.mods.ac.tileentity.TELiquidIO;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

public class GuiLiquidIO extends GuiAM
{
	private TELiquidIO liquidIO;
	
	public GuiLiquidIO(InventoryPlayer player, TELiquidIO tile)
	{
		super(new ContainerLiquidIO(player, tile));
		this.liquidIO = tile;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
	{
		this.drawTitle(liquidIO.getName());		
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
		super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
		this.drawLiquidBar(this.liquidIO,120, 20);
		this.drawSlot(20, 32);
		this.drawSlot(60, 32);
	}
	
}
