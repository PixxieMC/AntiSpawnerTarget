package net.pixxy.antispawnertarget;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;

public final class AntiSpawnerTarget extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent e) {
        if (e.getSpawnReason().equals(CreatureSpawnEvent.SpawnReason.SPAWNER) ||
            e.getSpawnReason().equals(CreatureSpawnEvent.SpawnReason.CUSTOM)) {
            e.getEntity().setMetadata("spawner-spawn",
                new FixedMetadataValue(this, true));
            e.getEntity().setSilent(true);
        }
    }

    @EventHandler
    public void onEntityTarget(EntityTargetLivingEntityEvent e) {
        if (e.getEntity().hasMetadata("spawner-spawn") &&
            e.getTarget() instanceof Player) {
            e.setCancelled(true);
        }
    }
}
