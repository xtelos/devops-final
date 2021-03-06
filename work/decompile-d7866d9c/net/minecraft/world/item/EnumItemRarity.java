package net.minecraft.world.item;

import net.minecraft.EnumChatFormat;

public enum EnumItemRarity {

    COMMON(EnumChatFormat.WHITE), UNCOMMON(EnumChatFormat.YELLOW), RARE(EnumChatFormat.AQUA), EPIC(EnumChatFormat.LIGHT_PURPLE);

    public final EnumChatFormat e;

    private EnumItemRarity(EnumChatFormat enumchatformat) {
        this.e = enumchatformat;
    }
}
