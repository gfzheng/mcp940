package net.minecraft.item;

import net.minecraft.block.Block;

public class ItemAir extends Item
{
    private final Block field_190904_a;

    public ItemAir(Block p_i47264_1_)
    {
        this.field_190904_a = p_i47264_1_;
    }

    /**
     * Returns the unlocalized name of this item. This version accepts an ItemStack so different stacks can have
     * different names based on their damage or NBT.
     */
    public String getUnlocalizedName(ItemStack stack)
    {
        return this.field_190904_a.getUnlocalizedName();
    }

    /**
     * Returns the unlocalized name of this item.
     */
    public String getUnlocalizedName()
    {
        return this.field_190904_a.getUnlocalizedName();
    }
}
