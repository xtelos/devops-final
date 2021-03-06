package net.minecraft.world.inventory;

import net.minecraft.world.IInventory;
import net.minecraft.world.InventorySubcontainer;
import net.minecraft.world.entity.player.EntityHuman;
import net.minecraft.world.entity.player.PlayerInventory;
import net.minecraft.world.item.ItemStack;

// CraftBukkit start
import org.bukkit.craftbukkit.inventory.CraftInventory;
import org.bukkit.craftbukkit.inventory.CraftInventoryView;
// CraftBukkit end

public class ContainerShulkerBox extends Container {

    private final IInventory c;
    // CraftBukkit start
    private CraftInventoryView bukkitEntity;
    private PlayerInventory player;

    @Override
    public CraftInventoryView getBukkitView() {
        if (bukkitEntity != null) {
            return bukkitEntity;
        }

        bukkitEntity = new CraftInventoryView(this.player.player.getBukkitEntity(), new CraftInventory(this.c), this);
        return bukkitEntity;
    }
    // CraftBukkit end

    public ContainerShulkerBox(int i, PlayerInventory playerinventory) {
        this(i, playerinventory, new InventorySubcontainer(27));
    }

    public ContainerShulkerBox(int i, PlayerInventory playerinventory, IInventory iinventory) {
        super(Containers.SHULKER_BOX, i);
        a(iinventory, 27);
        this.c = iinventory;
        this.player = playerinventory; // CraftBukkit - save player
        iinventory.startOpen(playerinventory.player);
        boolean flag = true;
        boolean flag1 = true;

        int j;
        int k;

        for (j = 0; j < 3; ++j) {
            for (k = 0; k < 9; ++k) {
                this.a((Slot) (new SlotShulkerBox(iinventory, k + j * 9, 8 + k * 18, 18 + j * 18)));
            }
        }

        for (j = 0; j < 3; ++j) {
            for (k = 0; k < 9; ++k) {
                this.a(new Slot(playerinventory, k + j * 9 + 9, 8 + k * 18, 84 + j * 18));
            }
        }

        for (j = 0; j < 9; ++j) {
            this.a(new Slot(playerinventory, j, 8 + j * 18, 142));
        }

    }

    @Override
    public boolean canUse(EntityHuman entityhuman) {
        return this.c.a(entityhuman);
    }

    @Override
    public ItemStack shiftClick(EntityHuman entityhuman, int i) {
        ItemStack itemstack = ItemStack.b;
        Slot slot = (Slot) this.slots.get(i);

        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();

            itemstack = itemstack1.cloneItemStack();
            if (i < this.c.getSize()) {
                if (!this.a(itemstack1, this.c.getSize(), this.slots.size(), true)) {
                    return ItemStack.b;
                }
            } else if (!this.a(itemstack1, 0, this.c.getSize(), false)) {
                return ItemStack.b;
            }

            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.b);
            } else {
                slot.d();
            }
        }

        return itemstack;
    }

    @Override
    public void b(EntityHuman entityhuman) {
        super.b(entityhuman);
        this.c.closeContainer(entityhuman);
    }
}
