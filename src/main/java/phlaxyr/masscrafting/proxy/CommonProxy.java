package phlaxyr.masscrafting.proxy;


import net.minecraft.entity.player.EntityPlayer;
import phlaxyr.masscrafting.blocks.BlockRegistrar;

public abstract class CommonProxy {
	public void preInit() {
		BlockRegistrar.preInit();
	}
	public void init() {
		BlockRegistrar.init();
	}
	public void postInit() {
		BlockRegistrar.postInit();
	}
	abstract public boolean playerIsInCreativeMode(EntityPlayer player);

	abstract public boolean isDedicatedServer();
}
