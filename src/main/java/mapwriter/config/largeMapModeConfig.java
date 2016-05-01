package mapwriter.config;

import mapwriter.gui.ModGuiConfig.ModBooleanEntry;
import mapwriter.gui.ModGuiConfigHUD.MapPosConfigEntry;

public class largeMapModeConfig extends MapModeConfig
{
	public largeMapModeConfig(String configCategory)
	{
		super(configCategory);
	}

	@Override
	public void loadConfig()
	{
		super.loadConfig();
		this.enabled = ConfigurationHandler.configuration.getBoolean(
				"enabled",
				this.configCategory,
				this.enabledDef,
				"",
				"mw.config.map.enabled");
		this.rotate = ConfigurationHandler.configuration.getBoolean(
				"rotate",
				this.configCategory,
				this.rotateDef,
				"",
				"mw.config.map.rotate");
		this.circular = ConfigurationHandler.configuration.getBoolean(
				"circular",
				this.configCategory,
				this.circularDef,
				"",
				"mw.config.map.circular");
		this.coordsMode = ConfigurationHandler.configuration.getString(
				"coordsMode",
				this.configCategory,
				this.coordsModeDef,
				"",
				coordsModeStringArray,
				"mw.config.map.coordsMode");
		this.borderMode = ConfigurationHandler.configuration.getBoolean(
				"borderMode",
				this.configCategory,
				this.borderModeDef,
				"",
				"mw.config.map.borderMode");
		this.biomeMode = ConfigurationHandler.configuration.getString(
				"biomeMode",
				this.configCategory,
				this.biomeModeDef,
				"",
				coordsModeStringArray,
				"mw.config.map.biomeMode");
	}

	@Override
	public void setDefaults()
	{
		this.rotateDef = true;
		this.circularDef = true;
		this.coordsModeDef = coordsModeStringArray[1];
		this.borderModeDef = true;
		this.heightPercentDef = -1;
		this.xPosDef = 50;
		this.yPosDef = 5;
		this.heightPercentDef = 88;
		this.widthPercentDef = 91;

		ConfigurationHandler.configuration
				.get(this.configCategory, "enabled", this.enabled)
				.setRequiresWorldRestart(true);
		ConfigurationHandler.configuration
				.get(this.configCategory, "rotate", this.rotate)
				.setConfigEntryClass(ModBooleanEntry.class);
		ConfigurationHandler.configuration
				.getCategory(this.mapPosCategory)
				.setConfigEntryClass(MapPosConfigEntry.class)
				.setLanguageKey("mw.config.map.ctgy.position")
				.setShowInGui(true);
	}
}
