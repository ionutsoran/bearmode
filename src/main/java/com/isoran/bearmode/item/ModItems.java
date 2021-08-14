package com.isoran.bearmode.item;

import com.isoran.bearmode.Bearmode;
import com.isoran.bearmode.block.ModBlocks;
import com.isoran.bearmode.block.ModFluids;
import com.isoran.bearmode.entity.ModEntityTypes;
import com.isoran.bearmode.util.Registration;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;

public class ModItems {

    public static final RegistryObject<Item> COPPER_INGOT =
            Registration.ITEMS.register("copper_ingot",
                    () -> new Item(new Item.Properties().tab(Bearmode.COURSE_TAB)));

    public static final RegistryObject<Item> COPPER_WIRE =
            Registration.ITEMS.register("copper_wire",
                    () -> new Item(new Item.Properties().tab(Bearmode.COURSE_TAB)));

    public static final RegistryObject<Item> COPPERED_APPLE =
            Registration.ITEMS.register("coppered_apple",
                    () -> new CopperedApple());

    public static final RegistryObject<Item> ZUCCINI_SEED =
            Registration.ITEMS.register("zuccini_seed",
                    () -> new BlockItem(ModBlocks.ZUCCINI_CROP.get(),
                            new Item.Properties().tab(Bearmode.COURSE_TAB)));
    @Deprecated
    public static final RegistryObject<Item> OIL_BUCKET =
            Registration.ITEMS.register("oil_bucket",
                    () -> new BucketItem(ModFluids.OIL_FLUID::get,
                            new Item.Properties().tab(Bearmode.COURSE_TAB).stacksTo(1)));

    public static final RegistryObject<Item> JUICED_VILLAGER_EGG =
            Registration.ITEMS.register("juiced_villager_egg",
                    () -> new ModSpawnEggItem(
                            ModEntityTypes.JUICED_VILLAGER,
                            1,
                            0xff115544,
                            new Item.Properties().tab(Bearmode.COURSE_TAB)));

    public static final RegistryObject<Item> BUFFALO_EGG =
            Registration.ITEMS.register("buffalo_egg",
                    () -> new ModSpawnEggItem(
                            ModEntityTypes.BUFFALO,
                            0xff241344,
                            0xff123455,
                            new Item.Properties().tab(Bearmode.COURSE_TAB)));

    /* TOOLS */
    public static final RegistryObject<Item> COPPER_SHOVEL =
            Registration.ITEMS.register("coppered_shovel",
                    () -> new ShovelItem(ModItemTier.COPPER, 0f, 0f,
                            new Item.Properties()
                                    .addToolType(ToolType.SHOVEL, 2)
                                    .tab(Bearmode.COURSE_TAB)));

    public static final RegistryObject<Item> COPPER_SWORD =
            Registration.ITEMS.register("coppered_sword",
                    () -> new SwordItem(ModItemTier.COPPER, 2, 0f,
                            new Item.Properties()
                                    .tab(Bearmode.COURSE_TAB)));

    public static final RegistryObject<Item> COPPER_PICKAXE =
            Registration.ITEMS.register("coppered_pickaxe",
                    () -> new PickaxeItem(ModItemTier.COPPER, 2, 0f,
                            new Item.Properties()
                                    .addToolType(ToolType.PICKAXE, 2)
                                    .tab(Bearmode.COURSE_TAB)));

    public static final RegistryObject<Item> COPPER_AXE =
            Registration.ITEMS.register("coppered_axe",
                    () -> new AxeItem(ModItemTier.COPPER, 2, 0f,
                            new Item.Properties()
                                    .addToolType(ToolType.AXE, 2)
                                    .tab(Bearmode.COURSE_TAB)));

    public static final RegistryObject<Item> COPPER_HOE =
            Registration.ITEMS.register("coppered_hoe",
                    () -> new HoeItem(ModItemTier.COPPER, 2, 0f,
                            new Item.Properties()
                                    .addToolType(ToolType.HOE, 2)
                                    .tab(Bearmode.COURSE_TAB)));

    /* ARMOR */

    public static final RegistryObject<Item> COPPER_HELMET =
            Registration.ITEMS.register("copper_helmet",
                    () ->  new ArmorItem(ModArmorMaterial.COPPER, EquipmentSlotType.HEAD,
                            new Item.Properties().tab(Bearmode.COURSE_TAB)));

    public static final RegistryObject<Item> COPPER_CHESTPLATE =
            Registration.ITEMS.register("copper_chestplate",
                    () ->  new ArmorItem(ModArmorMaterial.COPPER, EquipmentSlotType.CHEST,
                            new Item.Properties().tab(Bearmode.COURSE_TAB)));

    public static final RegistryObject<Item> COPPER_LEGGINGS =
            Registration.ITEMS.register("copper_leggings",
                    () ->  new ArmorItem(ModArmorMaterial.COPPER, EquipmentSlotType.LEGS,
                            new Item.Properties().tab(Bearmode.COURSE_TAB)));

    public static final RegistryObject<Item> COPPER_BOOTS =
            Registration.ITEMS.register("copper_boots",
                    () ->  new ArmorItem(ModArmorMaterial.COPPER, EquipmentSlotType.FEET,
                            new Item.Properties().tab(Bearmode.COURSE_TAB)));

    public static void register() {}

    public enum ModItemTier implements IItemTier
    {
        COPPER(2, 150, 2.5f, 0f, 15,
                Ingredient.of(new ItemStack(ModItems.COPPER_INGOT.get())));

        private final int harvesLevel;
        private final int maxUses;
        private final float efficiency;
        private final float attackDamage;
        private final int enchantability;
        private final Ingredient repairMaterial;

        ModItemTier(int harvesLevel, int maxUses, float efficiency, float attackDamage, int enchantability, Ingredient repairMaterial) {
            this.harvesLevel = harvesLevel;
            this.maxUses = maxUses;
            this.efficiency = efficiency;
            this.attackDamage = attackDamage;
            this.enchantability = enchantability;
            this.repairMaterial = repairMaterial;
        }

        @Override
        public int getUses() {
            return maxUses;
        }

        @Override
        public float getSpeed() {
            return efficiency;
        }

        @Override
        public float getAttackDamageBonus() {
            return attackDamage;
        }

        @Override
        public int getLevel() {
            return harvesLevel;
        }

        @Override
        public int getEnchantmentValue() {
            return enchantability;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return repairMaterial;
        }
    }

    public enum ModArmorMaterial implements IArmorMaterial
    {
        COPPER(50, new int[] {2, 7, 5, 3}, 10, SoundEvents.ARMOR_EQUIP_IRON,
                Ingredient.of(new ItemStack(ModItems.COPPER_INGOT.get())), Bearmode.MOD_ID + ":copper",
                0, 0.1f);

        private final int durabilityForSlot;
        private final int[] defenseForSlot;
        private final int enchantmentValue;
        private final SoundEvent equipSound;
        private final Ingredient repairIngredient;
        private final String name;
        private final float toughness;
        private final float knockbackResistance;

        ModArmorMaterial(int durabilityForSlot, int[] defenseForSlot, int enchantmentValue,
                         SoundEvent equipSound, Ingredient repairIngredient, String name,
                         float toughness, float knockbackResistance) {

            this.durabilityForSlot = durabilityForSlot;
            this.defenseForSlot = defenseForSlot;
            this.enchantmentValue = enchantmentValue;
            this.equipSound = equipSound;
            this.repairIngredient = repairIngredient;
            this.name = name;
            this.toughness = toughness;
            this.knockbackResistance = knockbackResistance;
        }

        @Override
        public int getDurabilityForSlot(EquipmentSlotType slotIn) {
            return durabilityForSlot;
        }

        @Override
        public int getDefenseForSlot(EquipmentSlotType slotIn) {
            return defenseForSlot[slotIn.getIndex()];
        }

        @Override
        public int getEnchantmentValue() {
            return enchantmentValue;
        }

        @Override
        public SoundEvent getEquipSound() {
            return equipSound;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return repairIngredient;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public float getToughness() {
            return toughness;
        }

        @Override
        public float getKnockbackResistance() {
            return knockbackResistance;
        }
    }
}
