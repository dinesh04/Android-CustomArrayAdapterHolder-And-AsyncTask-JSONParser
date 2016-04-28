package com.example.dineshb.customadapterwithasyncjsonparser;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by dineshb on 4/28/2016.
 */
public class JsonParser {
    private InputStream stream;
    private HttpURLConnection connection;
    private URL url;
    private String json="";
    private BufferedReader reader;
    private StringBuffer buffer;
    private  String TAG="Exception from JsonParser class ......";

    public String getJSON(String urlFromParent){

        try{

            this.url=new URL(urlFromParent);
            connection=(HttpURLConnection) this.url.openConnection();
            connection.connect();
            stream=connection.getInputStream();

            reader = new BufferedReader(new InputStreamReader(stream));
            buffer=new StringBuffer();

            String line="";
            while ((line = reader.readLine()) != null){

                buffer.append(line);
            }
        }  catch (Exception e){Log.e(TAG,"And Exception is  "+e.getMessage());}

        finally {
            if(connection!=null){
                connection.disconnect();

                try{
                    if(reader!=null){
                        reader.close();
                    }
                }catch (IOException e){Log.e(TAG,"And Exception is  "+e.getMessage());}
            }
            }


            return  buffer.toString();

    }

}
