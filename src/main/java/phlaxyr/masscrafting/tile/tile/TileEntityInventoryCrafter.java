package phlaxyr.masscrafting.tile.tile;

import java.util.Arrays;

import javax.annotation.Nullable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;

public abstract class TileEntityInventoryCrafter extends TileEntity implements IInventory{
	
	ItemStack result;
	ItemStack[] items;
	
	public TileEntityInventoryCrafter(int rows, int columns) {
		items = new ItemStack[rows*columns];
		
		//instantiates all in items
		clear();
		
	}
	/*<--STANDARD SH-->T*/
	public abstract String getName();
	public boolean hasCustomName() {return false;}
	public int getInventoryStackLimit() {return 64;}
	@Override
	public ITextComponent getDisplayName() {
		return this.hasCustomName() ? 
				new TextComponentString(this.getName()) 
				: new TextComponentTranslation(this.getName());
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		if (this.worldObj.getTileEntity(this.pos) != this) return false;
		final double X_CENTRE_OFFSET = 0.5;
		final double Y_CENTRE_OFFSET = 0.5;
		final double Z_CENTRE_OFFSET = 0.5;
		final double MAXIMUM_DISTANCE_SQ = 8.0 * 8.0;
		return player.getDistanceSq(pos.getX() + X_CENTRE_OFFSET, pos.getY() + Y_CENTRE_OFFSET, pos.getZ() + Z_CENTRE_OFFSET) < MAXIMUM_DISTANCE_SQ;
	}
	/*<--STANDARD SH-->T*/
	public int getSizeInventory() {return items.length + 1;}	
	/*<--HOPPERS AND SHIFTING-->*/
	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return true;
	}
	@Override
	public ItemStack getStackInSlot(int index) {
//        System.out.println("getStackInSlot "+index);
		if(index == 0) {
			return result;
		}
		return items[index-1];
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
        System.out.println(index);
//		System.out.println("decrStackSize "+index);
		markDirty();
        if(index == 0){

        	//if there is a crafting recipe available
            if(isntEmpty(result)){
            	
            	/*
            	//decrease everything in crafting grid
                for(int x = 1;x <= items.length;x++){
                    decrStackSize(x, 1);
                }
                //*/
                
                if(result.stackSize <= count) {
                	//moves result into return value
                    ItemStack craft = result;
                    result = null;
                    return craft;
                }
                ItemStack split = result.splitStack(count);
                if(result.stackSize <= 0)
                    result = null;
                return split;
            }
            else
                return null;
        }
        return ItemStackHelper.getAndSplit(this.items, index-1, count);
	}

    @Nullable
    public ItemStack removeStackFromSlot(int index)
    {
        System.out.println(index);
    	if(index == 0) {
            if(isntEmpty(result)){
                for(int x = 1;x <= items.length;x++){
                	//since the result _should_ always be of size 1, this works
                    decrStackSize(x, 1);
                }

                ItemStack craft = result;
                result = null;
                markDirty();
                return craft;

            }
            else
                return null;
    	}
        return ItemStackHelper.getAndRemove(this.items, index-1);
    }

    //TODO NO PROBLEMO -- PROBABLY
	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		if(index == 0) {
			result = stack;
		} else {
			items[index-1] = stack;
		
			//over the limit
			if (isntEmpty(stack) && stack.stackSize > getInventoryStackLimit()) { //  isEmpty(); getStackSize()
				stack.stackSize = this.getInventoryStackLimit();  //setStackSize
			}
		}
		markDirty();
	}



	
	/*<--NBT SH-->T*/
	@Override
    public void readFromNBT(NBTTagCompound tag) {
        result = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("Result"));
        
        NBTTagList list = tag.getTagList("Items", /**of type nbt compound*/10);
        for(int x = 0;x < list.tagCount();x++) {
        	
        	ItemStack itemstak = ItemStack.loadItemStackFromNBT(list.getCompoundTagAt(x));
            items[list.getCompoundTagAt(x).getByte("Slot")] = itemstak;
        }
    }
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound tag) {
    	super.writeToNBT(tag);
    	NBTTagList dataForAllSlots = new NBTTagList();
    	
    	//result
    	if(isntEmpty(result)) {
    		NBTTagCompound nbtResult = new NBTTagCompound();
    		result.writeToNBT(nbtResult);
    		tag.setTag("Result", nbtResult);
    	}
    	else tag.removeTag("Result");
    	//crafting grid
		for (int i = 0; i < this.items.length; ++i) {
			if (isntEmpty(this.items[i]))	{ //isEmpty()
				NBTTagCompound nbtCraft = new NBTTagCompound();
				nbtCraft.setByte("Slot", (byte) i);
				items[i].writeToNBT(nbtCraft);
				dataForAllSlots.appendTag(nbtCraft);
			}
		}
		// the array of hashmaps is then inserted into the parent hashmap for the container
		tag.setTag("Items", dataForAllSlots);
		
		/* Items [
		 * 	 Craft1b{...},
		 *   Craft2b{...},
		 *   Craft3b{...},
		 *   ...
		 *   Craft25b{...},
		 * ],
		 * Result{...}
		 */
		// return the NBT Tag Compound
		return tag;
    }
    /*<--NBT SH-->T*/
    
    
    /*<--USEFUL U-->TILS*/
	public void clear() {
		result = null;
		Arrays.fill(items, null);  //empty item
	}
	public boolean isEmpty(ItemStack item) {
		return item == null;
	}
	public boolean isntEmpty(ItemStack item) {
		return !isEmpty(item);
	}    
    /*<--USEFUL U-->TILS*/
	
	
	/*<--USELESS SH-->T*/
	public void openInventory(EntityPlayer player) {}
	public void closeInventory(EntityPlayer player) {}
	public int getField(int i){return 0;}public void setField(int i, int v){}public int getFieldCount(){return 0;}
}
