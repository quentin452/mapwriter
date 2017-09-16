package mapwriter.api;

import java.util.List;

public interface IMapView
{
	public int adjustZoomLevel(int n);

	public int getDimension();

	public double getDimensionScaling(int playerDimension);

	public double getHeight();

	public double getMaxX();

	public double getMaxZ();

	public double getMinX();

	public double getMinZ();

	public int getPixelsPerBlock();

	public int getRegionZoomLevel();

	public boolean getUndergroundMode();

	public double getWidth();

	public double getX();

	public double getZ();

	public int getZoomLevel();

	public boolean isBlockWithinView(double bX, double bZ, boolean circular);

	public void nextDimension(List<Integer> dimensionList, int n);

	public void panView(double relX, double relZ);

	public void setDimension(int dimension);

	public void setDimensionAndAdjustZoom(int dimension);

	public void setMapWH(IMapMode mapMode);

	public void setMapWH(int w, int h);

	public void setTextureSize(int n);

	public void setUndergroundMode(boolean enabled);

	public void setViewCentre(double vX, double vZ);

	public void setViewCentreScaled(double vX, double vZ, int playerDimension);

	public int setZoomLevel(int zoomLevel);

	// bX and bZ are the coordinates of the block the zoom is centred on.
	// The relative position of the block in the view will remain the same
	// as before the zoom.
	public void zoomToPoint(int newZoomLevel, double bX, double bZ);
}
