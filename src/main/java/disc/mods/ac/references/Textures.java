package disc.mods.ac.references;

import disc.mods.ac.helpers.ResourceLocationHelper;
import net.minecraft.util.ResourceLocation;

public class Textures
{
	public static final String RESOURCE_PREFIX = Names.Mod.ID.toLowerCase() + ":";
	
	public static class Gui
	{
		public static final ResourceLocation BaseGui = ResourceLocationHelper.getGuiLocation("BaseGUI.png");
		public static final ResourceLocation Slot = ResourceLocationHelper.getGuiLocation("Slot.png");
		public static final ResourceLocation PowerBar = ResourceLocationHelper.getGuiLocation("PowerBar.png");
		public static final ResourceLocation LiquidBar = ResourceLocationHelper.getGuiLocation("LiquidBar.png");
		public static final ResourceLocation LiquidBarOverlay = ResourceLocationHelper.getGuiLocation("LiquidBarOverlay.png");
	}
	
	public static class Block
	{
		public static final ResourceLocation Side = ResourceLocationHelper.getBlockLocation("side.png");
		public static final ResourceLocation Input = ResourceLocationHelper.getBlockLocation("input.png");
		public static final ResourceLocation Output = ResourceLocationHelper.getBlockLocation("output.png");
	}
}
