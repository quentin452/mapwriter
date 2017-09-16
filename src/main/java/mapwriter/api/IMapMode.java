package mapwriter.api;

import java.awt.Point;

public interface IMapMode
{
	public Point.Double blockXZtoScreenXY(IMapView mapView, double bX, double bZ);

	public Point.Double getClampedScreenXY(IMapView mapView, double bX, double bZ);

	public IMapModeConfig getConfig();

	public int getH();

	public int getHPixels();

	public Point.Double getNewPosPoint(double mouseX, double mouseY);

	public int getTextColour();

	public int getTextX();

	public int getTextY();

	public int getW();

	public int getWPixels();

	public int getX();

	public int getXTranslation();

	public int getY();

	public int getYTranslation();

	public boolean posWithin(int mouseX, int mouseY);

	public Point screenXYtoBlockXZ(IMapView mapView, int sx, int sy);

	public void setScreenRes();

	public void setScreenRes(int dw, int dh, int sw, int sh, double scaling);

	public void updateMargin();
}
