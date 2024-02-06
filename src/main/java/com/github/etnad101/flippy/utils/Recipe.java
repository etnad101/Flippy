package com.github.etnad101.flippy.utils;

import com.github.etnad101.flippy.Flippy;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

import java.util.List;

public class Recipe {
    public List<String> ingredients;
    public List<Integer> quantities;

    public float totalCost(ICommandSender sender) {
        float totalCost = 0;
        sender.addChatMessage(new ChatComponentText("Cost Breakdown"));
        for (int i = 0; i < ingredients.size(); i++) {
            String name = ingredients.get(i);
            int amount = quantities.get(i);
            Product prod = Flippy.bazaar.products.get(name);
            float cost = prod.quick_status.sellPrice * amount;
            totalCost += cost;
            sender.addChatMessage(new ChatComponentText(name + ": " + amount + " -> §7$" + cost));
        }
        sender.addChatMessage(new ChatComponentText("Total Cost -> §7$" + totalCost));
        return totalCost;
    }
}
