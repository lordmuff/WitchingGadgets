package witchinggadgets.core;

import com.gtnewhorizon.gtnhmixins.IEarlyMixinLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class EarlyMixinLoader implements IEarlyMixinLoader {

    @Override
    public String getMixinConfig() {
        return "mixins.WitchingGadgets.early.json";
    }

    @Override
    public List<String> getMixins(Set<String> loadedCoreMods) {
        return new ArrayList<>();
    }

}
