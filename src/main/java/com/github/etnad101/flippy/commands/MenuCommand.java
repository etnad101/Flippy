package com.github.etnad101.flippy.commands;

import com.github.etnad101.flippy.Flippy;
import com.github.etnad101.flippy.MenuGui;
import com.github.etnad101.flippy.utils.JsonHandler;
import com.github.etnad101.flippy.utils.Product;
import com.github.etnad101.flippy.utils.Recipes;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import org.lwjgl.Sys;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.io.IOException;

public class MenuCommand extends CommandBase {
    @Override
    public String getCommandName() {
        return "flippy";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        try {
            Flippy.bazaar = JsonHandler.getBazzar();
        } catch (IOException e) {
            System.out.println(e);
        }

        if (args.length == 0) {
            Flippy.screenToOpenNextTick = new MenuGui();
            return;
        }

        if (args.length != 3) {
            sender.addChatMessage(new ChatComponentText("§cInvalid arguments -> /flippy <type> <item_id> <amount>"));
            return;
        }

        String type = args[0];
        String id = args[1];
        int amount = 0;
        try {
            amount = Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
            sender.addChatMessage(new ChatComponentText("§c'amount' must be a number"));
        }

        float profit;
        switch (type) {
            case "normal":
                Product prod = Flippy.bazaar.products.get(id);
                profit = prod.calculateProfit(amount);
                sendProfit(sender, profit);
                break;

            case "craft":
                try {
                    Recipes recipes = JsonHandler.getRecipes();
                    try {
                        profit = recipes.calculateProfit(sender, id, amount);
                    } catch (NullPointerException e) {
                        sender.addChatMessage(new ChatComponentText("§cThis item either cannot be crafted, or the recipe hasn't been implemented yet"));
                        break;
                    }
                    sendProfit(sender, profit);
                } catch (IOException e) {
                    sender.addChatMessage(new ChatComponentText("§c" + e));
                    System.out.println(e);
                    break;
                }
                break;

            default:
                sender.addChatMessage(new ChatComponentText("§cInvalid type - Must be 'normal' or 'craft'"));
        }
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        if (args.length == 1) {
            return getListOfStringsMatchingLastWord(args, "normal", "craft");
        }

        if (args.length == 2) {
            List<String> autofill = new ArrayList<String>( Flippy.bazaar.products.keySet() );
            return getListOfStringsMatchingLastWord(args, autofill);
        }

        return Arrays.asList();
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    private void sendProfit(ICommandSender sender, float profit) {
        String color;
        if (profit > 0) {
            color = "§6";
        } else {
            color = "§c";
        }
        sender.addChatMessage(new ChatComponentText("Total Profit -> $" + color + profit));
    }
}
