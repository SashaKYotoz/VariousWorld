package net.sashakyotoz.variousworld.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.common.extensions.IForgeMenuType;

import net.minecraft.world.inventory.MenuType;

import net.sashakyotoz.variousworld.client.menus.ArchOfGemsMenu;
import net.sashakyotoz.variousworld.client.menus.MycolocyfarographGUIMenu;
import net.sashakyotoz.variousworld.client.menus.DisenchantTableGUIMenu;
import net.sashakyotoz.variousworld.client.menus.ArmorStationMenu;
import net.sashakyotoz.variousworld.VariousWorld;

public class VWMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.MENU_TYPES, VariousWorld.MODID);
	public static final RegistryObject<MenuType<ArchOfGemsMenu>> ARCH_OF_GEMS = REGISTRY.register("archofgems", () -> IForgeMenuType.create(ArchOfGemsMenu::new));
	public static final RegistryObject<MenuType<ArmorStationMenu>> ARMOR_STATION = REGISTRY.register("armor_station", () -> IForgeMenuType.create(ArmorStationMenu::new));
	public static final RegistryObject<MenuType<DisenchantTableGUIMenu>> DISENCHANT_TABLE_GUI = REGISTRY.register("disenchant_table_gui", () -> IForgeMenuType.create(DisenchantTableGUIMenu::new));
	public static final RegistryObject<MenuType<MycolocyfarographGUIMenu>> MYCOLOCYFAROGRAPH_GUI = REGISTRY.register("mycolocyfarograph_gui", () -> IForgeMenuType.create(MycolocyfarographGUIMenu::new));
}
