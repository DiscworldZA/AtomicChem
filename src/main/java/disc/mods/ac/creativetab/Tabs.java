package disc.mods.ac.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class Tabs {
	public static final CreativeTabs BlockTab = new CreativeTabs("ac.TabBlocks") {
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(Items.CLOCK);
		}
	};
}
