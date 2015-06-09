/**
 * Created by martin on 12.05.15.
 */

import java.util.HashSet;
import java.util.List;
import java.util.logging.Logger;
import java.util.Set;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.BlockIterator;
import org.bukkit.util.Vector;

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

        } else if (command.getName().equalsIgnoreCase("up")) {

            // Check if sender is a Player
            if(sender instanceof Player) {
                // Get a list of nearby entities
                Player me = (Player) sender;
                List<Entity> list = me.getNearbyEntities(20.0, 20.0, 20.0);

                // Iterate over each item in this list
                for(Entity target : list) {

                    // Check if its a player
                    if(target instanceof Player) {
                        // Create a new vector pointing upwards
                        Vector v = new Vector(0,2,0);
                        // and set the targets velocity to this vector
                        target.setVelocity(v);
                    }
                }

            }


        } else if (command.getName().equalsIgnoreCase("flyingcreeper")) {

            // Check if its a player
            if (sender instanceof Player ) {

                // get the player and her location
                Player player = (Player) sender;
                Location loc = player.getLocation();

                // add 5 to the Y of the location
                // use getY() and setY() ... setY(getY() + 5)

                loc.setY( loc.getY() + 5 );

                // Spawn a Bat at that location
                // player.getWorld().spawn(...)
                Bat bat = player.getWorld().spawn(loc, Bat.class);

                // Spawn a creeper at the same location
                Creeper creeper = player.getWorld().spawn(loc, Creeper.class);

                // set the creeper as passenger of the bat
                bat.setPassenger(creeper);

                // add an invisibility potion effect on the bat

                PotionEffect potion = new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 1);
                bat.addPotionEffect(potion);

                potion = new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 1);
                bat.addPotionEffect(potion);

            }

        }else if (command.getName().equalsIgnoreCase("stuck")) {

            // Check if there is exactly one argument

                // Get the player from its name (argument)

                // If the player exists (and is online) call a new function stuck()
            
        }
        return true;
    }

    public boolean stuck(Player player) {

        // Get the players world

        // Get the players location

        // Modify the location in X and Z by adding 0.5


        // Define my stucture by defining x,y,z offsets

        int[][] offsets = {
        //       x  y   z
                {0, -1, 0},
                {0, 2, 0},
                {1, 0, 0},
                {1, 1, 0},
                {-1, 0, 0},
                {-1, 1, 0},
                {0, 0, 1},
                {0, 1, 1},
                {0, 0, -1},
                {0, 1, -1},
        };

        // Loop through the offsets array

            // Get x,y,z from the inner array

            // Get block at player position + offset

            // Set type of Block to STONE

    }



    @EventHandler
    public void onPlayerInteractBlock(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        if(p.getItemInHand().getType() == Material.SEEDS) {

            Block b = p.getTargetBlock((Set<Material>) null, 200);
            Location loc = b.getLocation();
            p.getWorld().strikeLightning(loc);

        } else if (p.getItemInHand().getType() == Material.DIRT) {


            // Create a BlockIterator
            BlockIterator blocks = new BlockIterator(p, 100);


            // Iterate over all blocks in sight (using iterators hasNext()
            // and next() methods
            while (blocks.hasNext()) {

                // Get the next block
                Block b = blocks.next();

                // play an effect on current block MOBSPAWNER.FLAMES
                // use the playEffect(...) method on player
                p.playEffect(b.getLocation(), Effect.MOBSPAWNER_FLAMES, null);


                // if the block is not AIR then set to LAVA
                if(b.getType() != Material.AIR) {
                    b.setType(Material.SAND);
                    p.playSound(b.getLocation(), Sound.EXPLODE, 1.0f, 5.0f);
                    break;
                }

            }

        }

    }



}














