package com.minecats.cindyk.raincats;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Ocelot;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

/**
 * Created by cindy on 7/12/14.
 */

public class CatListener implements Listener {

    @EventHandler
    void onOcelotSpawn(EntitySpawnEvent event)
    {

        if(event.getEntity().getType() == EntityType.OCELOT)
        {
            Ocelot oc = (Ocelot) event.getEntity();
            RainCats.plugin.getLogger().info("ESE: " + oc.getUniqueId().toString() + " at " + oc.getLocation().toString());
            //Nevermind...

        }

    }
}
