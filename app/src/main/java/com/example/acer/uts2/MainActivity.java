package com.example.acer.uts2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.acer.uts2.Model.GetLogin;
import com.example.acer.uts2.Rest.ApiClient;
import com.example.acer.uts2.Rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private CheckBox chkRemember;
    private Button btnLogin;
    private EditText txtUsername;
    private EditText txtPassword;
    ApiInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        this.chkRemember = (CheckBox) this.findViewById(R.id.chk_remember);
        this.btnLogin = (Button) this.findViewById(R.id.btn_login1);
        this.txtUsername = (EditText) this.findViewById(R.id.txt_username);
        this.txtPassword = (EditText) this.findViewById(R.id.txt_password);

        //cek apakah ada status isloggedin = true dishared pref. jk true, maka lanjut ke layar home
        if (this.isLoggedIn()) {
            Intent intent = new Intent(this.getApplicationContext(), MainActivity2.class);
            this.startActivity(intent);

        }
        //login button click event
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = txtUsername.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();

                //cek form login
                if (!username.isEmpty() && !password.isEmpty()) {
                    //lakukan login
                    doLogin(username, password);
                } else {
                    //notif user
                    Toast.makeText(getApplicationContext(), "isikan username dan password", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private boolean isLoggedIn() {
        //cek apakah ada shared pref login
        SharedPreferences pref = getSharedPreferences("key", Context.MODE_PRIVATE);
        return pref.getBoolean("isloggedin", false);
    }

    private void openHome() {
        Intent intent = new Intent(this.getApplicationContext(), MainActivity2.class);
        startActivity(intent);
    }

    private void saveLogin(String username) {
        //simpan data login ke shared pref
        SharedPreferences sharedpref = getSharedPreferences("key", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpref.edit();

        //simpan isloggedin true berupa boolean
        editor.putBoolean("isloggedin", true);
        //simpan data lainnya berupa string
        editor.putString("username", username);
        editor.apply();
    }

    private void doLogin(final String username, final String password) {
        //panggil request api
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call loginCall = mApiInterface.loginUser(username, password);

        loginCall.enqueque(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                //jika request sukses
                if (response.isSuccessful()) {
                    //buat object model GetLogin dari response
                    GetLogin loginobject = (GetLogin) response.body();

                    //jika status = success (sesuai respon dari rest server)
                    if (loginobject.getStatus().equals("success")) {
                        //simpan data email user ke shared pref
                        saveLogin(username);
                        //buka layar home
                        openHome();
                    } else {
                        Toast.makeText(Login.this, "Email atau password salah", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Login.this, "Error, coba lagi", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                //jika gagal, beri notif
                Toast.makeText(login.this, "gagal:" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}


//    private void checkSavedCredentials(){
//        SharedPreferences handler = this.getSharedPreferences("nama_file_pref_disimpan", Context.MODE_PRIVATE);
//
//        String username = handler.getString("username", "");
//        String password = handler.getString("password", "");
//
//        boolean loginCorrect = this.checkCredentials(username, password);
//
//        if (loginCorrect)
//            this.openHome(username);
//    }
//
//    private void login(){
//        String username = this.txtUsername.getText().toString();
//        String password = this.txtPassword.getText().toString();
//
//        boolean loginCorrect = this.checkCredentials(username, password);
//
//        if (loginCorrect){
//            boolean remember = this.chkRemember.isChecked();
//
//            if (remember){
//                this.saveCredentials();
//            }
//            openHome(username);
//        }
//        else{
//            Toast.makeText(this.getApplicationContext(), "Invalid username and/or password!", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    private boolean checkCredentials(String username, String password){
//        if (username.equals("admin") && password.equals("admin"))
//            return true;
//        else
//            return false;
//    }
//
//    private void saveCredentials(){
//        SharedPreferences handler = this.getSharedPreferences("nama_file_pref_disimpan", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = handler.edit();
//
//        editor.putString("username", this.txtUsername.getText().toString());
//        editor.putString("password", this.txtPassword.getText().toString());
//        editor.apply();
//    }
//
//    
//}
