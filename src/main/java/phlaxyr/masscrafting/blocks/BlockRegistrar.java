package phlaxyr.masscrafting.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import phlaxyr.masscrafting.Registrar;
import phlaxyr.masscrafting.blocks.withGui.BlockCrafter5by5;

public class BlockRegistrar extends Registrar {
	
	public static BlockSimple block_massBlock;
	public static BlockCrafter5by5 block_mWorkbench;
	
	public static ItemBlock item_massBlock;
	public static ItemBlock item_mWorkbench;
	
	public static final void preInit() {
		block_massBlock = new BlockSimple(Material.IRON, CreativeTabs.MATERIALS);
		block_massBlock.setUnlocalizedName("mass_block");
		block_massBlock.setRegistryName("mass_block");
		GameRegistry.register(block_massBlock);
		
	    item_massBlock = new ItemBlock(block_massBlock);
	    item_massBlock.setRegistryName(block_massBlock.getRegistryName());
	    GameRegistry.register(item_massBlock);
		
	    
	    
	    
		block_mWorkbench = new BlockCrafter5by5(Material.WOOD);
		block_mWorkbench.setUnlocalizedName("m_workbench");
		block_mWorkbench.setRegistryName("m_workbench");
		GameRegistry.register(block_mWorkbench);
		
		item_mWorkbench = new ItemBlock(block_mWorkbench);
		item_mWorkbench.setRegistryName(block_mWorkbench.getRegistryName());
	    GameRegistry.register(item_mWorkbench);
	}
}
