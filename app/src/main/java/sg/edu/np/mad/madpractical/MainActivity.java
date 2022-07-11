package sg.edu.np.mad.madpractical;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent receivingEnd = getIntent();
        Integer position = receivingEnd.getIntExtra("userPosition",0);
        User user = ListActivity.userList.get(position);

        TextView NameTxt = findViewById(R.id.nameText);
        TextView DescTxt = findViewById(R.id.descText);

        NameTxt.setText(String.format("%s", user.name));
        DescTxt.setText(String.format("%s", user.description));

        Button btn = findViewById(R.id.fButton);

        set(user,btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String toastN;
                if(user.followed == false){
                    usr.followed = true;
                    toastN = "Followed";
                }
                else{
                    user.followed = false;
                    toastN = "Unfollowed";
                }
                set(user,btn);

                Toast Noti = Toast.makeText(MainActivity.this,toastN,Toast.LENGTH_SHORT);
                Noti.show();
            }
        });

        Button messageButton = findViewById(R.id.mButton);
        messageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newAct = new Intent(MainActivity.this,MessageGroup.class);
                startActivity(newAct);
            }
        });

    }
    public void setF(User user,Button btn){
        TextView txt = btn;
        if(user.followed == false){
            txt.setText("Follow");
        }
        else{
            txt.setText("Unfollow");
        }
    }

    public User initUser(){
        User user = new User("username","desc",1,false);
        return user;
    }
}