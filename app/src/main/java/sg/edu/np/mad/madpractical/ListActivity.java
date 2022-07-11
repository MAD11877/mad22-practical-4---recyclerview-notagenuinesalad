package sg.edu.np.mad.madpractical;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListActivity extends AppCompatActivity {

    public static ArrayList<User> userList = initRandomUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        UserAdapter mAdapter = new UserAdapter(userList);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(ListActivity.this);

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }

    public static AlertDialog createAlert(Integer position,Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Profile");
        builder.setMessage(userList.get(position).name);
        builder.setCancelable(true);
        builder.setNegativeButton("CLOSE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
        });
        builder.setPositiveButton("VIEW", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent act = new Intent(context,MainActivity.class);
                act.putExtra("userPosition", position);
                context.startActivity(act);
            }
        });
        AlertDialog alert = builder.create();
        return alert;

    }
    public static ArrayList<User> initRandomUser(){
        ArrayList<User> userList = new ArrayList<User>();

        while (userList.size() <20) {
            Random rand = new Random();
            String randName = "Name" + Integer.toString(rand.nextInt());
            String randDesc = "Description "+ Integer.toString(Math.abs(rand.nextInt()));
            Integer randId;
            Boolean randFollowed = rand.nextBoolean();

            while (true){
                Boolean repeatId = false;
                randId = Math.abs(rand.nextInt());
                for(User i : userList){
                    if(i.id == randId){
                        repeatId = true;
                    }
                }
                if(repeatId == false){
                    break;
                }
            }

            User user = new User(randName,randDesc,randId,randFollowed);
            userList.add(user);
        }
        return userList;
    }

}