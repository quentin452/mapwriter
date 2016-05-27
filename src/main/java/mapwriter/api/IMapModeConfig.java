package mapwriter.api;

public interface IMapModeConfig
{
	public String getConfigCategory();

	public String getMapPosCategory();

	public String[] getCoordsModeStringArray();

	public boolean getEnabled();

	public boolean getRotate();

	public boolean getCircular();

	public String getCoordsMode();

	public boolean getBorderMode();

	public int getPlayerArrowSize();

	public int getMarkerSize();

	public int getTrailMarkerSize();

	public int getAlphaPercent();

	public String getBiomeMode();

	public double getXPos();

	public double getYPos();

	public double getHeightPercent();

	public double getWidthPercent();
}
