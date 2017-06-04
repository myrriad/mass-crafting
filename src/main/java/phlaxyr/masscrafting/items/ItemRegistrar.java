package phlaxyr.masscrafting.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.registry.GameRegistry;
import phlaxyr.masscrafting.Registrar;

public class ItemRegistrar extends Registrar {
	public static ItemSimple item_massIngot;
	
	public static void preInit() {
		item_massIngot = new ItemSimple(CreativeTabs.MATERIALS);
		item_massIngot.setUnlocalizedName("mass_ingot");
		item_massIngot.setRegistryName("mass_ingot");
		GameRegistry.register(item_massIngot);
		
	}
}
