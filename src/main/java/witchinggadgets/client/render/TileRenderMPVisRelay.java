package witchinggadgets.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

public class TileRenderMPVisRelay extends TileEntitySpecialRenderer
{
	float[][] faces= new float[][]{{1,0},{.5f,.25f},{.5f,.75f},{1,1}};

	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float f)
	{
		GL11.glPushMatrix();

		Tessellator tes = Tessellator.instance;
		GL11.glDisable(GL11.GL_LIGHTING);
		//		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glTranslatef((float)x, (float)y, (float)z);

		double disty = tile.yCoord-RenderManager.instance.viewerPosY;
		double distz = tile.zCoord-RenderManager.instance.viewerPosZ;
		double distxz =  Math.sqrt( distx*distx + distz*distz );
		double dist = Math.sqrt( distxz*distxz + disty*disty );
		float line = (float) (dist>=32?1: 6-dist/32*6);
		GL11.glLineWidth(line);

		int step = 400;
		float third = 1/3f;
		float sixth = third*2;
		GL11.glTranslatef(.5f,.5f,.5f);








		GL11.glEnable(GL11.GL_LIGHTING);

		GL11.glPopMatrix();
	}

}