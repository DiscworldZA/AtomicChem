package disc.mods.ac.creativetab;

import disc.mods.ac.init.ACBlocks;
import disc.mods.ac.references.Names;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

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
