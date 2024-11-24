package com.lsdevcloud.chatmentions;

import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created 24.11.2024
 *
 * @author lsdevcloud
 */

public final class ChatMentions extends JavaPlugin implements Listener
{

    @Override
    public void onEnable()
    {
        Bukkit.getPluginManager().registerEvents(this, this);
        // Plugin startup logic

    }

    @Override
    public void onDisable()
    {
        // Plugin shutdown logic
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onChat(final AsyncPlayerChatEvent event)
    {

        final String[] splitMessage = event.getMessage().split(" ");

        StringBuilder message = new StringBuilder("§r");
        for (int i = 0; i < splitMessage.length; i++)
        {
            final String string = splitMessage[i];
            if (Bukkit.getOnlinePlayers().stream().map(HumanEntity::getName).anyMatch(s -> s.equals(string)))
            {

                message.append("§b@" + string + "§r ");
            }
            else
            {
                message.append(string + " ");
            }
        }

        event.setMessage(message.toString());
    }
}
