package com.schoperation.randomservericon;

import com.schoperation.randomservericon.file.IconLoader;
import com.schoperation.randomservericon.listener.ServerListPingListener;
import java.util.List;
import java.util.logging.Logger;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.CachedServerIcon;

public class RandomServerIcon extends JavaPlugin {

    private Logger logger;
    private IconLoader iconLoader;

    public void onLoad() {
        super.onLoad();
        logger = getLogger();
        iconLoader = new IconLoader(logger, getServer());
    }

    @Override
    public void onEnable() {
        logger.info("Loading icons from server-icons folder...");
        List<CachedServerIcon> icons = iconLoader.loadIcons();
        logger.info("Loaded " + icons.size() + " server icons.");

        getServer()
            .getPluginManager()
            .registerEvents(new ServerListPingListener(icons), this);
    }
}
