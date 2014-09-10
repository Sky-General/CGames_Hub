package net.creativegames.hub.MainPlugin;

import net.creativegames.hub.Main;
import net.creativegames.hub.API.Colors;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GameModeCommand implements CommandExecutor {
	Main plugin;
	public GameModeCommand(Main plugin, String cmd){
		this.plugin = plugin;
		plugin.getCommand(cmd).setExecutor(this);
	}
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(cmd.getName().equalsIgnoreCase("gamemode")){
			if(!(sender instanceof Player)) return false;
			Player player = (Player) sender;
			if(player.hasPermission("cgames.gamemode")){
				if(args.length == 1){
					String mode = args[0];
					switch(mode){
					case "1": player.setGameMode(GameMode.CREATIVE);
					player.sendMessage(Colors.colorize("&e[&3CreativeGames&e] &3You are now in Creative mode."));
					break;
					case "creative": player.setGameMode(GameMode.CREATIVE);
					player.sendMessage(Colors.colorize("&e[&3CreativeGames&e] &3You are now in Creative mode."));
					break;
					case "0": player.setGameMode(GameMode.SURVIVAL);
					player.sendMessage(Colors.colorize("&e[&3CreativeGames&e] &3You are now in Survival mode."));
					break;
					case "survival": player.setGameMode(GameMode.SURVIVAL);
					player.sendMessage(Colors.colorize("&e[&3CreativeGames&e] &3You are now in Survival mode."));
					break;
					case "2": player.setGameMode(GameMode.ADVENTURE);
					player.sendMessage(Colors.colorize("&e[&3CreativeGames&e] &3You are now in Adventure mode."));
					break;
					case "adventure": player.setGameMode(GameMode.ADVENTURE);
					player.sendMessage(Colors.colorize("&e[&3CreativeGames&e] &3You are now in Adventure mode."));
					break;
					default: player.sendMessage(Colors.colorize("&e[&3CreativeGames&e] &cInvalid Gamemode."));
					}
					}
				if(args.length == 2){
					if(Bukkit.getPlayer(args[1]) == null){
						player.sendMessage(Colors.colorize("&e[&eCreativeGames&e] &cThat player is not online."));
						return false;
						}
					Player target = Bukkit.getPlayer(args[1]);
					String mode = args[0];
					switch(mode){
					case "1": target.setGameMode(GameMode.CREATIVE);
					target.sendMessage(Colors.colorize("&e[&3CreativeGames&e] &3You are now in Creative mode."));
					player.sendMessage(Colors.colorize("&e[&3CreativeGames&e]&3 " + target.getName() + " is now in Creative mode."));
					break;
					case "creative": target.setGameMode(GameMode.CREATIVE);
					target.sendMessage(Colors.colorize("&e[&3CreativeGames&e] &3You are now in Creative mode."));
					player.sendMessage(Colors.colorize("&e[&3CreativeGames&e]&3 " + target.getName() + " is now in Creative mode."));
					break;
					case "0": target.setGameMode(GameMode.SURVIVAL);
					target.sendMessage(Colors.colorize("&e[&3CreativeGames&e] &3You are now in Survival mode."));
					player.sendMessage(Colors.colorize("&e[&3CreativeGames&e]&3 " + target.getName() + " is now in Survival mode."));
					break;
					case "survival": target.setGameMode(GameMode.SURVIVAL);
					target.sendMessage(Colors.colorize("&e[&3CreativeGames&e] &3You are now in Survival mode."));
					player.sendMessage(Colors.colorize("&e[&3CreativeGames&e]&3 " + target.getName() + " is now in Survival mode."));
					break;
					case "2": target.setGameMode(GameMode.ADVENTURE);
					target.sendMessage(Colors.colorize("&e[&3CreativeGames&e] &3You are now in Adventure mode."));
					player.sendMessage(Colors.colorize("&e[&3CreativeGames&e]&3 " + target.getName() + " is now in Adventure mode."));
					break;
					case "adventure": target.setGameMode(GameMode.ADVENTURE);
					target.sendMessage(Colors.colorize("&e[&3CreativeGames&e] &3You are now in Adventure mode."));
					player.sendMessage(Colors.colorize("&e[&3CreativeGames&e]&3 " + target.getName() + " is now in Adventure mode."));
					break;
					default: player.sendMessage(Colors.colorize("&e[&3CreativeGames&e] &cInvalid Gamemode."));
					}
					}
				} else player.sendMessage(Colors.colorize("&e[&3CreativeGames&e] &cInvalid Permission."));
			}
		return false;
		}
	}
