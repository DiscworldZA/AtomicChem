package disc.mods.ac.handler;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;
import disc.mods.ac.init.ACBlocks;

public class RecipeHandler
{
	public static void init()
	{
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ACBlocks.blockMachineCasing, 1), true, new Object[] { "sss", "sis", "sss", 's', "ingotLead", 'i', Blocks.iron_block }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ACBlocks.blockMachineGlassCasing, 1), true, new Object[] { "sss", "sis", "sss", 's', "ingotLead", 'i', Blocks.glass }));
	}
}
