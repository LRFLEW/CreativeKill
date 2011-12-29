package com.LRFLEW.bukkit.ck;

import net.minecraft.server.DamageSource;
import org.bukkit.GameMode;
import org.bukkit.craftbukkit.entity.CraftLivingEntity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityListener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class CreativeKill extends JavaPlugin {
	
	public void onDisable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		System.out.println( pdfFile.getName() + " says Goodbye!" );
	}
	
	public void onEnable() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Event.Type.ENTITY_DAMAGE, new EntityEvents(), Priority.Monitor, this);
		PluginDescriptionFile pdfFile = this.getDescription();
		System.out.println( pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!" );
	}
	
	private class EntityEvents extends EntityListener {
		public void onEntityDamage(EntityDamageEvent e) {
			if (e.isCancelled()) return;
			if (!(e instanceof EntityDamageByEntityEvent)) return;
			EntityDamageByEntityEvent event = (EntityDamageByEntityEvent)e;
			if (!(event.getDamager() instanceof Player)) return;
			Player player = (Player)event.getDamager();
			if (!player.getGameMode().equals(GameMode.CREATIVE)) return;
			if (!player.hasPermission("creativekill")) return;
			if (!(event.getEntity() instanceof LivingEntity)) return;
			LivingEntity le = (LivingEntity)event.getEntity();
			if (le instanceof Player) return;
			le.setHealth(0);
			((CraftLivingEntity)le).getHandle().die(DamageSource.OUT_OF_WORLD);
		}
	}
}