package ovh.zeme.zantidrop;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class DropListener implements Listener {

    private final zAntiDrop plugin;

    public DropListener(zAntiDrop plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerDrop(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        String worldName = player.getWorld().getName();

        boolean droppingEnabled = plugin.isDropEnabled();
        String worldStatus = plugin.getConfig().getString("worlds." + worldName);
        if (worldStatus != null) {
            if (worldStatus.equalsIgnoreCase("enabled")) {
                droppingEnabled = true;
            } else if (worldStatus.equalsIgnoreCase("disabled")) {
                droppingEnabled = false;
            }
        }

        if (droppingEnabled) {
            return;
        }

        if (player.getGameMode() == GameMode.CREATIVE || player.hasPermission("zantidrop.dropitems")) {
            return;
        }

        event.setCancelled(true);

        String blockedMessage = plugin.getConfig().getString("blocked-message");
        if (blockedMessage != null && !blockedMessage.isEmpty()) {
            player.sendMessage(ColorUtils.translate(blockedMessage));
        }
    }
}
