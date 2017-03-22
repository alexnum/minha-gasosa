package com.minhagasosa;

import android.content.Context;
import android.util.Log;
import android.util.Pair;

import com.minhagasosa.dao.Datastore;
import com.minhagasosa.dao.Rota;
import com.minhagasosa.preferences.MinhaGasosaPreference;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Leonardo on 25/04/2016.
 */
public final class Utils {

    private Utils(){
    }

    public static List<Pair<String, Float>> calculaDistanciaPorRota(Context context, final String mes, final String ano) {
        String select = "SELECT * FROM ROTA";

        List<Rota> listaRotas = new Datastore(context).get()
                .select(Rota.class)
                .get().toList();
        List<Pair<String, Float>> listaRotaDistancia = new ArrayList<>();

        int iAno = ano != null ? Integer.parseInt(ano) : -1;
        int iMes = mes != null ? Integer.parseInt(mes) : -1;

        for (int i = 0; i < listaRotas.size(); i++) {
            Date data = listaRotas.get(i).getData();
            Calendar c = Calendar.getInstance();
            c.setTime(data);

            Rota current = listaRotas.get(i);

            if (ano == null && mes == null || c.get(Calendar.YEAR) + 0 == iAno && c.get(Calendar.MONTH) + 1 == iMes) {
                double atual = current.getDistanciaIda();

                if (current.isIdaEVolta())
                    atual += listaRotas.get(i).getDistanciaVolta();

                if (current.isRepeteSemana())
                    atual *= current.getRepeticoes();

                Log.e("RoutesDistancia", "Indice: " + i + " " + listaRotas.get(i).getNome() + ": " + atual + " " + listaRotas.get(i).getData());
                listaRotaDistancia.add(new Pair<>(listaRotas.get(i).getNome(), (float) atual));
            }
        }
        return listaRotaDistancia;
    }

    public static List<Pair<String, Float>> calculaDistanciaPorRotaSemanal(Context context, final String mes, final String ano) {
        Calendar cal = Calendar.getInstance();
        Calendar first = (Calendar) cal.clone();
        first.add(Calendar.DAY_OF_WEEK,
                first.getFirstDayOfWeek() - first.get(Calendar.DAY_OF_WEEK));
        Calendar last = (Calendar) first.clone();
        last.add(Calendar.DAY_OF_YEAR, 6);

        List<Rota> listaRotas = new Datastore(context).get()
                .select(Rota.class)
                .where(Rota.ROTINEIRA.eq(false))
                .and(Rota.DATA.gte(first.getTime()))
                .and(Rota.DATA.gte(last.getTime()))
                .get().toList();

        List<Pair<String, Float>> listaRotaDistancia = new ArrayList<>();

        int iAno = ano != null ? Integer.parseInt(ano) : -1;
        int iMes = mes != null ? Integer.parseInt(mes) : -1;

        for (int i = 0; i < listaRotas.size(); i++) {
            Date data = listaRotas.get(i).getData();
            Calendar c = Calendar.getInstance();
            c.setTime(data);

            Rota current = listaRotas.get(i);

            if (ano == null && mes == null ||
                    c.get(Calendar.YEAR) + 0 == iAno && c.get(Calendar.MONTH) + 1 == iMes) {
                double atual = current.getDistanciaIda();

                if (current.isIdaEVolta())
                    atual += listaRotas.get(i).getDistanciaVolta();

                if (current.isRepeteSemana())
                    atual *= current.getRepeticoes();

                Log.e("RoutesDistancia", "Indice: " + i + " " + listaRotas.get(i).getNome() + ": " + atual + " " + listaRotas.get(i).getData());
                listaRotaDistancia.add(new Pair<>(listaRotas.get(i).getNome(), (float) atual));
            }
        }
        return listaRotaDistancia;
    }

    public static void calculaDistanciaTotalC(Context context, final String mes, final String ano) {
        List<Pair<String, Float>> listaRotaDistancia = calculaDistanciaPorRota(context, mes, ano);
        float soma = 0.0f;

        for (int i = 0; i < listaRotaDistancia.size(); i++) {
            soma += listaRotaDistancia.get(i).second;
        }
        MinhaGasosaPreference.setDistanciaTotal(soma, context);
    }

    public static void calculaDistanciaTotalSemanalmente(Context context, final String mes, final String ano) {
        List<Pair<String, Float>> listaRotaDistancia = calculaDistanciaPorRotaSemanal(context, mes, ano);
        float soma = 0.0f;

        for (int i = 0; i < listaRotaDistancia.size(); i++) {
            soma += listaRotaDistancia.get(i).second;
        }
        MinhaGasosaPreference.setDistanciaTotal(soma, context);
    }

    public static float calculaDistanciaTotal(Context context, String mes, String ano) {
        List<Pair<String, Float>> listaRotaDistancia = calculaDistanciaPorRota(context, mes, ano);
        float total = 0.0f;
        Log.d("Utils", "num de rotas = " + listaRotaDistancia.size());

        for (int i = 0; i < listaRotaDistancia.size(); i++) {
            total += listaRotaDistancia.get(i).second;
        }
        return total;
    }

    public static List<Pair<String, Float>> calculaPrincipaisRotas(Context context, final String mes, String ano) {
        List<Pair<String, Float>> listaRotaDistancia = calculaDistanciaPorRota(context, mes, ano);
        List<Pair<String, Float>> listaOrdenada = new ArrayList<>();

        while (listaOrdenada.size() < 3 && listaRotaDistancia.size() != 0) {
            int index = 0;
            for (int i = 0; i < listaRotaDistancia.size(); i++) {
                if (listaRotaDistancia.get(i).second > listaRotaDistancia.get(index).second) {
                    index = i;
                }
            }
            Log.e("RotasPrincipais", "RotaPrincipal: " + listaRotaDistancia.get(index).first);
            listaOrdenada.add(listaRotaDistancia.remove(index));
        }

        return listaOrdenada;
    }

    public static List<Pair<String, Float>> calculaPrincipaisRotasSemanalmente(Context context, final String mes, String ano) {
        List<Pair<String, Float>> listaRotaDistancia = calculaDistanciaPorRotaSemanal(context, mes, ano);
        List<Pair<String, Float>> listaOrdenada = new ArrayList<>();

        while (listaOrdenada.size() < 3 && listaRotaDistancia.size() != 0) {
            int index = 0;
            for (int i = 0; i < listaRotaDistancia.size(); i++) {
                if (listaRotaDistancia.get(i).second > listaRotaDistancia.get(index).second) {
                    index = i;
                }
            }
            Log.e("RotasPrincipais", "RotaPrincipal: " + listaRotaDistancia.get(index).first);
            listaOrdenada.add(listaRotaDistancia.remove(index));
        }

        return listaOrdenada;
    }
}
