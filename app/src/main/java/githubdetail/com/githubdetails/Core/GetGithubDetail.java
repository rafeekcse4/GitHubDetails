package githubdetail.com.githubdetails.Core;

import android.content.Context;

import java.util.List;

import githubdetail.com.githubdetails.Model.Item;

/**
 * Created by mohamed Rafeek on 26/1/2019.
 */

public interface GetGithubDetail {


    interface View{
        void onGetDateSuccess(String message, List<Item> list);
        void onGetDataFailure(String message);
        void onGetInternetStatus(boolean status);

    }

    interface Presenter{
         void getDataFromURL(String Url,Context context);
         void getInternetAvailblity(Context context);
    }

    interface Interactor{
         void initNetworkCall(Context context,String Url);
         void initNetworkStatus(Context context);
    }
    interface onGetDataListener{
         void onSuccess(String message,List<Item> list);
         void onFailure(String message);
         void onInternetStatus(boolean status);
    }

    interface onDateUpdateListner{
         void DateUpdate(String date);
    }

    interface DetailsActPresenter{
         void getDateAvaliablity(String date);
    }
    interface DetailsView{
         void onGetDateViewListener(String date);
    }

    interface DetailInteractor{
         void changeToDateForamt(String date);
    }




}
