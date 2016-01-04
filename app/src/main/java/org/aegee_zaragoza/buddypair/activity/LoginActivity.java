package org.aegee_zaragoza.buddypair.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import org.aegee_zaragoza.buddypair.R;
import org.aegee_zaragoza.buddypair.database.DatabaseHelper;

public class LoginActivity extends AppCompatActivity {

    private UserLoginTask loginTask;

    private EditText usernameView;
    private EditText passwordView;
    private CheckBox rememberPasswordCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameView = (EditText) findViewById(R.id.username);
        passwordView = (EditText) findViewById(R.id.password);
        rememberPasswordCheckBox = (CheckBox) findViewById(R.id.remember_password);

        SharedPreferences settings = getSharedPreferences("buddy_pair_prefs", 0);
        boolean remember_password = settings.getBoolean("remember_password", false);
        if (remember_password) {
            usernameView.setText(settings.getString("username", ""));
            passwordView.setText(settings.getString("password", ""));
        }
        rememberPasswordCheckBox.setChecked(remember_password);

        Button loginButton = (Button) findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin();
            }
        });
    }

    public void attemptLogin() {
        passwordView.setError(null);

        String username = usernameView.getText().toString();
        String password = passwordView.getText().toString();
        boolean remember_password = rememberPasswordCheckBox.isChecked();

        View focusView = null;
        if (TextUtils.isEmpty(password)) {
            passwordView.setError(getString(R.string.error_field_required));
            focusView = passwordView;
        }
        if (TextUtils.isEmpty(username)) {
            usernameView.setError(getString(R.string.error_field_required));
            focusView = usernameView;
        }
        if (focusView != null) {
            focusView.requestFocus();
        } else {
            loginTask = new UserLoginTask(username, password, remember_password);
            loginTask.execute();
        }
    }

    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {
        private final String username;
        private final String password;
        private final boolean remember_password;

        public UserLoginTask(String username, String password, boolean remember_password) {
            this.username = username;
            this.password = password;
            this.remember_password = remember_password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            return DatabaseHelper.connect(username, password);
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            SharedPreferences settings = getSharedPreferences("buddy_pair_prefs", 0);
            if (remember_password) {
                settings.edit().putString("username", username).commit();
                settings.edit().putString("password", password).commit();
            } else {
                settings.edit().clear().commit();
            }
            settings.edit().putBoolean("remember_password", remember_password).commit();
            if (success) {
                Intent i = new Intent(LoginActivity.this, StudentListActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                //startActivity(new Intent(LoginActivity.this, StudentListActivity.class));
            } else {
                passwordView.setError(getString(R.string.error_incorrect_password));
                passwordView.requestFocus();
            }
        }
    }
}
