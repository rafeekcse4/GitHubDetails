package githubdetail.com.githubdetails.Model;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.IOException;
import java.text.ParseException;
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




    public static String getFormatedDate(String dateString) {
        if (!dateString.equals("")){
            {
                String formattedDate = null;
                String date_after = formateDateFromstring("yyyy-MM-dd'T'HH:mm:ss'Z'", "MMM dd, yyyy HH:mm:ss a", dateString);

//        System.out.println("date_after="+date_after);

                SimpleDateFormat df = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss", Locale.ENGLISH);
                SimpleDateFormat df1 = new SimpleDateFormat("dd-MMM-yy hh:mm a", Locale.ENGLISH);

                df.setTimeZone(TimeZone.getTimeZone("UTC"));
                Date date;
                try {
                    date = df.parse(date_after);
                    df.setTimeZone(TimeZone.getDefault());
                    formattedDate= df1.format(date);
//            System.out.println("formattedDate="+formattedDate);
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return formattedDate;
            }
        }else
            return "00-00-0000 00:00";
    }


    private static String formateDateFromstring(String inputFormat, String outputFormat, String inputDate){

        Date parsed;
        String outputDate = "";

        SimpleDateFormat df_input = new SimpleDateFormat(inputFormat, java.util.Locale.getDefault());
        SimpleDateFormat df_output = new SimpleDateFormat(outputFormat, java.util.Locale.getDefault());

        try {
            parsed = df_input.parse(inputDate);
            outputDate = df_output.format(parsed);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return outputDate;

    }


}

