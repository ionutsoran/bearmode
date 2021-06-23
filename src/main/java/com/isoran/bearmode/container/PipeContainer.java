package com.isoran.bearmode.container;

import com.isoran.bearmode.block.ModBlocks;
import com.isoran.bearmode.tileentity.PipeTile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

import javax.annotation.Nullable;

public class PipeContainer extends Container {

    private TileEntity tileEntity;
    private PlayerEntity playerEntity;
    private IItemHandler playerInventory;

    protected PipeContainer(@Nullable ContainerType<?> p_i50105_1_, int p_i50105_2_) {
        super(p_i50105_1_, p_i50105_2_);
    }

    public PipeContainer(int windowId, World world, BlockPos pos, PlayerInventory playerInventory, PlayerEntity player)
    {
        super(ModContainers.PIPE_CONTAINER.get(), windowId);
        this.tileEntity = world.getBlockEntity(pos);
        this.playerEntity = player;
        this.playerInventory= new InvWrapper(playerInventory);

        if (tileEntity!=null)
        {
            tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h ->{
                addSlot(new SlotItemHandler(h, 0, 30,57));
                addSlot(new SlotItemHandler(h, 1, 134,15));
                addSlot(new SlotItemHandler(h, 2, 134,57));
            });
        }

        layoutPlayerInventorySlots(8,84);
    }

    @Override
    public boolean stillValid(PlayerEntity player) {
        return stillValid(IWorldPosCallable.create(tileEntity.getLevel(),
                tileEntity.getBlockPos()),playerEntity, ModBlocks.PIPE.get());
    }

    private int addSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx)
    {
        for(int i = 0; i < amount; i++)
        {
            addSlot(new SlotItemHandler(handler, index, x, y));
            x +=dx;
            index++;
        }

        return index;
    }

    private int addSlotBox(IItemHandler handler, int index, int x, int y, int horAmount, int dx, int verAmount, int dy)
    {
        for (int j=0; j<verAmount;j++)
        {
            index = addSlotRange(handler, index, x,y, horAmount, dx);
            y +=dy;
        }

        return index;
    }

    private void layoutPlayerInventorySlots(int leftCol, int topRow)
    {
        addSlotBox(playerInventory, 9, leftCol, topRow, 9, 18,3,18);

        topRow +=58;
        addSlotRange(playerInventory, 0, leftCol, topRow, 9,18);
    }

    @Override
    public ItemStack quickMoveStack(PlayerEntity playerIn, int index)
    {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot !=null && slot.getItem().isStackable())
        {
            ItemStack stack = slot.getItem().getStack();
            itemStack = stack.copy();

            if  (index==0){
                if(!this.moveItemStackTo(stack,1,37, true))
                {
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(stack, itemStack);
            }
            else
            {
                if(stack.getItem() == Items.DIAMOND){
                    if(!this.moveItemStackTo(stack, 0,1, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else
                if (index < 28)
                {
                    if(!this.moveItemStackTo(stack,28,37, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else
                    if (index < 37 && !this.moveItemStackTo(stack, 1,28,false))
                    {
                        return ItemStack.EMPTY;
                    }
            }

            if(stack.isEmpty())
            {
                slot.set(ItemStack.EMPTY);
            }
            else
            {
                slot.setChanged();
            }

            if(stack.getCount() == itemStack.getCount())
            {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, stack);
        }

        return itemStack;
    }

    @OnlyIn(Dist.CLIENT)
    public int getEnergyLevel(){
        return ((PipeTile) this.tileEntity).getEnergyLevel();
    }
}
