package net.example

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin

/**
 * An example class which listens to player join & player leave
 */
private class JoinLeaveListener: Listener {
    @EventHandler
    fun onPlayerJoin(e: PlayerJoinEvent) {
        val player = e.player
        // same as e.setJoinMessage()
        e.joinMessage = "Welcome %s to the server!".format(player.displayName)
    }

    @EventHandler
    fun onPlayerLeave(e: PlayerQuitEvent) {
        val player = e.player

        e.quitMessage = "See you %s!".format(player.displayName)
    }
}

private class ExampleCommandListener: CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) return false
        if (args.isEmpty()) return false

        if (args[0] == "get") {
            if (args.size == 1) {
                val stack = ItemStack(Material.DIAMOND, 10)
                sender.inventory.addItem(stack)
                sender.sendMessage("gave 10 diamonds to ${sender.displayName}")
            } else if (args.size == 2) {
                try {
                    val amount = args[1].toInt()

                    val stack = ItemStack(Material.DIAMOND, amount)
                    sender.inventory.addItem(stack)
                    sender.sendMessage("gave $amount diamonds to ${sender.displayName}")
                } catch (ex: java.lang.NumberFormatException) {
                    sender.sendMessage("wrong [amount] format -- should be a number")
                    return false
                }
            }

            return true
        }

        return false
    }
}

/**
 * main class
 * See plugin.yml
 */
class MainClass: JavaPlugin() {
    override fun onEnable() {
        super.onEnable()

        // usage: diamonds.get
        this.getCommand("diamonds")?.setExecutor(ExampleCommandListener())
        Bukkit.getServer().pluginManager.registerEvents(JoinLeaveListener(), this)
        logger.info("Added join & leave listener")
    }
}
