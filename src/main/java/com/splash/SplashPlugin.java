package com.splash;

import org.bukkit.plugin.java.JavaPlugin;
import com.splash.listeners.PlayerLandListener;
import com.splash.config.ConfigManager;

public class SplashPlugin extends JavaPlugin {
    
    private ConfigManager configManager;
    private PlayerLandListener playerLandListener;
    
    @Override
    public void onEnable() {
        // Initialize configuration manager
        this.configManager = new ConfigManager(this);
        configManager.loadConfig();
        
        // Register event listener
        this.playerLandListener = new PlayerLandListener(configManager);
        getServer().getPluginManager().registerEvents(playerLandListener, this);
        
        getLogger().info("Splash plugin has been enabled!");
    }
    
    @Override
    public void onDisable() {
        getLogger().info("Splash plugin has been disabled!");
    }
    
    public ConfigManager getConfigManager() {
        return configManager;
    }
}

