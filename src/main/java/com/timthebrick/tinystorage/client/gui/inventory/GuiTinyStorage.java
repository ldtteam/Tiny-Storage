package com.timthebrick.tinystorage.client.gui.inventory;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.timthebrick.tinystorage.client.gui.widgets.GuiImageButton;
import com.timthebrick.tinystorage.client.gui.widgets.IButtonTooltip;
import com.timthebrick.tinystorage.client.gui.widgets.settings.AccessMode;
import com.timthebrick.tinystorage.client.gui.widgets.settings.ButtonSettings;
import com.timthebrick.tinystorage.core.TinyStorageLog;
import com.timthebrick.tinystorage.inventory.ContainerTinyStorage;
import com.timthebrick.tinystorage.network.PacketHandler;
import com.timthebrick.tinystorage.network.message.MessageConfigButton;
import com.timthebrick.tinystorage.tileentity.TileEntityTinyStorage;

public class GuiTinyStorage extends GuiContainer {

	private GuiImageButton accessMode;
	private TileEntityTinyStorage tileEntity;

	public GuiTinyStorage(Container container, TileEntityTinyStorage te) {
		super(container);
		this.tileEntity = te;
	}

	public void addButtons() {
		this.accessMode = new GuiImageButton(this.guiLeft - 18, this.guiTop + 8, ButtonSettings.AUTOMATED_SIDE_ACCESS, AccessMode.DISABLED);
		if(tileEntity.hasUniqueOwner()){
			this.buttonList.add(accessMode);
		}
	}

	@Override
	public void drawScreen(int mouse_x, int mouse_y, float btn) {
		super.drawScreen(mouse_x, mouse_y, btn);

		boolean hasClicked = Mouse.isButtonDown(0);

		for (Object c : this.buttonList) {
			if (c instanceof IButtonTooltip) {
				IButtonTooltip tooltip = (IButtonTooltip) c;
				int x = tooltip.xPos(); // ((GuiImgButton) c).xPosition;
				int y = tooltip.yPos(); // ((GuiImgButton) c).yPosition;

				if (x < mouse_x && x + tooltip.getWidth() > mouse_x && tooltip.isVisible()) {
					if (y < mouse_y && y + tooltip.getHeight() > mouse_y) {
						if (y < 15) {
							y = 15;
						}

						String msg = tooltip.getMessage();
						if (msg != null) {
							this.drawTooltip(x + 11, y + 4, 0, msg);
						}
					}
				}
			}
		}
	}

	public void drawTooltip(int par2, int par3, int forceWidth, String Msg) {
		GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS);
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		RenderHelper.disableStandardItemLighting();
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		String[] var4 = Msg.split("\n");

		if (var4.length > 0) {
			int var5 = 0;
			int var6;
			int var7;

			for (var6 = 0; var6 < var4.length; ++var6) {
				var7 = this.fontRendererObj.getStringWidth(var4[var6]);

				if (var7 > var5) {
					var5 = var7;
				}
			}

			var6 = par2 + 12;
			var7 = par3 - 12;
			int var9 = 8;

			if (var4.length > 1) {
				var9 += 2 + (var4.length - 1) * 10;
			}

			if (this.guiTop + var7 + var9 + 6 > this.height) {
				var7 = this.height - var9 - this.guiTop - 6;
			}

			if (forceWidth > 0) {
				var5 = forceWidth;
			}

			this.zLevel = 300.0F;
			itemRender.zLevel = 300.0F;
			int var10 = -267386864;
			this.drawGradientRect(var6 - 3, var7 - 4, var6 + var5 + 3, var7 - 3, var10, var10);
			this.drawGradientRect(var6 - 3, var7 + var9 + 3, var6 + var5 + 3, var7 + var9 + 4, var10, var10);
			this.drawGradientRect(var6 - 3, var7 - 3, var6 + var5 + 3, var7 + var9 + 3, var10, var10);
			this.drawGradientRect(var6 - 4, var7 - 3, var6 - 3, var7 + var9 + 3, var10, var10);
			this.drawGradientRect(var6 + var5 + 3, var7 - 3, var6 + var5 + 4, var7 + var9 + 3, var10, var10);
			int var11 = 1347420415;
			int var12 = (var11 & 16711422) >> 1 | var11 & -16777216;
			this.drawGradientRect(var6 - 3, var7 - 3 + 1, var6 - 3 + 1, var7 + var9 + 3 - 1, var11, var12);
			this.drawGradientRect(var6 + var5 + 2, var7 - 3 + 1, var6 + var5 + 3, var7 + var9 + 3 - 1, var11, var12);
			this.drawGradientRect(var6 - 3, var7 - 3, var6 + var5 + 3, var7 - 3 + 1, var11, var11);
			this.drawGradientRect(var6 - 3, var7 + var9 + 2, var6 + var5 + 3, var7 + var9 + 3, var12, var12);

			for (int var13 = 0; var13 < var4.length; ++var13) {
				String var14 = var4[var13];

				if (var13 == 0) {
					var14 = '\u00a7' + Integer.toHexString(15) + var14;
				} else {
					var14 = "\u00a77" + var14;
				}

				this.fontRendererObj.drawStringWithShadow(var14, var6, var7, -1);

				if (var13 == 0) {
					var7 += 2;
				}

				var7 += 10;
			}

			this.zLevel = 0.0F;
			itemRender.zLevel = 0.0F;
		}
		GL11.glPopAttrib();
	}

	@Override
	public void onGuiClosed() {
		super.onGuiClosed();
	}

	public void drawFG() {
		if (this.accessMode != null) {
			this.accessMode.set(this.tileEntity.accessMode);
		}
	}

	public void drawBG() {
		this.handleButtonVisibility();
	}

	protected void handleButtonVisibility() {
		if (this.accessMode != null) {
			this.accessMode.setVisibility(true);
		}
	}

	@Override
	public void initGui() {
		super.initGui();
		this.addButtons();
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float opacity, int x, int y) {

	}

	@Override
	protected void mouseClicked(int xCoord, int yCoord, int btn) {
		if (btn == 1) {
			for (Object o : this.buttonList) {
				GuiButton guibutton = (GuiButton) o;
				if (guibutton.mousePressed(this.mc, xCoord, yCoord)) {
					super.mouseClicked(xCoord, yCoord, 0);
					return;
				}
			}
		}
		super.mouseClicked(xCoord, yCoord, btn);
	}

	@Override
	protected void actionPerformed(GuiButton btn) {
		super.actionPerformed(btn);
		boolean backwards = Mouse.isButtonDown(1);
		if (btn == this.accessMode) {
			PacketHandler.INSTANCE.sendToServer(new MessageConfigButton(Minecraft.getMinecraft().thePlayer, this.accessMode.getSetting(), backwards, this.tileEntity.xCoord, this.tileEntity.yCoord, this.tileEntity.zCoord));
		}
	}

}
