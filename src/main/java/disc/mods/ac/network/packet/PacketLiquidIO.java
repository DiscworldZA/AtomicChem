package disc.mods.ac.network.packet;

import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import disc.mods.ac.tileentity.TELiquidIO;

public class PacketLiquidIO implements IMessage, IMessageHandler<PacketLiquidIO, IMessage>
{
	public int x, y, z;
	public FluidStack fluidStack;
	public int capacity;
	
	public PacketLiquidIO()
	{
	}
	
	public PacketLiquidIO(TELiquidIO tile)
	{
		if (tile != null)
		{
			this.x = tile.xCoord;
			this.y = tile.yCoord;
			this.z = tile.zCoord;			
			fluidStack = tile.getInternalTank().getFluid();
			capacity = tile.getInternalTank().getCapacity();
		}
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		this.x = buf.readInt();
		this.y = buf.readInt();
		this.z = buf.readInt();
		if (buf.readBoolean())
		{
			this.fluidStack = new FluidStack(buf.readInt(), buf.readInt());
			if (buf.readBoolean())
			{
				this.fluidStack.tag = ByteBufUtils.readTag(buf);
			}
		}
		else
		{
			this.fluidStack = null;
		}

		this.capacity = buf.readInt();
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		if(this.fluidStack != null)
		{
			buf.writeBoolean(true);
			buf.writeInt(this.fluidStack.fluidID);
			buf.writeInt(this.fluidStack.amount);
			if (this.fluidStack.tag != null)
			{
				buf.writeBoolean(true);
				ByteBufUtils.writeTag(buf, this.fluidStack.tag);
			}
			else
			{
				buf.writeBoolean(false);
			}
		}
		else
		{
			buf.writeBoolean(false);
		}

		buf.writeInt(this.capacity);
	}
	
	@Override
	public IMessage onMessage(PacketLiquidIO message, MessageContext ctx)
	{
		if (FMLClientHandler.instance().getClient().theWorld.isRemote)
		{
			TileEntity tile = FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.x, message.y, message.z);
			if (tile instanceof TELiquidIO)
			{
				TELiquidIO liquidIO = (TELiquidIO) tile;
				liquidIO.getInternalTank().setCapacity(message.capacity);
				liquidIO.getInternalTank().setFluid(message.fluidStack);
			}
		}
		return null;
	}
}
