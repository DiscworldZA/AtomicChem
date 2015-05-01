package chase.mods.testmod.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class MyBlock extends Block
{

	public MyBlock(Material mat)
    {
	    super(mat);
	    this.setCreativeTab(CreativeTabs.tabBlock);
	    this.setBlockName("TestBlock");
	    this.setLightLevel(50f);
    }
}
