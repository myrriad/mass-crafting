package phlaxyr.masscrafting.blocks.gui;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockWorkbench;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IInteractionObject;
import net.minecraft.world.World;
import phlaxyr.masscrafting.blocks.BlockSimple;

public abstract class BlockCraftGUI extends BlockGUI{

	public BlockCraftGUI(Material materialIn) {
		super(materialIn);
		this.setCreativeTab(CreativeTabs.DECORATIONS);
		
		// TODO Auto-generated constructor stub
	}

    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (worldIn.isRemote)
        {
            return true;
        }
        else
        {
            playerIn.displayGui(getGUI(worldIn, pos));
            
            return true;
        }
    }
    
    public abstract IInteractionObject getGUI(World worldin, BlockPos pos);
    


}
