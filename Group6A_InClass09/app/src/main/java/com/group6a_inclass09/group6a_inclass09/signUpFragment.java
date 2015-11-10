package com.group6a_inclass09.group6a_inclass09;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link signUpFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class signUpFragment extends Fragment {

    public TextView fName, fEmail, fPass, fRePass;

    private SignUpInterface fListener;

    public signUpFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fName = (TextView) getView().findViewById(R.id.textViewFirstName);
        fEmail = (TextView) getView().findViewById(R.id.editTextEmail);
        fPass = (TextView) getView().findViewById(R.id.editTextPassword);
        fRePass = (TextView) getView().findViewById(R.id.editTextConfirmPassword);


        getView().findViewById(R.id.buttonCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fListener.goToLogin();
            }
        });

        getView().findViewById(R.id.buttonSignUp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lName,lEmail,lPassword,lRePass;

                lName = fName.getText().toString();
                lEmail = fEmail.getText().toString();
                lPassword = fPass.getText().toString();
                lRePass = fRePass.getText().toString();

                if(lName.isEmpty()){
                    fName.setError("Enter Name");
                }
                if(lEmail.isEmpty()){
                    fEmail.setError("Enter Email");
                }
                if (lPassword.isEmpty()){
                    fPass.setError("Empty Password");
                }
                if (lRePass.isEmpty()){
                    fRePass.setError("Re-Enter Password");
                }
                if(!lPassword.equals(lRePass)){
                    fPass.setText("");
                    fRePass.setText("");
                    Toast.makeText(getActivity(), "Passwords Mismatch", Toast.LENGTH_SHORT).show();
                    return;
                }


                ParseUser lSignupUser = new ParseUser();
                lSignupUser.setEmail(lEmail);
                lSignupUser.setPassword(lPassword);
                lSignupUser.setUsername(lEmail);
                lSignupUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Toast.makeText(getActivity(), "Signup Successful",Toast.LENGTH_SHORT).show();
                            fListener.goToLogin();
                        } else {
                            e.printStackTrace();
                            Toast.makeText(getActivity(), "Email Already exists",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
//        try {
//            mListener = (OnFragmentInteractionListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
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

    public void cancelOnClick (View aView){

    }

    public void signUpOnClick (View aView){

    }

    public interface SignUpInterface{
        public void goToLogin();
    }

}
