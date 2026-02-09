package com.inkblindcat.multitools;

import com.hypixel.hytale.codec.Codec;
import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;

public class MultitoolsConfig {

    public static final BuilderCodec<MultitoolsConfig> CODEC =
            BuilderCodec.builder(MultitoolsConfig.class, MultitoolsConfig::new)

                    .append(new KeyedCodec<>("EnableDurabilityOverride", Codec.BOOLEAN),
                            (cfg, v, ei) -> cfg.enableDurabilityOverride = v,
                            (cfg, ei) -> cfg.enableDurabilityOverride).add()

                    .append(new KeyedCodec<>("CrudeMultitoolMaxDurability", Codec.DOUBLE),
                            (cfg, v, ei) -> cfg.crudeMultitoolMaxDurability = v,
                            (cfg, ei) -> cfg.crudeMultitoolMaxDurability).add()

                    .append(new KeyedCodec<>("CopperMultitoolMaxDurability", Codec.DOUBLE),
                            (cfg, v, ei) -> cfg.copperMultitoolMaxDurability = v,
                            (cfg, ei) -> cfg.copperMultitoolMaxDurability).add()

                    .append(new KeyedCodec<>("IronMultitoolMaxDurability", Codec.DOUBLE),
                            (cfg, v, ei) -> cfg.ironMultitoolMaxDurability = v,
                            (cfg, ei) -> cfg.ironMultitoolMaxDurability).add()

                    .append(new KeyedCodec<>("ThoriumMultitoolMaxDurability", Codec.DOUBLE),
                            (cfg, v, ei) -> cfg.thoriumMultitoolMaxDurability = v,
                            (cfg, ei) -> cfg.thoriumMultitoolMaxDurability).add()

                    .append(new KeyedCodec<>("CobaltMultitoolMaxDurability", Codec.DOUBLE),
                            (cfg, v, ei) -> cfg.cobaltMultitoolMaxDurability = v,
                            (cfg, ei) -> cfg.cobaltMultitoolMaxDurability).add()

                    .append(new KeyedCodec<>("AdamantiteMultitoolMaxDurability", Codec.DOUBLE),
                            (cfg, v, ei) -> cfg.adamantiteMultitoolMaxDurability = v,
                            (cfg, ei) -> cfg.adamantiteMultitoolMaxDurability).add()

                    .append(new KeyedCodec<>("OnyxiumMultitoolMaxDurability", Codec.DOUBLE),
                            (cfg, v, ei) -> cfg.onyxiumMultitoolMaxDurability = v,
                            (cfg, ei) -> cfg.onyxiumMultitoolMaxDurability).add()

                    .append(new KeyedCodec<>("MithrilMultitoolMaxDurability", Codec.DOUBLE),
                            (cfg, v, ei) -> cfg.mithrilMultitoolMaxDurability = v,
                            (cfg, ei) -> cfg.mithrilMultitoolMaxDurability).add()

                    .append(new KeyedCodec<>("FiretineMultitoolMaxDurability", Codec.DOUBLE),
                            (cfg, v, ei) -> cfg.firetineMultitoolMaxDurability = v,
                            (cfg, ei) -> cfg.firetineMultitoolMaxDurability).add()

                    .build();

    private boolean enableDurabilityOverride = false;
    private double crudeMultitoolMaxDurability = 150;
    private double copperMultitoolMaxDurability = 200;
    private double ironMultitoolMaxDurability = 250;
    private double thoriumMultitoolMaxDurability = 325;
    private double cobaltMultitoolMaxDurability = 325;
    private double adamantiteMultitoolMaxDurability = 400;
    private double mithrilMultitoolMaxDurability = 400;
    private double onyxiumMultitoolMaxDurability = 450;
    private double firetineMultitoolMaxDurability = 400;

    public MultitoolsConfig() {}

    public boolean isDurabilityOverrideEnabled() {
        return enableDurabilityOverride;
    }

    public double getCrudeMultitoolMaxDurability() {
        return crudeMultitoolMaxDurability;
    }

    public double getCopperMultitoolMaxDurability() {
        return copperMultitoolMaxDurability;
    }

    public double getIronMultitoolMaxDurability() {
        return ironMultitoolMaxDurability;
    }

    public double getThoriumMultitoolMaxDurability() {
        return thoriumMultitoolMaxDurability;
    }

    public double getCobaltMultitoolMaxDurability() {
        return cobaltMultitoolMaxDurability;
    }

    public double getAdamantiteMultitoolMaxDurability() {
        return adamantiteMultitoolMaxDurability;
    }

    public double getOnyxiumMultitoolMaxDurability() {
        return onyxiumMultitoolMaxDurability;
    }

    public double getMithrilMultitoolMaxDurability() {
        return mithrilMultitoolMaxDurability;
    }

    public double getFiretineMultitoolMaxDurability() {
        return firetineMultitoolMaxDurability;
    }
}
