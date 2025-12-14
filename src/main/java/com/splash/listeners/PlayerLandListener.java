package com.splash.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import com.splash.config.ConfigManager;
import com.splash.SplashPlugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;

public class PlayerLandListener implements Listener {
    
    private final ConfigManager configManager;
    private final Set<Player> inAir = new HashSet<>();
    
    public PlayerLandListener(ConfigManager configManager) {
        this.configManager = configManager;
    }
    
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location from = event.getFrom();
        Location to = event.getTo();
        
        if (to == null) return;
        
        // Check if player is on ground
        Block blockBelow = to.getBlock().getRelative(0, -1, 0);
        boolean wasOnGround = from.getBlock().getRelative(0, -1, 0).getType().isSolid();
        boolean isOnGround = blockBelow.getType().isSolid();
        
        // Track if player was in air
        if (!wasOnGround && !isOnGround) {
            inAir.add(player);
        }
        
        // Detect landing (was in air, now on ground)
        if (inAir.contains(player) && isOnGround) {
            inAir.remove(player);
            // Check if location is within configured area (if area restriction is enabled)
            if (configManager.isLocationInArea(to)) {
                createSplashEffect(to, player);
            }
        }
    }
    
    private void createSplashEffect(Location location, Player player) {
        int radius = configManager.getSplashRadius();
        boolean permanent = configManager.isPermanent();
        Material splashMaterial = configManager.getSplashMaterial();
        
        Location center = location.clone();
        center.setY(center.getY() - 1); // Block below player
        
        Set<Block> affectedBlocks = new HashSet<>();
        
        // Create splash pattern (similar to TNT explosion pattern)
        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                double distance = Math.sqrt(x * x + z * z);
                
                if (distance <= radius) {
                    // Create a more natural splash pattern with height variation
                    double heightFactor = 1.0 - (distance / radius);
                    int height = (int) Math.ceil(heightFactor * 2);
                    
                    for (int y = 0; y <= height; y++) {
                        Block block = center.clone().add(x, y, z).getBlock();
                        
                        // Only place blocks in air or replace certain blocks
                        if (block.getType().isAir() || 
                            block.getType() == Material.WATER || 
                            block.getType() == Material.LAVA) {
                            
                            block.setType(splashMaterial);
                            affectedBlocks.add(block);
                        }
                    }
                }
            }
        }
        
        // If not permanent, schedule removal
        if (!permanent) {
            int duration = configManager.getSplashDuration();
            JavaPlugin plugin = JavaPlugin.getPlugin(SplashPlugin.class);
            
            // Use Paper's modern scheduler API
            plugin.getServer().getScheduler().runTaskLater(plugin, () -> {
                for (Block block : affectedBlocks) {
                    if (block.getType() == splashMaterial) {
                        block.setType(Material.AIR);
                    }
                }
            }, duration * 20L); // Convert seconds to ticks
        }
    }
}

