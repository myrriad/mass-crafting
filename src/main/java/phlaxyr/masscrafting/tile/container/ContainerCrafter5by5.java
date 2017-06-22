package phlaxyr.masscrafting.tile.container;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import phlaxyr.masscrafting.crafting.manager.ManagerExtendable;
import phlaxyr.masscrafting.tile.tile.TileEntityInventoryCrafter;

public class ContainerCrafter5by5 extends ContainerCrafterExtendable{

	public ContainerCrafter5by5(InventoryPlayer playerInventory, World worldIn,
			BlockPos posIn, TileEntityInventoryCrafter tile, ManagerExtendable manager) {
		super(playerInventory, worldIn, posIn, tile, manager, 5, 5);
		
	}

	@Override
	public int bodyX() {
		// TODO Auto-generated method stub
		return 12;
	}

	@Override
	public int bodyY() {
		// TODO Auto-generated method stub
		return 106;
	}

	@Override
	public int resultX() {
		// TODO Auto-generated method stub
		return 143;
	}

	@Override
	public int resultY() {
		// TODO Auto-generated method stub
		return 44;
	}

	@Override
	public int gridX() {
		// TODO Auto-generated method stub
		return 21;
	}

	@Override
	public int gridY() {
		// TODO Auto-generated method stub
		return 12;
	}
	
	




}
