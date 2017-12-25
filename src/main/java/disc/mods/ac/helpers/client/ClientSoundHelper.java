package disc.mods.ac.helpers.client;

import net.minecraft.client.audio.PositionedSoundRecord;
import cpw.mods.fml.client.FMLClientHandler;
import disc.mods.ac.helpers.ResourceLocationHelper;

public class ClientSoundHelper
{
    public static void playSound(String soundName, float xCoord, float yCoord, float zCoord, float volume, float pitch)
    {
        FMLClientHandler.instance().getClient().getSoundHandler().playSound(new PositionedSoundRecord(ResourceLocationHelper.getResourceLocation(soundName), volume, pitch, xCoord, yCoord, zCoord));
    }
}