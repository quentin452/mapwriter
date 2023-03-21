package mapwriter.forge;

import net.minecraftforge.common.config.Configuration;

import java.io.File;
import java.util.List;

public class MwConfig extends Configuration {

    public MwConfig(File file) {
        super(file, true);
    }

    public boolean getOrSetBoolean(String category, String key, boolean defaultValue) {
        return this.get(category, key, defaultValue ? 1 : 0).getInt() != 0;
    }

    public void setBoolean(String category, String key, boolean value) {
        this.get(category, key, value).set(value ? 1 : 0);
    }

    public int getOrSetInt(String category, String key, int defaultValue, int minValue, int maxValue) {
        int value = this.get(category, key, defaultValue).getInt();
        return Math.min(Math.max(minValue, value), maxValue);
    }

    public void setInt(String category, String key, int value) {
        this.get(category, key, value).set(value);
    }

    public void getIntList(String category, String key, List<Integer> list) {
        // convert List of integers to integer array to pass as default value
        int size = list.size();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = list.get(i);
        }

        // get integer array from config file
        int[] arrayFromConfig = null;
        try {
            arrayFromConfig = this.get(category, key, array).getIntList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (arrayFromConfig != null) {
            array = arrayFromConfig;
        }

        // convert integer array back to List of integers
        list.clear();
        for (int i : array) {
            list.add(i);
        }
    }

    public void setIntList(String category, String key, List<Integer> list) {
        // convert List of integers to integer array
        int size = list.size();
        String[] array = new String[size];
        for (int i = 0; i < size; i++) {
            array[i] = list.get(i).toString();
        }
        // write integer array to config file
        try {
            this.get(category, key, array).set(array);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
