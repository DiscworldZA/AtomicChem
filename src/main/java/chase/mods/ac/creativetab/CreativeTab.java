package chase.mods.ac.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import chase.mods.ac.init.ACBlocks;
import chase.mods.ac.references.Names;

public class CreativeTab
{
	public static final CreativeTabs AC_CTAB = new CreativeTabs(Names.Mod.ID.toLowerCase())
	{
		@Override
		public Item getTabIconItem()
		{
			return Item.getItemFromBlock(ACBlocks.blockMachineGlassCasing);
		}
	};
}
