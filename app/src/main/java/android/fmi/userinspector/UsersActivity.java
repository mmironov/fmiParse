package android.fmi.userinspector;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;


public class UsersActivity extends Activity {

    private RecyclerView usersList;

    private UsersAdapter adapter;

    private List<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        usersList = (RecyclerView) findViewById(R.id.usersList);
        usersList.setLayoutManager(new LinearLayoutManager(this));

        users = new ArrayList<User>();

        User user1 = new User();
        user1.setEmail("mmironov@fmi.uni-sofia.bg");
        user1.setMessageCount(5);

        User user2 = new User();
        user2.setEmail("miroslav@fmi.uni-sofia.bg");
        user2.setMessageCount(3);

        users.add(user1);
        users.add(user2);

        adapter = new UsersAdapter(users);
        usersList.setAdapter(adapter);
    }
}
