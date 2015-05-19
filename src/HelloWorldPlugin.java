/**
 * Created by martin on 12.05.15.
 */

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

public class HelloWorldPlugin extends JavaPlugin {
    public static Logger log = Logger.getLogger("Minecraft");

    public void onLoad() {
        log.info("[HelloWorldPlugin] Loaded...");
    }

    public void onEnable() {
        log.info("[HelloWorldPlugin] Starting up...");
    }

    public void onDisable() {
        log.info("[HelloWorldPlugin] Stopping...");
    }


}
