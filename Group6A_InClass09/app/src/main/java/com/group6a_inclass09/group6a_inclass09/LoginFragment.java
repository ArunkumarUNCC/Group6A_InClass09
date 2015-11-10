package com.group6a_inclass09.group6a_inclass09;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LoginFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class LoginFragment extends Fragment {

    private LoginInterface fListener;

    EditText fUserName,fPassword;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            fListener = (LoginInterface) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement LoginInterface methods");
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getView().findViewById(R.id.buttonCreateAccount).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fListener.gotoSignup();
            }
        });

        getView().findViewById(R.id.buttonLogIn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fUserName = (EditText) getView().findViewById(R.id.editTextUserName);
                fPassword = (EditText) getView().findViewById(R.id.editTextPassword);

                String lUser,lPass;
                lUser = fUserName.getText().toString();
                lPass = fPassword.getText().toString();

                if(lUser.isEmpty())
                    fUserName.setError("Email cannot be empty");
                if(lPass.isEmpty()) {
                    fPassword.setError("Password cannot be empty");
                    return;
                }

                ParseUser.logInInBackground(lUser, lPass, new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (e != null)
                        Toast.makeText(getActivity(), "Invalid Details", Toast.LENGTH_SHORT).show();
                        else{
                            fListener.gotoMessage();

                        }
                    }
                });


            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
        fListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

    public void createNewAccountOnClick (View aView){
        fListener.gotoSignup();
    }

    public void loginOnClick (View aView){
        fListener.gotoMessage();
    }

    public interface LoginInterface{
        public void gotoMessage();
        public void gotoSignup();
    }
}
