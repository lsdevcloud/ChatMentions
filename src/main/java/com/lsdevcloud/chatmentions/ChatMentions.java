package com.lsdevcloud.chatmentions;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created: 24.11.2024
 *
 * @author lsdevcloud
 */
public final class ChatMentions extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        getLogger().info("ChatMentions has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("ChatMentions has been disabled!");
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onChat(final AsyncPlayerChatEvent event) {
        final Set<String> onlinePlayerNames = Bukkit.getOnlinePlayers().stream().map(Player::getName).collect(Collectors.toSet());

        final String highlightedMessage = highlightMentions(event.getMessage(), onlinePlayerNames);

        event.setMessage(highlightedMessage);
    }

    private String highlightMentions(String message, Set<String> onlinePlayerNames) {
        String[] words = message.split("\\s+");
        StringBuilder formattedMessage = new StringBuilder("§r");

        for (String s : words) {
            if (onlinePlayerNames.contains(s)) {
                formattedMessage.append("§b@").append(s).append("§r ");
            } else {
                formattedMessage.append(s).append(" ");
            }
        }
        return formattedMessage.toString().trim();
    }
}
