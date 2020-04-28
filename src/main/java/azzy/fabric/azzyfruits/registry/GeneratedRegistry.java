package azzy.fabric.azzyfruits.registry;

import azzy.fabric.azzyfruits.generated.CindermoteFeature;
import azzy.fabric.azzyfruits.generated.PlantGen;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.CountChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.RandomPatchFeatureConfig;
import net.minecraft.world.gen.placer.SimpleBlockPlacer;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;

import static azzy.fabric.azzyfruits.ForgottenFruits.MODID;
import static azzy.fabric.azzyfruits.registry.CropRegistry.CLOUDBERRY_WILD;

public class GeneratedRegistry {

    public static Feature<DefaultFeatureConfig> register(String name, PlantGen item){
        Registry.register(Registry.FEATURE, new Identifier(MODID, name), item);
        return item;
    }

    private static final Feature<DefaultFeatureConfig> CINDERMOTE_FIELD = Registry.register(
            Registry.FEATURE,
            new Identifier(MODID, "cindermote_field"),
            new CindermoteFeature(DefaultFeatureConfig::deserialize)
    );

    public static void init(){
        Biomes.TAIGA.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Feature.RANDOM_PATCH.configure(new RandomPatchFeatureConfig.Builder(new WeightedBlockStateProvider().addState(CLOUDBERRY_WILD.getDefaultState(), 1), new SimpleBlockPlacer()).tries(30).spreadX(4).spreadZ(4).build()).createDecoratedFeature(Decorator.COUNT_CHANCE_HEIGHTMAP_DOUBLE.configure(new CountChanceDecoratorConfig(1, 0.075f))));
        Biomes.NETHER.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, CINDERMOTE_FIELD.configure(new DefaultFeatureConfig()).createDecoratedFeature(Decorator.CHANCE_HEIGHTMAP.configure(new ChanceDecoratorConfig(1000))));
    }
}
