package disc.mods.ac.proxy;

import disc.mods.core.proxy.base.IProxyClientBase;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy implements IProxy, IProxyClientBase {

}
