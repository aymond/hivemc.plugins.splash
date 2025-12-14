package com.splash.config;

import org.bukkit.Location;
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
    
    /**
     * Check if area restriction is enabled
     * @return true if both corner coordinates are set, false otherwise
     */
    public boolean isAreaRestrictionEnabled() {
        Integer x1 = getCorner1X();
        Integer y1 = getCorner1Y();
        Integer z1 = getCorner1Z();
        Integer x2 = getCorner2X();
        Integer y2 = getCorner2Y();
        Integer z2 = getCorner2Z();
        
        return x1 != null && y1 != null && z1 != null && 
               x2 != null && y2 != null && z2 != null;
    }
    
    /**
     * Check if a location is within the configured area
     * @param location The location to check
     * @return true if location is within the area (or area restriction is disabled), false otherwise
     */
    public boolean isLocationInArea(Location location) {
        if (!isAreaRestrictionEnabled()) {
            return true; // No restriction, allow everywhere
        }
        
        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();
        
        int x1 = getCorner1X();
        int y1 = getCorner1Y();
        int z1 = getCorner1Z();
        int x2 = getCorner2X();
        int y2 = getCorner2Y();
        int z2 = getCorner2Z();
        
        // Find min/max for each axis
        int minX = Math.min(x1, x2);
        int maxX = Math.max(x1, x2);
        int minY = Math.min(y1, y2);
        int maxY = Math.max(y1, y2);
        int minZ = Math.min(z1, z2);
        int maxZ = Math.max(z1, z2);
        
        // Check if location is within the bounding box
        return x >= minX && x <= maxX &&
               y >= minY && y <= maxY &&
               z >= minZ && z <= maxZ;
    }
    
    private Integer getCorner1X() {
        String path = "splash.area.corner1.x";
        return config.isSet(path) && !config.getString(path, "").equalsIgnoreCase("null") 
            ? config.getInt(path) : null;
    }
    
    private Integer getCorner1Y() {
        String path = "splash.area.corner1.y";
        return config.isSet(path) && !config.getString(path, "").equalsIgnoreCase("null") 
            ? config.getInt(path) : null;
    }
    
    private Integer getCorner1Z() {
        String path = "splash.area.corner1.z";
        return config.isSet(path) && !config.getString(path, "").equalsIgnoreCase("null") 
            ? config.getInt(path) : null;
    }
    
    private Integer getCorner2X() {
        String path = "splash.area.corner2.x";
        return config.isSet(path) && !config.getString(path, "").equalsIgnoreCase("null") 
            ? config.getInt(path) : null;
    }
    
    private Integer getCorner2Y() {
        String path = "splash.area.corner2.y";
        return config.isSet(path) && !config.getString(path, "").equalsIgnoreCase("null") 
            ? config.getInt(path) : null;
    }
    
    private Integer getCorner2Z() {
        String path = "splash.area.corner2.z";
        return config.isSet(path) && !config.getString(path, "").equalsIgnoreCase("null") 
            ? config.getInt(path) : null;
    }
    
    public void reload() {
        loadConfig();
    }
}

