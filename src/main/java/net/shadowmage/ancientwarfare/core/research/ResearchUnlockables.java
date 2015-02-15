/**
 * This class was created by <MCManuelLP> on request by <TheMattaBase>
 *
 * Do with it whatever you want.
 */
package net.shadowmage.ancientwarfare.core.research;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.shadowmage.ancientwarfare.core.config.AWCoreStatics;
import net.shadowmage.ancientwarfare.core.crafting.AWCraftingManager;
import net.shadowmage.ancientwarfare.core.util.StringTools;

import java.util.*;

public class ResearchUnlockables {
    private static boolean hasInit = false;

	public static void loadRecipes()
	{
		if (hasInit)
		{
			return;
		}
		hasInit = true;
		parseRecipes(StringTools.getResourceLines(AWCoreStatics.resourcePath + "research_items_api.csv"));
	}
    
    public static void parseRecipes(List<String> lines)
	{
		String[] split;

		String research_name;

		String item_name;
		int item_damage;
		int item_count;

		Object[] craft_par;

		Item crafting_item;
		Block crafting_block;

		Item item;
		Block block;
		ItemStack stack;

		for(String line : lines)
		{
			split = StringTools.parseStringArray(line);

			research_name = split[0].startsWith("research.") ? split[0] : "research." + split[0];

			item_name = split[1];
			item_damage = StringTools.safeParseInt(split[2]);
			item_count = StringTools.safeParseInt(split[3]);

			craft_par = new Object[split.length - 4];
			craft_par[0] = split[4];
			craft_par[1] = split[5];
			craft_par[2] = split[6];

			int j = 3;
			for(int i = 7; i < split.length; i++, i++, j++, j++)
			{
				craft_par[j] = split[i].charAt(0);

				crafting_item = (Item)Item.itemRegistry.getObject(split[i + 1]);
				if(crafting_item == null)
				{
					crafting_block = (Block)Block.blockRegistry.getObject(split[i + 1]);
					if(crafting_block == null || crafting_block == Blocks.air)
					{
						throw new RuntimeException("Could not locate item for name: " + split[i+1]);
					}
					else
					{
						craft_par[j+1] = crafting_block;
					}
				}
				else
				{
					craft_par[j+1] = crafting_item;
				}
			}

			item = (Item)Item.itemRegistry.getObject(item_name);
			if(item == null)
			{
				block = (Block)Block.blockRegistry.getObject(item_name);
				if(block == null || block == Blocks.air)
				{
					throw new RuntimeException("Could not locate item for name: " + item_name);
				}
				else
				{
					stack = new ItemStack(block, item_count, item_damage);
				}
			}
			else
			{
				stack = new ItemStack(item, item_count, item_damage);
			}
			System.out.println(String.format("Creating resource dependant recipe: %s, %s, %s", stack, research_name, Arrays.asList(craft_par)));
			AWCraftingManager.INSTANCE.createRecipe(stack, research_name, craft_par);
		}
	}
}
