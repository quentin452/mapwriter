package mapwriter.forge;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Optional;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import mapwriter.Mw;
import mapwriter.Tags;
import modwarriors.notenoughkeys.api.Api;
import modwarriors.notenoughkeys.api.KeyBindingPressedEvent;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;

public class MwKeyHandler {
    private static final String notEnoughKeysModId = "notenoughkeys";
    public static KeyBinding keyMapGui = new KeyBinding("key.mw_open_gui", Keyboard.KEY_M, Tags.MODNAME);
    public static KeyBinding keyNewMarker = new KeyBinding("key.mw_new_marker", Keyboard.KEY_INSERT, Tags.MODNAME);
    public static KeyBinding keyMapMode = new KeyBinding("key.mw_next_map_mode", Keyboard.KEY_N, Tags.MODNAME);
    public static KeyBinding keyNextGroup = new KeyBinding("key.mw_next_marker_group", Keyboard.KEY_COMMA, Tags.MODNAME);
    public static KeyBinding keyTeleport = new KeyBinding("key.mw_teleport", Keyboard.KEY_PERIOD, Tags.MODNAME);
    public static KeyBinding keyZoomIn = new KeyBinding("key.mw_zoom_in", Keyboard.KEY_PRIOR, Tags.MODNAME);
    public static KeyBinding keyZoomOut = new KeyBinding("key.mw_zoom_out", Keyboard.KEY_NEXT, Tags.MODNAME);
    public static KeyBinding keyUndergroundMode = new KeyBinding("key.mw_underground_mode", Keyboard.KEY_U, Tags.MODNAME);

    public final KeyBinding[] keys =
            {
                    keyMapGui,
                    keyNewMarker,
                    keyMapMode,
                    keyNextGroup,
                    keyTeleport,
                    keyZoomIn,
                    keyZoomOut,
                    keyUndergroundMode
            };

    public MwKeyHandler() {
        ArrayList<String> listKeyDescs = new ArrayList<>();
        // Register bindings
        for (KeyBinding key : this.keys) {
            if (key != null) {
                ClientRegistry.registerKeyBinding(key);
                listKeyDescs.add(key.getKeyDescription());
            }
        }

        if (Loader.isModLoaded(notEnoughKeysModId)) {
            Api.registerMod(Tags.MODNAME, listKeyDescs.toArray(new String[0]));
        }
    }

    @SubscribeEvent
    public void keyEvent(InputEvent.KeyInputEvent event) {
        if (!Loader.isModLoaded(notEnoughKeysModId)) {
            this.checkKeys();
        }
    }

    @Optional.Method(modid = notEnoughKeysModId)
    @SubscribeEvent
    public void keyEventSpecial(KeyBindingPressedEvent event) {
        if (event.isKeyBindingPressed) {
            Mw.instance.onKeyDown(event.keyBinding);
        }
    }

    private void checkKeys() {
        for (KeyBinding key : keys) {
            if (key != null && key.isPressed()) {
                Mw.instance.onKeyDown(key);
            }
        }
    }
}
