package net.creativegames.hub.MainPlugin;

import java.util.HashMap;
import java.util.Map;

import net.creativegames.hub.Main;
import net.creativegames.hub.API.Colors;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class MainListener implements Listener {
	Main plugin;
	Map<String, Boolean> hidesPlayers = new HashMap<String, Boolean>();
	public static Map<String, Boolean> inGames = new HashMap<String, Boolean>();
	public MainListener(Main plugin){
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	@EventHandler
	public void onFoodLevelChange(FoodLevelChangeEvent e){
		e.setFoodLevel(20);
	}
	@EventHandler
	public void onPlayerClickInv(InventoryClickEvent e){
		if(e.getWhoClicked().hasPermission("cgames.invinteract")) return;
		e.setCancelled(true);
	}
	@EventHandler
	public void onPlayerDropItem(PlayerDropItemEvent e){
		if(e.getPlayer().hasPermission("cgames.dropitem")) return;
		e.setCancelled(true);
	}
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e){
		Player player = e.getPlayer();
		if(e.getPlayer().getItemInHand().getType().equals(Material.WOOL)){
			short data = e.getPlayer().getItemInHand().getDurability();
			switch(data){
			case 5: inGames.put(player.getName(), false);
			player.getItemInHand().setDurability((short) 14);
			player.sendMessage(Colors.colorize("&e[&3CreativeGames&e] &3You have left the Hub Games."));
			ItemMeta m = player.getItemInHand().getItemMeta();
			m.setDisplayName(Colors.colorize("&7[&eHubGames &cOff&7]"));
			player.getItemInHand().setItemMeta(m);
			break;
			case 14: inGames.put(player.getName(), true);
			player.getItemInHand().setDurability((short) 5);
			player.sendMessage(Colors.colorize("&e[&3CreativeGames&e] &3You have joined the Hub Games."));
			ItemMeta m1 = player.getItemInHand().getItemMeta();
			m1.setDisplayName(Colors.colorize("&7[&eHubGames &aOn&7]"));
			player.getItemInHand().setItemMeta(m1);
			break;
			default: inGames.put(player.getName(), true);
			player.getItemInHand().setDurability((short) 5);
			player.sendMessage(Colors.colorize("&e[&3CreativeGames&e] &3You have joined the Hub Games."));
			ItemMeta m11 = player.getItemInHand().getItemMeta();
			m11.setDisplayName(Colors.colorize("&7[&eHubGames &aOn&7]"));
			player.getItemInHand().setItemMeta(m11);
			}
		}
		if(e.getPlayer().getItemInHand().getType().equals(Material.EYE_OF_ENDER)){
			if(hidesPlayers.containsKey(e.getPlayer().getName())) {
				if(hidesPlayers.get(e.getPlayer().getName()) == true){
					for(Player player1 : Bukkit.getOnlinePlayers()){
						e.getPlayer().showPlayer(player1);
					}
					hidesPlayers.put(e.getPlayer().getName(), false);
					e.getPlayer().sendMessage(Colors.colorize("&e[&3CreativeGames&e] &3Poof. All the players reappeared!"));
					if(!e.getPlayer().hasPermission("cgames.build")) e.setCancelled(true);
					return;
				}
				if(hidesPlayers.get(e.getPlayer().getName()).equals(false)){
					for(Player player1 : Bukkit.getOnlinePlayers()){
						e.getPlayer().hidePlayer(player1);
					}
					hidesPlayers.put(e.getPlayer().getName(), true);
					e.getPlayer().sendMessage(Colors.colorize("&e[&3CreativeGames&e] &3Poof. All the players disappeared!"));
				}
			}
		}
		if(!e.getPlayer().hasPermission("cgames.build")) e.setCancelled(true);
	}
	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent e){
		e.setQuitMessage(null);
	}
	@EventHandler
	public void onPlayerInteract(PlayerInteractEntityEvent e){
		if(e.getRightClicked() instanceof Player){
			Player player = e.getPlayer();
			Player target = (Player) e.getRightClicked();
			if(target.equals(player.getPassenger())){
				return;
			}
			player.setPassenger(target);
			target.sendMessage(Colors.colorize("&e[&3CreativeGames&e] &3You are riding " + player.getName() + "."));
			player.sendMessage(Colors.colorize("&e[&3CreativeGames&e]&3 " + target.getName() + " is riding you."));
		}
	}
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e){
		e.getPlayer().setMaxHealth(2.0);
		e.getPlayer().setHealth(2.0);
		e.setJoinMessage(null);
		e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&3CreativeGames&e]&3 Welcome to the network!"));
		e.getPlayer().teleport(e.getPlayer().getWorld().getSpawnLocation());
		Player player = e.getPlayer();
		ItemStack pgun = new ItemStack(Material.IRON_BARDING);
		ItemMeta pgm = pgun.getItemMeta();
		pgm.setDisplayName(Colors.colorize("&7[&ePaintball Gun&7]"));
		pgun.setItemMeta(pgm);
		ItemStack eoe = new ItemStack(Material.EYE_OF_ENDER);
		eoe.addUnsafeEnchantment(Enchantment.LUCK, 1);
		ItemMeta em = eoe.getItemMeta();
		em.setDisplayName(Colors.colorize("&7[&eHide Players&7]"));
		eoe.setItemMeta(em);
		ItemStack wool = new ItemStack(Material.WOOL);
		ItemMeta wm = wool.getItemMeta();
		wm.setDisplayName(Colors.colorize("&7[&eHubGames &aOn&7]"));
		wool.setItemMeta(wm);
		wool.setDurability((short) 5);
		player.getInventory().setItem(4, eoe);
		player.getInventory().setItem(0, pgun);
		player.getInventory().setItem(8, wool);
		inGames.put(player.getName(), true);
		for(Player p : Bukkit.getOnlinePlayers()){
			if(hidesPlayers.containsKey(player.getName())){
				if(hidesPlayers.get(player) == true){
					player.showPlayer(p);
					if(hidesPlayers.get(p.getName()) == true){
						p.hidePlayer(player);
					}
				}
				return;
			}
			hidesPlayers.put(player.getName(), false);
		}
		
	}
}
