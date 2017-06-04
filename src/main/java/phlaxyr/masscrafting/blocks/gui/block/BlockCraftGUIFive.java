package phlaxyr.masscrafting.blocks.gui.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IInteractionObject;
import net.minecraft.world.World;
import phlaxyr.masscrafting.blocks.BlockRegistrar;
import phlaxyr.masscrafting.blocks.gui.BlockCraftGUI;
import phlaxyr.masscrafting.blocks.gui.CraftGUI;
import phlaxyr.masscrafting.container.ContainerCraftFive;

public class BlockCraftGUIFive extends BlockCraftGUI{

	public BlockCraftGUIFive(Material materialIn) {
		super(materialIn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public IInteractionObject getGUI(World worldin, BlockPos pos) {

		return new CraftGUI(worldin, pos) {

			@Override
			public String getModID() {
				return "masscrafting";
			}

			@Override
			public Block getBlock() {
				// TODO Auto-generated method stub
				return BlockRegistrar.block_mWorkbench;
			}

			@Override
			public Container getContainer(InventoryPlayer playerInventory, World worldin, BlockPos pos) {
				return new ContainerCraftFive(playerInventory, worldin, pos);
			}
			
		};
	}

}
