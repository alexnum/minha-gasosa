package com.minhagasosa.fragments.Refuel;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.minhagasosa.ChartView;
import com.minhagasosa.NewRefuelActivity;
import com.minhagasosa.R;
import com.minhagasosa.dao.Abastecimento;
import com.minhagasosa.dao.AbastecimentoDao;
import com.minhagasosa.dao.DaoMaster;
import com.minhagasosa.dao.DaoSession;
import com.minhagasosa.preferences.MinhaGasosaPreference;
import com.minhagasosa.utils.GenerateData;
import com.minhagasosa.utils.MonthYearPickerDialog;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;
import butterknife.Unbinder;
import de.greenrobot.dao.query.QueryBuilder;

import static butterknife.OnItemSelected.Callback.NOTHING_SELECTED;


/**
 * A simple {@link Fragment} subclass.
 */
public class RefuelFragment extends Fragment implements  DatePickerDialog.OnDateSetListener{

    @BindView(R.id.etDate)
    EditText etDate;
    @BindView(R.id.etTotal)
    EditText etTotal;
    @BindView(R.id.etLitres)
    EditText etLitres;
    @BindView(R.id.etKM)
    EditText etKm;
    @BindView(R.id.etPrice)
    EditText etPrice;
    @BindView(R.id.cbFullTank)
    CheckBox cbFull;
    @BindView(R.id.spinnerRefuel)
    Spinner spinner;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.etKMperLitre)
    EditText etKmPerL;
    Button btSelectMonth;
    private Unbinder unbinder;
    private ChartView ExpensechartView;
    private BarChart barChart;
    List<Abastecimento> abastecimentos;
    List<Abastecimento> data;
    GenerateData mGenerator;
    AbastecimentoDao abastecimentoDao;
    MonthYearPickerDialog pd;

    private int[] colors = {
            Color.rgb(104, 241, 175),
            Color.rgb(164, 228, 251),
            Color.rgb(242, 247, 158),
            Color.rgb(255, 102, 0)
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_refuel, container, false);
        PieChart pieChart = (PieChart) view.findViewById(R.id.chart);
        barChart = (BarChart) view.findViewById(R.id.bar_chart);
        btSelectMonth = (Button) view.findViewById(R.id.bt_select_month);
        barChart.getAxisRight().setDrawLabels(false);
        barChart.getAxisLeft().setDrawLabels(false);
        barChart.getXAxis().setDrawLabels(true);
        barChart.getXAxis().setAxisMinimum(0);
        barChart.getXAxis().setAxisMaximum(30);
        unbinder = ButterKnife.bind(this, view);
        DatePickerDialog datePickerDialog = customDatePicker();
        //((ViewGroup) datePickerDialog.getDatePicker()).findViewById(Resources.getSystem().getIdentifier("day", "id", "android")).setVisibility(View.GONE);
        pd = new MonthYearPickerDialog();
        pd.setListener(this);
        btSelectMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pd.show(getFragmentManager(), "MonthYearPickerDialog");
            }
        });
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(getContext(), "casosa-db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession session = daoMaster.newSession();
        abastecimentoDao  = session.getAbastecimentoDao();

        Date date1 = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date1);
        onDateSet(null, c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, 0);
        return view;
    }

    private DatePickerDialog customDatePicker() {
        DatePickerDialog dpd = new DatePickerDialog(getContext(), null, 2014, 1, 24);
        try {

            Field[] datePickerDialogFields = dpd.getClass().getDeclaredFields();
            for (Field datePickerDialogField : datePickerDialogFields) {
                if (datePickerDialogField.getName().equals("mDatePicker")) {
                    datePickerDialogField.setAccessible(true);
                    DatePicker datePicker = (DatePicker) datePickerDialogField
                            .get(dpd);
                    Field datePickerFields[] = datePickerDialogField.getType()
                            .getDeclaredFields();
                    for (Field datePickerField : datePickerFields) {
                        if ("mDayPicker".equals(datePickerField.getName())
                                || "mDaySpinner".equals(datePickerField
                                .getName())) {
                            datePickerField.setAccessible(true);
                            Object dayPicker = new Object();
                            dayPicker = datePickerField.get(datePicker);
                            ((View) dayPicker).setVisibility(View.GONE);
                        }
                    }
                }

            }
        } catch (Exception ex) {
        }
        return dpd;
    }

    private DatePickerDialog createDialogWithoutDateField() {
        DatePickerDialog dpd = new DatePickerDialog(getContext(), null, 2014, 1, 24);
        try {
            java.lang.reflect.Field[] datePickerDialogFields = dpd.getClass().getDeclaredFields();
            for (java.lang.reflect.Field datePickerDialogField : datePickerDialogFields) {
                if (datePickerDialogField.getName().equals("mDatePicker")) {
                    datePickerDialogField.setAccessible(true);
                    DatePicker datePicker = (DatePicker) datePickerDialogField.get(dpd);
                    java.lang.reflect.Field[] datePickerFields = datePickerDialogField.getType().getDeclaredFields();
                    for (java.lang.reflect.Field datePickerField : datePickerFields) {
                        Log.i("test", datePickerField.getName());
                        if ("mDaySpinner".equals(datePickerField.getName())) {
                            datePickerField.setAccessible(true);
                            Object dayPicker = datePickerField.get(datePicker);
                            ((View) dayPicker).setVisibility(View.GONE);
                        }
                    }
                }
            }
        }
        catch (Exception ex) {
        }
        return dpd;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Refuel Fragment", "Reload: " + MinhaGasosaPreference.getReloadRefuel(getContext()));
        init();
    }

    private void init() {
        loadRefuel();
        loadSpinnerData();
        getKmPerLitre();
        disableTexts();
    }


    @OnClick(R.id.fab)
    void newRefuel() {
        Intent intent = new Intent(getActivity(), NewRefuelActivity.class);
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void disableTexts() {
        disableTexts(etDate);
        disableTexts(etKm);
        disableTexts(etLitres);
        disableTexts(etPrice);
        disableTexts(etTotal);
        disableTexts(etKmPerL);

        cbFull.setFocusable(false);
        cbFull.setEnabled(false);
        cbFull.setCursorVisible(false);
        cbFull.setKeyListener(null);
        cbFull.setBackgroundColor(Color.TRANSPARENT);
        cbFull.setTextColor(Color.BLACK);
    }
    private void disableTexts(EditText editText){

        editText.setFocusable(false);
        editText.setEnabled(false);
        editText.setCursorVisible(false);
        editText.setKeyListener(null);
        editText.setBackgroundColor(Color.TRANSPARENT);
        editText.setTextColor(Color.BLACK);
    }

    private void loadRefuel(){
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(getContext(), "casosa-db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession session = daoMaster.newSession();
        AbastecimentoDao aDao = session.getAbastecimentoDao();
        abastecimentos  = aDao.loadAll();
        if (abastecimentos.size()>0){
            mGenerator = new GenerateData(abastecimentos);
            Abastecimento last = abastecimentos.get(abastecimentos.size()-1);
            loadData(last);
        }
    }


    private void loadData(Abastecimento mAbastecimento){
        Calendar c = Calendar.getInstance();
        c.setTime(mAbastecimento.getDataAbastecimento());
        int month =c.get(Calendar.MONTH)+1;
        String data =  c.get(Calendar.DATE)+"/"+
                month + "/"+
                c.get(Calendar.YEAR);
        etDate.setText(data);
        etKm.setText(mAbastecimento.getOdometro().toString());
        etLitres.setText(mAbastecimento.getLitros().toString());
        etPrice.setText(mAbastecimento.getPrecoCombustivel().toString());
        etTotal.setText(mAbastecimento.getPrecoTotal().toString());
        Log.d("Refuel RESTORED", "" + mAbastecimento.getTanqueCheio());
        cbFull.setChecked(mAbastecimento.getTanqueCheio());
    }

    @OnItemSelected(R.id.spinnerRefuel)
    public void itemSelected (Spinner spn, int position) {
        loadData(data.get(position));
    }

    @OnItemSelected(value = R.id.spinnerRefuel,
            callback = NOTHING_SELECTED)
    public void nadaSelected () {
    }

    private void loadSpinnerData() {
        data = new ArrayList<>();
        for (Abastecimento a: abastecimentos) {
            data.add(a.getCopy());
        }
        Collections.reverse(data);
        ArrayAdapter<Abastecimento> dataAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_item, data);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    public void getKmPerLitre() {
        if(mGenerator != null){
            double kmLitre = mGenerator.getKmPerLitre();
            if(kmLitre>0){
                etKmPerL.setText(String.valueOf(kmLitre));
                // Alteração momentanea para salvar o consumo por litro
                MinhaGasosaPreference.setConsumoUrbanoPrimario((float) kmLitre, getContext());
                MinhaGasosaPreference.setConsumoUrbanoSecundario((float) kmLitre, getContext());
                MinhaGasosaPreference.setConsumoRodoviarioPrimario((float) kmLitre, getContext());
                MinhaGasosaPreference.setConsumoRodoviarioSecundario((float) kmLitre, getContext());
            }else {
                etKmPerL.setText("ND");
            }
        }else{
            etKmPerL.setText("ND");
        }

    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int i2) {
        //abastecimentoDao
        QueryBuilder<Abastecimento> qb = abastecimentoDao.queryBuilder();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        try {
            Date date1 = dateFormat.parse(String.format("%02d", (Integer)month) + "/01/" + year);
            Calendar c = Calendar.getInstance();
            c.setTime(date1);
            c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
            Date date2 = dateFormat.parse(String.format("%02d", (Integer)month) + "/"+String.format("%02d", (Integer)c.get(Calendar.DAY_OF_MONTH))+"/" + year);
            qb.where(AbastecimentoDao.Properties.DataAbastecimento.between(date1.getTime(), date2.getTime()));
            List<Abastecimento> abastecimentos = qb.list();
            List<IBarDataSet> chartData = new ArrayList<IBarDataSet>();
            Calendar cal = Calendar.getInstance();
            barChart.clear();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            for (int i = 0; i < abastecimentos.size(); i++) {
                Abastecimento abs = abastecimentos.get(i);
                ArrayList<BarEntry> entry= new ArrayList<BarEntry>();
                cal.setTime(abs.getDataAbastecimento());
                int day = cal.get(Calendar.DAY_OF_MONTH);
                entry.add(new BarEntry(day, abs.getLitros()));
                BarDataSet set = new BarDataSet(entry, sdf.format(abs.getDataAbastecimento()));
                set.setColor(colors[i%3]);
                chartData.add(set);
            }
            BarData data = new BarData(chartData);
            barChart.setData(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}