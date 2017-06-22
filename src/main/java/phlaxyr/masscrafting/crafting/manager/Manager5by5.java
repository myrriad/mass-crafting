package phlaxyr.masscrafting.crafting.manager;

import net.minecraft.item.ItemStack;

import phlaxyr.masscrafting.crafting.recipe.RecipeExtendable;
import phlaxyr.masscrafting.items.ItemRegistrar;

public class Manager5by5 extends ManagerExtendable{

	static {
		INSTANCE = new Manager5by5();
	}

	public static ManagerExtendable getInstance() {
		return INSTANCE;
	}
	
	private Manager5by5() {
		super();
	}
	
	@Override
	public RecipeExtendable constructRecipe(int width, int height, ItemStack[] items, ItemStack output) {
		return new RecipeExtendable(width, height, items, output, 5, 5);
	}

	@Override
	public void addRecipes() {
		this.addRecipe(new ItemStack(ItemRegistrar.item_massIngot), new Object[]{
				"..E..",
				".NNN.",
				".NNN.",
				".NNN.",
				"E...E",
				
                  'E', ItemRegistrar.item_energyNugget,
                  'N', ItemRegistrar.item_massNugget// note carefully - 'E' not "E" !
		});
	}
		

	
}
