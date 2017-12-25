package disc.mods.ac.network;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import disc.mods.ac.network.packet.PacketLiquidIO;
import disc.mods.ac.network.packet.PacketTileEntityAM;
import disc.mods.ac.references.Names;

public class PacketHandler
{
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Names.Mod.ID.toLowerCase());

    public static void init()
    {
        INSTANCE.registerMessage(PacketLiquidIO.class, PacketLiquidIO.class, 0, Side.CLIENT);
    }
}