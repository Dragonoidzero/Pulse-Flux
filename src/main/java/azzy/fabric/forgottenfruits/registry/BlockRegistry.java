package azzy.fabric.forgottenfruits.registry;

import azzy.fabric.forgottenfruits.block.BEBlocks.*;
import azzy.fabric.forgottenfruits.block.NetherFarmland;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tools.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.shape.VoxelShape;

import java.util.ArrayList;

import static azzy.fabric.forgottenfruits.ForgottenFruits.*;
import static azzy.fabric.forgottenfruits.registry.CropRegistry.*;

public class BlockRegistry {

    public static final VoxelShape DEFAULT_SHAPE = Block.createCuboidShape(1, 0, 1, 15, 15, 15);

    //Other
    public static final NetherFarmland NETHER_FARMLAND = (NetherFarmland) register("nether_farmland", new NetherFarmland(FabricBlockSettings.of(Material.STONE).materialColor(MaterialColor.PURPLE).nonOpaque().sounds(BlockSoundGroup.SAND).lightLevel(2).dropsLike(Blocks.OBSIDIAN).breakByTool(FabricToolTags.PICKAXES, 3).hardness(20f).ticksRandomly().lightLevel(1).build()), defaultSettings());

    //misc blocks
    public static final Block CLOUDBERRY_BLOCK = register("cloudberry_block", new Block(FabricBlockSettings.of(Material.UNDERWATER_PLANT).materialColor(MaterialColor.ORANGE).nonOpaque().breakInstantly().sounds(BlockSoundGroup.SLIME).slipperiness(0.8f).build()), defaultSettings());
    public static final Block IGNOBLE_BLOCK = register("ignoble_silk_block", new Block(FabricBlockSettings.of(Material.WOOL).materialColor(MaterialColor.BROWN).sounds(BlockSoundGroup.SNOW).build()), materialSettings());

    //Block Entities
    public static final Block PRESS_BLOCK = register("press_block", new PressBlock(null , Material.WOOD, BlockSoundGroup.WOOD, 0, DEFAULT_SHAPE), new Item.Settings().group(BLOCK_ENTITIES));
    public static final Block BASKET_BLOCK = register("basket_block", new BasketBlock(null, Material.WOOL, BlockSoundGroup.WOOL, 0, Block.createCuboidShape(1, 0, 1, 15, 8, 15)), new Item.Settings().group(BLOCK_ENTITIES).maxCount(1), true);
    public static final Block WOODPIPE_BLOCK = register("wooden_pipe", new WoodPipe(null, Material.WOOD, BlockSoundGroup.WOOD, 0, DEFAULT_SHAPE), machineSettings());
    public static final Block BARREL_BLOCK = register("barrel_block", new BarrelBlock(null, Material.WOOD, BlockSoundGroup.WOOD, 0, DEFAULT_SHAPE), machineSettings());
    public static final Block WITCH_CAULDRON_BLOCK = register("witch_cauldron_block", new WitchCauldronBlock(null, Material.METAL, BlockSoundGroup.METAL, 0, Block.createCuboidShape(0, 0, 0, 16, 11, 16)), machineSettings());

    private static Item.Settings defaultSettings(){
        return new Item.Settings().group(PLANT_STUFF);
    }
    private static Item.Settings machineSettings(){
        return new Item.Settings().group(BLOCK_ENTITIES);
    }
    private static Item.Settings materialSettings(){
        return new Item.Settings().group(PLANT_MATERIALS);
    }

    private static Block register(String name, Block item, Item.Settings settings){
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, name), item);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, name), new BlockItem(item, settings));
        return item;
    }
    private static Block register(String name, Block item, Item.Settings settings, boolean storage){
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, name), item);
        return item;
    }

    @Environment(EnvType.CLIENT)
    public static ArrayList<Block> registryTrans = new ArrayList<>();
    @Environment(EnvType.CLIENT)
    public static ArrayList<Block> registryPartial = new ArrayList<>();

    @Environment(EnvType.CLIENT)
    public static void initTransparency(){
        registryTrans.add(CLOUDBERRY_BLOCK);
    }

    @Environment(EnvType.CLIENT)
    public static void initPartialblocks(){
        registryPartial.add(CLOUDBERRY_CROP);
        registryPartial.add(CLOUDBERRY_WILD);
        registryPartial.add(CINDERMOTE_CROP);
        registryPartial.add(CINDERMOTE_WILD);
    }

    public static void init(){
    }

}
