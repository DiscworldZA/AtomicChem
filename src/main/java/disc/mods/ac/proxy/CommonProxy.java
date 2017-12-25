package disc.mods.ac.proxy;

import disc.mods.ac.references.Names;
import disc.mods.ac.tileentity.TEItemIO;
import disc.mods.ac.tileentity.TELiquidIO;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public abstract class CommonProxy extends disc.mods.core.proxy.CommonProxy
{

    @Override
    public void preInit(FMLPreInitializationEvent event)
    {
        super.preInit(event);
        GameRegistry.registerTileEntity(TELiquidIO.class, "tile" + Names.Blocks.LiquidIO);
        GameRegistry.registerTileEntity(TEItemIO.class, "tile" + Names.Blocks.ItemIO);

    }
}
