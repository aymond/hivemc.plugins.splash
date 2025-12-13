package com.splash.config;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class ConfigManager {
    
    private final JavaPlugin plugin;
    private FileConfiguration config;
    
    public ConfigManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }
    
    public void loadConfig() {
        plugin.saveDefaultConfig();
        plugin.reloadConfig();
        this.config = plugin.getConfig();
    }
    
    public int getSplashRadius() {
        return config.getInt("splash.radius", 3);
    }
    
    public boolean isPermanent() {
        return config.getBoolean("splash.permanent", false);
    }
    
    public int getSplashDuration() {
        return config.getInt("splash.duration", 5);
    }
    
    public Material getSplashMaterial() {
        String materialName = config.getString("splash.material", "WATER");
        try {
            return Material.valueOf(materialName.toUpperCase());
        } catch (IllegalArgumentException e) {
            plugin.getLogger().warning("Invalid material: " + materialName + ". Using WATER as default.");
            return Material.WATER;
        }
    }
    
    public void reload() {
        loadConfig();
    }
}

