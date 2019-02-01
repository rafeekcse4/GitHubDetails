package githubdetail.com.githubdetails;

import android.content.Context;

import org.junit.Test;
import org.mockito.Mock;

import githubdetail.com.githubdetails.Model.Util;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UtilTest {

    @Mock
    Context context;



    @Test
    public void verifyDateformat() {
        String actual = Util.getFormatedDate("2017-08-21T02:01:13Z");
        // expected value is 100
        String expected = "21-Aug-17 07:31 AM";
        // use this method because float is not precise
        assertEquals( expected, actual);

    }

    @Test
    public void verifyDateformatNull() {
        String actual = Util.getFormatedDate("");
        // expected value is 100
        String expected = "00-00-0000 00:00";
        // use this method because float is not precise
        assertEquals( expected, actual);

    }



}