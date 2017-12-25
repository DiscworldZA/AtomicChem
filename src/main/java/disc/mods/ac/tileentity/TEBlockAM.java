package disc.mods.ac.tileentity;

import disc.mods.ac.references.Names;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public abstract class TEBlockAM extends TileEntity
{
    public ForgeDirection orientation;
    public String name;
    public String customName;
    public String owner;
	
	
	public TEBlockAM()
	{
        orientation = ForgeDirection.SOUTH;
        customName = "";
        owner = "";
	}
	

    public ForgeDirection getOrientation()
    {
        return orientation;
    }

    public void setOrientation(ForgeDirection orientation)
    {
        this.orientation = orientation;
    }

    public void setOrientation(int orientation)
    {
        this.orientation = ForgeDirection.getOrientation(orientation);
    }
    
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = "container."+ Names.Mod.ID + "." + name;
    }
    
    
    public String getCustomName()
    {
        return customName;
    }

    public void setCustomName(String customName)
    {
        this.customName = customName;
    }

    public String getOwner()
    {
        return owner;
    }

    public void setOwner(String owner)
    {
        this.owner = owner;
    }
    
    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        super.readFromNBT(nbtTagCompound);

        if (nbtTagCompound.hasKey(Names.NBT.Direction))
        {
            this.orientation = ForgeDirection.getOrientation(nbtTagCompound.getByte(Names.NBT.Direction));
        }

        if (nbtTagCompound.hasKey(Names.NBT.CustomName))
        {
            this.customName = nbtTagCompound.getString(Names.NBT.CustomName);
        }

        if (nbtTagCompound.hasKey(Names.NBT.Owner))
        {
            this.owner = nbtTagCompound.getString(Names.NBT.Owner);
        }
    }
    
    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);

        nbtTagCompound.setByte(Names.NBT.Direction, (byte) orientation.ordinal());

        if (this.hasCustomName())
        {
            nbtTagCompound.setString(Names.NBT.CustomName, customName);
        }

        if (this.hasOwner())
        {
            nbtTagCompound.setString(Names.NBT.Owner, owner);
        }
    }
    
    public boolean hasCustomName()
    {
        return customName != null && customName.length() > 0;
    }

    public boolean hasOwner()
    {
        return owner != null && owner.length() > 0;
    }
	
	public abstract void updateEntity();
}
