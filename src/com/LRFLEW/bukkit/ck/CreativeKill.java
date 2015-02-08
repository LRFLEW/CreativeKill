package com.LRFLEW.bukkit.ck;

import org.bukkit.GameMode;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class CreativeKill extends JavaPlugin implements Listener {
	
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
	}
	
	@EventHandler (priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void entityDamageEntity(EntityDamageByEntityEvent e) {
		if (!(e.getEntity() instanceof LivingEntity)) return;
		if (!(e.getDamager() instanceof Player)) return;
		Player player = (Player)e.getDamager();
		if (!player.getGameMode().equals(GameMode.CREATIVE)) return;
		if (!player.hasPermission("creativekill")) return;
		if (e.getEntity() instanceof Player) return;
		((LivingEntity)e.getEntity()).setHealth(0);
	}
}
