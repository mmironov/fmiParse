package android.fmi.userinspector;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;


public class UsersActivity extends Activity {

    private RecyclerView usersList;

    private UsersAdapter adapter;

    private List<ParseUser> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        if (ParseUser.getCurrentUser() == null) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
            return;
        }

        usersList = (RecyclerView) findViewById(R.id.usersList);
        usersList.setLayoutManager(new LinearLayoutManager(this));

        ParseQuery<ParseUser> usersQuery = ParseUser.getQuery();
        usersQuery.setLimit(25);
        usersQuery.setCachePolicy(ParseQuery.CachePolicy.IGNORE_CACHE);
        usersQuery.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> parseUsers, ParseException e) {
                if (e == null) {
                    users = parseUsers;

                    adapter = new UsersAdapter(users);
                    usersList.setAdapter(adapter);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.users, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_settings) {
            ParseUser.logOut();

            startActivity(new Intent(UsersActivity.this, MainActivity.class));
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
