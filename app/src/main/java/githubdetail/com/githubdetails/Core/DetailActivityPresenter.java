package githubdetail.com.githubdetails.Core;

/**
 * Created by mohamed Rafeek on 27/1/2019.
 */

public class DetailActivityPresenter implements GetGithubDetail.onDateUpdateListner,GetGithubDetail.DetailsActPresenter {

    private GetGithubDetail.DetailsView DetailsView;

    DetailActivityCore detailActivityCore;
    private GetGithubDetail.DetailsView detailsView;

    public DetailActivityPresenter(GetGithubDetail.DetailsView DetailsView){
        detailsView = DetailsView;


        detailActivityCore=new DetailActivityCore(this);
    }



    @Override
    public void DateUpdate(String date) {
        detailsView.onGetDateViewListener(date);
    }

    @Override
    public void getDateAvaliablity(String date) {
        detailActivityCore.changeToDateForamt(date);
    }
}
