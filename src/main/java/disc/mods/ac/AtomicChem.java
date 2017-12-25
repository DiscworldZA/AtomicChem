package disc.mods.ac;

import disc.mods.ac.handler.GuiHandler;
import disc.mods.ac.handler.RecipeHandler;
import disc.mods.ac.init.ACBlocks;
import disc.mods.ac.network.PacketHandler;
import disc.mods.ac.proxy.IProxy;
import disc.mods.ac.references.Names;
import disc.mods.ac.tileentity.TELiquidIO;
import disc.mods.core.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = Names.Mod.ID, name = Names.Mod.NAME, version = Names.Mod.VERSION)
public class AtomicChem
{
	@Instance(Names.Mod.ID)
	public static AtomicChem instance;
	
	@SidedProxy(clientSide = Names.Proxy.CLIENT_CLASS, serverSide = Names.Proxy.SERVER_CLASS)
	public static CommonProxy proxy;
	
	@EventHandler
	public void onServerStarting(FMLServerStartingEvent event)
	{
		// Register Commands
	}
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		//Init Config
		//Init PacketHandler
		PacketHandler.init();
		//Init Items
		//Init Blocks
		ACBlocks.init();		
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
		//Register Tile Entities
		GameRegistry.registerTileEntity(TELiquidIO.class, "tile" + Names.Blocks.LiquidIO);
		//Init Custom Rendering and Textures
		//Register EventHandlers
		//Init CraftingHandler
		//Init RecipeHandler
		RecipeHandler.init();
		//Register FuelHandler
	}
	
}
