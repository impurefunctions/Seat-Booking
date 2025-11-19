package Models;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.IOException;

/**
 * A utility class for checking the application's network status.
 */
public class AppStatus {
    Context context;

    /**
     * Constructs a new AppStatus object.
     *
     * @param context The application context.
     */
    public AppStatus(Context context) {
        this.context = context;
    }

    /**
     * Checks if the device has an active internet connection.
     *
     * @return True if the device is online, false otherwise.
     */
    public boolean isOnline() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }
}
