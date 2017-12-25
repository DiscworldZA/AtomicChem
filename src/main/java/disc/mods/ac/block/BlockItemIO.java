package disc.mods.ac.block;

import disc.mods.ac.AtomicChem;
import disc.mods.ac.helpers.BlockHelper;
import disc.mods.ac.references.GUIs;
import disc.mods.ac.references.Names;
import disc.mods.ac.tileentity.TEItemIO;
import disc.mods.core.block.CoreTileEntityBlock;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockItemIO extends CoreTileEntityBlock
{

    private IIcon iconInput;
    private IIcon iconOutput;
    private IIcon iconSide;

    public BlockItemIO()
    {
        super(Names.Blocks.ItemIO);
        this.setHardness(2.5f);
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
            return this.iconOutput;
        }
        if (side == facingDir.getOpposite().ordinal())
        {
            return this.iconInput;
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

    @Override
    public TileEntity createTileEntity(World world, IBlockState state)
    {
        return new TEItemIO();
    }

}
