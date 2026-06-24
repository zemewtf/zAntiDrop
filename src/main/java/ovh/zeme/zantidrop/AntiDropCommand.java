package ovh.zeme.zantidrop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

public class AntiDropCommand implements CommandExecutor, TabCompleter {

    private final zAntiDrop plugin;

    public AntiDropCommand(zAntiDrop plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player && !sender.hasPermission("zantidrop.admin")) {
            String noPerm = plugin.getConfig().getString("messages.no-permission");
            sender.sendMessage(ColorUtils.translate(noPerm));
            return true;
        }

        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("on")) {
                plugin.setDropEnabled(true);
                String enabledMsg = plugin.getConfig().getString("messages.enabled-global");
                sender.sendMessage(ColorUtils.translate(enabledMsg));
                return true;
            } else if (args[0].equalsIgnoreCase("off")) {
                plugin.setDropEnabled(false);
                String disabledMsg = plugin.getConfig().getString("messages.disabled-global");
                sender.sendMessage(ColorUtils.translate(disabledMsg));
                return true;
            }
        }

        String usageMsg = plugin.getConfig().getString("messages.usage");
        sender.sendMessage(ColorUtils.translate(usageMsg));
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (!sender.hasPermission("zantidrop.admin")) {
            return Collections.emptyList();
        }
        
        if (args.length == 1) {
            List<String> completions = new ArrayList<>();
            completions.add("on");
            completions.add("off");
            return StringUtil.copyPartialMatches(args[0], completions, new ArrayList<>());
        }
        
        return Collections.emptyList();
    }
}
