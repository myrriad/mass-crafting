package phlaxyr.masscrafting.crafting.recipe;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.world.World;

public final class RecipeExtendable extends ShapedRecipes{

	private final int GridX;
	private final int GridY;
	
	public int getGridHeight() {return GridY;}
	public int getGridWidth() {return GridX;}
	public RecipeExtendable(int recipeWidth, int recipeHeight, ItemStack[] items, ItemStack output, int gridWidth, int gridHeight) {
		super(recipeWidth, recipeHeight, items, output);
		GridX = gridWidth;
		GridY = gridHeight;
		
	}
	
	@Override
    public boolean matches(InventoryCrafting matrix, World world)
    {
        for (int i = 0; i <= GridX - this.recipeWidth; ++i)
        {
            for (int j = 0; j <= GridY - this.recipeHeight; ++j)
            {
                if (this.checkMatch(matrix, i, j, true))
                {
                    return true;
                }

                if (this.checkMatch(matrix, i, j, false))
                {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Checks if the region of a crafting inventory is match for the recipe.
     */
    private boolean checkMatch(InventoryCrafting matrix, int x, int y, boolean mirrored)
    {
        for (int k = 0; k < GridX; ++k)
        {
            for (int l = 0; l < GridY; ++l)
            {
                int i1 = k - x;
                int j1 = l - y;
                ItemStack itemstack = null;

                if (i1 >= 0 && j1 >= 0 && i1 < this.recipeWidth && j1 < this.recipeHeight)
                {
                    if (mirrored)
                    {
                        itemstack = this.recipeItems[this.recipeWidth - i1 - 1 + j1 * this.recipeWidth];
                    }
                    else
                    {
                        itemstack = this.recipeItems[i1 + j1 * this.recipeWidth];
                    }
                }

                ItemStack itemstack1 = matrix.getStackInRowAndColumn(k, l);

                if (itemstack1 != null || itemstack != null)
                {
                    if (itemstack1 == null && itemstack != null || itemstack1 != null && itemstack == null)
                    {
                        return false;
                    }

                    if (itemstack.getItem() != itemstack1.getItem())
                    {
                        return false;
                    }

                    if (itemstack.getItemDamage() != 32767 && itemstack.getItemDamage() != itemstack1.getItemDamage())
                    {
                        return false;
                    }
                }
            }
        }

        return true;
    }

}
