package witchinggadgets.common.blocks;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import witchinggadgets.WitchingGadgets;
import witchinggadgets.client.render.BlockRenderStoneDevice;
import witchinggadgets.common.blocks.tiles.TileEntityAgeingStone;
import witchinggadgets.common.blocks.tiles.TileEntityBlastfurnace;
import witchinggadgets.common.blocks.tiles.TileEntityEtherealWall;
import witchinggadgets.common.util.recipe.InfernalBlastfurnaceRecipe;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockWGStoneDevice extends BlockContainer
{
	public static String[] subNames = {"etherealWall","timeStone","blastFurnace"};
	IIcon[] icons = new IIcon[subNames.length];

	public BlockWGStoneDevice()
	{
		super(Material.rock);
		this.setHardness(4F);
		this.setResistance(10);
		setCreativeTab(WitchingGadgets.tabWG);
	}

	@Override
	public int damageDropped(int meta)
	{
		return meta;
	}
	
	@Override
	public void registerBlockIcons(IIconRegister iconRegister)
	{	
		for(int i=0;i<icons.length;i++)
			icons[i] = iconRegister.registerIcon("witchinggadgets:"+subNames[i]);
		TileEntityBlastfurnace.icon_bricks = iconRegister.registerIcon("witchinggadgets:blastFurnace");
		TileEntityBlastfurnace.icon_cornerBottomL = new IIcon[]{iconRegister.registerIcon("witchinggadgets:blastFurnace_cornerBottomL_off"),iconRegister.registerIcon("witchinggadgets:blastFurnace_cornerBottomL_on"),iconRegister.registerIcon("witchinggadgets:blastFurnace_cornerBottomL_special")};
		TileEntityBlastfurnace.icon_cornerBottomR = new IIcon[]{iconRegister.registerIcon("witchinggadgets:blastFurnace_cornerBottomR_off"),iconRegister.registerIcon("witchinggadgets:blastFurnace_cornerBottomR_on"),iconRegister.registerIcon("witchinggadgets:blastFurnace_cornerBottomR_special")};
		TileEntityBlastfurnace.icon_cornerTopL = new IIcon[]{iconRegister.registerIcon("witchinggadgets:blastFurnace_cornerTopL_off"),iconRegister.registerIcon("witchinggadgets:blastFurnace_cornerTopL_on"),iconRegister.registerIcon("witchinggadgets:blastFurnace_cornerTopL_special")};
		TileEntityBlastfurnace.icon_cornerTopR = new IIcon[]{iconRegister.registerIcon("witchinggadgets:blastFurnace_cornerTopR_off"),iconRegister.registerIcon("witchinggadgets:blastFurnace_cornerTopR_on"),iconRegister.registerIcon("witchinggadgets:blastFurnace_cornerTopR_special")};
		TileEntityBlastfurnace.icon_sideBottom = new IIcon[]{iconRegister.registerIcon("witchinggadgets:blastFurnace_sideBottom_off"),iconRegister.registerIcon("witchinggadgets:blastFurnace_sideBottom_on"),iconRegister.registerIcon("witchinggadgets:blastFurnace_sideBottom_special")};
		TileEntityBlastfurnace.icon_sideTop = new IIcon[]{iconRegister.registerIcon("witchinggadgets:blastFurnace_sideTop_off"),iconRegister.registerIcon("witchinggadgets:blastFurnace_sideTop_on"),iconRegister.registerIcon("witchinggadgets:blastFurnace_sideTop_special")};
		TileEntityBlastfurnace.icon_bottom = iconRegister.registerIcon("witchinggadgets:blastFurnace_bottom");
		TileEntityBlastfurnace.icon_bottomTBLR = new IIcon[]{iconRegister.registerIcon("witchinggadgets:blastFurnace_bottomT"),iconRegister.registerIcon("witchinggadgets:blastFurnace_bottomB"),iconRegister.registerIcon("witchinggadgets:blastFurnace_bottomL"),iconRegister.registerIcon("witchinggadgets:blastFurnace_bottomR")};
		TileEntityBlastfurnace.icon_internal = iconRegister.registerIcon("witchinggadgets:blastFurnace_internal");
		TileEntityBlastfurnace.icon_lava = iconRegister.registerIcon("witchinggadgets:blastFurnace_lava");
	}

	@Override
	public IIcon getIcon(int side, int meta)
	{

		return icons[Math.min(meta,icons.length-1)];
	}
	@Override
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side)
	{
		if(world.getTileEntity(x, y, z) instanceof TileEntityEtherealWall)
		{
			TileEntityEtherealWall tile = (TileEntityEtherealWall)world.getTileEntity(x, y, z);

			if(tile.camoID!=null)
			{
				if (tile.camoID != null && tile.isRenderTypeValid(tile.camoID.getRenderType(), tile.camoMeta) )
					return tile.camoID.getIcon(side, tile.camoMeta);
			}
		}

		if(world.getTileEntity(x, y, z) instanceof TileEntityBlastfurnace)
		{
			return ((TileEntityBlastfurnace)world.getTileEntity(x, y, z)).getTexture(side);
		}
		return this.icons[Math.min(world.getBlockMetadata(x, y, z),icons.length-1)];
	}
	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z)
	{
		if(world.getTileEntity(x, y, z) instanceof TileEntityEtherealWall)
		{
			TileEntityEtherealWall tile = (TileEntityEtherealWall)world.getTileEntity(x, y, z);
			if(tile.camoID!=null)
				return tile.camoID.getLightValue();
		}
		if(world.getTileEntity(x, y, z) instanceof TileEntityBlastfurnace)
		{
			int pos = ((TileEntityBlastfurnace)world.getTileEntity(x, y, z)).position;
			return pos==10||pos==12||pos==14||pos==16?13:  pos==22?15: 0;
		}
		return 0;
	}

	@Override
	public boolean canCreatureSpawn(EnumCreatureType type, IBlockAccess world, int x, int y, int z)
	{
		return false;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public boolean shouldSideBeRendered(IBlockAccess iBlockAccess, int x, int y, int z, int side)
	{
		int xx = x-ForgeDirection.getOrientation(side).offsetX;
		int yy = y-ForgeDirection.getOrientation(side).offsetY;
		int zz = z-ForgeDirection.getOrientation(side).offsetZ;
		if(iBlockAccess.getTileEntity(xx, yy, zz) instanceof TileEntityEtherealWall)
		{
			TileEntityEtherealWall tile = (TileEntityEtherealWall) iBlockAccess.getTileEntity(xx, yy, zz);
			boolean sameBlock = iBlockAccess.getBlock(x, y, z).equals(this) && iBlockAccess.getBlockMetadata(x, y, z) == iBlockAccess.getBlockMetadata(xx, yy, zz);
			boolean sameRenderBlock = tile.camoID!=null?(iBlockAccess.getBlock(x,y,z).equals(tile.camoID)&&iBlockAccess.getBlockMetadata(x,y,z)==tile.camoMeta):false;
			sameBlock |= sameRenderBlock;
			if(iBlockAccess.getTileEntity(x,y,z) instanceof TileEntityEtherealWall)
				sameBlock &= ((TileEntityEtherealWall)iBlockAccess.getTileEntity(x,y,z)).camoID!=null?(((TileEntityEtherealWall)iBlockAccess.getTileEntity(x,y,z)).camoID.equals(tile.camoID)&&((TileEntityEtherealWall)iBlockAccess.getTileEntity(x,y,z)).camoMeta==tile.camoMeta):false;
				
			return !sameBlock;
		}
		if(iBlockAccess.getTileEntity(xx, yy, zz) instanceof TileEntityBlastfurnace)
		{
			return true;
		}
		return super.shouldSideBeRendered(iBlockAccess, x,y,z, side);
	}

	@Override
	public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB aabb, List list, Entity entity)
	{
		if(world.getTileEntity(x, y, z) instanceof TileEntityEtherealWall)
		{
			TileEntityEtherealWall tile = (TileEntityEtherealWall)world.getTileEntity(x,y,z);

			if(tile != null && tile.master != null && tile.master.isAnyTileInNetPowered())
				return;
		}
		if(world.getTileEntity(x, y, z) instanceof TileEntityBlastfurnace)
		{
			int pos = ((TileEntityBlastfurnace)world.getTileEntity(x, y, z)).position;
			if(pos>17 && pos!= 22)
			{
				pos-=18;
				this.setBlockBounds(0f,0f,0f, 1f,.5f,1f);
				super.addCollisionBoxesToList(world, x, y, z, aabb, list, entity);

				this.setBlockBounds(pos%3==0?.5f:0,.5f,pos<3?.5f:0, (pos+1)%3==0?.5f:1,1,pos>5?.5f:1);
				super.addCollisionBoxesToList(world, x, y, z, aabb, list, entity);

				this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
				return;
			}
		}
		super.addCollisionBoxesToList(world, x, y, z, aabb, list, entity);	
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		if(world.getTileEntity(x, y, z) instanceof TileEntityBlastfurnace && ((TileEntityBlastfurnace)world.getTileEntity(x, y, z)).position==22)
		{
			return null;
		}
		return super.getCollisionBoundingBoxFromPool(world, x, y, z);
	}
	@Override
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z)
	{
		
		if(world.getTileEntity(x, y, z) instanceof TileEntityBlastfurnace && ((TileEntityBlastfurnace)world.getTileEntity(x, y, z)).position==22)
		{
			return AxisAlignedBB.getBoundingBox(x,y,z, x,y,z);
		}
		return super.getSelectedBoundingBoxFromPool(world, x, y, z);
	}
	@Override
	public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side)
	{
		if (world.getTileEntity(x, y, z) instanceof TileEntityBlastfurnace && ((TileEntityBlastfurnace)world.getTileEntity(x, y, z)).position==22)
			return false;
		return true;
	}
	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		if(!world.isRemote && world.getTileEntity(x, y, z) instanceof TileEntityBlastfurnace && ((TileEntityBlastfurnace)world.getTileEntity(x, y, z)).position==22)
			if(entity instanceof EntityItem)
			{
				ItemStack input = ((EntityItem)entity).getEntityItem();
				if(InfernalBlastfurnaceRecipe.getRecipeForInput(input)==null)
				{
					world.addBlockEvent(x,y,z, this, 5,0);
					entity.setDead();
					return;
				}

				int[] mPos = ((TileEntityBlastfurnace)world.getTileEntity(x, y, z)).masterPos;
				TileEntityBlastfurnace master = (TileEntityBlastfurnace) world.getTileEntity(mPos[0],mPos[1],mPos[2]);
				master.addStackToInputs(input);
				entity.setDead();
			}
			else if(entity instanceof EntityLivingBase && !entity.isImmuneToFire())
			{
				entity.attackEntityFrom(DamageSource.lava, 3.0F);
				entity.setFire(10);
			}
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata)
	{
		switch(metadata)
		{
		case 0:
			return new TileEntityEtherealWall();
		case 1:
			return new TileEntityAgeingStone();
		case 2:
			return new TileEntityBlastfurnace();
		}
		return null;
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack stack)
	{
		int playerViewQuarter = MathHelper.floor_double(entityLiving.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
		int meta = world.getBlockMetadata(x, y, z);
		int f = playerViewQuarter==0 ? 2:playerViewQuarter==1 ? 5:playerViewQuarter==2 ? 3: 4;
		/*if(meta == 0)
			((TileEntityEtherealWall)world.getTileEntity(x,y,z)).facing = f;
		else*/ 

	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block b, int side)
	{
		
		if(world.getTileEntity(x, y, z) instanceof TileEntityBlastfurnace)
		{

			int[] mPos = ((TileEntityBlastfurnace)world.getTileEntity(x, y, z)).masterPos;
			if(mPos!=null&&mPos.length>2 && world.getBlock(mPos[0],mPos[1],mPos[2]).equals(this))
			{
				byte pos = ((TileEntityBlastfurnace)world.getTileEntity(x, y, z)).position;
				removeBlastfurnace(world, mPos[0],mPos[1],mPos[2], x,y,z);
				if(pos!=22)
				{
					EntityItem blockDrop = new EntityItem(world, x+.5, y+.5, z+.5, pos<18?new ItemStack(TileEntityBlastfurnace.brickBlock[pos],1,0): new ItemStack(TileEntityBlastfurnace.stairBlock,1,TileEntityBlastfurnace.stairBlock!=Blocks.stone_brick_stairs?1:0));
					blockDrop.motionX = (float)world.rand.nextGaussian() * .05f;
					blockDrop.motionY = (float)world.rand.nextGaussian() * .05f + 0.2F;
					blockDrop.motionZ = (float)world.rand.nextGaussian() * .05f;
					world.spawnEntityInWorld(blockDrop);
				}
			}
		}
		super.breakBlock(world, x, y, z, b, side);
	}
	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
	{
		if(metadata==2)
			return new ArrayList();
		return super.getDrops(world, x, y, z, metadata, fortune);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	{
		if(world.getTileEntity(x, y, z) instanceof TileEntityEtherealWall)
		{
			TileEntityEtherealWall tile = (TileEntityEtherealWall) world.getTileEntity(x, y, z);
			ItemStack currentStack = player.getCurrentEquippedItem();
			Block camoID = null;
			int camoMeta = -1;
			boolean changeTexture = false;
			if(currentStack != null && currentStack.getItem() instanceof ItemBlock)
			{
				Block block = Block.getBlockFromItem(currentStack.getItem());
				int blockMeta = currentStack.getItemDamage();
				if(player.isSneaking() || (block instanceof BlockWGStoneDevice && blockMeta==0) )
					return false;
				if(block != null && tile.isRenderTypeValid(block.getRenderType(), blockMeta) && block.getMaterial() != Material.air)
				{
					if (block instanceof BlockDirectional)
						switch (side)
						{
						case 0:
						case 1:
							break;
						case 2:
							blockMeta = blockMeta & 12 | 2;
							break;
						case 3:
							blockMeta = blockMeta & 12 | 0;
							break;
						case 4:
							blockMeta = blockMeta & 12 | 1;
							break;
						case 5:
							blockMeta = blockMeta & 12 | 3;
							break;
						}
					if(block.getRenderType() == 39 && blockMeta == 2)
						switch (side)
						{
						case 0:
						case 1:
							blockMeta = 2;
							break;
						case 2:
						case 3:
							blockMeta = 4;
							break;
						case 4:
						case 5:
							blockMeta = 3;
						}
					if(block.getRenderType() == 31)
					{
						int j1 = blockMeta & 3;
						byte b0 = 0;

						switch (side)
						{
						case 0:
						case 1:
							b0 = 0;
							break;
						case 2:
						case 3:
							b0 = 8;
							break;
						case 4:
						case 5:
							b0 = 4;
						}
						blockMeta = j1 | b0;
					}
					camoID = block;
					camoMeta = blockMeta;
					changeTexture = true;
				}
			}
			else
				changeTexture = player.isSneaking();
			if(changeTexture)
			{
				tile.camoID = camoID;
				tile.camoMeta = camoMeta;
				world.markBlockRangeForRenderUpdate(x, y, z, x, y, z);
				//				PacketDispatcher.sendPacketToAllInDimension(tile.getDescriptionPacket(), world.provider.dimensionId);
			}
			return changeTexture;
		}
		
		return false;
	}

	@Override
	public void onNeighborChange(IBlockAccess world, int x, int y, int z, int tileX, int tileY, int tileZ)
	{
		if(world.getTileEntity(x, y, z) instanceof TileEntityEtherealWall)
		{
			TileEntityEtherealWall tile = (TileEntityEtherealWall) world.getTileEntity(x, y, z);
			if(tile != null)
				if(tile.master!=null)
					tile.master.freeSlaves();
		}
	}

	static void removeBlastfurnace(World world, int x, int y, int z, int hitX, int hitY, int hitZ)
	{
		world.setBlock(x, y, z, TileEntityBlastfurnace.brickBlock[4], 0, 3);
		world.markBlockForUpdate(x, y, z);

		for(int yy=0;yy<=2;yy++)
			for(int xx=-1;xx<=1;xx++)
				for(int zz=-1;zz<=1;zz++)
					if((yy!=0||xx!=0||zz!=0) && (x+xx!=hitX||y+yy!=hitY||z+zz!=hitZ))
						if(world.getTileEntity(x+xx, y+yy, z+zz) instanceof TileEntityBlastfurnace)
						{
							if(yy!=2)
								world.setBlock(x+xx, y+yy, z+zz, TileEntityBlastfurnace.brickBlock[yy*9 + (zz+1)*3 + (xx+1)], 0, 3);
							else if(xx==0&&zz==0)
								world.setBlock(x+xx, y+yy, z+zz, Blocks.lava, 0, 3);
							else
							{
								int md = xx==-1?0: xx==1?1: zz==-1?2: 3;
								world.setBlock(x+xx, y+yy, z+zz, TileEntityBlastfurnace.stairBlock, md, 3);
								if(world.getTileEntity(x+xx,y+yy,z+zz)!=null)
								{
									TileEntity tile = world.getTileEntity(x+xx,y+yy,z+zz);
									NBTTagCompound tag = new NBTTagCompound();
									tile.writeToNBT(tag);
									tag.setString("stair", "INFERNAL_BRICK");
									tile.readFromNBT(tag);
								}
							}
							world.markBlockForUpdate(x+xx, y+yy, z+zz);
						}
	}

	@Override
	public int getRenderType()
	{
		return BlockRenderStoneDevice.renderID;
	}

	@Override
	public boolean canRenderInPass(int pass)
	{
		BlockRenderStoneDevice.renderPass=pass;
		return true;
	}

	@Override
	public int colorMultiplier(IBlockAccess world, int x, int y, int z)
	{
		if(world.getTileEntity(x, y, z) instanceof TileEntityEtherealWall)
		{
			TileEntityEtherealWall tile = (TileEntityEtherealWall)world.getTileEntity(x, y, z);
			if(tile.camoID!=null)
				return tile.camoID instanceof BlockWGStoneDevice ? 0xFFFFFF : tile.camoID.colorMultiplier(world, x, y, z);
		}
		return 0xFFFFFF;
	}

}