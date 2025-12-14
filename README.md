# Splash Plugin

A Minecraft PaperMC plugin that creates a block splash effect when players land on the ground, similar to TNT explosions but without destruction and knockback effects.

## Features

- **Block Splash Effect**: Creates a visual splash of blocks when players land on the ground
- **Configurable Radius**: Adjust the size of the splash effect
- **Permanent or Temporary**: Choose whether splash blocks stay forever or disappear after a set duration
- **Customizable Material**: Configure which block type to use for the splash effect

## Configuration

Edit `config.yml` in your server's `plugins/Splash/` directory:

```yaml
splash:
  radius: 3          # Radius of splash effect in blocks
  permanent: false    # true = blocks stay, false = blocks disappear
  duration: 5         # Seconds before blocks disappear (if permanent is false)
  material: WATER     # Block material for splash effect
```

### Configuration Options

- **radius**: The radius of the splash effect in blocks (default: 3)
- **permanent**: Whether splash blocks are permanent (default: false)
- **duration**: How long blocks last before disappearing in seconds (default: 5, only used if permanent is false)
- **material**: The block material to use (default: WATER). See [Material enum](https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/Material.html) for valid options.

## Building

This plugin uses Maven. To build:

```bash
mvn clean package
```

The compiled JAR will be in the `target/` directory.

## Installation

1. Build the plugin using Maven
2. Copy the JAR file from `target/` to your server's `plugins/` directory
3. Restart your server or use `/reload` (not recommended)
4. Configure the plugin by editing `plugins/Splash/config.yml`
5. Use `/reload` or restart to apply configuration changes

## Requirements

- PaperMC 1.21.4 or higher (compatible with 1.21.8 to 1.21.11)
- Java 21 or higher

## Testing the Plugin

### Quick Test Setup

1. **Download PaperMC Server**:
   - Visit [PaperMC Downloads](https://papermc.io/downloads/paper)
   - Download PaperMC 1.21.4 or higher (compatible with 1.21.8 to 1.21.11)
   - Create a test server directory

2. **Install the Plugin**:
   ```bash
   # Copy the built JAR to your test server
   cp target/Splash-0.0.2.jar /path/to/test-server/plugins/
   ```

3. **Start the Server**:
   ```bash
   cd /path/to/test-server
   java -jar paper-1.21.4.jar
   ```
   - Accept the EULA by editing `eula.txt` and setting `eula=true`
   - Restart the server

4. **Verify Plugin Loaded**:
   - Check the server console for: `[Splash] Splash plugin has been enabled!`
   - Or use the command: `/plugins` to see if Splash is listed

### Testing the Splash Effect

1. **Basic Test**:
   - Join the server
   - Jump and fall from a height (at least 2-3 blocks)
   - Land on solid ground
   - You should see a splash of blocks appear around your landing point

2. **Test Different Configurations**:
   
   **Test Temporary Splash** (default):
   - Edit `plugins/Splash/config.yml`:
     ```yaml
     splash:
       radius: 3
       permanent: false
       duration: 5
       material: WATER
     ```
   - Reload: `/reload` or restart server
   - Jump and land - blocks should disappear after 5 seconds
   
   **Test Permanent Splash**:
   - Edit config:
     ```yaml
     splash:
       permanent: true
     ```
   - Reload and test - blocks should stay forever
   
   **Test Different Radius**:
   - Try `radius: 5` for larger splashes
   - Try `radius: 1` for smaller splashes
   
   **Test Different Materials**:
   - Try `material: LAVA` for lava splash
   - Try `material: GLASS` for glass splash
   - Try `material: CONCRETE` for concrete splash

3. **Edge Cases to Test**:
   - Landing in water (should still create splash)
   - Landing on different block types
   - Multiple players landing simultaneously
   - Landing from very high heights
   - Landing while moving horizontally

### Troubleshooting

- **Plugin doesn't load**: Check server logs for errors
- **No splash effect**: 
  - Verify you're falling from a height (not just walking)
  - Check that the block below you is solid
  - Check server console for any errors
- **Blocks don't disappear**: 
  - Verify `permanent: false` in config
  - Check `duration` value is reasonable
  - Restart server after config changes

### Development Testing

For faster iteration during development:

1. **Use a local test server** (recommended)
2. **Hot reload** (if using a plugin like PlugMan):
   ```bash
   /plugman reload Splash
   ```
3. **Monitor logs** for any errors or warnings

## License

[Add your license here]

