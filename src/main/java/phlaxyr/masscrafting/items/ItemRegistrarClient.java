package phlaxyr.masscrafting.items;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import phlaxyr.masscrafting.Registrar;

public class ItemRegistrarClient extends Registrar {
	public static void preInit() {
		ModelResourceLocation itemModelResourceLocation = new ModelResourceLocation(
				"masscrafting:itemModel_massIngot", "inventory");
		final int DEFAULT_ITEM_SUBTYPE = 0;
		ModelLoader.setCustomModelResourceLocation(ItemRegistrar.item_massIngot, DEFAULT_ITEM_SUBTYPE,
				itemModelResourceLocation);
	}
}
