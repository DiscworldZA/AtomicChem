package chase.mods.ac;

import chase.mods.ac.handler.GuiHandler;
import chase.mods.ac.handler.RecipeHandler;
import chase.mods.ac.init.ACBlocks;
import chase.mods.ac.network.PacketHandler;
import chase.mods.ac.proxy.IProxy;
import chase.mods.ac.references.Names;
import chase.mods.ac.tileentity.TELiquidIO;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = Names.Mod.ID, name = Names.Mod.NAME, version = Names.Mod.VERSION)
public class AtomicChem
{
	@Instance(Names.Mod.ID)
	public static AtomicChem instance;
	
	@SidedProxy(clientSide = Names.Proxy.CLIENT_CLASS, serverSide = Names.Proxy.SERVER_CLASS)
	public static IProxy proxy;
	
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
