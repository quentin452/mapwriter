package mapwriter;

import mapwriter.forge.MwForge;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.chunk.Chunk;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class MwUtil {

    public final static Pattern patternInvalidChars = Pattern.compile("[^\\p{IsAlphabetic}\\p{Digit}_]");

    public static void logInfo(String s, Object... args) {
        MwForge.logger.info(String.format(s, args));
    }

    public static void logWarning(String s, Object... args) {
        MwForge.logger.warn(String.format(s, args));
    }

    public static void logError(String s, Object... args) {
        MwForge.logger.error(String.format(s, args));
    }

    public static void debug(String s, Object... args) {
        MwForge.logger.debug(String.format(s, args));
    }

    public static void log(String s, Object... args) {
        logInfo(String.format(s, args));
    }

    public static String mungeString(String s) {
        s = s.replace('.', '_');
        s = s.replace('-', '_');
        s = s.replace(' ', '_');
        s = s.replace('/', '_');
        s = s.replace('\\', '_');
        return patternInvalidChars.matcher(s).replaceAll("");
    }

    public static void printBoth(String msg) {
        EntityClientPlayerMP thePlayer = Minecraft.getMinecraft().thePlayer;
        if (thePlayer != null) {
            thePlayer.addChatMessage(new ChatComponentText(msg));
        }
        MwUtil.log("%s", msg);
    }

    public static IntBuffer allocateDirectIntBuffer(int size) {
        return ByteBuffer.allocateDirect(size * 4).order(ByteOrder.nativeOrder()).asIntBuffer();
    }

    public static String getCurrentDateString() {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmm");
        return dateFormat.format(new Date());
    }

    public static int distToChunkSq(int x, int z, Chunk chunk) {
        int dx = (chunk.xPosition << 4) + 8 - x;
        int dz = (chunk.zPosition << 4) + 8 - z;
        return (dx * dx) + (dz * dz);
    }
}
