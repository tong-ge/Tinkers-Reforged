package mrthomas20121.tinkers_reforged.modules;

import mrthomas20121.tinkers_reforged.Reference;
import mrthomas20121.tinkers_reforged.compat.MekanismCompat;
import mrthomas20121.tinkers_reforged.library.MaterialGen;
import mrthomas20121.tinkers_reforged.ReforgedTraits;
import mrthomas20121.tinkers_reforged.config.TinkersReforgedConfig;
import mrthomas20121.tinkers_reforged.library.ModuleBase;
import net.minecraft.util.ResourceLocation;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.*;
import slimeknights.tconstruct.library.tools.IToolPart;
import slimeknights.tconstruct.library.utils.HarvestLevels;
import slimeknights.tconstruct.tools.TinkerMaterials;
import slimeknights.tconstruct.tools.TinkerTraits;

public class MaterialsMekanism extends ModuleBase {

    private final MaterialGen osmium = new MaterialGen("osmium", 0x7F8EB2, "Osmium", 700);
    private final Material refined_obsidian = new Material("ref_refined_obsidian", 0x463763);
    private final Material refined_glowstone = new Material("ref_refined_glowstone", 0xEAC829);

    public MaterialsMekanism() {
        super(new ResourceLocation(Reference.mekanism, "module"));
    }

    @Override
    public boolean canLoad() {
        return TinkersReforgedConfig.SettingMaterials.modules.mekanism;
    }

    @Override
    public void preInit() {
        if(TinkersReforgedConfig.SettingMaterials.materials.osmium) {
            osmium.preInit();
            osmium.getMaterial().addTrait(TinkerTraits.established, MaterialTypes.HEAD);
            osmium.getMaterial().addTrait(TinkerTraits.dense);
            TinkerRegistry.addMaterial(osmium.getMaterial());
            TinkerRegistry.addMaterialStats(osmium.getMaterial(),
                    new HeadMaterialStats(410, 5.5f, 5.5f, HarvestLevels.DIAMOND),
                    new HandleMaterialStats(1.2f, 80),
                    new ExtraMaterialStats(50),
                    new BowMaterialStats(2.9f, 7, 5.1f));
        }
        if(TinkersReforgedConfig.SettingMaterials.materials.refined_obsidian) {
            refined_obsidian.addTrait(ReforgedTraits.soft, MaterialTypes.HEAD);
            refined_obsidian.addTrait(TinkerTraits.duritos);
            TinkerRegistry.addMaterial(refined_obsidian);
            TinkerRegistry.addMaterialStats(refined_obsidian,
                    new HeadMaterialStats(550, 6.5f, 6.5f, HarvestLevels.OBSIDIAN),
                    new HandleMaterialStats(1.2f, 100),
                    new ExtraMaterialStats(50),
                    new BowMaterialStats(3.2f, 5, 6.1f));
        }
        if(TinkersReforgedConfig.SettingMaterials.materials.refined_glowstone) {
            refined_glowstone.addTrait(ReforgedTraits.overHealing, MaterialTypes.HEAD);
            refined_glowstone.addTrait(ReforgedTraits.blessedSyringe);
            TinkerRegistry.addMaterial(refined_glowstone);
            TinkerRegistry.addMaterialStats(refined_glowstone,
                    new HeadMaterialStats(550, 6.5f, 6.5f, HarvestLevels.OBSIDIAN),
                    new HandleMaterialStats(1.2f, 100),
                    new ExtraMaterialStats(50),
                    new BowMaterialStats(3.2f, 5, 6.1f));
        }
    }

    @Override
    public void init() {
        if(TinkersReforgedConfig.SettingMaterials.materials.osmium) {
            osmium.init();
        }
        if(TinkersReforgedConfig.SettingMaterials.materials.refined_obsidian) {
            refined_obsidian.addCommonItems("RefinedObsidian");
            refined_obsidian.setRepresentativeItem("ingotRefinedObsidian");
            for(IToolPart part : TinkerRegistry.getToolParts())
            {
                if(part.canUseMaterial(refined_obsidian) && (part.canBeCasted() || part.canBeCrafted()))
                {
                    MekanismCompat.addInfusionRecipe("DIAMOND", 10, part.getItemstackWithMaterial(TinkerMaterials.obsidian), part.getItemstackWithMaterial(refined_obsidian));
                }
            }
        }
        if(TinkersReforgedConfig.SettingMaterials.materials.refined_glowstone) {
            refined_glowstone.setRepresentativeItem("ingotRefinedGlowstone");
            refined_glowstone.addCommonItems("RefinedGlowstone");

            for(IToolPart part : TinkerRegistry.getToolParts())
            {
                if(part.canUseMaterial(refined_glowstone) && (part.canBeCasted() || part.canBeCrafted()))
                {
                    MekanismCompat.addOsmiumCompressorRecipe(part.getItemstackWithMaterial(TinkerRegistry.getMaterial("ref_glowstone")), part.getItemstackWithMaterial(refined_glowstone));
                }
            }
        }
    }
}
