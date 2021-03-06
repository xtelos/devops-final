package net.minecraft.world.item;

import net.minecraft.sounds.SoundCategory;
import net.minecraft.sounds.SoundEffects;
import net.minecraft.stats.StatisticList;
import net.minecraft.world.EnumHand;
import net.minecraft.world.InteractionResultWrapper;
import net.minecraft.world.entity.player.EntityHuman;
import net.minecraft.world.entity.projectile.EntityFishingHook;
import net.minecraft.world.item.enchantment.EnchantmentManager;
import net.minecraft.world.level.World;

import org.bukkit.event.player.PlayerFishEvent; // CraftBukkit

public class ItemFishingRod extends Item implements ItemVanishable {

    public ItemFishingRod(Item.Info item_info) {
        super(item_info);
    }

    @Override
    public InteractionResultWrapper<ItemStack> a(World world, EntityHuman entityhuman, EnumHand enumhand) {
        ItemStack itemstack = entityhuman.b(enumhand);
        int i;

        if (entityhuman.hookedFish != null) {
            if (!world.isClientSide) {
                i = entityhuman.hookedFish.b(itemstack);
                itemstack.damage(i, entityhuman, (entityhuman1) -> {
                    entityhuman1.broadcastItemBreak(enumhand);
                });
            }

            world.playSound((EntityHuman) null, entityhuman.locX(), entityhuman.locY(), entityhuman.locZ(), SoundEffects.ENTITY_FISHING_BOBBER_RETRIEVE, SoundCategory.NEUTRAL, 1.0F, 0.4F / (ItemFishingRod.RANDOM.nextFloat() * 0.4F + 0.8F));
        } else {
            // world.playSound((EntityHuman) null, entityhuman.locX(), entityhuman.locY(), entityhuman.locZ(), SoundEffects.ENTITY_FISHING_BOBBER_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (ItemFishingRod.RANDOM.nextFloat() * 0.4F + 0.8F));
            if (!world.isClientSide) {
                i = EnchantmentManager.c(itemstack);
                int j = EnchantmentManager.b(itemstack);

                // CraftBukkit start
                EntityFishingHook entityfishinghook = new EntityFishingHook(entityhuman, world, j, i);
                PlayerFishEvent playerFishEvent = new PlayerFishEvent((org.bukkit.entity.Player) entityhuman.getBukkitEntity(), null, (org.bukkit.entity.FishHook) entityfishinghook.getBukkitEntity(), PlayerFishEvent.State.FISHING);
                world.getServer().getPluginManager().callEvent(playerFishEvent);

                if (playerFishEvent.isCancelled()) {
                    entityhuman.hookedFish = null;
                    return InteractionResultWrapper.pass(itemstack);
                }
                world.playSound((EntityHuman) null, entityhuman.locX(), entityhuman.locY(), entityhuman.locZ(), SoundEffects.ENTITY_FISHING_BOBBER_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (ItemFishingRod.RANDOM.nextFloat() * 0.4F + 0.8F));
                world.addEntity(entityfishinghook);
                // CraftBukkit end
            }

            entityhuman.b(StatisticList.ITEM_USED.b(this));
        }

        return InteractionResultWrapper.a(itemstack, world.s_());
    }

    @Override
    public int c() {
        return 1;
    }
}
