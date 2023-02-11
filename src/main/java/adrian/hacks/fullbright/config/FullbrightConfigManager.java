package adrian.hacks.fullbright.config;

import adrian.hacks.AdriansMod.FabricModAdriansMod;
import adrian.hacks.AdriansMod.config.Config;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.charset.Charset;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class FullbrightConfigManager {

    private FullbrightConfig fullbrightConfig;

    private FabricModAdriansMod modHacks;
    private Gson gson;
    private File FullbrightConfigFile;

    private Executor executor = Executors.newSingleThreadExecutor();

    public FullbrightConfigManager(FabricModAdriansMod modHacks) {
        this.modHacks = modHacks;
        this.gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        this.FullbrightConfigFile = new File(FabricLoader.getInstance().getConfigDir().toFile(), "adrian.hacks.fullbright.FullbrightConfig");
        //run synchronously on first run so our options are available for the Autofish instance
        readFullbrightConfig(false);
    }

    public void readFullbrightConfig(boolean async) {

        Runnable task = () -> {
            try {
                //read if exists
                if (FullbrightConfigFile.exists()) {
                    String fileContents = FileUtils.readFileToString(FullbrightConfigFile, Charset.defaultCharset());
                    fullbrightConfig = gson.fromJson(fileContents, FullbrightConfig.class);

                } else { //write new if no config file exists
                    writeNewConfig();
                }

            } catch (Exception e) {
                e.printStackTrace();
                writeNewConfig();
            }
        };

        if (async) executor.execute(task);
        else task.run();
    }

    public void writeNewConfig() {
        fullbrightConfig = new FullbrightConfig();
        writeConfig(false);
    }

    public void writeConfig(boolean async) {
        Runnable task = () -> {
            try {
                if (fullbrightConfig != null) {
                    String serialized = gson.toJson(fullbrightConfig);
                    FileUtils.writeStringToFile(FullbrightConfigFile, serialized, Charset.defaultCharset());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        if (async) executor.execute(task);
        else task.run();
    }

    public FullbrightConfig getFullbrightConfig() {
        return fullbrightConfig;
    }
}

