package com.example.rishikesh.goalbold;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import static android.R.attr.fragment;

/**
 * Created by Rishikesh on 05-07-2017.
 */

public class Contactus extends Fragment {

    Button Kickit;
    EditText football;
    String to="rishikeshgaikar07@gmail.com";
    String subject="Feedback from App";

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Contact Us");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.contactus, container, false);
        Button kickit=(Button) view.findViewById(R.id.msgButton);
        final EditText football= (EditText) view.findViewById(R.id.msgText);
        kickit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String message=football.getText().toString();


                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{ to});
                email.putExtra(Intent.EXTRA_SUBJECT, subject);
                email.putExtra(Intent.EXTRA_TEXT, message);

                //need this to prompts email client only
                email.setType("message/rfc822");

                startActivity(Intent.createChooser(email, "Choose an Email client :"));
            }
        });
        return view;
    }



}

