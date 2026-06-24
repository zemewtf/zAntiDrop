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
        if (plugin.isDropEnabled()) {
            return;
        }

        Player player = event.getPlayer();

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
