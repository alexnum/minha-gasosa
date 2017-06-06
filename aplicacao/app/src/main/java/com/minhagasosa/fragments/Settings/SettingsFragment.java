package com.minhagasosa.fragments.Settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.minhagasosa.API.UsersService;
import com.minhagasosa.R;
import com.minhagasosa.activites.EndpointFactory;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SettingsFragment extends Fragment {

    Switch mSwitchNotification;
    Retrofit retrofit;
    UsersService m_usersService;
    SharedPreferences m_prefs;
    private final String NOTIFICATIONS_ON_KEY = "FBACTIVE";
    CompoundButton.OnCheckedChangeListener m_switchListener;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Listagem de Rotas");
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        retrofit  = EndpointFactory.buildEndpoint(getContext());
        m_usersService = retrofit.create(UsersService.class);
        mSwitchNotification = (Switch) view.findViewById(R.id.switchNotification);
        m_prefs = getActivity().getPreferences(Context.MODE_PRIVATE);
        boolean notificationsOn = m_prefs.getBoolean(NOTIFICATIONS_ON_KEY, true);

        m_switchListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean on) {
                Log.d("SETTINGS", "CheckChange");
                if(on){
                    m_usersService.restoreToken().enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            SharedPreferences.Editor editor = m_prefs.edit();
                            editor.putBoolean(NOTIFICATIONS_ON_KEY, true);
                            editor.commit();
                            Context c = getContext();
                            if(c != null){
                                Toast.makeText(c, "Notificações ativadas.", Toast.LENGTH_SHORT);
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Context c = getActivity();
                            if(c != null){
                                Toast.makeText(c, "Não foi possivel restaurar as notificações, tente novamente mais tarde.", Toast.LENGTH_LONG);
                            }
                            mSwitchNotification.setOnCheckedChangeListener(null);
                            mSwitchNotification.setChecked(false);
                            mSwitchNotification.setOnCheckedChangeListener(m_switchListener);
                        }
                    });
                }else{
                    m_usersService.removeToken().enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            SharedPreferences.Editor editor = m_prefs.edit();
                            editor.putBoolean(NOTIFICATIONS_ON_KEY, false);
                            editor.commit();
                            Context c = getActivity();
                            if(c != null){
                                Toast.makeText(c, "Notificações desativadas.", Toast.LENGTH_SHORT);
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Context c = getActivity();
                            if(c != null){
                                Toast.makeText(c, "Não foi possivel remover as notificações, tente novamente mais tarde.", Toast.LENGTH_LONG);
                            }
                            mSwitchNotification.setOnCheckedChangeListener(null);
                            mSwitchNotification.setChecked(true);
                            mSwitchNotification.setOnCheckedChangeListener(m_switchListener);
                        }
                    });
                }
            }
        };
        mSwitchNotification.setOnCheckedChangeListener(null);
        mSwitchNotification.setChecked(notificationsOn);
        mSwitchNotification.setOnCheckedChangeListener(m_switchListener);
        return view;
    }

}
