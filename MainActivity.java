package com.example.dineshb.customadapterwithasyncjsonparser;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button mListviewButton;
    private ListView mListView;
    private TextView mcustom_name_textView;
    private List<String> list;
    private ArrayAdapter customarrayAdapter;
    private String url="http://api.androidhive.info/contacts/";
    private  String TAG="Exception From MainActivity....";

    private JSONObject jsonResponse;
    private JSONArray jsonMainNode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mListviewButton=(Button)findViewById(R.id.mListViewButton);
        mListView=(ListView)findViewById(R.id.mListView);
        list= new ArrayList<String>();
        list.clear();
        list.add("Dinesh");
        list.add("Rajendra");
        list.add("Bhosale");
        customarrayAdapter=new CustomArrayAdapter(this,R.layout.custom_text_view,list);
        mListView.setAdapter(customarrayAdapter);

        mListviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncJSONParser asyncJSONParser=new AsyncJSONParser();
                asyncJSONParser.url=url;
                asyncJSONParser.execute();
            }
        });

    }




    public void showServerNameList(String json){
                 list.clear();
                 list.add("Dinesh");
                 list.add("Rajendra");
                 list.add("Bhosale");

         try{
             jsonResponse=new JSONObject(json);
             Log.d("Response String ",json);
             jsonMainNode=jsonResponse.optJSONArray("contacts");

                for (int i=0;i<jsonMainNode.length();i++){
                    JSONObject childNode=jsonMainNode.getJSONObject(i);
                    String person=childNode.optString("name");
                    list.add(person);
                    customarrayAdapter.notifyDataSetChanged();
                }


         }catch (Exception e){Log.e(TAG,"And Exception is  "+e.getMessage() );}


    }

    protected class AsyncJSONParser extends AsyncTask<String,String,String>{
        ProgressDialog progressDialog;
        String url;
        @Override
        protected void onPreExecute() {
            progressDialog=new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Loading data...");
            progressDialog.show();
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String json) {

            progressDialog.dismiss();
            //super.onPostExecute(jstring);
            showServerNameList(json);

        }

        @Override
        protected String doInBackground(String... params) {

            JsonParser jsonParser=new JsonParser();

            String json=jsonParser.getJSON(this.url);
            return json;
        }
    }


}
