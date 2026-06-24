package ovh.zeme.zantidrop;

import org.bukkit.plugin.java.JavaPlugin;

public final class zAntiDrop extends JavaPlugin {

    private boolean dropEnabled;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        this.dropEnabled = getConfig().getBoolean("drop-enabled", false);

        if (getCommand("antidrop") != null) {
            AntiDropCommand cmd = new AntiDropCommand(this);
            getCommand("antidrop").setExecutor(cmd);
            getCommand("antidrop").setTabCompleter(cmd);
        }

        getServer().getPluginManager().registerEvents(new DropListener(this), this);

        getLogger().info("zAntiDrop has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("zAntiDrop has been disabled!");
    }

    public boolean isDropEnabled() {
        return dropEnabled;
    }

    public void setDropEnabled(boolean dropEnabled) {
        this.dropEnabled = dropEnabled;
        getConfig().set("drop-enabled", dropEnabled);
        saveConfig();
    }

    public void reloadPluginConfig() {
        reloadConfig();
        this.dropEnabled = getConfig().getBoolean("drop-enabled", false);
    }
}
