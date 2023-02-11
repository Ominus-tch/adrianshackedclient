package adrian.hacks.xray;

import adrian.hacks.AdriansMod.FabricModAdriansMod;
import net.minecraft.block.BlockState;

import java.util.HashMap;
import java.util.HashSet;

public class Xray {
    private HashSet<String> xrayBlocks = new HashSet<>();
    private HashMap<String, Float> blockBrightness = new HashMap<>();

    public Xray() {
        xrayBlocks.add("Block{minecraft:ancient_debris}");
        xrayBlocks.add("Block{minecraft:anvil}");
        xrayBlocks.add("Block{minecraft:beacon}");
        xrayBlocks.add("Block{minecraft:bone_block}");
        xrayBlocks.add("Block{minecraft:bookshelf}");
        xrayBlocks.add("Block{minecraft:brewing_stand}");
        xrayBlocks.add("Block{minecraft:chain_command_block}");
        xrayBlocks.add("Block{minecraft:chest}");
        xrayBlocks.add("Block{minecraft:clay}");
        xrayBlocks.add("Block{minecraft:coal_block}");
        xrayBlocks.add("Block{minecraft:coal_ore}");
        xrayBlocks.add("Block{minecraft:command_block}");
        xrayBlocks.add("Block{minecraft:copper_ore}");
        xrayBlocks.add("Block{minecraft:crafting_table}");
        xrayBlocks.add("Block{minecraft:deepslate_coal_ore}");
        xrayBlocks.add("Block{minecraft:deepslate_copper_ore}");
        xrayBlocks.add("Block{minecraft:deepslate_diamond_ore}");
        xrayBlocks.add("Block{minecraft:deepslate_gold_ore}");
        xrayBlocks.add("Block{minecraft:deepslate_iron_ore}");
        xrayBlocks.add("Block{minecraft:deepslate_lapis_ore}");
        xrayBlocks.add("Block{minecraft:deepslate_redstone_ore}");
        xrayBlocks.add("Block{minecraft:diamond_block}");
        xrayBlocks.add("Block{minecraft:diamond_ore}");
        xrayBlocks.add("Block{minecraft:dispenser}");
        xrayBlocks.add("Block{minecraft:dropper}");
        xrayBlocks.add("Block{minecraft:emerald_block}");
        xrayBlocks.add("Block{minecraft:emerald_ore}");
        xrayBlocks.add("Block{minecraft:enchanting_table}");
        xrayBlocks.add("Block{minecraft:end_portal}");
        xrayBlocks.add("Block{minecraft:end_portal_frame}");
        xrayBlocks.add("Block{minecraft:ender_chest}");
        xrayBlocks.add("Block{minecraft:furnace}");
        xrayBlocks.add("Block{minecraft:glowstone}");
        xrayBlocks.add("Block{minecraft:gold_block}");
        xrayBlocks.add("Block{minecraft:gold_ore}");
        xrayBlocks.add("Block{minecraft:hopper}");
        xrayBlocks.add("Block{minecraft:iron_block}");
        xrayBlocks.add("Block{minecraft:iron_ore}");
        xrayBlocks.add("Block{minecraft:ladder}");
        xrayBlocks.add("Block{minecraft:lapis_block}");
        xrayBlocks.add("Block{minecraft:lapis_ore}");
        xrayBlocks.add("Block{minecraft:lodestone}");
        xrayBlocks.add("Block{minecraft:mossy_cobblestone}");
        xrayBlocks.add("Block{minecraft:mossy_stone_bricks}");
        xrayBlocks.add("Block{minecraft:netherite_block}");
        xrayBlocks.add("Block{minecraft:nether_gold_ore}");
        xrayBlocks.add("Block{minecraft:nether_portal}");
        xrayBlocks.add("Block{minecraft:nether_quartz_ore}");
        xrayBlocks.add("Block{minecraft:raw_copper_block}");
        xrayBlocks.add("Block{minecraft:raw_gold_block}");
        xrayBlocks.add("Block{minecraft:raw_iron_block}");
        xrayBlocks.add("Block{minecraft:redstone_block}");
        xrayBlocks.add("Block{minecraft:redstone_ore}");
        xrayBlocks.add("Block{minecraft:repeating_command_block}");
        xrayBlocks.add("Block{minecraft:spawner}");
        xrayBlocks.add("Block{minecraft:tnt}");
        xrayBlocks.add("Block{minecraft:torch}");
        xrayBlocks.add("Block{minecraft:trapped_chest}");
        xrayBlocks.add("Block{minecraft:obsidian}");

        blockBrightness.put("Block{minecraft:ancient_debris}", 1.0f);
        blockBrightness.put("Block{minecraft:anvil}", 1.0f);
        blockBrightness.put("Block{minecraft:beacon}", 1.0f);
        blockBrightness.put("Block{minecraft:bone_block}", 1.0f);
        blockBrightness.put("Block{minecraft:bookshelf}", 1.0f);
        blockBrightness.put("Block{minecraft:brewing_stand}", 1.0f);
        blockBrightness.put("Block{minecraft:chain_command_block}", 1.0f);
        blockBrightness.put("Block{minecraft:chest}", 1.0f);
        blockBrightness.put("Block{minecraft:clay}", 1.0f);
        blockBrightness.put("Block{minecraft:coal_block}", 1.0f);
        blockBrightness.put("Block{minecraft:coal_ore}", 1.0f);
        blockBrightness.put("Block{minecraft:command_block}", 1.0f);
        blockBrightness.put("Block{minecraft:copper_ore}", 1.0f);
        blockBrightness.put("Block{minecraft:crafting_table}", 1.0f);
        blockBrightness.put("Block{minecraft:deepslate_coal_ore}", 1.0f);
        blockBrightness.put("Block{minecraft:deepslate_copper_ore}", 1.0f);
        blockBrightness.put("Block{minecraft:deepslate_diamond_ore}", 1.0f);
        blockBrightness.put("Block{minecraft:deepslate_gold_ore}", 1.0f);
        blockBrightness.put("Block{minecraft:deepslate_iron_ore}", 1.0f);
        blockBrightness.put("Block{minecraft:deepslate_lapis_ore}", 1.0f);
        blockBrightness.put("Block{minecraft:deepslate_redstone_ore}", 1.0f);
        blockBrightness.put("Block{minecraft:diamond_block}", 1.0f);
        blockBrightness.put("Block{minecraft:diamond_ore}", 1.0f);
        blockBrightness.put("Block{minecraft:dispenser}", 1.0f);
        blockBrightness.put("Block{minecraft:dropper}", 1.0f);
        blockBrightness.put("Block{minecraft:emerald_block}", 1.0f);
        blockBrightness.put("Block{minecraft:emerald_ore}", 1.0f);
        blockBrightness.put("Block{minecraft:enchanting_table}", 1.0f);
        blockBrightness.put("Block{minecraft:end_portal}", 1.0f);
        blockBrightness.put("Block{minecraft:end_portal_frame}", 1.0f);
        blockBrightness.put("Block{minecraft:ender_chest}", 1.0f);
        blockBrightness.put("Block{minecraft:furnace}", 1.0f);
        blockBrightness.put("Block{minecraft:glowstone}", 1.0f);
        blockBrightness.put("Block{minecraft:gold_block}", 1.0f);
        blockBrightness.put("Block{minecraft:gold_ore}", 1.0f);
        blockBrightness.put("Block{minecraft:hopper}", 1.0f);
        blockBrightness.put("Block{minecraft:iron_block}", 1.0f);
        blockBrightness.put("Block{minecraft:iron_ore}", 1.0f);
        blockBrightness.put("Block{minecraft:ladder}", 1.0f);
        blockBrightness.put("Block{minecraft:lapis_block}", 1.0f);
        blockBrightness.put("Block{minecraft:lapis_ore}", 1.0f);
        blockBrightness.put("Block{minecraft:lodestone}", 1.0f);
        blockBrightness.put("Block{minecraft:mossy_cobblestone}", 1.0f);
        blockBrightness.put("Block{minecraft:mossy_stone_bricks}", 1.0f);
        blockBrightness.put("Block{minecraft:netherite_block}", 1.0f);
        blockBrightness.put("Block{minecraft:nether_gold_ore}", 1.0f);
        blockBrightness.put("Block{minecraft:nether_portal}", 1.0f);
        blockBrightness.put("Block{minecraft:nether_quartz_ore}", 1.0f);
        blockBrightness.put("Block{minecraft:raw_copper_block}", 1.0f);
        blockBrightness.put("Block{minecraft:raw_gold_block}", 1.0f);
        blockBrightness.put("Block{minecraft:raw_iron_block}", 1.0f);
        blockBrightness.put("Block{minecraft:redstone_block}", 1.0f);
        blockBrightness.put("Block{minecraft:redstone_ore}", 1.0f);
        blockBrightness.put("Block{minecraft:repeating_command_block}", 1.0f);
        blockBrightness.put("Block{minecraft:spawner}", 1.0f);
        blockBrightness.put("Block{minecraft:tnt}", 1.0f);
        blockBrightness.put("Block{minecraft:torch}", 1.0f);
        blockBrightness.put("Block{minecraft:trapped_chest}", 1.0f);
        blockBrightness.put("Block{minecraft:obsidian}", 1.0f);
    }

    public boolean showBlock(BlockState state) {
        if(xrayBlocks.contains(state.getBlock().toString())) {
            return true;
        }
        return false;
    }
}