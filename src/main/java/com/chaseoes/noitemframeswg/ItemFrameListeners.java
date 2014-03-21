package com.chaseoes.noitemframeswg;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.hanging.HangingPlaceEvent;

import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

public class ItemFrameListeners implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onHangingPlace(HangingPlaceEvent event) {
        if (!event.getPlayer().hasPermission("noitemframeswg.bypass")) {
            Location location = event.getBlock().getLocation();
            ApplicableRegionSet set = WGBukkit.getRegionManager(location.getWorld()).getApplicableRegions(location);
            for (ProtectedRegion region : set) {
                if (NoItemFramesWG.getInstance().getConfig().getStringList("regions").contains(region.getId())) {
                    event.setCancelled(true);
                    event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', NoItemFramesWG.getInstance().getConfig().getString("placement-denied-message")));
                    event.getPlayer().updateInventory();
                }
            }
        }
    }
}
