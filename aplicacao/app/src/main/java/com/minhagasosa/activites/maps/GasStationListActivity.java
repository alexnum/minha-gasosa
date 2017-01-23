package com.minhagasosa.activites.maps;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.minhagasosa.API.GasStationService;
import com.minhagasosa.R;
import com.minhagasosa.Transfer.GasStation;
import com.minhagasosa.activites.BaseActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GasStationListActivity extends BaseActivity {
    protected List<GasStation> gasStation;

    private ImageButton alcoholButton;
    private ImageButton gasButton;
    private ImageButton gasPlusButton;

    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gas_station_list);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle("Postos ordenados por preço");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        alcoholButton = (ImageButton) findViewById(R.id.button_alcohol);
        alcoholButton.setOnClickListener(clickListener);
        gasButton = (ImageButton) findViewById(R.id.button_gas);
        gasButton.setOnClickListener(clickListener);
        gasPlusButton = (ImageButton) findViewById(R.id.button_gas_plus);
        gasPlusButton.setOnClickListener(clickListener);

        GasStationService gasService = retrofit.create(GasStationService.class);
        gasService.getAllGasStation().enqueue(new Callback<List<GasStation>>() {
            @Override
            public void onResponse(Call<List<GasStation>> call, final Response<List<GasStation>> response) {
                if (response.code() == 200) {
                    gasStation = response.body();
                }

                for(int i = 0; i < gasStation.size(); i++){
                    for(int j = 0; j < gasStation.size() - 1; j++){
                        if(gasStation.get(j).getGasPrice() > gasStation.get(j + 1).getGasPrice()){
                            GasStation aux = gasStation.get(j);
                            gasStation.set(j, gasStation.get(j + 1));
                            gasStation.set(j + 1, aux);
                        }
                    }
                }

                adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_2, android.R.id.text1, gasStation) {
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        View view = super.getView(position, convertView, parent);
                        TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                        TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                        text1.setTextColor(Color.BLACK);
                        text2.setTextColor(Color.BLACK);

                        text1.setText(gasStation.get(position).getName());
                        text2.setText("Alcohol price: " + Float.toString(gasStation.get(position).getAlcoolPrice()) + " R$. \n" +
                                "Gas price: " + Float.toString(gasStation.get(position).getGasPrice()) + " R$. \n" +
                                "Gas Plus price: " + Float.toString(gasStation.get(position).getGasPlusPrice()) + " R$.");
                        return view;
                    }
                };

                ListView list = (ListView) findViewById(R.id.gas_station_list);
                list.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<GasStation>> call, Throwable t) {
                Log.e("Error", "Error getting gas stations" + t.toString());
            }
        });


    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button_alcohol:
                    for(int i = 0; i < gasStation.size(); i++){
                        for(int j = 0; j < gasStation.size() - 1; j++){
                            if(gasStation.get(j).getAlcoolPrice() > gasStation.get(j + 1).getAlcoolPrice()){
                                GasStation aux = gasStation.get(j);
                                gasStation.set(j, gasStation.get(j + 1));
                                gasStation.set(j + 1, aux);
                            }
                        }
                    }
                    adapter.notifyDataSetChanged();
                    Log.e("Log", "Alcool");
                    break;
                case R.id.button_gas:
                    for(int i = 0; i < gasStation.size(); i++){
                        for(int j = 0; j < gasStation.size() - 1; j++){
                            if(gasStation.get(j).getGasPrice() > gasStation.get(j + 1).getGasPrice()){
                                GasStation aux = gasStation.get(j);
                                gasStation.set(j, gasStation.get(j + 1));
                                gasStation.set(j + 1, aux);
                            }
                        }
                    }
                    adapter.notifyDataSetChanged();
                    Log.e("Log", "Gasolina");
                    break;
                case R.id.button_gas_plus:
                    for(int j = 0; j < gasStation.size() - 1; j++){
                        if(gasStation.get(j).getGasPlusPrice() > gasStation.get(j + 1).getGasPlusPrice()){
                            GasStation aux = gasStation.get(j);
                            gasStation.set(j, gasStation.get(j + 1));
                            gasStation.set(j + 1, aux);
                        }
                    }
                    adapter.notifyDataSetChanged();
                    Log.e("Log", "Gasolina Aditivada");
                    break;
            }
        }
    };
}
