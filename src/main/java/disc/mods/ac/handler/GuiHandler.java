package disc.mods.ac.handler;

import disc.mods.ac.client.gui.inventory.GuiItemIO;
import disc.mods.ac.client.gui.inventory.GuiLiquidIO;
import disc.mods.ac.inventory.ContainerItemIO;
import disc.mods.ac.inventory.ContainerLiquidIO;
import disc.mods.ac.references.GUIs;
import disc.mods.ac.tileentity.TEItemIO;
import disc.mods.ac.tileentity.TELiquidIO;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tile = world.getTileEntity(x, y, z);	
		
		if (ID == GUIs.ID.LiquidIO.ordinal())
		{
			return new ContainerLiquidIO(player.inventory, (TELiquidIO) tile);
		}
		if(ID == GUIs.ID.ItemIO.ordinal())
		{
			return new ContainerItemIO(player.inventory, (TEItemIO) tile);
		}
		return null;
	}
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tile = world.getTileEntity(x, y, z);
		
		if (ID == GUIs.ID.LiquidIO.ordinal())
		{
			return new GuiLiquidIO(player.inventory, (TELiquidIO) tile);
		}		
		if(ID == GUIs.ID.ItemIO.ordinal())
		{
			return new GuiItemIO(player.inventory, (TEItemIO) tile);
		}
		return null;
	}
	
}
