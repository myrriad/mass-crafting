package phlaxyr.masscrafting.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

import phlaxyr.masscrafting.blocks.BlockRegistrarClient;
import phlaxyr.masscrafting.recipe.RecipeRegistrar;

public class ClientProxy extends CommonProxy{
	public void preInit() {
		super.preInit();
		BlockRegistrarClient.preInit();
		RecipeRegistrar.preInit();
	}
	public void init() {
		super.init();
		BlockRegistrarClient.init();
		RecipeRegistrar.init();
	}
	public void postInit() {
		super.postInit();
		BlockRegistrarClient.postInit();
		RecipeRegistrar.postInit();
	}
	
	@Override
	public boolean playerIsInCreativeMode(EntityPlayer player) {
		if (player instanceof EntityPlayerMP) {
			EntityPlayerMP entityPlayerMP = (EntityPlayerMP)player;
			return entityPlayerMP.interactionManager.isCreative();
		} else if (player instanceof EntityPlayerSP) {
			return Minecraft.getMinecraft().playerController.isInCreativeMode();
		}
		return false;
	}
	
	@Override
	public boolean isDedicatedServer() {return false;}
	
}
