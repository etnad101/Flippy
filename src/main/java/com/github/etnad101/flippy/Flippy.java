package com.github.etnad101.flippy;

import com.github.etnad101.flippy.commands.MenuCommand;
import com.github.etnad101.flippy.utils.Bazaar;
import com.github.etnad101.flippy.utils.JsonHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.io.IOException;

@Mod(modid = "flippy", useMetadata=true)
public class Flippy {
    public static GuiScreen screenToOpenNextTick = null;
    public static Bazaar bazaar = null;

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) return;
        if (screenToOpenNextTick != null) {
            Minecraft.getMinecraft().displayGuiScreen(screenToOpenNextTick);
            screenToOpenNextTick = null;
        }
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        try {
            bazaar = JsonHandler.getBazzar();
        } catch (IOException e) {
            System.out.println(e);
        }
        MinecraftForge.EVENT_BUS.register(this);
        ClientCommandHandler.instance.registerCommand(new MenuCommand());
    }
}
