package phlaxyr.masscrafting.blocks;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import phlaxyr.masscrafting.Registrar;

public class BlockRegistrarClient extends Registrar{
	  public static void preInit()
	  {
	    // This step is necessary in order to make your block render properly when it is an item (i.e. in the inventory
	    //   or in your hand or thrown on the ground).
	    // It must be done on client only, and must be done after the block has been created in Common.preinit().
	    ModelResourceLocation itemModelResourceLocation = new ModelResourceLocation("masscrafting:mass_block", "inventory");
	    final int DEFAULT_ITEM_SUBTYPE = 0;
	    ModelLoader.setCustomModelResourceLocation(BlockRegistrar.item_massBlock, DEFAULT_ITEM_SUBTYPE, itemModelResourceLocation);
	    
	  }
}
