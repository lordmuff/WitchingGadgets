package witchinggadgets.common.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class InventoryBag implements IInventory
{
	private Container container;
	public ItemStack[] stackList;

	public InventoryBag(Container par1Container)
	{
		this.container = par1Container;
		this.stackList = new ItemStack[18];
	}


	@Override
	public int getSizeInventory()
	{
		return this.stackList.length;
	}

	@Override
	public ItemStack getStackInSlot(int i)
	{
		if(i >= this.getSizeInventory())return null;
		return this.stackList[i];
	}	

	@Override
	public ItemStack getStackInSlotOnClosing(int i)
	{
		if (this.stackList[i] != null)
		{
			ItemStack itemstack = this.stackList[i];
			this.stackList[i] = null;
			return itemstack;
		}
		return null;
	}

	@Override
	public ItemStack decrStackSize(int i, int j)
	{

		if (this.stackList[i] != null)
		{
			ItemStack itemstack;

			if (this.stackList[i].stackSize <= j)
			{
				itemstack = this.stackList[i];
				this.stackList[i] = null;
				this.markDirty();
				return itemstack;
			}
			itemstack = this.stackList[i].splitStack(j);

			if (this.stackList[i].stackSize == 0)
			{
				this.stackList[i] = null;
			}

			this.container.onCraftMatrixChanged(this);
			//this.onInventoryChanged();
			return itemstack;
		}
		return null;
	}


	@Override
	public void setInventorySlotContents(int i, ItemStack stack)
	{
		this.stackList[i] = stack;

		if (stack != null && stack.stackSize > this.getInventoryStackLimit())
		{
			stack.stackSize = this.getInventoryStackLimit();
		}

		this.container.onCraftMatrixChanged(this);
		//this.onInventoryChanged();
	}

	@Override
	public String getInventoryName() {
		return "container.WGPouch";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return true;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public void markDirty() {

	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return true;
	}

	@Override
	public void openInventory() {}

	@Override
	public void closeInventory() {}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return true;
	}

}
