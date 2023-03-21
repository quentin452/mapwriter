package mapwriter.api;

import mapwriter.map.MapView;
import mapwriter.map.mapmode.MapMode;

import java.util.ArrayList;

public interface IMwDataProvider {
    ArrayList<IMwChunkOverlay> getChunksOverlay(int dim, double centerX, double centerZ, double minX, double minZ, double maxX, double maxZ);

    //Returns what should be added to the status bar by the addon.
    String getStatusString(int dim, int bX, int bY, int bZ);

    //Call back for middle click.
    void onMiddleClick(int dim, int bX, int bZ, MapView mapview);

    //Callback for dimension change on the map
    void onDimensionChanged(int dimension, MapView mapview);

    void onMapCenterChanged(double vX, double vZ, MapView mapview);

    void onZoomChanged(int level, MapView mapview);

    void onOverlayActivated(MapView mapview);

    void onOverlayDeactivated(MapView mapview);

    void onDraw(MapView mapview, MapMode mapmode);

    boolean onMouseInput(MapView mapview, MapMode mapmode);
}
