package com.example.jsoninandroidstudio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.jsoninandroidstudio.Model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class MainActivity extends AppCompatActivity{

    private RecyclerView recyclerView;
    private Vector<User> users = new Vector<>();
    private UsersAdapter usersAdapter;
    private RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.myRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        requestQueue = Volley.newRequestQueue(this);
        parseJson();
        final Toast t = Toast.makeText(this,"refresh" , Toast.LENGTH_LONG);
        findViewById(R.id.refreshbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                users.clear();
                parseJson();
                t.show();
            }
        });
    }

    public void parseJson(){
        final Vector<User> userList = new Vector<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, "https://jsonplaceholder.ir/users", null
                ,new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                try {
                    int j = 0;
                    for(int i = 0 ; i < 100 && j < 10 ; i++){
                        if (i %10 == 0 ){
                            j++;
                            i = 0;
                        }
                        User user = new User();
                        JSONObject jsonObject = response.getJSONObject(i);
                        user.setId(jsonObject.getString("id"));
                        user.setEmail(jsonObject.getString("email"));
                        user.setUsername(jsonObject.getString("username"));
                        user.setAvatar(jsonObject.getString("avatar"));
                        userList.add(user);
                    }
                    users = userList;
                    usersAdapter = new UsersAdapter(MainActivity.this, users);
                    recyclerView.setAdapter(usersAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
                },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }
}




//package com.example.jsoninandroidstudio;
//
//import android.os.AsyncTask;
//
//import com.example.jsoninandroidstudio.Model.Address;
//import com.example.jsoninandroidstudio.Model.User;
//
//import org.json.JSONArray;
//import org.json.JSONObject;
//
//import java.io.BufferedReader;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.CountDownLatch;
//import java.util.concurrent.Semaphore;
//
//
//public class FetchData extends AsyncTask{
//    String data = "";
//    String line = "";
//    String singleParsed= "";
//    List<User> userList = new ArrayList<>();
////    @Override
//    protected Object doInBackground(Object[] objects) {
//        try {
//            URL url = new URL("https://jsonplaceholder.ir/users");
//            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//            InputStream inputStream = httpURLConnection.getInputStream();
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//
//            while(line != null){
//                line = bufferedReader.readLine();
//                data = data + line;
//            }
//
//            JSONArray jsonArray = new JSONArray(data);
//            for(int i = 0 ; i < jsonArray.length() ; i++){
//                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
//                JSONObject address = (JSONObject) ((JSONObject) jsonArray.get(i)).get("address");
//                User user = new User(jsonObject.get("id").toString(),jsonObject.get("username").toString(),
//                        jsonObject.get("email").toString(),jsonObject.get("password").toString() ,
//                        new Address(address.get("country").toString(),address.get("street").toString(),
//                                address.get("alley").toString(),address.get("number").toString()) ,jsonObject.get("phone").toString()
//                        ,jsonObject.get("website").toString(),jsonObject.get("company").toString());
//                userList.add(user);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    @Override
//    protected void onPostExecute(Object o) {
//        super.onPostExecute(o);
////        MainActivity.users.addAll(userList);
//    }
//
//}
//// singleParsed = "id :" + jsonObject.get("id") +"\n"+
////         "username :"  + jsonObject.get("username") +"\n"+
////         "email :"     + jsonObject.get("email") +"\n"+
////         "password :"  + jsonObject.get("password") + "\n"+
////         "avatar: "    + jsonObject.get("avatar") +"\n"+
////         "country: "   + address.get("country") +"\n"+
////         "city: "      + address.get("city") +"\n"+
////         "street: "    + address.get("street") +"\n"+
////         "alley: "     + address.get("alley") +"\n"+
////         "number: "    + address.get("number") +"\n" +
////         "phone: "     + jsonObject.get("phone") +"\n" +
////         "website: "   + jsonObject.get("website") +"\n" +
////         "company: "   + jsonObject.get("company") +"\n";