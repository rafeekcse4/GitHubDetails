package githubdetail.com.githubdetails.Model;


import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by mohamed Rafeek on 26/1/2019.
 */

public interface GetAllDetails {
     @GET("search/repositories?q=android&sort=stars&order=asc")
    //Call<List<Item>> getItem();
    Call<Example>   getExample();
}
