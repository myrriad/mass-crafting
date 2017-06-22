package phlaxyr.masscrafting.tile.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import phlaxyr.masscrafting.crafting.manager.Manager5by5;
import phlaxyr.masscrafting.tile.container.ContainerCrafter5by5;
import phlaxyr.masscrafting.tile.gui.blockguis.GuiCrafter5by5;
import phlaxyr.masscrafting.tile.tile.TileEntityInventoryCrafter;

public class GuiHandler5by5 implements IGuiHandler{

	public static int getGuiID() {return 1946;}

	// Gets the server side element for the given gui id- this should return a container
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID != getGuiID()) {
			System.err.println("Invalid ID: expected " + getGuiID() + ", received " + ID);
		}
		Manager5by5 manager = (Manager5by5) Manager5by5.getInstance();
		if(manager == null) {
			System.err.println("MANAGER IS NULL!");
		}
		BlockPos xyz = new BlockPos(x, y, z);
		TileEntity tileEntity = world.getTileEntity(xyz);
		if (tileEntity instanceof TileEntityInventoryCrafter) {
			TileEntityInventoryCrafter tile = (TileEntityInventoryCrafter) tileEntity;
			
		//	return new ContainerCrafter5by5(player.inventory, tileEntityInventoryBasic);
			return new ContainerCrafter5by5(player.inventory, world, xyz, 
					 tile, manager);
		}
		return null;	
	}

	// Gets the client side element for the given gui id- this should return a gui
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID != getGuiID()) {
			System.err.println("Invalid ID: expected " + getGuiID() + ", received " + ID);
		}

		BlockPos xyz = new BlockPos(x, y, z);
		TileEntity tileEntity = world.getTileEntity(xyz);
		if (tileEntity instanceof TileEntityInventoryCrafter) {
			TileEntityInventoryCrafter tileEntityInventoryBasic = (TileEntityInventoryCrafter) tileEntity;
			return new GuiCrafter5by5(player.inventory, world, xyz, tileEntityInventoryBasic, Manager5by5.getInstance());
		}
		return null;
	}

}
