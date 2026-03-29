package com.schoperation.randomservericon;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.bukkit.Server;
import org.bukkit.util.CachedServerIcon;

public class IconLoader {

    private Logger logger;
    private Server server;

    public IconLoader(Logger logger, Server server) {
        this.logger = logger;
        this.server = server;
    }

    public List<CachedServerIcon> loadIcons() {
        File iconDirectories = new File("server-icons");

        if (!iconDirectories.exists()) {
            boolean created = iconDirectories.mkdir();
            if (!created) {
                logger.severe(
                    "Could not find or create server-icons directory. Please ensure it exists at the root of your server, and restart."
                );
                return null;
            }
        }

        if (!iconDirectories.isDirectory()) {
            logger.severe(
                "server-icons already exists, but it's not a directory. Please remove/rename the existing file and restart the server."
            );
            return null;
        }

        return loadInDirectory(iconDirectories);
    }

    private List<CachedServerIcon> loadInDirectory(File directory) {
        List<CachedServerIcon> icons = new ArrayList<>();
        for (File entry : directory.listFiles()) {
            if (entry.isDirectory()) {
                icons.addAll(loadInDirectory(entry));
                continue;
            }

            if (!entry.canRead()) {
                logger.warning(
                    "Could not read " +
                        entry.getName() +
                        " for some reason; skipping..."
                );
                continue;
            }

            logger.info("File found: " + entry.getName());

            //icon = server.loadServerIcon(entry);
        }

        return icons;
    }
}
