package githubdetail.com.githubdetails.Core;

import githubdetail.com.githubdetails.Model.Util;

/**
 * Created by mohamed Rafeek on 27/1/2019.
 */

public class DetailActivityCore implements GetGithubDetail.DetailInteractor {

    private GetGithubDetail.onDateUpdateListner onDateUpdateListner;

    public DetailActivityCore(GetGithubDetail.onDateUpdateListner onDateUpdateListner ) {

        this.onDateUpdateListner = onDateUpdateListner;
    }

    @Override
    public void changeToDateForamt(String date) {

        String updateddate= Util.getFormatedDate(date);

        onDateUpdateListner.DateUpdate(updateddate);

    }
}
