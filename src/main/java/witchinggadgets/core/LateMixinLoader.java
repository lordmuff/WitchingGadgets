package witchinggadgets.core;

import com.gtnewhorizon.gtnhmixins.ILateMixinLoader;
import com.gtnewhorizon.gtnhmixins.LateMixin;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@LateMixin
public class LateMixinLoader implements ILateMixinLoader {

    @Override
    public String getMixinConfig() {
        return "mixins.WitchingGadgets.late.json";
    }

    @Override
    public List<String> getMixins(Set<String> loadedMods) {
        final List<String> mixins = new ArrayList<>();
        mixins.add("thaumcraft.MixinItemBootsTraveller");
        mixins.add("thaumcraft.MixinItemFocusPouchBauble");
        mixins.add("thaumcraft.MixinWorldGenEldritchRing");
        mixins.add("thaumcraft.MixinWorldGenHilltopStones");
        return mixins;
    }

}
