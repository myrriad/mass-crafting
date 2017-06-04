package phlaxyr.masscrafting.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

//slightly altered
public class BlockSimple extends Block {
	public BlockSimple(Material material, CreativeTabs creativeTab) {
		super(material);
		this.setCreativeTab(creativeTab); // the block will
															// appear on the
															// Blocks tab in
															// creative
	}

	// the block will render in the SOLID layer. See
	// http://greyminecraftcoder.blogspot.co.at/2014/12/block-rendering-18.html
	// for more information.
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.SOLID;
	}

	// used by the renderer to control lighting and visibility of other blocks.
	// set to true because this block is opaque and occupies the entire 1x1x1
	// space
	// not strictly required because the default (super method) is true
	@Override
	public boolean isOpaqueCube(IBlockState iBlockState) {
		return true;
	}

	// used by the renderer to control lighting and visibility of other blocks,
	// also by
	// (eg) wall or fence to control whether the fence joins itself to this
	// block
	// set to true because this block occupies the entire 1x1x1 space
	// not strictly required because the default (super method) is true
	@Override
	public boolean isFullCube(IBlockState iBlockState) {
		return true;
	}

	// render using a BakedModel (mbe01_block_simple.json -->
	// mbe01_block_simple_model.json)
	// not strictly required because the default (super method) is MODEL.
	@Override
	public EnumBlockRenderType getRenderType(IBlockState iBlockState) {
		return EnumBlockRenderType.MODEL;
	}
}
