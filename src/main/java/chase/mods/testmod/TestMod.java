package chase.mods.testmod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import chase.mods.testmod.block.MyBlock;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;


@Mod(modid = "tm", name = "Test Mod", version = "0.1 alpha")
public class TestMod
{
	@Instance("tm")
	public static TestMod instance;
	
	public static Block testBlock = new MyBlock(Material.ground);
	
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		GameRegistry.registerBlock(testBlock, "testBlock");
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		
	}
}
