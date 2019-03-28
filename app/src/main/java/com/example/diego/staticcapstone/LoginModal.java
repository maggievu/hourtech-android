package com.example.diego.staticcapstone;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;


public class LoginModal extends BottomSheetDialogFragment {
    private LoginListener mListener;
    private static final String TAG = "LoginModal";
    private static final int RC_SIGN_IN = 9001;
    private FirebaseAuth mAuth;
    private ProgressDialog pDialog;
    private GoogleSignInClient mGoogleSignInClient;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.login, container, false);


        Button buttonConfirm = v.findViewById(R.id.btnConfirm);
        Button buttonGoogle = v.findViewById(R.id.btnGoogleLogin);
        Button btnLogout = v.findViewById(R.id.btnLogout);

        pDialog = new ProgressDialog(getActivity());

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);

        mAuth = FirebaseAuth.getInstance();


        buttonGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });
        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onButtonClicked("confirm was clicked. implements the login flow later");
                dismiss();
            }
        });
        return v;
    }


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                // ...
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(getActivity(), "Login Failed: ", Toast.LENGTH_SHORT).show();
                        }

                        hideProgressDialog();
                    }

                });
    }


    private void updateUI(FirebaseUser user) {
        hideProgressDialog();
        TextView displayName = getActivity().findViewById(R.id.displayName);
        Button btnLogout = getActivity().findViewById(R.id.btnLogout);

        //ImageView profileImage = getActivity().findViewById(R.id.profilePic);
        if (user != null) {
//            displayName.setText(user.getDisplayName());
            // displayName.setVisibility(View.VISIBLE);


            Log.e(TAG, "logged as " + user.getDisplayName());
            Toast.makeText(getActivity(), "logged as " + user.getDisplayName(), Toast.LENGTH_SHORT).show();

            //get the user name and send to the profile class
//            Intent intent = new Intent(getActivity().getApplicationContext(), ProfileFragment.class);
            Intent intent = new Intent(getActivity().getApplicationContext(), Search.class);
            intent.putExtra("userName", user.getDisplayName());
            Uri profilePicUrl = user.getPhotoUrl();

            intent.putExtra("userPhoto", profilePicUrl);
            startActivity(intent);


            // Loading profile image
//            Uri profilePicUrl = user.getPhotoUrl();
            if (profilePicUrl != null) {
                //   Glide.with(this).load(profilePicUrl)
                //         .into(profileImage);
            }
            // profileImage.setVisibility(View.VISIBLE);
            //  getActivity().findViewById(R.id.sign_in_button).setVisibility(View.GONE);
//            getActivity().findViewById(R.id.btnLogout).setVisibility(View.VISIBLE);
        } else {

            Log.e(TAG, "not logged");

//             displayName.setVisibility(View.GONE);
            // profileImage.setVisibility(View.GONE);
            // getActivity().findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
//            getActivity().findViewById(R.id.btnLogout).setVisibility(View.GONE);
        }
    }

    private void hideProgressDialog() {
        pDialog.dismiss();
    }

    private void signOut() {
        // Firebase sign out
        mAuth.signOut();

        // Google sign out
        mGoogleSignInClient.signOut().addOnCompleteListener(getActivity(),
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        updateUI(null);
                    }
                });
    }


    public interface LoginListener {
        void onButtonClicked(String text);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {

            mListener = (LoginListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement loginmodal");
        }
    }
}
