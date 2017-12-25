package disc.mods.ac.proxy;

import disc.mods.core.proxy.base.IProxyBase;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public interface IProxy extends IProxyBase {

	@Override
	default void registerCapabilities() {

	}

	@Override
	default void initConfiguration(FMLPreInitializationEvent event) {

	}

	@Override
	default void registerEventHandlers() {

	}
}
