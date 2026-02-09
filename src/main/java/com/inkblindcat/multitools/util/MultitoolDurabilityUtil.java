package com.inkblindcat.multitools.util;

import com.hypixel.hytale.server.core.inventory.ItemStack;
import com.hypixel.hytale.server.core.inventory.container.ItemContainer;
import com.inkblindcat.multitools.Multitools;
import com.inkblindcat.multitools.MultitoolsConfig;

import java.util.Map;

public class MultitoolDurabilityUtil {

    public static void migrateContainerDurability(
            ItemContainer container
    ) {
        MultitoolsConfig config = Multitools.getInstance().getConfig().get();
        if (!config.isDurabilityOverrideEnabled()) return;
        if (container == null) return;

        Map<String, Double> durabilityMap = createDurabilityMap();

        short size = container.getCapacity();
        for (short slot = 0; slot < size; slot++) {
            migrateSlot(container, slot, durabilityMap);
        }
    }

    public static void migrateSlot(ItemContainer container, short slot, Map<String, Double> durabilityMap) {
        ItemStack stack = container.getItemStack(slot);
        if (stack == null || stack.isEmpty() || stack.isUnbreakable()) return;

        String itemId = stack.getItemId();

        Double newMaxDurability = durabilityMap.get(stack.getItemId());
        double oldMax = stack.getMaxDurability();
        double current = stack.getDurability();

        if (newMaxDurability == null || oldMax == newMaxDurability) return;
        if (oldMax <= 0) return;

        float ratio = (float) current / (float) oldMax;
        double newCurrent = Math.max(1, Math.round(newMaxDurability * ratio));

        ItemStack migrated = new ItemStack(itemId, stack.getQuantity())
                .withMaxDurability(newMaxDurability)
                .withDurability(newCurrent);

        container.setItemStackForSlot(slot, migrated);
    }

    private static Map<String, Double> createDurabilityMap() {
        MultitoolsConfig config = Multitools.getInstance().getConfig().get();
        return Map.of(
                "Tool_Hatchet_Pickaxe_Multitool_Crude", config.getCrudeMultitoolMaxDurability(),
                "Tool_Hatchet_Pickaxe_Multitool_Copper", config.getCopperMultitoolMaxDurability(),
                "Tool_Hatchet_Pickaxe_Multitool_Iron", config.getIronMultitoolMaxDurability(),
                "Tool_Hatchet_Pickaxe_Multitool_Thorium", config.getThoriumMultitoolMaxDurability(),
                "Tool_Hatchet_Pickaxe_Multitool_Cobalt", config.getCobaltMultitoolMaxDurability(),
                "Tool_Hatchet_Pickaxe_Multitool_Adamantite", config.getAdamantiteMultitoolMaxDurability(),
                "Tool_Hatchet_Pickaxe_Multitool_Onyxium", config.getOnyxiumMultitoolMaxDurability(),
                "Tool_Hatchet_Pickaxe_Multitool_Mithril", config.getMithrilMultitoolMaxDurability(),
                "Tool_Hatchet_Pickaxe_Multitool_Firetine", config.getFiretineMultitoolMaxDurability()
        );
    }
}
