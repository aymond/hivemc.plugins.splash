package com.splash;

import org.bukkit.plugin.java.JavaPlugin;
import com.splash.listeners.PlayerLandListener;
import com.splash.config.ConfigManager;

public class SplashPlugin extends JavaPlugin {
    
    private ConfigManager configManager;
    private PlayerLandListener playerLandListener;
    
    @Override
    public void onEnable() {
        try {
            // Initialize configuration manager
            this.configManager = new ConfigManager(this);
            configManager.loadConfig();
            
            // Register event listener
            this.playerLandListener = new PlayerLandListener(configManager, this);
            getServer().getPluginManager().registerEvents(playerLandListener, this);
            
            getLogger().info("Splash plugin has been enabled!");
            getLogger().info("Splash radius: " + configManager.getSplashRadius());
            getLogger().info("Splash material: " + configManager.getSplashMaterial());
            getLogger().info("Permanent: " + configManager.isPermanent());
            if (configManager.isAreaRestrictionEnabled()) {
                getLogger().info("Area restriction: ENABLED");
            } else {
                getLogger().info("Area restriction: DISABLED (splash works everywhere)");
            }
        } catch (Exception e) {
            getLogger().severe("Failed to enable Splash plugin: " + e.getMessage());
            e.printStackTrace();
            getServer().getPluginManager().disablePlugin(this);
        }
    }
    
    @Override
    public void onDisable() {
        getLogger().info("Splash plugin has been disabled!");
    }
    
    public ConfigManager getConfigManager() {
        return configManager;
    }
}

