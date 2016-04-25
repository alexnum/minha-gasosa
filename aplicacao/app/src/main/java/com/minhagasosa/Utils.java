package com.minhagasosa;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.util.Pair;

import com.minhagasosa.dao.DaoSession;
import com.minhagasosa.dao.Rota;
import com.minhagasosa.dao.RotaDao;
import com.minhagasosa.preferences.MinhaGasosaPreference;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leonardo on 25/04/2016.
 */
public class Utils {

    public static List<Rota> listRotas(DaoSession session, String select) {
        ArrayList<Rota> result = new ArrayList<Rota>();
        Cursor c = session.getDatabase().rawQuery(select, null);
        RotaDao rDao = session.getRotaDao();
        try {
            if (c.moveToFirst()) {
                do {
                    Rota r = new Rota();
                    r.setId(c.getLong(0));
                    r.setNome(c.getString(1));
                    r.setIdaEVolta(c.getInt(2) != 0);
                    r.setDistanciaIda(c.getFloat(3));
                    r.setDistanciaVolta(c.getFloat(4));
                    r.setRepeteSemana(c.getInt(5) != 0);
                    r.setRepetoicoes(c.getInt(6));
                    result.add(r);
                } while (c.moveToNext());
            }
        } finally {
            c.close();
        }
        return result;
    }

    public static List<Pair<String, Float>> calculaDistanciaPorRota(DaoSession session) {
        String select = "SELECT * FROM ROTA";

        ArrayList<Rota> listaRotas = (ArrayList<Rota>) listRotas(session, select);
        List<Pair<String, Float>> listaRotaDistancia = new ArrayList<>();

        for (int i = 0; i < listaRotas.size(); i++) {
            float atual;
            if (listaRotas.get(i).getIdaEVolta()) {
                atual = listaRotas.get(i).getDistanciaIda() + listaRotas.get(i).getDistanciaVolta();
                if (listaRotas.get(i).getRepeteSemana()) {
                    atual = atual * listaRotas.get(i).getRepetoicoes();
                }
            } else {
                atual = listaRotas.get(i).getDistanciaIda();
                if (listaRotas.get(i).getRepeteSemana()) {
                    atual = atual * listaRotas.get(i).getRepetoicoes();
                }
            }
            Log.e("RoutesDistancia", "Indice: " + i + " " + listaRotas.get(i).getNome() + ": " + atual);
            listaRotaDistancia.add(new Pair<>(listaRotas.get(i).getNome(), atual));
        }
        return listaRotaDistancia;
    }

    //CALCULO SEMANAL
    public static void calculaDistanciaTotal(DaoSession session, Context context) {
        List<Pair<String, Float>> listaRotaDistancia = calculaDistanciaPorRota(session);
        float soma = 0.0f;

        for (int i = 0; i < listaRotaDistancia.size(); i++) {
            soma += listaRotaDistancia.get(i).second;
        }
        MinhaGasosaPreference.setDistanciaTotal(soma, context);
    }
}
