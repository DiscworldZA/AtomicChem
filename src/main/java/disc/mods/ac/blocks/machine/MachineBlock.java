package disc.mods.ac.blocks.machine;

import disc.mods.ac.creativetab.Tabs;
import disc.mods.core.block.CoreBlock;

public abstract class MachineBlock extends CoreBlock {

	public MachineBlock(String name, String resourcePath) {
		super(name, resourcePath);
		this.setCreativeTab(Tabs.BlockTab);
	}

}
