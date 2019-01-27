package githubdetail.com.githubdetails.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.util.Linkify;
import android.util.Log;
import android.widget.TextView;

import githubdetail.com.githubdetails.Core.DetailActivityPresenter;
import githubdetail.com.githubdetails.Core.GetGithubDetail;
import githubdetail.com.githubdetails.Model.Item;
import githubdetail.com.githubdetails.Model.ItemParcelable;
import githubdetail.com.githubdetails.R;

public class DetailsActivity extends AppCompatActivity implements  GetGithubDetail.DetailsView {

    //Instanitate the text view
    TextView txt_projectname,txt_ownername,txt_owner_profile,txt_desc,txt_project_url,txt_createdate;

    Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        //update the view id
        txt_createdate=findViewById(R.id.id_txt_createdate_right);
        txt_desc=findViewById(R.id.id_txt_desc_right);
        txt_ownername=findViewById(R.id.id_txt_ownername_right);
        txt_project_url=findViewById(R.id.id_txt_project_url_right);
        txt_projectname=findViewById(R.id.id_txt_projectname_right);

        txt_owner_profile=findViewById(R.id.id_txt_owner_profile_right);


        Intent intent = getIntent();
        ItemParcelable itemdata = intent.getParcelableExtra("data");

        item = itemdata.getItem();
        Log.i("bucky second Activity", item + "---" + item.getOwner().getLogin());

        DetailActivityPresenter activityPresenter=new DetailActivityPresenter(this);
        activityPresenter.getDateAvaliablity(item.getCreatedAt());



    }





    @Override
    public void onGetDateViewListener(String date) {
        //Set the date
        txt_createdate.setText(date);

        //set the owner name
        txt_ownername.setText(item.getOwner().getLogin());

        //set the owner profile with link
        txt_owner_profile.setText(item.getOwner().getHtmlUrl());
        txt_owner_profile.setLinkTextColor(ContextCompat.getColor(this, R.color.brand_linkcolor));
        Linkify.addLinks(txt_owner_profile, Linkify.WEB_URLS | Linkify.PHONE_NUMBERS);
        Linkify.addLinks(txt_owner_profile, Linkify.ALL);


        //set the project name as full
        txt_projectname.setText(item.getFullName());


        //set the description
        txt_desc.setText( String.valueOf(item.getDescription()));


        //set the project details with link
        txt_project_url.setText(item.getHtmlUrl());
        txt_project_url.setLinkTextColor(ContextCompat.getColor(this, R.color.brand_linkcolor));
        Linkify.addLinks(txt_project_url, Linkify.WEB_URLS | Linkify.PHONE_NUMBERS);
        Linkify.addLinks(txt_project_url, Linkify.ALL);





    }
}
