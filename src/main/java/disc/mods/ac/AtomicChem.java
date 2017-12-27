package disc.mods.ac;

import disc.mods.ac.init.Blocks;
import disc.mods.ac.proxy.IProxy;
import disc.mods.ac.ref.References;
import disc.mods.core.DiscMod;
import disc.mods.core.proxy.base.IProxyBase;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;

@Mod(modid = References.Mod.Id, name = References.Mod.Name, version = References.Mod.Version, dependencies = References.Mod.DependencyString)
public class AtomicChem extends DiscMod {

	@Instance(References.Mod.Id)
	public static AtomicChem instance;

	@SidedProxy(clientSide = References.Proxy.Client, serverSide = References.Proxy.Server)
	public static IProxy proxy;

	@Override
	public String getModId() {
		return References.Mod.Id;
	}

	@Override
	public IProxyBase proxy() {
		return proxy;
	}

	@Override
	public Class getBlockEnum() {
		return Blocks.class;
	}

}
