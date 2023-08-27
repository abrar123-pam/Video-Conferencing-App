package com.example.zoom.ui.meetings;

import static android.content.Context.CONNECTIVITY_SERVICE;

import android.annotation.SuppressLint;
import android.content.Intent;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.zoom.R;
import com.example.zoom.call.utils.Constants;
import com.example.zoom.call.utils.SharedPrefsHelper;
import com.example.zoom.conference.ui.ConferenceCallActivity;
import com.quickblox.users.model.QBUser;


public class MeetingsFragment extends Fragment implements View.OnClickListener {

    private SharedPrefsHelper sharedPrefsHelper;
    private QBUser currentUser;
    private View root;
    private TextView textviewsendinvitation,textViewstart;
    int checkwifi=0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

         root = inflater.inflate(R.layout.fragment_meetings, container, false);
         sharedPrefsHelper=SharedPrefsHelper.getInstance();
         currentUser=sharedPrefsHelper.getQbUser();


        return root;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initviews();
        clicklistners();
    }


    @SuppressLint("SetTextI18n")
    private void initviews() {
        TextView tvTitle = getActivity().findViewById(R.id.tvTitle);
        TextView textViewMeetingID = root.findViewById(R.id.TextViewMeetingID);
        textViewstart = root.findViewById(R.id.textViewStart);
        textviewsendinvitation = root.findViewById(R.id.textViewSendInvitation);
        Log.e("test "," id "+currentUser);
        textViewMeetingID.setText(currentUser.getId().toString());

        tvTitle.setText(getResources().getString(R.string.title_meetings));
//        wifiManager = (WifiManager) getActivity().getApplicationContext().getSystemService(Context.WIFI_SERVICE);


    }
    private void clicklistners() {
        textViewstart.setOnClickListener(this);
        textviewsendinvitation.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.textViewStart:
                ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(CONNECTIVITY_SERVICE);
                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();


                if(activeNetwork.getType()== ConnectivityManager.TYPE_WIFI){
                    checkwifi=0;
                    navigateToMeetings();
                }else{

                    Toast.makeText(getActivity(), "ENABLE WIFI", Toast.LENGTH_SHORT).show();
                }


                break;
            case R.id.textViewSendInvitation:
                shareURL();
                break;
        }
    }

    private void shareURL() {
        String name = currentUser.getFullName();
        String id = currentUser.getId()+"";
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        intent.putExtra(Intent.EXTRA_SUBJECT,"Join Zoom Meeting");
        intent.putExtra(Intent.EXTRA_TEXT,
                name + " is inviting you to a scheduled Zoom meeting.\nJoin Zoom Meeting http://zoom.in/share?id="+id);
        startActivity(Intent.createChooser(intent,"Share a link!"));


    }

    private void navigateToMeetings() {
        String meetingId= currentUser.getId().toString();
        Intent intent = new Intent(getActivity(), ConferenceCallActivity.class);
        intent.putExtra(Constants.ACTION_KEY_CHANNEL_NAME,meetingId);
        intent.putExtra(Constants.ACTION_KEY_USER_NAME,currentUser.getFullName());
        intent.putExtra(Constants.ACTION_KEY_ENCRYPTION_KEY,"");
        intent.putExtra(Constants.ACTION_KEY_ENCRYPTION_MODE,getString(R.string.encryption_mode_value));
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}