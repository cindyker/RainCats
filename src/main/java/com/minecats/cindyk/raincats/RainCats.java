package com.minecats.cindyk.raincats;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by cindy on 7/12/14.
 */

public class RainCats extends JavaPlugin implements CommandExecutor {


    static RainCats plugin;

    public RainCats() {
        super();
    }

    @Override
    public void onEnable() {
        super.onEnable();

        plugin = this;

     //   getServer().getPluginManager().registerEvents(new CatListener(),this);
        getCommand("raincats").setExecutor(this);

    }


    @Override
    public void onDisable() {
        super.onDisable();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {

        //if not a player... we are done.
        if (!(sender instanceof Player)) {

            if(args.length != 1 )
                return false;

            if(getServer().getPlayer(args[0])== null)
            {
                sender.sendMessage("You need to send a valid player name.");
                return true;
            }
            else
            {
                getLogger().info("Console issued command : Raining cats on " + args[0]);

                    Raining rr = new Raining(this);
                    rr.rain(getServer().getPlayer(args[0]).getLocation(),64,5);



            }
        }
        else
        {
            if(args.length == 1 )
            {
                if( args[0].equalsIgnoreCase("all"))
                {
                    Raining rr = new Raining(this);
                    for(Player p:getServer().getOnlinePlayers())
                    {
                        if(p.isOnline())
                            rr.rain(p.getLocation(),64,5);
                    }
                }

            }
            if(args.length == 0)
            {
                getLogger().info("Raining cats on " + sender.getName());

                Raining rr = new Raining(this);
                rr.rain(getServer().getPlayer(sender.getName()).getLocation(),64,5);
            }


        }



        return false;

    }

}
