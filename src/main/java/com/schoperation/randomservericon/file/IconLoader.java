package com.schoperation.randomservericon.file;

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
                logger.warning(
                    "Could not find or create server-icons directory. Please ensure it exists at the root of your server, and restart."
                );
                return null;
            }

            logger.info(
                "Created server-icons folder; place your server icons in here."
            );
        }

        if (!iconDirectories.isDirectory()) {
            logger.warning(
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

            if (!entry.getName().endsWith(".png")) {
                logger.warning(
                    "Skipping " + entry.getName() + " as it is not a png."
                );
                continue;
            }

            if (!entry.canRead()) {
                logger.warning(
                    "Skipping " +
                        entry.getName() +
                        " as it cannot be read. Check the file permissions."
                );
                continue;
            }

            try {
                CachedServerIcon icon = server.loadServerIcon(entry);
                icons.add(icon);
            } catch (IllegalArgumentException e) {
                logger.warning(
                    "Skipping " +
                        entry.getName() +
                        " as it appears to be null; ensure it is a 64x64 png file."
                );
            } catch (Exception e) {
                logger.warning(
                    "Skipping " +
                        entry.getName() +
                        " as it does not fit the server icon criteria; ensure it is a 64x64 png file."
                );
            }
        }

        return icons;
    }
}
