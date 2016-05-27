package mapwriter.api;

import java.awt.Point;

public interface IMapMode
{
	public void setScreenRes(int dw, int dh, int sw, int sh, double scaling);

	public void setScreenRes();

	public void updateMargin();

	public Point screenXYtoBlockXZ(IMapView mapView, int sx, int sy);

	public Point.Double blockXZtoScreenXY(IMapView mapView, double bX, double bZ);

	public Point.Double getClampedScreenXY(IMapView mapView, double bX, double bZ);

	public boolean posWithin(int mouseX, int mouseY);

	public Point.Double getNewPosPoint(double mouseX, double mouseY);

	public int getXTranslation();

	public int getYTranslation();

	public int getX();

	public int getY();

	public int getW();

	public int getH();

	public int getWPixels();

	public int getHPixels();

	public IMapModeConfig getConfig();

	public int getTextX();

	public int getTextY();

	public int getTextColour();
}
