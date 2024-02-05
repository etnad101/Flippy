package com.github.etnad101.flippy;

import com.github.etnad101.flippy.utils.Bazaar;
import com.github.etnad101.flippy.utils.Product;
import com.github.etnad101.flippy.utils.RequestHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.Sys;

import java.io.IOException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class MenuGui extends GuiScreen {
    public static final float SCALE_FACTOR = 0.5F;
    public static final int MOUSE_LEFT = 0;
    public static final int MOUSE_RIGHT = 1;
    public static final int MOUSE_MIDDLE = 2;
    public static final int MOUSE_BACKWARD = 3;
    public static final int MOUSE_FORWARD = 4;

    public static final int REFRESH = 0;
    public static final int OFFSET = 1;

    @Override
    public void initGui() {
        super.initGui();
        this.buttonList.add(new GuiButton(REFRESH, (int) ((width/2 - 15) / SCALE_FACTOR), 0, (int) (30 / SCALE_FACTOR), (int) (20 / SCALE_FACTOR), "Refresh"));

        int id = 0;
        int xPos = 0;
        TreeMap<String, Product> tmap = new TreeMap<String, Product>( Flippy.bazaar.products );
        Set<String> setOfKeys =  tmap.keySet();
        FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;

        for (String key : setOfKeys) {
            System.out.println(key);
            if (id < 5) {
                String text = key;
                int textWidth = fr.getStringWidth(text);
                this.buttonList.add(new GuiButton(id + OFFSET, xPos, 25, textWidth, 20, key));
                xPos += textWidth;
                id++;
            }
        }
    }

    @Override
    public void actionPerformed(GuiButton button) throws IOException {
        if (button.id == REFRESH) {
            RequestHandler rh = new RequestHandler();
            try {
                Bazaar b = rh.get();
                System.out.println(b.lastUpdated);
                Flippy.bazaar = b;
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        GlStateManager.scale(SCALE_FACTOR, SCALE_FACTOR, SCALE_FACTOR);
        drawRect(0, 0, (int) (width / SCALE_FACTOR), (int) (height / SCALE_FACTOR), 0x5f000000);
        drawGradientRect(50, 50, (int) ((width - 50) / SCALE_FACTOR), (int) ((height - 50)  / SCALE_FACTOR), 0x7F6495ED , 0x7F6495ED );

        FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
        String text = "Test: " + Flippy.bazaar.lastUpdated;
        int textWidth = fr.getStringWidth(text);

        fr.drawString(text, width / 2 - 95, height / 2 - 18, -1);

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }
}
