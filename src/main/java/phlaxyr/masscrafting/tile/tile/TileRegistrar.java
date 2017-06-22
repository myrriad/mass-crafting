package phlaxyr.masscrafting.tile.tile;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import phlaxyr.masscrafting.MassCrafting;
import phlaxyr.masscrafting.Registrar;
import phlaxyr.masscrafting.tile.gui.GuiHandler5by5;
import phlaxyr.masscrafting.tile.gui.GuiHandlerRegistry;


public class TileRegistrar extends Registrar{
	public static void preInit() {
		GameRegistry.registerTileEntity(TileEntity5by5.class, "m_workbench");
		
		NetworkRegistry.INSTANCE.registerGuiHandler(MassCrafting.instance,GuiHandlerRegistry.getInstance());
		GuiHandlerRegistry.getInstance().registerGuiHandler(new GuiHandler5by5(), GuiHandler5by5.getGuiID());

	}
}
