package tan.core;

import net.minecraft.item.Item;
import tan.api.ContentRegistry;
import tan.configuration.TANConfigurationIDs;
import tan.items.ItemTANCanteen;
import tan.items.ItemTANFreshWaterBottle;
import tan.items.ItemTANMiscItems;
import tan.items.ItemTANThermometer;
import cpw.mods.fml.common.registry.GameRegistry;

public class TANItems
{
	public static Item canteen;
    public static Item thermometer;
    public static Item freshWaterBottle;
    public static Item miscItems;
    
    public static void init()
    {
        initializeItems();
        registerItems();
    }
    
    private static void initializeItems()
    {
    	canteen = new ItemTANCanteen(TANConfigurationIDs.canteenID).setUnlocalizedName("tan.canteen");
        thermometer = new ItemTANThermometer(TANConfigurationIDs.thermometerID).setUnlocalizedName("tan.thermometer");
        freshWaterBottle = new ItemTANFreshWaterBottle(TANConfigurationIDs.freshWaterBottleID).setUnlocalizedName("tan.freshWaterBottle");
        miscItems = new ItemTANMiscItems(TANConfigurationIDs.miscItemsID).setUnlocalizedName("tan.miscItems");
    }
    
    private static void registerItems()
    {
    	registerItem(canteen);
        registerItem(thermometer);
        registerItem(freshWaterBottle);
        registerItem(miscItems);
    }
    
    public static void registerItem(Item item)
    {
        String name = item.getUnlocalizedName().replace("item.", "");
        
        GameRegistry.registerItem(item, name);
        ContentRegistry.addItem(name, item);
    }
}
