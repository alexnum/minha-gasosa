package com.minhagasosa.fragments.carsettings;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import com.minhagasosa.R;
import com.minhagasosa.dao.Carro;
import com.minhagasosa.dao.Datastore;
import com.minhagasosa.dao.Marca;
import com.minhagasosa.dao.Modelo;
import com.minhagasosa.fragments.Home.HomeFragment;
import com.minhagasosa.preferences.MinhaGasosaPreference;
import com.path.android.jobqueue.Job;
import com.path.android.jobqueue.JobManager;
import com.path.android.jobqueue.Params;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import de.greenrobot.dao.query.Query;

public class CarSettingsFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    /**
     * atributo de MARCA do carro
     */
    private Spinner spinnerMarca;
    /**
     * Atributo de modelo do carro
     */
    private Spinner spinnerCarro;
    /**
     * atributo da versao do carro
     */
    private Spinner spinnerModelo;
    /**
     * atributo da potencia do carro
     */
    private Spinner spinnerPotencia;
    /**
     * atributo progresso
     */
    private ProgressDialog progress;
    private Datastore datastore;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Configurar Carro");
        View view = inflater.inflate(R.layout.activity_main, container, false);

        setHasOptionsMenu(true);

        datastore = new Datastore(getContext());
        JobManager jobManager = new JobManager(getContext());

        spinnerMarca = (Spinner) view.findViewById(R.id.spinnerMarca);
        spinnerCarro = (Spinner) view.findViewById(R.id.spinnerModelo);
        spinnerModelo = (Spinner) view.findViewById(R.id.spinnerVersao);
        spinnerPotencia = (Spinner) view.findViewById(R.id.spinnerPot);

        spinnerMarca.setOnItemSelectedListener(this);
        spinnerCarro.setOnItemSelectedListener(this);
        spinnerPotencia.setOnItemSelectedListener(this);


        if (datastore.get().count(Modelo.class).get().value() == 0) {
            getActivity().setFinishOnTouchOutside(false);

            progress = new ProgressDialog(getContext());
            progress.setCancelable(false);
            progress.setCanceledOnTouchOutside(false);
            progress.setMessage("Populando carros, isso so sera feito uma vez");
            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progress.setIndeterminate(true);
            progress.setProgress(0);
            progress.show();

            jobManager.addJobInBackground(new Job(new Params(1)) {
                @Override
                public void onAdded() {

                }

                @Override
                public void onRun() throws Exception {
                    popularDb();
                    progress.hide();

                    Fragment fragment = new CarSettingsFragment();
                    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                    Bundle argsCar = new Bundle();
                    argsCar.putBoolean("fromHome", true);
                    fragment.setArguments(argsCar);
                    ft.replace(R.id.content_frame, fragment);
                    ft.commit();
                }

                @Override
                protected void onCancel() {

                }
            });
        }

        popularMarcas();
        popularPotencias();

        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent == spinnerMarca) {
            popularCarros();
        } else if (parent == spinnerCarro) {
            popularModelos();
        } else if (parent == spinnerModelo) {
            // TODO
        } else if (parent == spinnerPotencia) {
            String selectedPot = (String) spinnerPotencia.getSelectedItem();
            if (selectedPot.isEmpty()) {
                MinhaGasosaPreference.putWithPotency(false, getContext());
                MinhaGasosaPreference.putPotency(0.0f, getContext());
            } else {
                MinhaGasosaPreference.putWithPotency(true, getContext());
                MinhaGasosaPreference.putPotency(Float.valueOf(selectedPot), getContext());
                MinhaGasosaPreference.setConsumoUrbanoPrimario(10.0f, getContext());
                MinhaGasosaPreference.setConsumoRodoviarioPrimario(10.5f, getContext());
                MinhaGasosaPreference.setConsumoUrbanoSecundario(11.0f, getContext());
                MinhaGasosaPreference.setConsumoUrbanoSecundario(11.5f, getContext());
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    /**
     * metodo que salva todas as informacoes do carro no banco
     *
     */
    private void salvarInformacoesCarro() {
        if (!MinhaGasosaPreference.getWithPotency(getContext())) {
            Modelo modelo = (Modelo) spinnerCarro.getSelectedItem();
            String versao = (String) spinnerModelo.getSelectedItem();

            MinhaGasosaPreference.setMarca(modelo.getCarro().getMarca().getId(), getContext());
            MinhaGasosaPreference.setModelo(modelo.getId(), getContext());
            MinhaGasosaPreference.setCarro(modelo.getCarro().getId(), getContext());
            MinhaGasosaPreference.setCarroIsFlex(modelo.isFlex(), getContext());
            MinhaGasosaPreference.setConsumoUrbanoPrimario(modelo.getConsumoUrbanoGasolina().floatValue(), getContext());
            MinhaGasosaPreference.setConsumoRodoviarioPrimario(modelo.getConsumoRodoviarioGasolina().floatValue(), getContext());
            MinhaGasosaPreference.setConsumoUrbanoSecundario(modelo.getConsumoUrbanoAlcool().floatValue(), getContext());
            MinhaGasosaPreference.setConsumoRodoviarioSecundario(modelo.getConsumoRodoviarioAlcool().floatValue(), getContext());
        }
        EditText edCapacidadeTanuqe = (EditText) getView().findViewById(R.id.ed_capacidade_tanque);
        String capacidade = edCapacidadeTanuqe.getText().toString();
        if (!capacidade.trim().isEmpty()) {
            try {
                float cap = Float.valueOf(capacidade);
                MinhaGasosaPreference.putCapacidadeDoTanque(cap, getContext());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        MinhaGasosaPreference.setDone(true, getContext());
        Fragment fragment = new HomeFragment();
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, fragment);
        ft.commit();
    }

    private void popularMarcas() {
        List<Marca> marcas = datastore.get()
                .select(Marca.class)
                .orderBy(Marca.NOME.desc())
                .get().toList();

        spinnerMarca.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, marcas));

        Marca selectedMarca = MinhaGasosaPreference.getMarca(getContext());

        if (marcas.contains(selectedMarca)) {
            spinnerMarca.setSelection(marcas.indexOf(selectedMarca), true);
            popularCarros();
        }
    }

    /**
     * metodo que popula as versoes do carro para o banco de dados
     *
     */
    private void popularModelos() {
        Carro carro = (Carro) spinnerCarro.getSelectedItem();

        List<Modelo> modelos = new Datastore(getContext()).get()
                .select(Modelo.class)
                .where(Modelo.CARRO.eq(carro))
                .get().toList();

        spinnerModelo.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, modelos));

        Modelo selectedModelo = MinhaGasosaPreference.getModelo(getContext());
        if (modelos.contains(selectedModelo)) {
            spinnerMarca.setSelection(modelos.indexOf(selectedModelo), true);
        }
    }

    /**
     * Metodo que retorna a soma das rotas cadastradas no sistema
     *
     * @return
     */

    private void popularCarros() {
        Marca marca = (Marca) spinnerMarca.getSelectedItem();

        List<Carro> carros = new Datastore(getContext()).get()
                .select(Carro.class)
                .where(Carro.MARCA.eq(marca))
                .get().toList();

        spinnerCarro.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, carros));

        Carro selectedCarro = MinhaGasosaPreference.getCarro(getContext());

        if (carros.contains(selectedCarro)) {
            spinnerCarro.setSelection(carros.indexOf(selectedCarro), true);
            popularModelos();
        }
    }

    private void popularPotencias() {
        String[] potencias = {"", "1.0", "1.4", "1.6", "1.8", "2.0", "2.2", "3.0"};
        spinnerPotencia.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, potencias));

        /*
        String selectedPotencia = MinhaGasosaPreference.getVersao(getContext());
        if (selectedPotencia != null) {
            spinnerMarca.setSelection(Arrays.asList(potencias).indexOf(selectedPotencia), true);
        }
        */

        // TODO
        // TODO
        // TODO
        // TODO
        // TODO
    }

    private void popularDb() {
        String jsonmarcas = loadJSONFromAsset("marcas.json");
        String jsoncarros = loadJSONFromAsset("Modelos.json");
        String jsonmodelos = loadJSONFromAsset("carrosminhagasosa.json");
        try {
            JSONArray marcas_novo = new JSONArray(jsonmarcas);
            JSONArray carros_novo = new JSONArray(jsoncarros);
            JSONArray modelos_novo = new JSONArray(jsonmodelos);

            SparseArray<String> carros = new SparseArray<>();

            for (int i = 0; i < marcas_novo.length(); i++) {
                Marca m = new Marca();
                m.setNome(marcas_novo.getString(i));
                datastore.get().insert(m);
            }

            for (int i = 0; i < carros_novo.length(); i++) {
                carros.append(carros_novo.getJSONObject(i).getInt("ID"), carros_novo.getJSONObject(i).getString("MODELO"));
            }

            for (int i = 0; i < modelos_novo.length(); i++) {
                JSONObject current = modelos_novo.getJSONObject(i);
                String nome_carro = carros.get(current.getInt("MODEL"));
                String nome_marca = current.getString("marca");
                Carro carro;

                try {
                    carro = datastore.get()
                            .select(Carro.class)
                            .where(Carro.NOME.eq(nome_carro))
                            .get().first();
                } catch (NoSuchElementException e) {
                    carro = new Carro();
                    carro.setNome(nome_carro);
                    Marca m = datastore.get()
                            .select(Marca.class)
                            .where(Marca.NOME.eq(nome_marca))
                            .get().first();
                    carro.setMarca(m);
                    datastore.get().insert(carro);
                }

                Modelo m = new Modelo();
                m.setNome(current.getString("VERSION"));
                m.setAno(current.getString("ano"));
                m.setCarro(carro);
                m.setConsumoRodoviarioAlcool(current.getDouble("rodoviario_alcool"));
                m.setConsumoUrbanoAlcool(current.getDouble("urbano_alcol"));
                m.setConsumoRodoviarioGasolina(current.getDouble("rodoviario"));
                m.setConsumoUrbanoGasolina(current.getDouble("urbano"));
                m.setFlex(current.getInt("FLEX") == 1);
                datastore.get().insert(m);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param fileName
     * @return
     */
    public final String loadJSONFromAsset(final String fileName) {
        String json = null;
        try {

            InputStream is = getActivity().getAssets().open(fileName);

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }

    /**
     * metodo que retorna o valor maximo que deve ser gasto
     *
     * @return
     */
    public final float getValorMaximo() {
        return MinhaGasosaPreference.getValorMaximoParaGastar(getActivity());
    }

    @Override
    public final void onCreateOptionsMenu(final Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.distance_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public final boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.done_route:
                salvarInformacoesCarro();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
