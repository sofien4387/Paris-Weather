package com.mtp.test.parisweather.utils;

import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * This class of file utils used to centralize any method
 * which have relation with local storage of sd card using.
 *
 * E.g:
 * - Save under local directory.
 *
 * @author Rahmouni Sofien
 */
public class FileUtils {

    private static final String TAG = "FileUtils";

    /** CACHE PARIS WEATHER APPLICATION*/
    public static final String CACHE_PARIS_WEATHER_APPLICATION = "ParisWeather";

    /**
     * Save image icon in the sd card.
     *
     * @param iconName icon name.
     * @param bitmap   current bitmap.
     * @return state of saving process: <code>true</code> means the saving of image has done with
     * success.
     */
    public static boolean saveImage(String iconName, Bitmap bitmap) {

        // Create paris weather cache file.
        if(createDirectoryApplication()){
            Log.e("", "Error occurred during creation application directory");
        }
        String path =  getCacheDirectoryApplication().getPath() + File.separator + iconName + ParisWeatherConstants.ICON_EXTENSION;
        // Init image stream file
        File file = new File(path);

        try {

            boolean resultCreation = file.createNewFile();

            if(!resultCreation){
                Log.e(TAG, "Error occurred during creation file:" + file.getName());
            }

            FileOutputStream outputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
            outputStream.close();

        } catch (IOException e) {
            Log.e(TAG, "Error occurred during creation application directory:" + e.getMessage());
            return false;
        }

        return true;
    }

    /**
     * Check given file is already exist or otherwise.
     *
     * @param path path of given file.
     * @return <code>true</code> means is exist or otherwise.
     */
    public static boolean isExistFile(String path) {
        return new File(path).exists();
    }

    /**
     * Create Directory Application
     *
     * @return the created directory.
     */
    private static boolean createDirectoryApplication() {

        File directory = getCacheDirectoryApplication();

        if (!directory.exists()) {
            if (!directory.mkdirs()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Getter cache directory application.
     *
     * @return instance of directory application.
     */
    public static File getCacheDirectoryApplication() {
        return new File(Environment.getExternalStorageDirectory() + File.separator + CACHE_PARIS_WEATHER_APPLICATION);
    }

    /**
     * Delete directories of cache application recursively.
     */
    public static void resetCacheDirectoryApplication() {

        File f = getCacheDirectoryApplication();

        if (f.isDirectory()) {
            for (File c : f.listFiles()) {
                if(!c.delete()){
                    Log.e(TAG, "Error occurred during deleting file:" + c.getName());
                }
            }
        }
    }
}
