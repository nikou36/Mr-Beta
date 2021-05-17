package com.example.maesrecipebeta.activities.data;

import android.util.Log;

import com.amplifyframework.core.Amplify;
import com.example.maesrecipebeta.activities.data.model.LoggedInUser;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    private boolean success;
    public Result<LoggedInUser> login(String username, String password) {

        try {
            // TODO: handle loggedInUser authentication
            LoggedInUser fakeUser =
                    new LoggedInUser(
                            java.util.UUID.randomUUID().toString(),
                            "John Doe");
            AmplifySignIn();
            System.out.println(this.success);
            return new Result.Success<>(fakeUser);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }

    private void setSuccess(){
        /*
        TODO: handle successful login here
         */

        System.out.println("TESTESTEST");
        this.success = true;
        System.out.println("Sucess: " + success);
    }

    private void setFailure(){
        /*
        TODO: Set failure here
         */
    }

    private void AmplifySignIn(){
        Amplify.Auth.signIn(
                "TestingTester3",
                "seattle123",
                result ->setSuccess()/*Log.i("AuthSignInTest", result.isSignInComplete() ? "Sign in succeeded" : "Sign in not complete")*/,
                error -> Log.e("AuthSignInTest", error.toString())
        );

    }
}