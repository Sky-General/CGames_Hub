package net.creativegames.hub;

import java.util.ArrayList;

import net.creativegames.hub.DoubleJump.DoubleJumpListener;
import net.creativegames.hub.Item.ItemListener;
import net.creativegames.hub.MainPlugin.GameModeCommand;
import net.creativegames.hub.MainPlugin.MainListener;
import net.creativegames.hub.MainPlugin.ReloadCommand;
import net.creativegames.hub.MainPlugin.SpawnCommand;
import net.creativegames.hub.PaintBall.PBallListener;
import net.creativegames.hub.ScoreBoard.SBoardListener;
import net.creativegames.hub.ScoreBoard.SBoardUpdate;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class Main extends JavaPlugin {
  public ScoreboardManager manager;
  public Scoreboard board;
  public Objective score;
  public ArrayList<String> list;

  public void onEnable()
  {
    saveDefaultConfig();
    this.list = ((ArrayList<String>)getConfig().getStringList("Messages"));
    this.manager = Bukkit.getScoreboardManager();
    this.board = this.manager.getNewScoreboard();
    this.score = this.board.registerNewObjective("dummy", "dummy");
    new SBoardListener(this);
    new ItemListener(this);
    new DoubleJumpListener(this);
    new PBallListener(this);
    new ReloadCommand(this, "preload");
    new SpawnCommand(this, "spawn");
    new MainListener(this);
    new GameModeCommand(this, "gamemode");
    for (Player player : Bukkit.getOnlinePlayers())
		Bukkit.getScheduler().scheduleSyncDelayedTask(this, new SBoardUpdate(player, this));
  }
}