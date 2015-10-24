package com.smithsmodding.tinystorage.client.gui.widgets;

import com.smithsmodding.tinystorage.client.gui.widgets.settings.ButtonAppearance;
import com.smithsmodding.tinystorage.common.reference.References;
import com.smithsmodding.tinystorage.util.client.Colours;
import com.smithsmodding.tinystorage.client.gui.widgets.settings.ButtonSettings;
import com.smithsmodding.tinystorage.client.gui.widgets.settings.EnableMode;
import com.smithsmodding.tinystorage.client.gui.widgets.settings.EnumPair;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class GuiCharButton extends GuiButton {

    private static final Pattern COMPILE = Pattern.compile("%s");
    private static final Pattern PATTERN_NEW_LINE = Pattern.compile("\\n", Pattern.LITERAL);
    private static Map<EnumPair, ButtonAppearance> appearances;
    private final Enum buttonSetting;
    private char message;
    public String fillVar;
    private Enum currentValue;

    public GuiCharButton(int x, int y, Enum idx, Enum val, char message) {
        super(0, 0, 16, "");
        this.buttonSetting = idx;
        this.currentValue = val;
        this.xPosition = x;
        this.yPosition = y;
        this.width = 16;
        this.height = 16;
        this.message = message;
        registerAppearances();
    }

    private void registerAppearances() {
        if (appearances == null) {
            appearances = new HashMap<EnumPair, ButtonAppearance>();
            this.registerApp(254, ButtonSettings.CIRCLE, EnableMode.ENABLED);
            this.registerApp(254, ButtonSettings.CIRCLE, EnableMode.DISABLED);
            this.registerApp(255, ButtonSettings.SQUARE, EnableMode.ENABLED);
            this.registerApp(255, ButtonSettings.SQUARE, EnableMode.DISABLED);
        }
    }

    private void registerApp(int iconIndex, ButtonSettings setting, Enum val) {
        ButtonAppearance a = new ButtonAppearance();
        a.displayName = "";
        a.displayValue = "";
        a.index = iconIndex;
        appearances.put(new EnumPair(setting, val), a);
    }

    public void setVisibility(boolean vis) {
        this.visible = vis;
        this.enabled = vis;
    }

    @Override
    public void drawButton(Minecraft minecraft, int par2, int par3) {
        if (this.visible) {
            minecraft.renderEngine.bindTexture(new ResourceLocation(References.MOD_ID + ":textures/gui/guiButtonImages.png"));
            int iconIndex = this.getIconIndex();
            if (this.enabled) {
                GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            } else {
                GL11.glColor4f(0.5f, 0.5f, 0.5f, 1.0f);
            }
            this.field_146123_n = par2 >= this.xPosition && par3 >= this.yPosition && par2 < this.xPosition + this.width && par3 < this.yPosition + this.height;
            int uv_y = (int) Math.floor(iconIndex / 16);
            int uv_x = iconIndex - uv_y * 16;
            //this.drawTexturedModalRect(this.xPosition, this.yPosition, 256 - 16, 256 - 16, 16, 16);
            this.drawTexturedModalRect(this.xPosition, this.yPosition, uv_x * 16, uv_y * 16, 16, 16);
            //TinyStorageLog.info(this.yPosition + ", " + this.height + ", " + (this.height / 2));
            this.drawCenteredString(minecraft.fontRenderer, StatCollector.translateToLocal(String.valueOf(message)), this.xPosition + (this.width / 2), this.yPosition + (this.height / 4), Colours.TEXT_WHITE);
            this.mouseDragged(minecraft, par2, par3);
        }
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
    }

    private int getIconIndex() {
        if (this.buttonSetting != null && this.currentValue != null) {
            ButtonAppearance app = appearances.get(new EnumPair(this.buttonSetting, this.currentValue));
            if (app == null) {
                return 256 - 1;
            }
            return app.index;
        }
        return 256 - 1;
    }

    public ButtonSettings getSetting() {
        return (ButtonSettings) this.buttonSetting;
    }

    public Enum getCurrentValue() {
        return this.currentValue;
    }

    public void set(Enum e) {
        if (this.currentValue != e) {
            this.currentValue = e;
        }
    }
}
