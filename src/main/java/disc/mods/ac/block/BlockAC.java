package disc.mods.ac.block;

import java.util.Random;

import disc.mods.ac.AtomicChem;
import disc.mods.ac.creativetab.CreativeTab;
import disc.mods.ac.helpers.BlockHelper;
import disc.mods.ac.references.Textures;
import disc.mods.core.block.CoreTileEntityBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class BlockAC extends CoreTileEntityBlock
{
    public ResourceLocation textureLocation;

    public BlockAC(String Name)
    {
        super(Material.ROCK, Name);
        this.setCreativeTab(CreativeTab.AC_CTAB);
    }

    public BlockAC(String Name, Material material)
    {
        this(Name);
    }

    public void setTexture(ResourceLocation location)
    {
        this.textureLocation = location;
    }

    public void registerItemModel(ItemBlock itemBlock)
    {
        AtomicChem.proxy.registerItemRenderer(itemBlock, 0);
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }

    @Override
    public String getUnlocalizedName()
    {
        return String.format("tile.%s%s", Textures.RESOURCE_PREFIX, getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        dropInventory(worldIn, pos);
        super.breakBlock(worldIn, pos, state);
    }

    public boolean getUseNeighborBrightness()
    {
        return true;
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLivingBase, ItemStack itemStack)
    {
        ForgeDirection direction = BlockHelper.determineOrientation(x, y, z, entityLivingBase);
        int meta = world.getBlockMetadata(x, y, z);
        world.setBlockMetadataWithNotify(x, y, z, BlockHelper.setOrientation(meta, direction), 2);
    }

    protected void dropInventory(World world, BlockPos pos)
    {
        TileEntity tileEntity = world.getTileEntity(pos);

        if (!(tileEntity instanceof IInventory))
        {
            return;
        }

        IInventory inventory = (IInventory) tileEntity;

        for (int i = 0; i < inventory.getSizeInventory(); i++)
        {
            ItemStack itemStack = inventory.getStackInSlot(i);

            if (itemStack != null && itemStack.getCount() > 0)
            {
                Random rand = new Random();

                float dX = rand.nextFloat() * 0.8F + 0.1F;
                float dY = rand.nextFloat() * 0.8F + 0.1F;
                float dZ = rand.nextFloat() * 0.8F + 0.1F;

                EntityItem entityItem = new EntityItem(world, pos.getX() + dX, pos.getY() + dY, pos.getZ() + dZ, itemStack.copy());

                if (itemStack.hasTagCompound())
                {
                    entityItem.getEntityItem().setTagCompound((NBTTagCompound) itemStack.getTagCompound().copy());
                }

                float factor = 0.05F;
                entityItem.motionX = rand.nextGaussian() * factor;
                entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
                entityItem.motionZ = rand.nextGaussian() * factor;
                world.spawnEntity(entityItem);
                itemStack.setCount(0);
            }
        }
    }

    public boolean breakWithHammer(World world, EntityPlayer player, int x, int y, int z)
    {
        if (player.getHeldItem() != null && player.getHeldItem().getItem() instanceof IToolHammer)
        {
            this.dropBlockAsItem(world, x, y, z, new ItemStack(this));
            world.setBlock(x, y, z, net.minecraft.init.Blocks.air);
            world.removeTileEntity(x, y, z);
            return true;
        }
        return false;
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state)
    {
        // TODO Auto-generated method stub
        return null;
    }

}
