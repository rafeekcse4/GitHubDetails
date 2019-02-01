package githubdetail.com.githubdetails;


import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import githubdetail.com.githubdetails.Model.Example;
import githubdetail.com.githubdetails.Model.GetAllDetails;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.assertEquals;


public class HomePagePresenterTest {

    private MockWebServer server ;

    @Before
    public void setup(){
        server= new MockWebServer();
        try {
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void http200Async() throws InterruptedException, IOException {

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(server.url("/"))

                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
            .client(new OkHttpClient.Builder().build())
                .build();
        GetAllDetails example = retrofit.create(GetAllDetails.class);

        server.enqueue(new MockResponse().setBody(getJson()).setResponseCode(200)
                .throttleBody(1024, 1, TimeUnit.SECONDS)); // Simulate SocketTimeout);
        final AtomicReference<Response<Example>> responseRef = new AtomicReference<>();

        example.getExample().enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                responseRef.set(response);
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
              //  t.printStackTrace();
            }
        });
        RecordedRequest request = server.takeRequest();
        String path= "/search/repositories?q=android&sort=stars&order=asc";

        assertEquals(path, request.getPath());

        server.shutdown();
    }



    private String getJson(){
        JsonParser jsonParser=new JsonParser();
        try {
            Object o=jsonParser.parse(new FileReader("D:/project/WorkingProject/GitHubDetails/app/src/respose.txt"));
            JsonObject jsonObject= (JsonObject) o;
            return String.valueOf(jsonObject);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

}
