package githubdetail.com.githubdetails.Model;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by mohamed Rafeek on 26/1/2019.
 */

public class Util {

//Check the internet Connection

    public static boolean isInternetAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork == null) return false;

        switch (activeNetwork.getType()) {
            case ConnectivityManager.TYPE_WIFI:
                if ((activeNetwork.getState() == NetworkInfo.State.CONNECTED ||
                        activeNetwork.getState() == NetworkInfo.State.CONNECTING) &&
                        isInternet())
                    return true;
                break;
            case ConnectivityManager.TYPE_MOBILE:
                if ((activeNetwork.getState() == NetworkInfo.State.CONNECTED ||
                        activeNetwork.getState() == NetworkInfo.State.CONNECTING) &&
                        isInternet())
                    return true;
                break;
            default:
                return false;
        }
        return false;
    }


    private static boolean isInternet() {

    Runtime runtime = Runtime.getRuntime();
    try {
        Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
        int exitValue = ipProcess.waitFor();
//            Debug.i(exitValue + "");

        return (exitValue == 0);
    } catch (IOException | InterruptedException e) {
        e.printStackTrace();
    }

    return false;
}


    public static String getDate(String string)
    {
        String v;
        try
        {
            // String string = "2016-12-02T00:00:00.000Z";
            // String defaultTimezone = TimeZone.getDefault().getID();
           /* SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.ENGLISH);
            df.setTimeZone(TimeZone.getDefault());*/
            SimpleDateFormat df = new SimpleDateFormat(
                    "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
            df.setTimeZone(TimeZone.getTimeZone("UTC"));

            Date date = df.parse(string.replaceAll("Z$", "+0000"));
            v=(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss")).format(date);
        }
        catch (Exception e)
        {
            v = "00-00-0000 00:00";
        }
        return v;
    }


}

