package com.github.neomaster.rhythmcraft.listener;

import com.github.neomaster.rhythmcraft.main.Startup;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

import java.util.*;

public class RcTabComplete implements TabCompleter {

    Startup plugin;
    public RcTabComplete(Startup plugin) {
        this.plugin = plugin;
        plugin.getCommand("rc").setTabCompleter(this);
    }

    private static final List<String> COMMANDS = Arrays.asList("shop", "host", "play", "getpack");

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return (args.length > 0) ? StringUtil.copyPartialMatches(args[0], COMMANDS, new ArrayList<>()) : null;
    }
}
