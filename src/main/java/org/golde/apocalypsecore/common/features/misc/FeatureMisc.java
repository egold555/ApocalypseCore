package org.golde.apocalypsecore.common.features.misc;

import org.golde.apocalypsecore.common.ApocalypseCore;
import org.golde.apocalypsecore.common.blocks._ACBlock;
import org.golde.apocalypsecore.common.features.Feature;
import org.golde.apocalypsecore.common.features.misc.blocks.lootchest.BlockLootChest;
import org.golde.apocalypsecore.common.features.misc.commands.CommandDeadBody;
import org.golde.apocalypsecore.common.features.misc.commands.CommandKit;
import org.golde.apocalypsecore.common.features.misc.entity.EntityDeadBody;
import org.golde.apocalypsecore.common.features.misc.entity.EntityFallingLootCrate;
import org.golde.apocalypsecore.common.features.misc.items.ItemWrench;
import org.golde.apocalypsecore.common.features.misc.items.kit.ItemKit;
import org.golde.apocalypsecore.common.items._ACItem;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;

public class FeatureMisc extends Feature {

	public static _ACBlock exampleBlock;
	public static _ACItem exampleItem;
	public static ItemWrench wrench;
	public static BlockLootChest lootChest;
	public static ItemKit kit;

//	public static Fluid fluidGasoline;
//	public static _ACBlockFluidClassic blockFluidGasoline;

	@Override
	public void registerFluids() {
//		fluidGasoline = new Fluid(
//				"gasoline",
//				new ResourceLocation(ApocalypseCore.MODID + ":blocks/fluid/gasoline_still"),
//				new ResourceLocation(ApocalypseCore.MODID + ":blocks/fluid/gasoline_flow")
//				).setDensity(789).setViscosity(1200);
//		registerFluid(fluidGasoline);
	}


	@Override
	public void registerBlocks() {
		//registerBlock(blockFluidGasoline = new _ACBlockFluidClassic("fluid_gasoline", fluidGasoline).setFlammability(60, 200));
		registerBlock(exampleBlock = new _ACBlock("example_block"));
		registerBlock(lootChest = new BlockLootChest());
	}

	@Override
	public void registerItems() {
		registerItem(exampleItem = new _ACItem("example_item"));
		registerItem(wrench = new ItemWrench());
		registerItem(kit = new ItemKit());
	}

	@Override
	public void registerEntities() {

		registerEntity(EntityEntryBuilder.create()
				.entity(EntityFallingLootCrate.class)
				.id(new ResourceLocation(ApocalypseCore.MODID, "fallinglootcrate"), entityId++)
				.name("fallinglootcrate")
				.tracker(64, 1, true));
		
		registerEntity(EntityEntryBuilder.create()
				.entity(EntityDeadBody.class)
				.id(new ResourceLocation(ApocalypseCore.MODID, "deadbody"), entityId++)
				.name("deadbody")
				.tracker(64, 1, true));
	}
	
	@Override
	public void registerSeverCommands() {
		registerServerCommand(new CommandKit());
		registerServerCommand(new CommandDeadBody());
	}
	


	@Override
	public ItemStack getTabIcon() {
		return new ItemStack(wrench);
	}



}
