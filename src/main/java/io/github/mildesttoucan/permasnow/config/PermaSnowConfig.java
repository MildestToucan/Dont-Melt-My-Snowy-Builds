package io.github.mildesttoucan.permasnow.config;

import folk.sisby.kaleido.api.WrappedConfig;
import folk.sisby.kaleido.lib.quiltconfig.api.annotations.Comment;

public class PermaSnowConfig extends WrappedConfig {

    @Comment("Whether snow should melt from light sources (Vanilla behavior) Off by default.")
    public boolean shouldSnowMelt = false;

    @Comment("If snow melting is allowed, then this setting can be used to change the required light level. 11 is the Vanilla default.")
    public int snowMeltingLightLevel = 11;


    @Comment("Whether ice blocks should melt from light sources (Vanilla behavior) On by default.")
    @Comment("Note this only toggles ice blocks melting from light sources as they would in Vanilla. Nothing else.")
    public boolean shouldIceMelt = true;

    @Comment("If ice melting is allowed, then this setting can be used to change the required light level. 11 is the Vanilla default.")
    @Comment("Note that the ice's melting light level is also subtracted by the ice block's own light level when checking for melting.")
    public int iceMeltingLightLevel = 11;
}
