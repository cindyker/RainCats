package com.minecats.cindyk.raincats;

import net.minecraft.server.v1_7_R4.MathHelper;
import org.bukkit.Effect;
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
public class Raining { //extends BukkitRunnable {

    private static final Random random = new Random();
    RainCats plugin;

    Raining(RainCats plugin)
    {
       this.plugin = plugin;
      //  this.run();
    }


    private final double a = 1, b = 0.2;

    public void rain(final Location l, final int iter, final int space) {

        new BukkitRunnable() {

            int i = 0;

            @Override
            public void run() {

                if (i >= iter) {
                    this.cancel();
                    return;
                }

                float x = i * space / 10;

                double dx = a * Math.exp(b * x) * MathHelper.cos(x);
                double dz = a * Math.exp(b * x) * MathHelper.sin(x);

                Location relative = l.clone().add(dx, 15, dz);

                final Ocelot ocelot = l.getWorld().spawn(relative, Ocelot.class);

               // RainCats.plugin.getLogger().info("cat id: " + ocelot.getUniqueId().toString());

                ocelot.setCatType(Ocelot.Type.values()[random.nextInt(Ocelot.Type.values().length)]);
                ocelot.setTamed(true);
                ocelot.setBaby();
                ocelot.setSitting(random.nextBoolean());

                new BukkitRunnable() {

                    @Override
                    public void run() {

                        Location l = ocelot.getLocation();
                        ocelot.remove();
                        l.getWorld().spigot().playEffect(l.add(0, 0.3, 0), Effect.COLOURED_DUST, 0, 0, 0.5F, 0.5F, 0.5F, 1, 32, 64);
                    }

                }.runTaskLater(plugin, (long) (100 + 100 * Math.abs(random.nextGaussian())));

                i++;
            }

        }.runTaskTimer(plugin, 0, 10L);
    }





//    @Override
//    public void run()
//    {
//        //final Mob cat = Mob.OCELOT;
//        //final Ocelot ocelot = (Ocelot)cat.spawn(user.getWorld(), server, user.getEyeLocation());
//        //
//        //
//
//
//            Location loc1 = user.getLocation();
//
//           loc1.add(0+adjust,10+adjust,0-adjust);
//           final Ocelot ocelot = (Ocelot) user.getWorld().spawnEntity(loc1, EntityType.OCELOT);
//
////            if (ocelot == null)
////            {
////                return;
////            }
//            RainCats.plugin.getLogger().info("RainCats: cat id: " + ocelot.getUniqueId().toString());
//
//            final int i = random.nextInt(Ocelot.Type.values().length);
//            ocelot.setCatType(Ocelot.Type.values()[i]);
//            ocelot.setTamed(true);
//            ocelot.setBaby();
//
//            //ocelot.setVelocity(loc1.getDirection());
//
//            //ocelot.setVelocity(user.getEyeLocation().getDirection().multiply(2));
//
//            class KittyCannonExplodeTask implements Runnable
//            {
//                @Override
//                public void run()
//                {
//                  //  final Location loc = ocelot.getLocation();
//                    ocelot.remove();
//                   // loc.getWorld().createExplosion(loc, 0F);
//                }
//            }
//            RainCats.plugin.getServer().getScheduler().scheduleSyncDelayedTask(RainCats.plugin, new KittyCannonExplodeTask(), 40+adjust);
//
//
//    }
}
