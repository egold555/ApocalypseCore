package org.golde.apocalypsecore.common.features.weapons;

import org.golde.apocalypsecore.common.ApocalypseCore;
import org.golde.apocalypsecore.common.features.Feature;
import org.golde.apocalypsecore.common.features.weapons.blocks.BlockLandMine;
import org.golde.apocalypsecore.common.features.weapons.client.render.entity.RenderEntityGrapplingHook;
import org.golde.apocalypsecore.common.features.weapons.client.render.entity.RenderEntityMolotovCocktail;
import org.golde.apocalypsecore.common.features.weapons.client.render.entity.RenderItemSmokebomb;
import org.golde.apocalypsecore.common.features.weapons.entity.EntityGrapplingHook;
import org.golde.apocalypsecore.common.features.weapons.entity.EntityMolotovCocktail;
import org.golde.apocalypsecore.common.features.weapons.entity.EntitySmokeBombThrowable;
import org.golde.apocalypsecore.common.features.weapons.entity.EntitySmokeCloud;
import org.golde.apocalypsecore.common.features.weapons.items.ItemFlamethrower;
import org.golde.apocalypsecore.common.features.weapons.items.ItemGrapplingHook;
import org.golde.apocalypsecore.common.features.weapons.items.ItemMolotovCocktail;
import org.golde.apocalypsecore.common.features.weapons.items.ItemNightVisionGoggles;
import org.golde.apocalypsecore.common.features.weapons.items.ItemSmokeBomb;
import org.golde.apocalypsecore.common.features.weapons.items.ItemTaser;
import org.golde.apocalypsecore.common.items._ACItemMeleeWeapon;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class FeatureWeapons extends Feature {

	public static ItemFlamethrower flamethrower;
	public static ItemSmokeBomb smokeBomb;
	public static _ACItemMeleeWeapon itemBaseBallBat;
	public static _ACItemMeleeWeapon itemBaseBallBatSpiked;
	public static ItemTaser taser;
	public static ItemMolotovCocktail molotovCocktail;

	public static ItemNightVisionGoggles nightVisionGoggles;
	public static ItemGrapplingHook grapplingHook;

	public static BlockLandMine landMine;

	public static SoundEvent SOUND_NIGHT_VISION_ON;
	public static SoundEvent SOUND_NIGHT_VISION_OFF;
	public static SoundEvent SOUND_TASER;


	@Override
	public void registerBlocks() {
		registerBlock(landMine = new BlockLandMine());
	}

	@Override
	public void registerItems() {
		registerItem(smokeBomb = new ItemSmokeBomb());
		registerItem(flamethrower = new ItemFlamethrower());
		registerItem(itemBaseBallBat = new _ACItemMeleeWeapon("bat", 3, 50, Blocks.PLANKS));
		registerItem(itemBaseBallBatSpiked = new _ACItemMeleeWeapon("bat_spiked", 6, 100, Blocks.PLANKS));
		registerItem(taser = new ItemTaser());
		registerItem(molotovCocktail = new ItemMolotovCocktail());

		registerItem(nightVisionGoggles = new ItemNightVisionGoggles());
		registerItem(grapplingHook = new ItemGrapplingHook());
	}

	@Override
	public ItemStack getTabIcon() {
		ItemStack is = new ItemStack(taser);
		is.setTagCompound(new NBTTagCompound()); //fix null
		is.getTagCompound().setBoolean("active", true);
		return is;
	}

	@Override
	public void registerSoundEffects() {
		SOUND_NIGHT_VISION_ON = registerSoundEvent("nightvision.on");
		SOUND_NIGHT_VISION_OFF = registerSoundEvent("nightvision.off");
		SOUND_TASER = registerSoundEvent("taser");
	}

	@Override
	public void registerEntities() {
		registerEntity(EntityEntryBuilder.create()
				.entity(EntitySmokeBombThrowable.class)
				.id(new ResourceLocation(ApocalypseCore.MODID, "smokebomb"), entityId++)
				.name("smokebomb")
				.tracker(64, 1, true));

		registerEntity(EntityEntryBuilder.create()
				.entity(EntitySmokeCloud.class)
				.id(new ResourceLocation(ApocalypseCore.MODID, "smokecloud"), entityId++)
				.name("smokecloud")
				.tracker(64, 1, true));

		registerEntity(EntityEntryBuilder.create()
				.entity(EntityGrapplingHook.class)
				.id(new ResourceLocation(ApocalypseCore.MODID, "grapplinghook"), entityId++)
				.name("grapplinghook")
				.tracker(64, 1, true));

		registerEntity(EntityEntryBuilder.create()
				.entity(EntityMolotovCocktail.class)
				.id(new ResourceLocation(ApocalypseCore.MODID, "molotovcocktail"), entityId++)
				.name("molotovcocktail")
				.tracker(64, 1, true));
	}

	@Override
	public void regsterEntityRenderers() {
		RenderingRegistry.registerEntityRenderingHandler(EntitySmokeBombThrowable.class, RenderEntitySmokeBombThrowableFactory.INSTANCE);
		RenderingRegistry.registerEntityRenderingHandler(EntityGrapplingHook.class, RenderGrapplingHookFactory.INSTANCE);
		RenderingRegistry.registerEntityRenderingHandler(EntityMolotovCocktail.class, RenderEntityMolotovCocktailFactory.INSTANCE);
	}

	@SideOnly(Side.CLIENT)
	public static class RenderEntitySmokeBombThrowableFactory implements IRenderFactory<EntitySmokeBombThrowable> {
		public final static RenderEntitySmokeBombThrowableFactory INSTANCE = new RenderEntitySmokeBombThrowableFactory();

		@Override
		public Render<EntitySmokeBombThrowable> createRenderFor(RenderManager manager)
		{
			return new RenderItemSmokebomb(manager, FeatureWeapons.smokeBomb);
		}
	}


	@SideOnly(Side.CLIENT)
	public static class RenderGrapplingHookFactory implements IRenderFactory<EntityGrapplingHook> {
		public final static RenderGrapplingHookFactory INSTANCE = new RenderGrapplingHookFactory();

		@Override
		public Render<EntityGrapplingHook> createRenderFor(RenderManager manager)
		{
			return new RenderEntityGrapplingHook(manager);
		}
	}

	@SideOnly(Side.CLIENT)
	public static class RenderEntityMolotovCocktailFactory implements IRenderFactory<EntityMolotovCocktail> {
		public final static RenderEntityMolotovCocktailFactory INSTANCE = new RenderEntityMolotovCocktailFactory();

		@Override
		public Render<EntityMolotovCocktail> createRenderFor(RenderManager manager)
		{
			return new RenderEntityMolotovCocktail(manager, FeatureWeapons.molotovCocktail);
		}
	}

}
