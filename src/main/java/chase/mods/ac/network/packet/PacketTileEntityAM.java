package chase.mods.ac.network.packet;

import io.netty.buffer.ByteBuf;
import chase.mods.ac.tileentity.TEBlockAM;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PacketTileEntityAM implements IMessage, IMessageHandler<PacketTileEntityAM, IMessage>
{
	
	public int x, y, z;
	
	public PacketTileEntityAM()
	{
		
	}
	
	public PacketTileEntityAM(TEBlockAM tile)
	{
        this.x = tile.xCoord;
        this.y = tile.yCoord;
        this.z = tile.zCoord;;
	}
	

	@Override
    public void fromBytes(ByteBuf buf)
    {
        this.x = buf.readInt();
        this.y = buf.readInt();
        this.z = buf.readInt();
    }

	@Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
    }

	@Override
    public IMessage onMessage(PacketTileEntityAM message, MessageContext ctx)
    {
	    return null;
    }
	
}
