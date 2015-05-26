/**
 * Created by martin on 12.05.15.
 */

import java.util.HashSet;
import java.util.logging.Logger;
import java.util.Set;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class HelloWorldPlugin extends JavaPlugin implements Listener {
    public static Logger log = Logger.getLogger("Minecraft");

    public void onLoad() {
        log.info("[HelloWorldPlugin] Loaded...");
    }

    public void onEnable() {
        getServer().getPluginManager().registerEvents(this,this);
        log.info("[HelloWorldPlugin] Starting up...");
    }

    public void onDisable() {
        log.info("[HelloWorldPlugin] Stopping...");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("hello")) {

            if(sender instanceof Player) {
                String message = "[Server] Hey, here is the Hello World Plugin, dude!";
                getServer().broadcastMessage(message);

                return true;
            } else {
                sender.sendMessage("Du Hirsch, der Command geht nur als Player");
                return true;
            }

        } else if (command.getName().equalsIgnoreCase("killplayer")) { // Check if the command is "killplayer"
            // Get the player in a variable (from args[0])

            Player opfer = sender.getServer().getPlayer(args[0]);

            if(args.length != 1) {
                sender.sendMessage("Please specify a player to kill.");
                return true;
            }

            // Make sure the player is online (is it null?)

            if(opfer == null) {
                sender.sendMessage("Player "+args[0]+" is not online!");
                return true;
            }

            // if player is online -> setHealth(0.0D)

            opfer.getWorld().createExplosion(opfer.getLocation(), 4.0f );
            opfer.setHealth(0.0);

        }

        return true;


    }

    @EventHandler
    public void onPlayerInteractBlock(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        //if(p.getItemInHand().getType() == Material.SEEDS) {

            Block b = p.getTargetBlock((Set<Material>) null, 200);
            Location loc = b.getLocation();
            p.getWorld().strikeLightning(loc);

        //}

    }



}














