package phlaxyr.masscrafting.tile.gui.blockguis;

import java.awt.Color;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import phlaxyr.masscrafting.tile.container.ContainerCrafterExtendable;
import phlaxyr.masscrafting.tile.tile.TileEntityInventoryCrafter;

@SideOnly(Side.CLIENT)
public class GuiCrafter extends GuiContainer
{
    private static final ResourceLocation tex = new ResourceLocation("masscrafting:textures/gui/m_workbench_gui.png");

    
    TileEntityInventoryCrafter tile;
    /*
    public GuiCrafter(InventoryPlayer invp, World world, BlockPos pos,
    		TileEntityInventoryCrafter table, ManagerExtendable manager, 
    		int gridX, int gridY, int textureSizeX, int textureSizeY)
    {
        super(new ContainerCrafterExtendable(invp, world, pos, manager, gridX, gridY, textureSizeX, textureSizeY));
        this.xSize = textureSizeX;
        this.ySize = textureSizeY;
    }*/
    public GuiCrafter(ContainerCrafterExtendable container, TileEntityInventoryCrafter tile,
    		int textureSizeX, int textureSizeY) {
    	super(container);
    	this.tile = tile;
    	this.xSize = textureSizeX;
    	this.ySize = textureSizeY;
    }
    
    
    
    @Override
    protected void drawGuiContainerForegroundLayer(int i, int j)
    {
		fontRendererObj.drawString(tile.getDisplayName().getUnformattedText(), 95, 5, Color.darkGray.getRGB());
        //this.fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
    {

        this.mc.renderEngine.bindTexture(tex);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        int foo = (this.width - this.xSize) / 2;
        int bar = (this.height - this.ySize) / 2;
        //change
        this.drawTexturedModalRect(foo, bar, 0, 0, this.xSize, this.ySize);
    }
}

