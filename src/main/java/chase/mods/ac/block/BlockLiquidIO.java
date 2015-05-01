package chase.mods.ac.block;

import net.minecraft.block.BlockLiquid;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import chase.mods.ac.AtomicChem;
import chase.mods.ac.helpers.BlockHelper;
import chase.mods.ac.references.GUIs;
import chase.mods.ac.references.Names;
import chase.mods.ac.references.Textures;
import chase.mods.ac.tileentity.TELiquidIO;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockLiquidIO extends BlockAC implements ITileEntityProvider
{
	private IIcon iconInput;
	private IIcon iconOutput;
	private IIcon iconSide;
	
	public BlockLiquidIO()
	{
		this.setBlockName(Names.Blocks.LiquidIO);
		this.setHardness(2.5f);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int metaData)
	{
		return new TELiquidIO();
	}
	
	public void onBlockDestroyedByExplosion(World world, int x, int y, int z, Explosion p_149723_5_)
	{
		TileEntity tile = world.getTileEntity(x, y, z);
		if (tile != null && tile instanceof TELiquidIO)
		{
			TELiquidIO lIO = (TELiquidIO) tile;
			if (lIO.getInternalTank().getCapacity() > 0 && lIO.getInternalTank().getFluid().getFluid().getBlock() != null)
			{
				world.setBlock(x, y, z, lIO.getInternalTank().getFluid().getFluid().getBlock());
			}
		}
	}
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
	{
		if (player.isSneaking())
		{
			return this.breakWithHammer(world, player, x, y, z);
		}
		if (player.getHeldItem() != null)
		{
			ItemStack item = player.getHeldItem();
			TileEntity tile = world.getTileEntity(x, y, z);
			if (tile != null && tile instanceof TELiquidIO)
			{
				TELiquidIO lIO = (TELiquidIO) tile;
				if (FluidContainerRegistry.isBucket(item) && FluidContainerRegistry.isFilledContainer(item))
				{
					if (world.isRemote)
					{
						return true;
					}
					FluidStack fluid = FluidContainerRegistry.getFluidForFilledItem(item);
					if (lIO.canFill(ForgeDirection.UP, fluid.getFluid()))
					{
						lIO.fill(BlockHelper.getOrientation(lIO.blockMetadata).getOpposite(), fluid, true);
						FluidContainerRegistry.drainFluidContainer(player.getHeldItem());
						if (!player.capabilities.isCreativeMode)
						{
							player.setCurrentItemOrArmor(0, FluidContainerRegistry.EMPTY_BUCKET);
						}
						return true;
					}
				}
				else
				{
					player.openGui(AtomicChem.instance, GUIs.ID.LiquidIO.ordinal(), world, x, y, z);
					return true;
				}
				
			}
		}
		else
		{
			//player.openGui(AtomicChem.instance, GUIs.ID.LiquidIO.ordinal(), world, x, y, z);
		}
		return false;
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
