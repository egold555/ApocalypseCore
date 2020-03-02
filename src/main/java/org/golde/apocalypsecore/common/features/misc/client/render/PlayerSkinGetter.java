package org.golde.apocalypsecore.common.features.misc.client.render;

import java.io.File;
import java.util.UUID;

import javax.annotation.Nullable;

import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.renderer.ImageBufferDownload;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PlayerSkinGetter {

	private final UUID uuid;
	private NetworkPlayerInfo playerInfo;

	public PlayerSkinGetter(UUID uuid) {
		this.uuid = uuid;
	}

	/**
	 * Checks if this instance of AbstractClientPlayer has any associated player data.
	 */
	public boolean hasPlayerInfo()
	{
		return this.getPlayerInfo() != null;
	}

	@Nullable
	protected NetworkPlayerInfo getPlayerInfo()
	{
		if (this.playerInfo == null)
		{
			this.playerInfo = Minecraft.getMinecraft().getConnection().getPlayerInfo(uuid);
		}

		return this.playerInfo;
	}

	/**
	 * Returns true if the player has an associated skin.
	 */
	public boolean hasSkin()
	{
		NetworkPlayerInfo networkplayerinfo = this.getPlayerInfo();
		return networkplayerinfo != null && networkplayerinfo.hasLocationSkin();
	}

	/**
	 * Returns true if the player instance has an associated skin.
	 */
	public ResourceLocation getLocationSkin()
	{
		NetworkPlayerInfo networkplayerinfo = this.getPlayerInfo();
		return networkplayerinfo == null ? DefaultPlayerSkin.getDefaultSkin(uuid) : networkplayerinfo.getLocationSkin();
	}

	@Nullable
	public ResourceLocation getLocationCape()
	{
		NetworkPlayerInfo networkplayerinfo = this.getPlayerInfo();
		return networkplayerinfo == null ? null : networkplayerinfo.getLocationCape();
	}

	public boolean isPlayerInfoSet()
	{
		return this.getPlayerInfo() != null;
	}

	/**
	 * Gets the special Elytra texture for the player.
	 */
	@Nullable
	public ResourceLocation getLocationElytra()
	{
		NetworkPlayerInfo networkplayerinfo = this.getPlayerInfo();
		return networkplayerinfo == null ? null : networkplayerinfo.getLocationElytra();
	}

	public String getSkinType()
	{
		NetworkPlayerInfo networkplayerinfo = this.getPlayerInfo();
		return networkplayerinfo == null ? DefaultPlayerSkin.getSkinType(uuid) : networkplayerinfo.getSkinType();
	}

}
