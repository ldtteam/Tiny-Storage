package com.timthebrick.tinystorage.client.gui.widgets;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import com.timthebrick.tinystorage.client.gui.widgets.settings.AccessMode;
import com.timthebrick.tinystorage.client.gui.widgets.settings.ButtonSettings;
import com.timthebrick.tinystorage.reference.Names;
import com.timthebrick.tinystorage.reference.References;

public class GuiImageButton extends GuiButton implements IButtonTooltip {

	private static final Pattern COMPILE = Pattern.compile("%s");
	private static final Pattern PATTERN_NEW_LINE = Pattern.compile("\\n", Pattern.LITERAL);
	private static Map<EnumPair, ButtonAppearance> appearances;
	private final Enum buttonSetting;
	public boolean halfSize = false;
	public String fillVar;
	private Enum currentValue;

	public GuiImageButton(int x, int y, Enum idx, Enum val) {
		super(0, 0, 16, "");

		this.buttonSetting = idx;
		this.currentValue = val;
		this.xPosition = x;
		this.yPosition = y;
		this.width = 16;
		this.height = 16;

		if (appearances == null) {
			appearances = new HashMap<EnumPair, ButtonAppearance>();
			this.registerApp(0, ButtonSettings.AUTOMATED_SIDE_ACCESS, AccessMode.DISABLED, Names.ButtonTooltips.ACCESS_MODE_TITLE, Names.ButtonTooltips.ACCESS_MODE_BLOCKED);
			this.registerApp(1, ButtonSettings.AUTOMATED_SIDE_ACCESS, AccessMode.INPUT_ONLY, Names.ButtonTooltips.ACCESS_MODE_TITLE, Names.ButtonTooltips.ACCESS_MODE_INPUT_ONLY);
			this.registerApp(2, ButtonSettings.AUTOMATED_SIDE_ACCESS, AccessMode.OUTPUT_ONLY, Names.ButtonTooltips.ACCESS_MODE_TITLE, Names.ButtonTooltips.ACCESS_MODE_OUTPUT_ONLY);
			this.registerApp(3, ButtonSettings.AUTOMATED_SIDE_ACCESS, AccessMode.INPUT_OUTPUT, Names.ButtonTooltips.ACCESS_MODE_TITLE, Names.ButtonTooltips.ACCESS_MODE_BOTH);
		}
	}

	private void registerApp(int iconIndex, ButtonSettings setting, Enum val, String title, String hint) {
		ButtonAppearance a = new ButtonAppearance();
		a.displayName = title;
		a.displayValue = hint;
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
			int iconIndex = this.getIconIndex();

			if (this.halfSize) {
				this.width = 8;
				this.height = 8;

				GL11.glPushMatrix();
				GL11.glTranslatef(this.xPosition, this.yPosition, 0.0F);
				GL11.glScalef(0.5f, 0.5f, 0.5f);

				if (this.enabled) {
					GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
				} else {
					GL11.glColor4f(0.5f, 0.5f, 0.5f, 1.0f);
				}

				minecraft.renderEngine.bindTexture(new ResourceLocation(References.MOD_ID + ":textures/gui/ButtonImages.png"));
				this.field_146123_n = par2 >= this.xPosition && par3 >= this.yPosition && par2 < this.xPosition + this.width && par3 < this.yPosition + this.height;

				int uv_y = (int) Math.floor(iconIndex / 16);
				int uv_x = iconIndex - uv_y * 16;

				this.drawTexturedModalRect(0, 0, 256 - 16, 256 - 16, 16, 16);
				this.drawTexturedModalRect(0, 0, uv_x * 16, uv_y * 16, 16, 16);
				this.mouseDragged(minecraft, par2, par3);

				GL11.glPopMatrix();
			} else {
				if (this.enabled) {
					GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
				} else {
					GL11.glColor4f(0.5f, 0.5f, 0.5f, 1.0f);
				}

				minecraft.renderEngine.bindTexture(new ResourceLocation(References.MOD_ID + ":textures/gui/ButtonImages.png"));
				this.field_146123_n = par2 >= this.xPosition && par3 >= this.yPosition && par2 < this.xPosition + this.width && par3 < this.yPosition + this.height;

				int uv_y = (int) Math.floor(iconIndex / 16);
				int uv_x = iconIndex - uv_y * 16;

				this.drawTexturedModalRect(this.xPosition, this.yPosition, 256 - 16, 256 - 16, 16, 16);
				this.drawTexturedModalRect(this.xPosition, this.yPosition, uv_x * 16, uv_y * 16, 16, 16);
				this.mouseDragged(minecraft, par2, par3);
			}
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

	@Override
	public String getMessage() {
		String displayName = null;
		String displayValue = null;

		if (this.buttonSetting != null && this.currentValue != null) {
			ButtonAppearance buttonAppearance = appearances.get(new EnumPair(this.buttonSetting, this.currentValue));
			if (buttonAppearance == null) {
				return "No Such Message";
			}

			displayName = buttonAppearance.displayName;
			displayValue = buttonAppearance.displayValue;
		}

		if (displayName != null) {
			String name = StatCollector.translateToLocal(displayName);
			String value = StatCollector.translateToLocal(displayValue);

			if (name == null || name.isEmpty()) {
				name = displayName;
			}
			if (value == null || value.isEmpty()) {
				value = displayValue;
			}

			if (this.fillVar != null) {
				value = COMPILE.matcher(value).replaceFirst(this.fillVar);
			}

			value = PATTERN_NEW_LINE.matcher(value).replaceAll("\n");
			StringBuilder sb = new StringBuilder(value);

			int i = sb.lastIndexOf("\n");
			if (i <= 0) {
				i = 0;
			}
			while (i + 30 < sb.length() && (i = sb.lastIndexOf(" ", i + 30)) != -1) {
				sb.replace(i, i + 1, "\n");
			}

			return name + '\n' + sb;
		}
		return null;
	}

	@Override
	public int xPos() {
		return this.xPosition;
	}

	@Override
	public int yPos() {
		return this.yPosition;
	}

	@Override
	public int getWidth() {
		return this.halfSize ? 8 : 16;
	}

	@Override
	public int getHeight() {
		return this.halfSize ? 8 : 16;
	}

	@Override
	public boolean isVisible() {
		return this.visible;
	}

	public void set(Enum e) {
		if (this.currentValue != e) {
			this.currentValue = e;
		}
	}

	static class EnumPair {
		final Enum setting;
		final Enum value;

		EnumPair(Enum a, Enum b) {
			this.setting = a;
			this.value = b;
		}

		@Override
		public int hashCode() {
			return this.setting.hashCode() ^ this.value.hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == null) {
				return false;
			}
			if (this.getClass() != obj.getClass()) {
				return false;
			}
			EnumPair other = (EnumPair) obj;
			return other.setting == this.setting && other.value == this.value;
		}
	}

	private static class ButtonAppearance {
		public int index;
		public String displayName;
		public String displayValue;
	}
}
