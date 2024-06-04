package witchinggadgets.common.magic;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class WGEnchantStealth extends Enchantment
{
	public WGEnchantStealth(int id)
	{
		super(id, 0, EnumEnchantmentType.armor);
		this.setName("wg.stealth");
	}

	@Override
	public int getMinEnchantability(int lvl)
	{
	    return 5+(lvl-1)*11;
	}
	@Override
	public int getMaxEnchantability(int lvl)
	{
	    return getMinEnchantability(lvl) + 20;
	}

	@Override
	public int getMaxLevel()
	{
		return 5;
	}

	@Override
	public boolean canApply(ItemStack stack)
	{
		boolean b = stack!=null && stack.getItem() instanceof ItemArmor && ( ((ItemArmor)stack.getItem()).armorType==2||((ItemArmor)stack.getItem()).armorType==3 );
		return b;
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack)
	{
		return false;
	}
}
