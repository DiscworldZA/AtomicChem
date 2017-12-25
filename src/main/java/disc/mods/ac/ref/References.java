package disc.mods.ac.ref;

public class References {
	public static class Mod {
		public static final String Id = "ac";
		public static final String Name = "Atomic Chem";
		public static final String Version = "@VERSION@";
		public static final String DependencyString = "required-after:disccore";
	}
	
	public static class Proxy{
		private static final String root = "disc.mods.ac.proxy.";
		public static final String Server = root + "ServerProxy";
		public static final String Client = root + "ClientProxy";
		public static final String Common = root + "CommonProxy";
	}
}
