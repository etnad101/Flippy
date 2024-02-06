package com.github.etnad101.flippy.utils;

import com.github.etnad101.flippy.Flippy;
import net.minecraft.command.ICommandSender;

import java.util.Hashtable;

public class Recipes {
    public Hashtable<String, Recipe> recipes;

    @Override
    public String toString() {
        return "Recipes{" +
                "recipes=" + recipes +
                '}';
    }

    public float calculateProfit(ICommandSender sender, String itemId, int amount) throws NullPointerException{
        float totalCost = recipes.get(itemId).totalCost(sender);
        float sellValue = Flippy.bazaar.products.get(itemId).quick_status.buyPrice;
        return (sellValue - totalCost) * amount;
    }
}
