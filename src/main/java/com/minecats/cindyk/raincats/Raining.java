package com.minecats.cindyk.raincats;

import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Ocelot;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

/**
 * Created by cindy on 7/12/14.
 */
public class Raining extends BukkitRunnable {

    private static final Random random = new Random();
    Player user;
    int adjust;


    Raining(Player user, int adjuster)
    {
        this.user = user;
        adjust = adjuster;
        this.run();
    }

    @Override
    public void run()
    {
        //final Mob cat = Mob.OCELOT;
        //final Ocelot ocelot = (Ocelot)cat.spawn(user.getWorld(), server, user.getEyeLocation());
        //
        //


            Location loc1 = user.getLocation();

           loc1.add(0+adjust,10+adjust,0-adjust);
           final Ocelot ocelot = (Ocelot) user.getWorld().spawnEntity(loc1, EntityType.OCELOT);

//            if (ocelot == null)
//            {
//                return;
//            }
            RainCats.plugin.getLogger().info("RainCats: cat id: " + ocelot.getUniqueId().toString());

            final int i = random.nextInt(Ocelot.Type.values().length);
            ocelot.setCatType(Ocelot.Type.values()[i]);
            ocelot.setTamed(true);
            ocelot.setBaby();

            //ocelot.setVelocity(loc1.getDirection());

            //ocelot.setVelocity(user.getEyeLocation().getDirection().multiply(2));

            class KittyCannonExplodeTask implements Runnable
            {
                @Override
                public void run()
                {
                  //  final Location loc = ocelot.getLocation();
                    ocelot.remove();
                   // loc.getWorld().createExplosion(loc, 0F);
                }
            }
            RainCats.plugin.getServer().getScheduler().scheduleSyncDelayedTask(RainCats.plugin, new KittyCannonExplodeTask(), 40+adjust);


    }
}
