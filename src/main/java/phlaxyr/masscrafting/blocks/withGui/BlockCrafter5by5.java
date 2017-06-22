package phlaxyr.masscrafting.blocks.withGui;

import net.minecraft.block.Block;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IInteractionObject;
import net.minecraft.world.World;
import phlaxyr.masscrafting.blocks.BlockRegistrar;
import phlaxyr.masscrafting.tile.container.ContainerCrafter5by5;
import phlaxyr.masscrafting.tile.gui.GuiHandler5by5;
import phlaxyr.masscrafting.tile.gui.blockguis.GuiCrafter;
import phlaxyr.masscrafting.tile.tile.TileEntity5by5;

public class BlockCrafter5by5 extends BlockCrafter{

	public BlockCrafter5by5(Material materialIn) {
		super(materialIn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntity5by5();
	}

	@Override
	public int getGuiID() {
		// TODO Auto-generated method stub
		return GuiHandler5by5.getGuiID();
	}



}
