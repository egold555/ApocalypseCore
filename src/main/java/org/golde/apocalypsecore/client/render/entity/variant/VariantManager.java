package org.golde.apocalypsecore.client.render.entity.variant;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.UUID;

import org.golde.apocalypsecore.ApocalypseCore;

import com.google.common.collect.ListMultimap;
import com.google.common.collect.Multimaps;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class VariantManager {

	private static ListMultimap<RandomTextureType, ResourceLocation> textures;
	
	public static void registerRenders() {
		textures = Multimaps.newListMultimap(new EnumMap<>(RandomTextureType.class), ArrayList::new);
		registerTextures(RandomTextureType.ZOMBIE, 608, new ResourceLocation("textures/entity/zombie/zombie.png"));
		RenderingRegistry.registerEntityRenderingHandler(EntityZombie.class, RenderZombieVariant::new);
		
	}
	
	@SideOnly(Side.CLIENT)
	private static void registerTextures(RandomTextureType type, int count, ResourceLocation vanilla) {
		String name = type.name().toLowerCase();
		for(int i = 1; i < count + 1; i++) {
			System.out.println("Registering: " + String.format("textures/entity/random/%s/%d.png", name, i));
			textures.put(type, new ResourceLocation(ApocalypseCore.MODID, String.format("textures/entity/random/%s/%d.png", name, i)));
			
		}
		if(vanilla != null)
			textures.put(type, vanilla);
		
		
	}
	
	@SideOnly(Side.CLIENT)
	public static ResourceLocation getRandomTexture(Entity e, RandomTextureType type) {
		List<ResourceLocation> styles = textures.get(type);
		UUID id = e.getUniqueID();
		long most = id.getMostSignificantBits();
		int choice = Math.abs((int) (most % styles.size()));
		//System.out.println(choice);
		return styles.get(choice);
	}
	
	public enum RandomTextureType {
		ZOMBIE
	}
	
}
