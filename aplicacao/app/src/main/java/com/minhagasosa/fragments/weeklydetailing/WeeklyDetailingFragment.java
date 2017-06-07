package com.minhagasosa.fragments.weeklydetailing;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.google.android.gms.vision.text.Text;
import com.minhagasosa.ChartView;
import com.minhagasosa.R;
import com.minhagasosa.utils.MonthYearPickerDialog;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class WeeklyDetailingFragment extends Fragment implements  DatePickerDialog.OnDateSetListener {
    private ChartView chartViewCurrentMonth;
    private ChartView chartViewLastMonth;
    private boolean isFromLastYear = false;
    MonthYearPickerDialog pd;
    TextView selectedMonth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Detalhamento Semanal");
        View view = inflater.inflate(R.layout.activity_detalhamento_semanal, container, false);
        PieChart pieChartCurrentMonth = (PieChart) view.findViewById(R.id.chart_current_month);
        chartViewCurrentMonth = new ChartView(getContext(), pieChartCurrentMonth);


        PieChart pieChartLastMonth = (PieChart) view.findViewById(R.id.chart_last_month);
        chartViewLastMonth = new ChartView(getContext(), pieChartLastMonth);
        Button btChangeMonth = (Button) view.findViewById(R.id.bt_change_month);
        selectedMonth = (TextView) view.findViewById(R.id.tv_selected_month);
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        selectedMonth.setText(month+"/"+year);
        chartViewCurrentMonth.iniciaDistancias(String.format("%02d", month), String.format("%04d", year));
        pd = new MonthYearPickerDialog();
        pd.setListener(this);
        btChangeMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pd.show(getFragmentManager(), "MonthYearPickerDialog");
            }
        });
        return view;
    }


    private String getLastMonth(GregorianCalendar calendar) {
        // vai de 0 a 11;
        switch (calendar.get(GregorianCalendar.MONTH)) {
            case 0: // janeiro
                isFromLastYear = true;
                return "Dec";
            case 1: // fevereiro
                return "Jan";
            case 2: // março
                return "Feb";
            case 3: // abril
                return "Mar";
            case 4: // maio
                return "Apr";
            case 5: // junho
                return "May";
            case 6: // julho
                return "Jun";
            case 7: // agosto
                return "Jul";
            case 8: // setembro
                return "Aug";
            case 9: // outubro
                return "Set";
            case 10: // novembro
                return "Oct";
            case 11: // dezembro
                return "Nov";
        }
        return null;
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int i2) {
        chartViewCurrentMonth.clearChart();
        chartViewCurrentMonth.iniciaDistancias(String.format("%02d", month), String.format("%04d", year));
        selectedMonth.setText(month+"/"+year);
    }
}
