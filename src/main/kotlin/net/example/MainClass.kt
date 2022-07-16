package net.example

import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.plugin.java.JavaPlugin

// build with:
// gradlew shadowJar

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

/**
 * main class
 * See plugin.yml
 */
class MainClass: JavaPlugin() {
    override fun onEnable() {
        super.onEnable()

        Bukkit.getServer().pluginManager.registerEvents(JoinLeaveListener(), this)
        logger.info("Added join & leave listener")
    }
}
