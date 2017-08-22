package net.minecraft.item;

import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemFirework extends Item
{
    /**
     * Called when a Block is right-clicked with this Item
     */
    public EnumActionResult onItemUse(EntityPlayer stack, World playerIn, BlockPos worldIn, EnumHand pos, EnumFacing hand, float facing, float hitX, float hitY)
    {
        if (!playerIn.isRemote)
        {
            ItemStack itemstack = stack.getHeldItem(pos);
            EntityFireworkRocket entityfireworkrocket = new EntityFireworkRocket(playerIn, (double)((float)worldIn.getX() + facing), (double)((float)worldIn.getY() + hitX), (double)((float)worldIn.getZ() + hitY), itemstack);
            playerIn.spawnEntityInWorld(entityfireworkrocket);

            if (!stack.capabilities.isCreativeMode)
            {
                itemstack.func_190918_g(1);
            }
        }

        return EnumActionResult.SUCCESS;
    }

    public ActionResult<ItemStack> onItemRightClick(World itemStackIn, EntityPlayer worldIn, EnumHand playerIn)
    {
        if (worldIn.isElytraFlying())
        {
            ItemStack itemstack = worldIn.getHeldItem(playerIn);

            if (!itemStackIn.isRemote)
            {
                EntityFireworkRocket entityfireworkrocket = new EntityFireworkRocket(itemStackIn, itemstack, worldIn);
                itemStackIn.spawnEntityInWorld(entityfireworkrocket);

                if (!worldIn.capabilities.isCreativeMode)
                {
                    itemstack.func_190918_g(1);
                }
            }

            return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, worldIn.getHeldItem(playerIn));
        }
        else
        {
            return new ActionResult<ItemStack>(EnumActionResult.PASS, worldIn.getHeldItem(playerIn));
        }
    }
}
