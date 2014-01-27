package com.chaseoes.noitemframeswg;

import java.io.IOException;
import java.util.List;

import net.gravitydevelopment.updater.Updater;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.mcstats.Metrics;

public class NoItemFramesWG extends JavaPlugin {

    private static NoItemFramesWG instance;

    public static NoItemFramesWG getInstance() {
        return instance;
    }

    public void onEnable() {
        instance = this;
        getConfig().options().copyDefaults(true);
        saveConfig();
        getServer().getPluginManager().registerEvents(new ItemFrameListeners(), this);

        if (getConfig().getBoolean("allow-auto-updating")) {
            new Updater(this, 73310, this.getFile(), Updater.UpdateType.DEFAULT, false);
        }

        try {
            Metrics metrics = new Metrics(this);
            metrics.start();
        } catch (IOException e) {
            // Failed to submit the stats :-(
        }
    }

    public void onDisable() {
        instance = null;
    }

    public boolean onCommand(CommandSender cs, Command cmnd, String string, String[] strings) {
        if (strings.length == 0) {
            cs.sendMessage(ChatColor.YELLOW + getDescription().getName() + ChatColor.GRAY + " version " + getDescription().getVersion() + ChatColor.GRAY + " by " + ChatColor.YELLOW + getDescription().getAuthors().get(0) + ChatColor.GRAY + ".");
            return true;
        }

        if (!cs.hasPermission("noitemframeswg.admin")) {
            cs.sendMessage(ChatColor.RED + "You don't have permission.");
            return true;
        }

        if (strings[0].equalsIgnoreCase("add")) {
            if (strings.length == 2) {
                List<String> regions = getRegions();
                if (!regions.contains(strings[1])) {
                    regions.add(strings[1]);
                    getConfig().set("regions", regions);
                    saveConfig();
                    cs.sendMessage(ChatColor.RED + "Item frame placement is now blocked in the region " + strings[1] + ".");
                } else {
                    cs.sendMessage(ChatColor.RED + "Item frame placement is already blocked in that region!");
                }
            } else {
                cs.sendMessage(ChatColor.RED + "Usage: /" + cmnd.getName() + " add <region name>");
            }
        }

        if (strings[0].equalsIgnoreCase("remove")) {
            if (strings.length == 2) {
                List<String> regions = getRegions();
                if (regions.contains(strings[1])) {
                    regions.remove(strings[1]);
                    getConfig().set("regions", regions);
                    saveConfig();
                    cs.sendMessage(ChatColor.RED + "Item frame placement is no longer blocked in the region " + strings[1] + ".");
                } else {
                    cs.sendMessage(ChatColor.RED + "Item frame placement isn't blocked in that region!");
                }
            } else {
                cs.sendMessage(ChatColor.RED + "Usage: /" + cmnd.getName() + " add <region name>");
            }
        }

        if (strings[0].equalsIgnoreCase("reload")) {
            reloadConfig();
            saveConfig();
            cs.sendMessage(ChatColor.YELLOW + "Successfully reloaded configuration.");
        }
        return true;
    }

    public List<String> getRegions() {
        return getConfig().getStringList("regions");
    }

}
