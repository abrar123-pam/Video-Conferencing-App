package com.example.zoom.ui.settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.zoom.AboutActivity;
import com.example.zoom.R;
import com.example.zoom.Welcome.WelcomeActivity;
import com.example.zoom.call.services.LoginService;
import com.example.zoom.call.utils.Constants;
import com.example.zoom.call.utils.SharedPrefsHelper;
import com.example.zoom.call.utils.UsersUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.quickblox.messages.services.SubscribeService;

public class SettingsFragment extends Fragment implements View.OnClickListener {

    private RelativeLayout relativeLayoutContacts, relativeLayoutMeetings,
            relativeLayoutChat, relativeLayoutAbout, relativeLayoutLogout;
    private View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

         root = inflater.inflate(R.layout.fragment_settings, container, false);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        iniViews();
        listener();
    }



    private void iniViews() {
        TextView textViewTitle = getActivity().findViewById(R.id.tvTitle);
        textViewTitle.setText(getActivity().getResources().getString(R.string.title_settings));

        TextView textViewName = root.findViewById(R.id.textViewName);
        TextView textViewEmail = root.findViewById(R.id.textViewEmail);
        relativeLayoutContacts = root.findViewById(R.id.rlContacts);
        relativeLayoutMeetings = root.findViewById(R.id.rlMeetings);
        relativeLayoutChat = root.findViewById(R.id.rlChat);
        relativeLayoutAbout = root.findViewById(R.id.rlabout);
        relativeLayoutLogout = root.findViewById(R.id.rlLogout);


        textViewName.setText(SharedPrefsHelper.getInstance().getQbUser().getFullName());
        textViewEmail.setText(SharedPrefsHelper.getInstance().get(Constants.USER_EMAIL));
    }

    private void listener() {
        relativeLayoutContacts.setOnClickListener(this);
        relativeLayoutMeetings.setOnClickListener(this);
        relativeLayoutChat.setOnClickListener(this);
        relativeLayoutAbout.setOnClickListener(this);
        relativeLayoutLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.rlContacts:
                Navigation.findNavController(v).navigate(R.id.navigation_contacts);
                break;
            case R.id.rlMeetings:
                Navigation.findNavController(v).navigate(R.id.navigation_meeting);
                break;
            case R.id.rlChat:
                Navigation.findNavController(v).navigate(R.id.navigation_meetchat);
                break;
            case R.id.rlabout:
                startActivity(new Intent(getActivity(), AboutActivity.class));
                break;
            case R.id.rlLogout:
                logout();
                break;

        }
        
    }

    private void logout() {
        SubscribeService.unSubscribeFromPushes(getActivity());
        LoginService.logout(getActivity());
        UsersUtils.removeUserData(getActivity());
        SharedPrefsHelper.getInstance().clearAllData();
        FirebaseAuth.getInstance().signOut();
        startLoginActivity();

    }

    private void startLoginActivity() {

        Intent intent = new Intent(getActivity(), WelcomeActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
}