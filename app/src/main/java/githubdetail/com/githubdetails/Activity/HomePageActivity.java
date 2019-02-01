package githubdetail.com.githubdetails.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import githubdetail.com.githubdetails.Adapter.GithubAdapter;
import githubdetail.com.githubdetails.Core.GetGithubDetail;
import githubdetail.com.githubdetails.Core.HomePagePresenter;
import githubdetail.com.githubdetails.Model.Item;
import githubdetail.com.githubdetails.Model.ItemParcelable;
import githubdetail.com.githubdetails.R;

public class HomePageActivity extends AppCompatActivity implements GetGithubDetail.View, GithubAdapter.ClickLister {

    //Instantiate the Text view and  recycler view
   private TextView txt_network_status_message;
    private RecyclerView recyclerView;

    //creating object the presenter
   private HomePagePresenter homePagePresenter;


    //instantiate the adapter
    private GithubAdapter githubAdapter;
    //Instantiate the layout manager
    private RecyclerView.LayoutManager mLayoutmanager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set the view for text view and recycler view
        txt_network_status_message = findViewById(R.id.id_txt_errormessage);
        recyclerView = findViewById(R.id.id_recyclerview);

        //create the object for layout manager
        mLayoutmanager=new LinearLayoutManager(this);

        //create the object for presenter class
        homePagePresenter = new HomePagePresenter(this);

        //verify intenet avaliablity
        homePagePresenter.getInternetAvailblity(this);
    }

    @Override
    public void onGetDateSuccess(String message, List<Item> list) {

        //set the adapter with list view
        githubAdapter=new GithubAdapter(this,list);

        //visible recycler view
        recyclerView.setVisibility(View.VISIBLE);
        recyclerView.setLayoutManager(mLayoutmanager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(githubAdapter);

        //set the item click listener for recycler view
        githubAdapter.setClickLister(this);
    }

    @Override
    public void onGetDataFailure(String message) {

        recyclerView.setVisibility(View.GONE);
        txt_network_status_message.setVisibility(View.VISIBLE);

        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onGetInternetStatus(boolean status) {
        if (status) {
            txt_network_status_message.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);


            homePagePresenter.getDataFromURL("https://api.github.com/", this);
        } else {

            txt_network_status_message.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);

        }

    }

    @Override
    public void itemClick(View view, int position, Item item) {
        //Parcel the item object and move to another acitivity
        ItemParcelable itemParcelable=new ItemParcelable(item);
        Intent intent=new Intent(this,DetailsActivity.class);
        intent.putExtra("data",itemParcelable);
        startActivity(intent);

    }
}
