package chase.mods.ac.block;

import net.minecraft.client.renderer.texture.IIconRegister;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import chase.mods.ac.references.Names;
import chase.mods.ac.references.Textures;

public class BlockMachineGlassCasing extends BlockAC
{
	public BlockMachineGlassCasing()
	{
		this.setHardness(2.5F);
		this.setBlockName(Names.Blocks.MachineGlassCasing);
		this.setTexture(Textures.Block.Side);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		this.blockIcon = iconRegister.registerIcon(Names.Mod.ID + ":sideGlass");
	}
	
    @SideOnly(Side.CLIENT)
    public int getRenderBlockPass()
    {
        return 0;
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }
    
    public boolean isOpaqueCube()
    {
        return false;
    }
}
