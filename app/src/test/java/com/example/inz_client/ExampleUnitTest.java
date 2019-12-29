package com.example.inz_client;

import android.content.Intent;

import com.example.inz_client.models.ImageResponse;
import com.example.inz_client.models.Product;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void ass(){
        ArrayList<Product> lp= new ArrayList<>();
        lp.add(new Product("pierwszy",1.0,12.0));
        lp.add(new Product("drugi",2.0,32.0));
        ImageResponse response = new ImageResponse("Lidl","2019-11-11",lp);
        ImageResponse result;
        Intent i = new Intent();
        i.putExtra("proba",response);
        result = i.getParcelableExtra("proba");
        assertEquals(response,result);
    }
}