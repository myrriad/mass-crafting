package phlaxyr.masscrafting.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import phlaxyr.masscrafting.Registrar;

public class ItemRegistrar extends Registrar {
	public static ItemSimple item_massIngot, item_massNugget, item_energyNugget;
	
	public static void preInit() {
		item_massIngot = new ItemSimple(CreativeTabs.MATERIALS);
		item_massIngot.setUnlocalizedName("mass_ingot");
		item_massIngot.setRegistryName("mass_ingot");
		GameRegistry.register(item_massIngot);
		
		item_massNugget = new ItemSimple(CreativeTabs.MATERIALS);
		item_massNugget.setUnlocalizedName("mass_nugget");
		item_massNugget.setRegistryName("mass_nugget");
		GameRegistry.register(item_massNugget);
		
		item_energyNugget = new ItemSimple(CreativeTabs.MATERIALS);
		item_energyNugget.setUnlocalizedName("energy_nugget");
		item_energyNugget.setRegistryName("energy_nugget");
		GameRegistry.register(item_energyNugget);
	}
	
	public static void init() {
		OreDictionary.registerOre("ingotMass", item_massIngot);
		OreDictionary.registerOre("nuggetMass", item_massNugget);
		
		OreDictionary.registerOre("nuggetEnergy", item_energyNugget);
	}
}
