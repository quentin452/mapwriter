package mapwriter.api;

import java.util.List;

public interface IMapView
{
	public void setViewCentre(double vX, double vZ);

	public double getX();

	public double getZ();

	public double getWidth();

	public double getHeight();

	public void panView(double relX, double relZ);

	public int setZoomLevel(int zoomLevel);

	public int adjustZoomLevel(int n);

	public int getZoomLevel();

	public int getRegionZoomLevel();

	// bX and bZ are the coordinates of the block the zoom is centred on.
	// The relative position of the block in the view will remain the same
	// as before the zoom.
	public void zoomToPoint(int newZoomLevel, double bX, double bZ);

	public void setDimension(int dimension);

	public void setDimensionAndAdjustZoom(int dimension);

	public void nextDimension(List<Integer> dimensionList, int n);

	public int getDimension();

	public void setMapWH(int w, int h);

	public void setMapWH(IMapMode mapMode);

	public double getMinX();

	public double getMaxX();

	public double getMinZ();

	public double getMaxZ();

	public double getDimensionScaling(int playerDimension);

	public void setViewCentreScaled(double vX, double vZ, int playerDimension);

	public void setTextureSize(int n);

	public int getPixelsPerBlock();

	public boolean isBlockWithinView(double bX, double bZ, boolean circular);

	public boolean getUndergroundMode();

	public void setUndergroundMode(boolean enabled);
}
