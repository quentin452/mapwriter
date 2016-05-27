package mapwriter.map.mapmode;

import java.awt.Point;

import mapwriter.api.IMapMode;
import mapwriter.api.IMapView;
import mapwriter.config.MapModeConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

public class MapMode implements IMapMode
{
	private int sw = 320;
	private int sh = 240;
	private double screenScalingFactor = 1.0;

	// calculated before every frame drawn by updateMapDimensions
	private int xTranslation = 0;
	private int yTranslation = 0;
	private int x = -25; // x cordinate in the middle of the map
	private int y = -25; // y cordinate in the middle of the map
	private int w = 50;
	private int h = 50;
	private int wPixels = 50;
	private int hPixels = 50;

	private int textX = 0;
	private int textY = 0;
	private int textColour = 0xffffffff;

	// config settings
	private double widthPercent;
	private double heightPercent;
	private double xPos;
	private double yPos;

	private int mouseXOffset;
	private int mouseYOffset;

	private MapModeConfig config;

	public MapMode(MapModeConfig config)
	{
		this.config = config;
		this.updateMargin();
	}

	public void setScreenRes(int dw, int dh, int sw, int sh, double scaling)
	{
		if ((sw != this.sw) || (sh != this.sh) || (scaling != this.screenScalingFactor))
		{
			this.sw = sw;
			this.sh = sh;
			this.screenScalingFactor = scaling;
			this.update();
		}
	}

	public void setScreenRes()
	{
		Minecraft mc = Minecraft.getMinecraft();
		ScaledResolution sRes = new ScaledResolution(mc);
		this.setScreenRes(
				mc.displayWidth,
				mc.displayHeight,
				sRes.getScaledWidth(),
				sRes.getScaledHeight(),
				sRes.getScaleFactor());
	}

	public void updateMargin()
	{
		if (this.widthPercent != this.config.widthPercent
				|| this.heightPercent != this.config.heightPercent || this.xPos != this.config.xPos
				|| this.yPos != this.config.yPos)
		{
			this.widthPercent = this.config.widthPercent;
			this.heightPercent = this.config.heightPercent;
			this.xPos = this.config.xPos;
			this.yPos = this.config.yPos;

			this.update();
		}
	}

	private void update()
	{
		double x, y;

		this.w = (int) ((double) this.sw * (this.widthPercent / 100.0));
		this.h = (int) ((double) this.sh * (this.heightPercent / 100.0));

		if (this.config.circular)
		{
			this.w = this.h;
		}

		// make sure width and height are multiples of 2
		this.w &= -2;
		this.h &= -2;

		x = ((this.sw - this.w) * (this.xPos / 100.0));
		y = ((this.sh - this.h) * (this.yPos / 100.0));

		this.xTranslation = (int) (x + (this.w >> 1));
		this.yTranslation = (int) (y + (this.h >> 1));

		this.x = -(this.w >> 1);
		this.y = -(this.h >> 1);

		this.wPixels = (int) Math.round((this.w) * this.screenScalingFactor);
		this.hPixels = (int) Math.round((this.h) * this.screenScalingFactor);

		// calculate coords display location
		this.textX = 0;
		this.textY = (this.h >> 1) + 4;
	}

	public Point screenXYtoBlockXZ(IMapView mapView, int sx, int sy)
	{
		double withinMapX = ((double) (sx - this.xTranslation)) / ((double) this.w);
		double withinMapY = ((double) (sy - this.yTranslation)) / ((double) this.h);
		int bx = (int) Math.floor((mapView.getX() + (withinMapX * mapView.getWidth())));
		int bz = (int) Math.floor((mapView.getZ() + (withinMapY * mapView.getHeight())));
		return new Point(bx, bz);
	}

	public Point.Double blockXZtoScreenXY(IMapView mapView, double bX, double bZ)
	{
		double xNorm = (bX - mapView.getX()) / mapView.getWidth();
		double zNorm = (bZ - mapView.getZ()) / mapView.getHeight();
		return new Point.Double(this.w * xNorm, this.h * zNorm);
	}

	public Point.Double getClampedScreenXY(IMapView mapView, double bX, double bZ)
	{
		double xRel = (bX - mapView.getX()) / mapView.getWidth();
		double zRel = (bZ - mapView.getZ()) / mapView.getHeight();
		double limit = 0.49;

		if (!this.config.circular)
		{
			if (xRel < -limit)
			{
				zRel = (-limit * zRel) / xRel;
				xRel = -limit;
			}
			if (xRel > limit)
			{
				zRel = (limit * zRel) / xRel;
				xRel = limit;
			}
			if (zRel < -limit)
			{
				xRel = (-limit * xRel) / zRel;
				zRel = -limit;
			}
			if (zRel > limit)
			{
				xRel = (limit * xRel) / zRel;
				zRel = limit;
			}
			if (xRel < -limit)
			{
				zRel = (-limit * zRel) / xRel;
				xRel = -limit;
			}
			if (xRel > limit)
			{
				zRel = (limit * zRel) / xRel;
				xRel = limit;
			}
		}
		else
		{
			double dSq = (xRel * xRel) + (zRel * zRel);
			if (dSq > (limit * limit))
			{
				double a = Math.atan2(zRel, xRel);
				xRel = limit * Math.cos(a);
				zRel = limit * Math.sin(a);
			}
		}

		// multiply by the overlay size and add the overlay position to
		// get the position within the overlay in screen coordinates
		return new Point.Double(this.w * xRel, this.h * zRel);
	}

	public boolean posWithin(int mouseX, int mouseY)
	{
		mouseXOffset = mouseX - this.xTranslation;
		mouseYOffset = mouseY - this.yTranslation;
		return this.isMouseYWithinSlotBounds(mouseY) && this.isMouseXWithinSlotBounds(mouseX);
	}

	private boolean isMouseYWithinSlotBounds(int mouseY)
	{
		return (mouseY >= this.yTranslation + this.y) && (mouseY <= this.yTranslation - this.y);
	}

	private boolean isMouseXWithinSlotBounds(int mouseX)
	{
		return (mouseX >= this.xTranslation + this.x) && (mouseX <= this.xTranslation - this.x);
	}

	public Point.Double getNewPosPoint(double mouseX, double mouseY)
	{
		int newX = (int) (mouseX - mouseXOffset);
		int newY = (int) (mouseY - mouseYOffset);

		if (newX + this.x < 0)
		{
			newX = -this.x;
		}
		if (newX - this.x > this.sw)
		{
			newX = this.sw - -this.x;
		}

		if (newY + this.y < 0)
		{
			newY = -this.y;
		}
		if (newY - this.y > this.sh)
		{
			newY = this.sh - -this.y;
		}

		double x = ((newX - (this.w / 2)) * 100.0) / (this.sw - this.w);
		double y = ((newY - (this.h / 2)) * 100.0) / (this.sh - this.h);

		Point.Double pos = new Point.Double(x, y);
		return pos;
	}

	public int getXTranslation()
	{
		return this.xTranslation;
	}

	public int getYTranslation()
	{
		return this.yTranslation;
	}

	public int getX()
	{
		return this.x;
	}

	public int getY()
	{
		return this.y;
	}

	public int getW()
	{
		return this.w;
	}

	public int getH()
	{
		return this.h;
	}

	public int getWPixels()
	{
		return this.wPixels;
	}

	public int getHPixels()
	{
		return this.hPixels;
	}

	public MapModeConfig getConfig()
	{
		return this.config;
	}

	public int getTextX()
	{
		return this.textX;
	}

	public int getTextY()
	{
		return this.textY;
	}

	public int getTextColour()
	{
		return this.textColour;
	}
}
