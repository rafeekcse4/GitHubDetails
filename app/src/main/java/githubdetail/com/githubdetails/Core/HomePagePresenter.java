package githubdetail.com.githubdetails.Core;

import android.content.Context;

import java.util.List;

import githubdetail.com.githubdetails.Model.Item;

/**
 * Created by mohamed Rafeek on 26/1/2019.
 */

public class HomePagePresenter implements GetGithubDetail.Presenter,GetGithubDetail.onGetDataListener {

    private GetGithubDetail.View view;
    private Interactor interactor;

    public HomePagePresenter(GetGithubDetail.View view){
        this.view=view;
        interactor=new Interactor(this);
    }




    @Override
    public void getDataFromURL(String Url, Context context) {
        interactor.initNetworkCall(context, Url);
    }

    @Override
    public void getInternetAvailblity(Context context) {
        interactor.initNetworkStatus(context);

    }

    @Override
    public void onSuccess(String message, List<Item> list) {
            view.onGetDateSuccess(message,list);
    }

    @Override
    public void onFailure(String message) {
        view.onGetDataFailure(message);
    }

    @Override
    public void onInternetStatus(boolean status) {
        view.onGetInternetStatus(status);
    }


}
