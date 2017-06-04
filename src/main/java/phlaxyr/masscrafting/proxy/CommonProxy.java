package phlaxyr.masscrafting.proxy;


import net.minecraft.entity.player.EntityPlayer;
import phlaxyr.masscrafting.blocks.BlockRegistrar;
import phlaxyr.masscrafting.items.ItemRegistrar;
import phlaxyr.masscrafting.recipe.RecipeRegistrar;

public abstract class CommonProxy {
	public void preInit() {
		BlockRegistrar.preInit();
		ItemRegistrar.preInit();
		RecipeRegistrar.preInit();

	}
	public void init() {
		BlockRegistrar.init();
		ItemRegistrar.init();
		RecipeRegistrar.init();

	}
	public void postInit() {
		BlockRegistrar.postInit();
		ItemRegistrar.postInit();
		RecipeRegistrar.postInit();

	}
	abstract public boolean playerIsInCreativeMode(EntityPlayer player);

	abstract public boolean isDedicatedServer();
}
