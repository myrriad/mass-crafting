package phlaxyr.masscrafting.crafting.manager;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockStone;
import net.minecraft.block.BlockStoneSlab;
import net.minecraft.block.BlockStoneSlabNew;
import net.minecraft.block.BlockWall;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.world.World;
import phlaxyr.masscrafting.crafting.recipe.RecipeExtendable;

public abstract class ManagerExtendable {
    /** The static instance of this class */
    static ManagerExtendable INSTANCE;
    /** A list of all the recipes added */
    private final List<IRecipe> recipes = Lists.<IRecipe>newArrayList();

    
    
    /**
     * Returns the static instance of this class
     * @throws Exception 
     */
    public static ManagerExtendable getInstance() throws Exception {
        throw new AssertionError("This method must be masked by a subclass!");
    }

    protected ManagerExtendable() {
    	addRecipes();
        /*
    	(new RecipesTools()).addRecipes(this);
        (new RecipesWeapons()).addRecipes(this);
        (new RecipesIngots()).addRecipes(this);
        (new RecipesFood()).addRecipes(this);
        (new RecipesCrafting()).addRecipes(this);
        (new RecipesArmor()).addRecipes(this);
        (new RecipesDyes()).addRecipes(this);
        this.recipes.add(new RecipesArmorDyes());
        this.recipes.add(new RecipeBookCloning());
        this.recipes.add(new RecipesMapCloning());
        this.recipes.add(new RecipesMapExtending());
        this.recipes.add(new RecipeFireworks());
        this.recipes.add(new RecipeRepairItem());
        this.recipes.add(new RecipeTippedArrow());
        (new RecipesBanners()).addRecipes(this);
        (new ShieldRecipes()).addRecipes(this);
        this.addRecipe(new ItemStack(Items.PAPER, 3), new Object[] {"###", '#', Items.REEDS});
        this.addShapelessRecipe(new ItemStack(Items.BOOK, 1), new Object[] {Items.PAPER, Items.PAPER, Items.PAPER, Items.LEATHER});
        this.addRecipe(new ItemStack(Blocks.field_189880_di, 1), new Object[] {"XXX", "XXX", "XXX", 'X', new ItemStack(Items.DYE, 1, EnumDyeColor.WHITE.getDyeDamage())});
        */
        Collections.sort(this.recipes, new Comparator<IRecipe>()
        {
            public int compare(IRecipe p_compare_1_, IRecipe p_compare_2_)
            {
                return 
                		p_compare_1_ instanceof ShapelessRecipes && p_compare_2_ instanceof ShapedRecipes 
                		? 1 	
                				: (p_compare_2_ instanceof ShapelessRecipes && p_compare_1_ instanceof ShapedRecipes 
                						? -1 
                								: (p_compare_2_.getRecipeSize() < p_compare_1_.getRecipeSize() 
                										? -1 
                												: (p_compare_2_.getRecipeSize() > p_compare_1_.getRecipeSize() 
                														? 1 
                																: 0)));
            }
        });
    }

    /**
     *
     * Called in the constructor; adds all of the recipes.
     */
    public abstract void addRecipes();
    
    /**
     * Adds a shaped recipe to the games recipe list.
     */
    public RecipeExtendable addRecipe(ItemStack stack, Object... recipeComponents)
    {
        String s = "";
        int i = 0;
        int j = 0;
        int k = 0;

        if (recipeComponents[i] instanceof String[])
        {
            String[] astring = (String[])((String[])recipeComponents[i++]);

            for (String s2 : astring)
            {
                ++k;
                j = s2.length();
                s = s + s2;
            }
        }
        else
        {
            while (recipeComponents[i] instanceof String)
            {
                String s1 = (String)recipeComponents[i++];
                ++k;
                j = s1.length();
                s = s + s1;
            }
        }

        Map<Character, ItemStack> map;

        for (map = Maps.<Character, ItemStack>newHashMap(); i < recipeComponents.length; i += 2)
        {
            Character character = (Character)recipeComponents[i];
            ItemStack itemstack = null;

            if (recipeComponents[i + 1] instanceof Item)
            {
                itemstack = new ItemStack((Item)recipeComponents[i + 1]);
            }
            else if (recipeComponents[i + 1] instanceof Block)
            {
                itemstack = new ItemStack((Block)recipeComponents[i + 1], 1, 32767);
            }
            else if (recipeComponents[i + 1] instanceof ItemStack)
            {
                itemstack = (ItemStack)recipeComponents[i + 1];
            }

            map.put(character, itemstack);
        }

        ItemStack[] aitemstack = new ItemStack[j * k];

        for (int l = 0; l < j * k; ++l)
        {
            char c0 = s.charAt(l);

            if (map.containsKey(Character.valueOf(c0)))
            {
                aitemstack[l] = ((ItemStack)map.get(Character.valueOf(c0))).copy();
            }
            else
            {
                aitemstack[l] = null;
            }
        }

        RecipeExtendable shapedrecipes = constructRecipe(j, k, aitemstack, stack);
        this.recipes.add(shapedrecipes);
        return shapedrecipes;
    }

    
    /**
     * 
     * Simply use the method parameters as constructor parameters.
     */
    public abstract RecipeExtendable constructRecipe(int width, int height, ItemStack[] items, ItemStack output);
    
    /**
     * Adds a shapeless crafting recipe to the the game.
     */
    public void addShapelessRecipe(ItemStack stack, Object... recipeComponents)
    {
        List<ItemStack> list = Lists.<ItemStack>newArrayList();

        for (Object object : recipeComponents)
        {
            if (object instanceof ItemStack)
            {
                list.add(((ItemStack)object).copy());
            }
            else if (object instanceof Item)
            {
                list.add(new ItemStack((Item)object));
            }
            else
            {
                if (!(object instanceof Block))
                {
                    throw new IllegalArgumentException("Invalid shapeless recipe: unknown type " + object.getClass().getName() + "!");
                }

                list.add(new ItemStack((Block)object));
            }
        }

        this.recipes.add(new ShapelessRecipes(stack, list));
    }

    /**
     * Adds an IRecipe to the list of crafting recipes.
     */
    public void addRecipe(IRecipe recipe)
    {
        this.recipes.add(recipe);
    }

    /**
     * Retrieves an ItemStack that has multiple recipes for it.
     */
    @Nullable
    public ItemStack findMatchingRecipe(InventoryCrafting craftMatrix, World worldIn)
    {
        for (IRecipe irecipe : this.recipes)
        {
            if (irecipe.matches(craftMatrix, worldIn))
            {
                return irecipe.getCraftingResult(craftMatrix);
            }
        }

        return null;
    }

    public ItemStack[] getRemainingItems(InventoryCrafting craftMatrix, World worldIn)
    {
        for (IRecipe irecipe : this.recipes)
        {
            if (irecipe.matches(craftMatrix, worldIn))
            {
                return irecipe.getRemainingItems(craftMatrix);
            }
        }

        ItemStack[] aitemstack = new ItemStack[craftMatrix.getSizeInventory()];

        for (int i = 0; i < aitemstack.length; ++i)
        {
            aitemstack[i] = craftMatrix.getStackInSlot(i);
        }

        return aitemstack;
    }

    /**
     * returns the List<> of all recipes
     */
    public List<IRecipe> getRecipeList()
    {
        return this.recipes;
    }
}