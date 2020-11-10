package com.example.cyprusreservations.ui.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.util.Patterns;

import com.example.cyprusreservations.data.LoginRepository;
import com.example.cyprusreservations.data.Result;
import com.example.cyprusreservations.data.model.LoggedInUser;
import com.example.cyprusreservations.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
    private MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();
    private LoginRepository loginRepository;

    LoginViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    LiveData<LoginFormState> getLoginFormState() {
        return loginFormState;
    }

    LiveData<LoginResult> getLoginResult() {
        return loginResult;
    }

    public void login(String username, String password) {
        // can be launched in a separate asynchronous job
        Result<LoggedInUser> result = loginRepository.login(username, password);

        if (result instanceof Result.Success) {
            LoggedInUser data = ((Result.Success<LoggedInUser>) result).getData();
            loginResult.setValue(new LoginResult(new LoggedInUserView(data.getDisplayName())));
        } else {
            loginResult.setValue(new LoginResult(R.string.login_failed));
        }
    }

    public void loginDataChanged(String username, String password) {
        if (!isUserNameValid(username)) {
            loginFormState.setValue(new LoginFormState(R.string.invalid_username, null));
        } else if (!isPasswordValid(password)) {
            loginFormState.setValue(new LoginFormState(null, R.string.invalid_password));
        } else {
            loginFormState.setValue(new LoginFormState(true));
        }
    }

    // A placeholder username validation check
    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        if (username.contains("@gmail.com")) {
            return true;
        } else {
            return !username.trim().isEmpty();
        }
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {

        if(password.trim().length()<8 && password.trim().length()>15 && hasNumber(password) && hasSpecialChar(password) && hasCapitalLeter(password)){
            return true;
        }else{
            return false;
        }
    }

    private boolean hasNumber(String pass){
        for(int i=0; i<pass.length();i++){
            if(Character.isDigit(pass.charAt(i))){
                return true;
            }
        }
        return false;
    }

    public Boolean hasSpecialChar(String s) {
        //int counter =0;
        if (s == null || s.trim().isEmpty()) {
            return false;
        }
        Pattern p = Pattern.compile("[^A-Za-z0-9]");
        Matcher m = p.matcher(s);
        // boolean b = m.matches();

        boolean b = m.find();
        if (b == true)
            return true;
        else
            return false;
    }

    public Boolean hasCapitalLeter(String pass){
        if(pass.equals(pass.toLowerCase())){
            return false;
        }else{
            return true;
        }
    }
}