package phlaxyr.masscrafting.tile.gui.blockguis;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import phlaxyr.masscrafting.crafting.manager.ManagerExtendable;
import phlaxyr.masscrafting.tile.container.ContainerCrafter5by5;
import phlaxyr.masscrafting.tile.tile.TileEntityInventoryCrafter;

public class GuiCrafter5by5 extends GuiCrafter{

	public GuiCrafter5by5(InventoryPlayer ip, World worldIn, BlockPos pos, 
			TileEntityInventoryCrafter tile, ManagerExtendable manager) {
		super(new ContainerCrafter5by5(ip, worldIn, pos, tile, manager), 183, 188);
	}

}
