package phlaxyr.masscrafting.container;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ContainerCraftFive extends ContainerCraftExtendable{

	public ContainerCraftFive(InventoryPlayer playerInventory, World worldIn, BlockPos posIn) {
		super(playerInventory, worldIn, posIn);
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public InventoryCrafting getMatrix() {
		return new InventoryCrafting(this, 5, 5);
		
	}

}
