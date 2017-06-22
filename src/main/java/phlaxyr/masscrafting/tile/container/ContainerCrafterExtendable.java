package phlaxyr.masscrafting.tile.container;

import javax.annotation.Nullable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import phlaxyr.masscrafting.blocks.withGui.BlockCrafter;
import phlaxyr.masscrafting.crafting.manager.ManagerExtendable;
import phlaxyr.masscrafting.tile.inventory.InventoryTileCraftResult;
import phlaxyr.masscrafting.tile.inventory.InventoryTileCrafting;
import phlaxyr.masscrafting.tile.tile.TileEntityInventoryCrafter;

public abstract class ContainerCrafterExtendable extends Container
{
	/** COPY-PASTA'D */
    /** The crafting matrix inventory, except adjustable. */
    private InventoryTileCrafting craftMatrix; 

    
    private final ManagerExtendable MANAGER;
    
    public IInventory craftResult;
    private final World worldObj;
    /** Position of the workbench */
    private final BlockPos pos;
    
    /**measured at the upper left corner (inside the black border)*/
    public abstract int bodyX();
    /**measured at the upper left corner (inside the black border)*/
    public abstract int bodyY();
    /**measured at the upper left corner (inside the black border)*/
    public int hotbarY() {
    	return bodyY() + 58;
    }
    /**measured at the upper left corner (inside the black border)*/
    public int hotbarX() {
    	return bodyX();
    }
    /**measured at the upper left corner (inside the black border)*/
    public abstract int resultX();
    /**measured at the upper left corner (inside the black border)*/
    public abstract int resultY();
    /**measured at the upper left corner (inside the black border)*/
    public abstract int gridX();
    /**measured at the upper left corner (inside the black border)*/
    public abstract int gridY();
    public ContainerCrafterExtendable(InventoryPlayer playerInventory, World worldIn, 
    		BlockPos posIn, TileEntityInventoryCrafter tile, ManagerExtendable manager, 
    		int gridX, int gridY)
    {
    	MANAGER = manager;
    	
    	craftResult = new InventoryTileCraftResult(tile);
    	craftMatrix = new InventoryTileCrafting(this, gridX, gridY, tile);
    	
        this.worldObj = worldIn;
        this.pos = posIn;
        
        //crafting output
        this.addSlotToContainer(new SlotCrafting(playerInventory.player, this.craftMatrix, this.craftResult,
        		
        		
        		0, resultX()+4, resultY()+4) {
        	
        });
        
        
        //crafting grid
        for (int i = 0; i < gridX; ++i)
        {
            for (int j = 0; j < gridY; ++j)
            {
                this.addSlotToContainer(new Slot(this.craftMatrix, j + i * gridY, 
                		
                		gridX() + j * 18, gridY() + i * 18));
            }
        }
        
        
        //main inventory
        for (int k = 0; k < 3; ++k) //rows
        {
            for (int i1 = 0; i1 < 9; ++i1) //slots in each row
            {
                this.addSlotToContainer(new Slot(playerInventory, /*slot in row*/i1 + /*row*/k * 9 + /*hotbar slots*/9, 
                		
                		bodyX() + i1 * 18, bodyY() + k * 18));
            }
        }

        //hotbar
        for (int l = 0; l < 9; ++l)
        {
            this.addSlotToContainer(new Slot(playerInventory, l, hotbarX() + l * 18, hotbarY()));
        }

        this.onCraftMatrixChanged(this.craftMatrix);
    }
    
    public boolean canInteractWith(EntityPlayer playerIn) {
        return this.worldObj.getBlockState(this.pos).getBlock() 
        		instanceof BlockCrafter ? withinVicinity(playerIn) : false;
    }

    
    //
    /**
     * Take a stack from the specified inventory slot.
     * Is called whenever something is quick-moved.
     */
    @Nullable
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
        System.out.println(index);
    	ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(index);
        
        int slotOffset = (craftMatrix.getWidth()*craftMatrix.getHeight())+1;
        
        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            
            
            if (index == 0)
            {
           	/*
            	
            	int success = decrementGridAndGetSuccessCount(64);
            	
            	itemstack1.stackSize += success * itemstack1.stackSize;
            	
            	//*/
            	if (!this.mergeItemStack(itemstack1, slotOffset, slotOffset+36, true)) {
                	//shift crafting output into inventory
                    return null;
                }

                //if only some have been shifted, notify everything that something has been crafted
                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (index >= slotOffset && index < slotOffset+27) //main, excluding hotbar
            {
                if (!this.mergeItemStack(itemstack1, slotOffset+27, slotOffset+36, false)) 
                	//shift from main inventory into hotbar
                {
                    return null;
                }
            }
            else if (index >= slotOffset+27 && index < slotOffset+36) //hotbar
            {
                if (!this.mergeItemStack(itemstack1, slotOffset, slotOffset+27, false))
                	//shift from hotbar into main inventory
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, slotOffset, slotOffset+36, false))	
            {
                return null;
            }

            
            
            
            
            
            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
                //putstack triggers Inventory.setinventoryslotcontents
                //which triggers Tile.setinventoryslotcontents
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize)
            {
                return null;
            }

            if(index == 0) 
            //itemstack1 is the crafting result
            //this thing can only affect the crafting
            slot.onPickupFromSlot(playerIn, itemstack1);
            
            /*
                     net.minecraftforge.fml.common.FMLCommonHandler.instance().firePlayerCraftingEvent(playerIn, itemstack1, craftMatrix);
        
        
 
             
            //*/
        }

        return itemstack;
    }
   
    
    public void onPickupFromSlot(Slot slot, EntityPlayer playerIn, ItemStack stack)
    {
        net.minecraftforge.fml.common.FMLCommonHandler.instance().firePlayerCraftingEvent(playerIn, stack, craftMatrix);
        net.minecraftforge.common.ForgeHooks.setCraftingPlayer(playerIn);
        ItemStack[] aitemstack = MANAGER.getRemainingItems(this.craftMatrix, playerIn.worldObj);
        net.minecraftforge.common.ForgeHooks.setCraftingPlayer(null);

        for (int i = 0; i < aitemstack.length; ++i)
        {
            ItemStack itemstack = this.craftMatrix.getStackInSlot(i);
            ItemStack itemstack1 = aitemstack[i];

            if (itemstack != null)
            {
                this.craftMatrix.decrStackSize(i, 1);
                itemstack = this.craftMatrix.getStackInSlot(i);
            }

            if (itemstack1 != null)
            {
                if (itemstack == null)
                {
                    this.craftMatrix.setInventorySlotContents(i, itemstack1);
                }
                else if (ItemStack.areItemsEqual(itemstack, itemstack1) && ItemStack.areItemStackTagsEqual(itemstack, itemstack1))
                {
                    itemstack1.stackSize += itemstack.stackSize;
                    this.craftMatrix.setInventorySlotContents(i, itemstack1);
                }
                else if (!playerIn.inventory.addItemStackToInventory(itemstack1))
                {
                    playerIn.dropItem(itemstack1, false);
                }
            }
        }
    }
    
    public boolean withinVicinity(EntityPlayer playerIn) {
    	 return playerIn.getDistanceSq(
    			 (double)this.pos.getX() + 0.5D, 
    			 (double)this.pos.getY() + 0.5D, 
    			 (double)this.pos.getZ() + 0.5D
    			 ) <= 64.0D;
    }
    
    
    public void onCraftMatrixChanged(IInventory inventoryIn)
    {
        this.craftResult.setInventorySlotContents(0, MANAGER.findMatchingRecipe(this.craftMatrix, this.worldObj));
    }
    public boolean canMergeSlot(ItemStack stack, Slot slotIn)
    {
        return slotIn.inventory != this.craftResult && super.canMergeSlot(stack, slotIn);
    }
}