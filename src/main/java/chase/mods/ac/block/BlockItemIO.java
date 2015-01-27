package chase.mods.ac.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import chase.mods.ac.AtomicChem;
import chase.mods.ac.helpers.BlockHelper;
import chase.mods.ac.references.GUIs;
import chase.mods.ac.references.Names;
import chase.mods.ac.tileentity.TEItemIO;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockItemIO extends BlockAC implements ITileEntityProvider
{
	
	private IIcon iconInput;
	private IIcon iconOutput;
	private IIcon iconSide;
	
	public BlockItemIO()
	{
		this.setBlockName(Names.Blocks.ItemIO);
		this.setHardness(2.5f);
	}

	@Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
    {
	    return new TEItemIO();
    }
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
	{
		if (player.isSneaking())
		{
			return this.breakWithHammer(world, player, x, y, z);
		}
		else
		{
			player.openGui(AtomicChem.instance, GUIs.ID.ItemIO.ordinal(), world, x, y, z);
			return true;
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		this.iconInput = iconRegister.registerIcon(Names.Mod.ID + ":input");
		this.iconOutput = iconRegister.registerIcon(Names.Mod.ID + ":output");
		this.iconSide = iconRegister.registerIcon(Names.Mod.ID + ":side");
	}
	
	
	@Override
	public IIcon getIcon(IBlockAccess blockAccess, int x, int y, int z, int side)
	{
		TileEntity tileEntity = blockAccess.getTileEntity(x, y, z);
		int meta = blockAccess.getBlockMetadata(x, y, z);
		ForgeDirection facingDir = BlockHelper.getOrientation(meta);
		if (side == facingDir.ordinal())
		{
			return this.iconInput;
		}
		if (side == facingDir.getOpposite().ordinal())
		{
			return this.iconOutput;
		}
		return this.iconSide;
	}
	
	@Override
	public IIcon getIcon(int side, int meta)
	{
		if (side == 3)
		{
			return this.iconInput;
		}
		else
		{
			return iconSide;
		}
	}
	
}
