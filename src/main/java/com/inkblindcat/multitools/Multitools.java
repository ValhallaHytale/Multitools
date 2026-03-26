package com.inkblindcat.multitools;

import com.hypixel.hytale.server.core.event.events.player.PlayerReadyEvent;
import com.hypixel.hytale.server.core.modules.interaction.interaction.config.Interaction;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import com.hypixel.hytale.server.core.util.Config;
import com.inkblindcat.multitools.event.ContainerMtConfigSystem;
import com.inkblindcat.multitools.event.PlayerReadyMtConfigEvent;

import javax.annotation.Nonnull;

public class Multitools extends JavaPlugin {
    private static Multitools INSTANCE;

    private final Config<MultitoolsConfig> config;

    public Multitools(@Nonnull JavaPluginInit init) {
        super(init);
        INSTANCE = this;
        this.config = this.withConfig("Multitools", MultitoolsConfig.CODEC);
    }

    @Override
    protected void setup() {
        super.setup();
        this.config.save();

        // (old: LivingEntityInventoryChangeEvent removed in newer server versions)
        this.getEventRegistry().registerGlobal(PlayerReadyEvent.class, PlayerReadyMtConfigEvent::onPlayerReady);
        this.getEntityStoreRegistry().registerSystem(new ContainerMtConfigSystem());
    }

    public static Multitools getInstance() {
        return INSTANCE;
    }

    public Config<MultitoolsConfig> getConfig() {
        return config;
    }
}
