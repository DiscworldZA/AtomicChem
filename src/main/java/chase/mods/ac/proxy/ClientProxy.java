package chase.mods.ac.proxy;

import chase.mods.ac.helpers.client.ClientSoundHelper;

public class ClientProxy extends CommonProxy
{
	
	@Override
	public void registerEventHandlers()
	{
		super.registerEventHandlers();
	}
	
	@Override
	public void initRenderingAndTextures()
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void registerKeybindings()
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void playSound(String soundName, float xCoord, float yCoord, float zCoord, float volume, float pitch)
	{
		ClientSoundHelper.playSound(soundName, xCoord, yCoord, zCoord, volume, pitch);
		
	}
	
}
