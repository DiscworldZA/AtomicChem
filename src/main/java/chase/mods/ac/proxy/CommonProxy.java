package chase.mods.ac.proxy;

import chase.mods.ac.references.Names;
import chase.mods.ac.tileentity.*;
import cpw.mods.fml.common.registry.GameRegistry;

public abstract class CommonProxy implements IProxy
{

	@Override
    public void registerTileEntities()
    {
		 GameRegistry.registerTileEntity(TELiquidIO.class, "tile" + Names.Blocks.LiquidIO);
		 GameRegistry.registerTileEntity(TEItemIO.class, "tile" + Names.Blocks.ItemIO);
	    
    }

	@Override
    public void registerEventHandlers()
    {
	    // TODO Auto-generated method stub
	    
    }	
}
