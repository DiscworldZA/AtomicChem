package chase.mods.ac.block;

import net.minecraft.client.renderer.texture.IIconRegister;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import chase.mods.ac.references.Names;
import chase.mods.ac.references.Textures;

public class BlockMachineCasing extends BlockAC
{
	public BlockMachineCasing()
	{
		this.setHardness(2.5F);
		this.setBlockName(Names.Blocks.MachineCasing);
		this.setTexture(Textures.Block.Side);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		this.blockIcon = iconRegister.registerIcon(Names.Mod.ID + ":side");
	}
}
