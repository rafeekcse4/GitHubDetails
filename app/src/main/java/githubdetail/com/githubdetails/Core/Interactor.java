package githubdetail.com.githubdetails.Core;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.Window;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import githubdetail.com.githubdetails.Model.Example;
import githubdetail.com.githubdetails.Model.GetAllDetails;
import githubdetail.com.githubdetails.Model.Item;
import githubdetail.com.githubdetails.Model.Util;
import githubdetail.com.githubdetails.R;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mohamed Rafeek on 26/1/2019.
 */

public class Interactor implements GetGithubDetail.Interactor {

    private GetGithubDetail.onGetDataListener onGetDataListener;
    private  Dialog dialog;

    Interactor(GetGithubDetail.onGetDataListener onGetDataListener){

        this.onGetDataListener = onGetDataListener;
    }

    @Override
    public void initNetworkCall(Context context, String Url) {
        Gson gson=new GsonBuilder().setLenient().create();
        Retrofit retrofit=new Retrofit.Builder().baseUrl(Url).addConverterFactory(GsonConverterFactory.create(gson)).build();
        GetAllDetails  getAllDetails=retrofit.create(GetAllDetails.class);
        final retrofit2.Call<Example> listCall=getAllDetails.getExample();
        showDialog(context);

        listCall.enqueue(new retrofit2.Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                Log.d("bucky on respose",response.message());
                List<Item> items=response.body().getItems();
                hideDialog();
                onGetDataListener.onSuccess("success",items);
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Log.i("bucky on respose",t.getMessage());
                hideDialog();
                onGetDataListener.onFailure(t.getMessage());
            }
        });
    }

    //To show the loader during the service call
    private void showDialog(Context context){
        dialog =new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_loading_dialog);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

    }

    private void hideDialog(){
        if (dialog!=null){
            dialog.hide();
        }

    }

    @Override
    public void initNetworkStatus(Context context) {
        showDialog(context);
        boolean status= Util.isInternetAvailable(context);
        hideDialog();
        onGetDataListener.onInternetStatus(status);
    }
}
