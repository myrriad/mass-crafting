package phlaxyr.masscrafting.blocks.gui;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IInteractionObject;
import net.minecraft.world.World;
import phlaxyr.masscrafting.blocks.BlockSimple;
import phlaxyr.masscrafting.container.ContainerCraftExtendable;

public abstract class CraftGUI implements IInteractionObject
{
    private final World world;
    private final BlockPos position;
    
    private String modid;
    private Block block;
    
    public CraftGUI(World worldIn, BlockPos pos)
    {
        this.world = worldIn;
        this.position = pos;
    }

    /**
     * Get the name of this object. For players this returns their username
     */
    public String getName()
    {
        return null;
    }

    /**
     * Returns true if this thing is named
     */
    public boolean hasCustomName()
    {
        return false;
    }

    /**
     * Get the formatted ChatComponent that will be used for the sender's username in chat
     */
    public ITextComponent getDisplayName()
    {
        return new TextComponentTranslation(getBlock().getUnlocalizedName() + ".name", new Object[0]);
    }

    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn)
    {
        return getContainer(playerInventory, this.world, this.position);
    }
    
    
    
    public String getGuiID() {
    	return getModID() + getBlock().getRegistryName();
    }
    /*{
    	return "minecraft:crafting_table";
    }*/
    
    public abstract Container getContainer(InventoryPlayer playerInventory, World worldin, BlockPos pos);
    public abstract String getModID();
    public abstract Block getBlock();	
}

