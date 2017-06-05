package com.minhagasosa.fragments.Settings;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Switch;

import com.google.firebase.iid.FirebaseInstanceId;
import com.minhagasosa.R;
import com.minhagasosa.RotaAdapter;
import com.minhagasosa.dao.DaoMaster;
import com.minhagasosa.dao.DaoSession;
import com.minhagasosa.dao.Rota;
import com.minhagasosa.dao.RotaDao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SettingsFragment extends Fragment {

    Switch mSwitchNotification;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Listagem de Rotas");
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        mSwitchNotification = (Switch) view.findViewById(R.id.switchNotification);
        try {
            FirebaseInstanceId.getInstance().deleteInstanceId();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mSwitchNotification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean on) {
                if(on){

                }else{

                }
            }
        });
        return view;
    }

}
