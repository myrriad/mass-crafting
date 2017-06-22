package phlaxyr.masscrafting.tile.inventory;

import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import phlaxyr.masscrafting.tile.tile.TileEntityInventoryCrafter;


/**
 * 
 * "repurposed" from avaritia's dire crafting table
 * */
public class InventoryTileCrafting extends InventoryCrafting {

	private TileEntityInventoryCrafter craft;
	private Container container;

	
	//remember that slot is between 0 and gridsize-1, whereas the container index is between 1 and gridSize!
	public InventoryTileCrafting(Container cont, int x, int y, TileEntityInventoryCrafter table){
	        super(cont, x, y);
	        craft = table;
	        container = cont;
	    }

	@Override
	public ItemStack getStackInSlot(int slot /*0-25*/) {
		
		return slot >= this.getSizeInventory() ? null : craft.getStackInSlot(slot+1);
	}

	@Override
	public ItemStack getStackInRowAndColumn(int x, int y) {
		if (x >= 0 && x < this.getWidth() && y >=0 && y < this.getHeight()) {
			int intx = x + y * this.getWidth();
			return this.getStackInSlot(intx);
		} else {
			return null;
		}
	}

	/*
	 * @Override public ItemStack getStackInSlotOnClosing (int par1) { return
	 * null; }
	 */

	
	
	@Override
	public ItemStack decrStackSize(int slot, int decrement) {
		ItemStack stack = craft.getStackInSlot(slot+1);
		if (stack != null) {
			ItemStack itemstack;
			if (stack.stackSize <= decrement) {
				itemstack = stack.copy();
				stack = null;
				craft.setInventorySlotContents(slot+1, null);
				this.container.onCraftMatrixChanged(this);
				return itemstack;
			} else {
				itemstack = stack.splitStack(decrement);
				if (stack.stackSize == 0) {
					stack = null;
                    craft.setInventorySlotContents(slot+1, null);
				}
				this.container.onCraftMatrixChanged(this);
				return itemstack;
			}
		} else {
			return null;
		}
	}

	//TODO NO PROBLEMO -- PROBABLY
	@Override
	public void setInventorySlotContents(int slot, ItemStack itemstack) {
		//Remember -- this is the grid
		craft.setInventorySlotContents(slot+1, itemstack);
		this.container.onCraftMatrixChanged(this);
	}

}
