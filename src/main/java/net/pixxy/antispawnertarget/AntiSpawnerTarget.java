package net.pixxy.antispawnertarget;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.SpawnerSpawnEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;

public final class AntiSpawnerTarget extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onEntitySpawn(SpawnerSpawnEvent e) {
        e.getEntity().setSilent(true);
        e.getEntity().setMetadata("spawner-spawn",
            new FixedMetadataValue(this, "true"));
    }

    @EventHandler
    public void onEntityTarget(EntityTargetEvent e) {
        if (e.getEntity().hasMetadata("spawner-spawn") &&
            e.getTarget() instanceof Player) {
            e.setCancelled(true);
        }
    }
}
