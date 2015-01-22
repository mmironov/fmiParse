package android.fmi.userinspector;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class MainActivity extends Activity implements View.OnClickListener {

    private EditText emailEditText;
    private EditText passwordEditText;

    private Button loginButton;
    private Button signUpButton;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Parse.initialize(this, "zeFYwwcEIyzzYXgzjanoNKyvF80px3haP7KacqLF", "KtM4qPA8KtFygmn0VZdtOio1LAcSdUIOmbdVNwPX");

        if(ParseUser.getCurrentUser() != null) {
            startActivity(new Intent(this, UsersActivity.class));
            finish();
            return;
        }

        setContentView(R.layout.activity_main);

        emailEditText = (EditText) findViewById(R.id.emailEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);

        loginButton = (Button) findViewById(R.id.loginButton);
        signUpButton = (Button) findViewById(R.id.signupButton);

        loginButton.setOnClickListener(this);
        signUpButton.setOnClickListener(this);
    }

    @Override public void onClick(View v) {
        final int id = v.getId();

        EditText error = null;
        emailEditText.setError(null);
        passwordEditText.setError(null);

        final String username = emailEditText.getText().toString();
        if (TextUtils.isEmpty(username) || !username.contains("@")) {
            error = emailEditText;
        }

        final String password = passwordEditText.getText().toString();
        if (TextUtils.isEmpty(password)) {
            error = passwordEditText;
        }

        if (id == loginButton.getId()) {
            if (!TextUtils.isEmpty(username) && username.contains("@") && !TextUtils.isEmpty(password)) {
                ParseUser.logInInBackground(username, password, new LogInCallback() {
                    @Override public void done(ParseUser parseUser, ParseException e) {
                        if (e == null) {
                            startActivity(new Intent(MainActivity.this, UsersActivity.class));
                            finish();
                            return;
                        } else {
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            } else {
                if (error != null) {
                    error.setError("Invalid input!");
                }
            }
        }

        if (id == signUpButton.getId()) {
            if (!TextUtils.isEmpty(username) && username.contains("@") && !TextUtils.isEmpty(password)) {
                ParseUser user = new ParseUser();

                user.setEmail(username);
                user.setUsername(username);
                user.setPassword(password);

                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            startActivity(new Intent(MainActivity.this, UsersActivity.class));
                            finish();
                            return;
                        } else {
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            } else {
                if (error != null) {
                    error.setError("Invalid input!");
                }
            }
        }
    }
}
