package com.schoperation.randomservericon.listener;

import java.util.List;
import java.util.Random;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.util.CachedServerIcon;

public class ServerListPingListener implements Listener {

    private List<CachedServerIcon> icons;
    private Random rng;

    public ServerListPingListener(List<CachedServerIcon> icons) {
        this.icons = icons;
        rng = new Random();
    }

    @EventHandler
    public void onServerListPing(ServerListPingEvent event) {
        int index = rng.nextInt(0, icons.size());

        try {
            event.setServerIcon(icons.get(index));
        } catch (IllegalArgumentException e) {
            return;
        } catch (UnsupportedOperationException e) {
            return;
        }
    }
}
