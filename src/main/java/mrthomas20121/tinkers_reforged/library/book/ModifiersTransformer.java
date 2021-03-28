package mrthomas20121.tinkers_reforged.library.book;

import mrthomas20121.tinkers_reforged.TinkersReforged;
import mrthomas20121.tinkers_reforged.modules.actuallyadditions.MaterialsAA;
import net.minecraftforge.fml.common.Loader;
import slimeknights.mantle.client.book.data.BookData;
import slimeknights.mantle.client.book.data.PageData;
import slimeknights.mantle.client.book.data.SectionData;
import slimeknights.mantle.client.book.repository.FileRepository;
import slimeknights.tconstruct.library.book.content.ContentListing;
import slimeknights.tconstruct.library.book.sectiontransformer.SectionTransformer;
import slimeknights.tconstruct.library.modifiers.ModifierTrait;

public class ModifiersTransformer extends SectionTransformer {

    public ModifiersTransformer()
    {
        super("ref_modifiers");
    }

    @Override
    public void transform(BookData book, SectionData section) {
        if(Loader.isModLoaded("actuallyadditions"))
        {
            addModifier(section, MaterialsAA.modEnderStar);
            addModifier(section, MaterialsAA.modLensKiller);
            addModifier(section, MaterialsAA.modLensMiner);
        }
    }
    private static void addModifier(SectionData section, ModifierTrait mod)
    {
        ContentListing listing = (ContentListing)section.pages.get(0).content;
        PageData page = new PageData();
        page.source = new FileRepository(TinkersReforged.MODID+":book");
        page.parent = section;
        page.type = "modifier";
        page.data = "modifiers/" + mod.identifier + ".json";
        section.pages.add(page);
        page.load();
        listing.addEntry(mod.getLocalizedName(), page);
    }
}