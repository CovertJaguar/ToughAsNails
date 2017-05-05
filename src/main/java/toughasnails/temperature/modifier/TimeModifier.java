package toughasnails.temperature.modifier;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import toughasnails.api.temperature.Temperature;
import toughasnails.temperature.TemperatureDebugger;
import toughasnails.temperature.TemperatureDebugger.Modifier;
import toughasnails.temperature.TemperatureTrend;
import toughasnails.util.BiomeUtils;

import static toughasnails.util.TimeUtil.F.*;

public class TimeModifier extends TemperatureModifier
{
    public static final int TIME_TARGET_MODIFIER = 7;
    
    /**
     * Multiplies how much should the temperature be increased/decreased by the closer the
     * biome temp is to a extreme hot or cold
     */
    public static final float EXTREMITY_MULTIPLIER = 1.25F;
    
    public TimeModifier(TemperatureDebugger debugger)
    {
        super(debugger);
    }
    
    @Override
    public int modifyChangeRate(World world, EntityPlayer player, int changeRate, TemperatureTrend trend)
    {
        return changeRate;
    }

    @Override
    public Temperature modifyTarget(World world, EntityPlayer player, Temperature temperature)
    {
        Biome biome = world.getBiomeGenForCoords(player.getPosition());
        long worldTime = world.getWorldTime();
        
        float extremityModifier = BiomeUtils.getBiomeTempExtremity(biome);
        //Reaches the highest point during the middle of the day and at midnight. Normalized to be between -1 and 1
        float timeOfDay = worldTime % TICKS_TOTAL;
        float timeNorm;
        if(timeOfDay < TICKS_IN_DAY) {
            //day time
            timeNorm = (-Math.abs(timeOfDay - TICKS_TO_NOON) + TICKS_TO_NOON) / TICKS_TO_NOON;
        } else {
            //night time
            timeNorm = (Math.abs(timeOfDay - TICKS_IN_DAY - TICKS_TO_MIDNIGHT) - TICKS_TO_MIDNIGHT) / TICKS_TO_MIDNIGHT;
        }
        //float timeNormOld = (-Math.abs(((worldTime + 6000) % 24000.0F) - 12000.0F) + 6000.0F) / 6000.0F;

        int temperatureLevel = temperature.getRawValue();
        int newTemperatureLevel = temperatureLevel;

        debugger.start(Modifier.TIME_TARGET, newTemperatureLevel);
        
        if (world.provider.isSurfaceWorld())
        {
        	newTemperatureLevel += TIME_TARGET_MODIFIER * timeNorm * (Math.max(1.0F, extremityModifier * EXTREMITY_MULTIPLIER));
        }
        
        debugger.end(newTemperatureLevel);
        
        return new Temperature(newTemperatureLevel);
    }
}
