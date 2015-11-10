package com.group6a_inclass09.group6a_inclass09;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.DeleteCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Arunkumar's on 11/9/2015.
 */
public class MessageAdapter extends ArrayAdapter<ParseObject> {

    TextView fCommonTextView;
    ImageView fImageView;

    static Context fContext;
    int resource;

    List<ParseObject> messages;

    public MessageAdapter(Context context, int resource, List<ParseObject> objects) {
        super(context, resource, objects);

        this.fContext = context;
        this.resource = resource;
        this.messages = objects;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) fContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resource,parent,false);
        }

        final ParseObject obj = messages.get(position);

        //Set Names
        ParseUser user = obj.getParseUser("createdBy");
        String name = user.getString("username");
        fCommonTextView = (TextView) convertView.findViewById(R.id.textViewFirstName);
        fCommonTextView.setText(name);


        //Set Message
        fCommonTextView = (TextView) convertView.findViewById(R.id.textViewMessageBody);
        fCommonTextView.setText(obj.getString("message"));


        //Set Date
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        Date date = obj.getCreatedAt();
        String result = formatter.format(date);

        fCommonTextView = (TextView) convertView.findViewById(R.id.textViewDate);
        fCommonTextView.setText(result);

        ParseUser tempUser = ParseUser.getCurrentUser();
        String tempName = tempUser.getString("Name");
        if (name.equals(tempName)) {

            //OnClick Delete
            fImageView = (ImageView) convertView.findViewById(R.id.imageViewDeleteButton);
            fImageView.setImageResource(R.drawable.delete_icon);
            fImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    obj.deleteInBackground(new DeleteCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                messagesFragment.userMessages.remove(position);
                                notifyDataSetChanged();

                            }

                        }
                    });
                }
            });
        }

        return convertView;
    }
}
