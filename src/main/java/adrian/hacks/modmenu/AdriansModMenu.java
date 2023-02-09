package adrian.hacks.modmenu;

import adrian.hacks.AdriansMod.FabricModAdriansMod;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import adrian.hacks.gui.AdriansModsScreenBuilder;

public class AdriansModMenu implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> AdriansModsScreenBuilder.buildScreen(FabricModAdriansMod.getInstance(), parent);
    }
}
