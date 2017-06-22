package phlaxyr.masscrafting.tile.inventory;


import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.item.ItemStack;
import phlaxyr.masscrafting.tile.tile.TileEntityInventoryCrafter;

public class InventoryTileCraftResult extends InventoryCraftResult{
    private TileEntityInventoryCrafter craft;

    public InventoryTileCraftResult(TileEntityInventoryCrafter table){
        craft = table;
    }

    @Override
    public ItemStack getStackInSlot (int par1)
    {
        return craft.getStackInSlot(0);
    }

    //triggers whenever slot is clicked
    //and whenever container.transferstackinslot is called
    @Override
    public ItemStack decrStackSize (int slot, int count)
    {
        ItemStack stack = craft.getStackInSlot(0);
        if (stack != null)
        {
            ItemStack itemstack = stack;
            craft.setInventorySlotContents(0, null);
            return itemstack;
        }
        else
        {
            return null;
        }
    }

    /*@Override
    public ItemStack getStackInSlotOnClosing (int par1)
    {
        return null;
    }*/

    @Override
    public void setInventorySlotContents (int par1, ItemStack par2ItemStack)
    {
        craft.setInventorySlotContents(0, par2ItemStack);
    }
}
