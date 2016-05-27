package mapwriter.api;

import java.util.ArrayList;

public interface IMwDataProvider
{
	public ArrayList<IMwChunkOverlay> getChunksOverlay(int dim, double centerX, double centerZ,
			double minX, double minZ, double maxX, double maxZ);

	// Returns what should be added to the status bar by the addon.
	public String getStatusString(int dim, int bX, int bY, int bZ);

	// Call back for middle click.
	public void onMiddleClick(int dim, int bX, int bZ, IMapView mapview);

	// Callback for dimension change on the map
	public void onDimensionChanged(int dimension, IMapView mapview);

	public void onMapCenterChanged(double vX, double vZ, IMapView mapview);

	public void onZoomChanged(int level, IMapView mapview);

	public void onOverlayActivated(IMapView mapview);

	public void onOverlayDeactivated(IMapView mapview);

	public void onDraw(IMapView mapview, IMapMode mapmode);

	public boolean onMouseInput(IMapView mapview, IMapMode mapmode);

	// return null if nothing should be drawn on fullscreen map
	public ILabelInfo getLabelInfo(int mouseX, int mouseY);
}
