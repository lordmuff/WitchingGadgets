package witchinggadgets.common.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import witchinggadgets.common.blocks.tiles.TileEntityAgeingStone;

public class BlockTimeStone extends BlockContainer
{
	public BlockTimeStone() {
		super(Material.rock);
		this.setHardness(0.8F);
		this.setResistance(10);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	{
		return false;
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		this.blockIcon = iconRegister.registerIcon("witchinggadgets:timeStone");
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityAgeingStone();
	}

}
