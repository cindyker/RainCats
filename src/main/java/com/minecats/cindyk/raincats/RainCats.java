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

        getServer().getPluginManager().registerEvents(new CatListener(),this);
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
                for(int x=0;x<30;x++)
                {   int adjust = x%4;
                    Raining rr = new Raining(getServer().getPlayer(args[0]),adjust);
                }

            }
        }
        else
        {
            getLogger().info("Raining cats on " + sender.getName());
            for(int x=0;x<30;x++)
            {
                int adjust = x%4;
                Raining rr = new Raining(getServer().getPlayer(sender.getName()),adjust);
            }

        }



        return false;

    }

}
