package phlaxyr.masscrafting.recipe;


import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import phlaxyr.masscrafting.Registrar;
import phlaxyr.masscrafting.blocks.BlockRegistrar;
import phlaxyr.masscrafting.items.ItemRegistrar;

public class RecipeRegistrar extends Registrar{
	public static void init() {
		{
			GameRegistry.addRecipe(new ItemStack(BlockRegistrar.block_massBlock), new Object[]{
					"III",
					"III",
					"III",
	                  'I', ItemRegistrar.item_massIngot     // note carefully - 'E' not "E" !
			});
			GameRegistry.addShapelessRecipe(
					new ItemStack(ItemRegistrar.item_massIngot, 9),
					new ItemStack(BlockRegistrar.block_massBlock));
		}
	}
}
