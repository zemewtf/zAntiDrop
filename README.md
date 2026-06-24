# zAntiDrop

A Minecraft plugin to disable dropping of items.

## Commands & Permissions

- **Command**: `/antidrop <on/off/reload>`
- **Permissions**: `zantidrop.admin` (to use the command), `zantidrop.dropitems` (to drop items)

## Configuration

```yaml
# If true, all players can drop items.
# If false, players cannot drop items unless they are in creative mode or have the 'zantidrop.dropitems' permission.
drop-enabled: false

# Per-world settings.
# If a world is set to 'enabled', players can drop items.
# If set to 'disabled', players cannot drop items (unless creative or possessing permission).
# If a world is not listed, it will fall back to the global 'drop-enabled' setting above.
worlds:
  world-disabled: disabled

# The message sent to players when their item drop is blocked.
# Color codes (using & or hex &#RRGGBB) and \n (for newlines) are supported.
# Set to "" to disable the message.
blocked-message: "&cYou do not have permission to drop items!"

# Messages
messages:
  no-permission: "&cYou do not have permission to run this command!"
  usage: "&cUsage: /antidrop <on|off|reload>"
  enabled-global: "&aItem dropping has been enabled globally."
  disabled-global: "&cItem dropping has been disabled globally."
  reloaded: "&aConfiguration has been reloaded."
```

## Compilation

The project uses Gradle. To compile the plugin and generate the `.jar` file, run the following command in the project root:

```bash
./gradlew build
```

You can find the compiled `.jar` file in `build/libs/zAntiDrop-X.jar`.
