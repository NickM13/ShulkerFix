package com.nickm13.shulker;

import com.nickm13.shulker.listener.BreakListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author NickM13
 * @since 3/15/2021
 */
public class ShulkerFix extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new BreakListener(), this);
    }

    @Override
    public void onDisable() {

    }

}
