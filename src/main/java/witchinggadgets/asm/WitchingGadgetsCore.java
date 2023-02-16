package witchinggadgets.asm;

import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.ModMetadata;
import witchinggadgets.WitchingGadgets;

public class WitchingGadgetsCore extends DummyModContainer {

    public WitchingGadgetsCore() {
        super(new ModMetadata());
        ModMetadata metadata = getMetadata();
        metadata.modId = WitchingGadgets.MODID + "Core";
        metadata.name = WitchingGadgets.MODNAME + " Core";
        metadata.version = WitchingGadgets.VERSION;
        metadata.authorList.add("BluSunrize");
        metadata.parent = "WitchingGadgets";
    }
}
