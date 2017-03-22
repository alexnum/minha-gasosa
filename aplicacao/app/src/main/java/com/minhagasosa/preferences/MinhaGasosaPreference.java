package com.minhagasosa.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.minhagasosa.R;
import com.minhagasosa.dao.Carro;
import com.minhagasosa.dao.Datastore;
import com.minhagasosa.dao.Marca;
import com.minhagasosa.dao.Modelo;

import java.util.NoSuchElementException;

/**
 * Created by Vinicius Silva on 06/04/2016.
 */
public final class MinhaGasosaPreference {

    final private static String PREFERENCE = "com.minhagasosa.preference";
    private MinhaGasosaPreference() {

    }

    private static SharedPreferences.Editor getSharedPreference(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE,
                Context.MODE_PRIVATE);
        return preferences.edit();
    }

    public static void putWithPotency(boolean withPotency, Context context) {
        SharedPreferences.Editor editor = getSharedPreference(context);
        editor.putBoolean(context.getString(R.string.com_potencia), withPotency);
        editor.commit();
    }

    public static void putPotency(float potency, Context context) {
        SharedPreferences.Editor editor = getSharedPreference(context);
        editor.putFloat(context.getString(R.string.sharedPotencia), potency);
        editor.commit();
    }

    public static boolean getWithPotency(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE,
                Context.MODE_PRIVATE);
        return preferences.getBoolean(context.getString(R.string.com_potencia), false);

    }

    public static float getPotency(Context context) {
        final SharedPreferences preferences = context.getSharedPreferences(PREFERENCE,
                Context.MODE_PRIVATE);
        return preferences.getFloat(context.getString(R.string.sharedPotencia), -1);
    }


    public static void putPrice(float price, Context context) {
        SharedPreferences.Editor editor = getSharedPreference(context);
        editor.putFloat(context.getString(R.string.shared_price), price);
        editor.commit();
    }


    public static float getPrice(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE,
                Context.MODE_PRIVATE);
        return preferences.getFloat(context.getString(R.string.shared_price), 0);

    }


    public static void setFirstTime(boolean is_first_time, Context context) {
        SharedPreferences.Editor editor = getSharedPreference(context);
        editor.putBoolean(context.getString(R.string.is_first_time), is_first_time);
        editor.commit();
    }

    public static boolean getFirstTime(Context context) {
        final SharedPreferences preferences = context.getSharedPreferences(PREFERENCE,
                Context.MODE_PRIVATE);
        return preferences.getBoolean(context.getString(R.string.is_first_time), true);
    }

    public static void setCarroIsFlex(boolean is_flex, Context context) {
        SharedPreferences.Editor editor = getSharedPreference(context);
        editor.putBoolean(context.getString(R.string.is_flex), is_flex);
        editor.commit();
    }

    public static boolean getCarroIsFlex(Context context) {
        final SharedPreferences preferences = context.getSharedPreferences(PREFERENCE,
                Context.MODE_PRIVATE);
        return preferences.getBoolean(context.getString(R.string.is_flex), false);
    }

    public static void setConsumoUrbanoPrimario(float consumo, Context context) {
        SharedPreferences.Editor editor = getSharedPreference(context);
        editor.putFloat(context.getString(R.string.consumoUrbanoPrimario), consumo);
        editor.commit();
    }

    public static float getConsumoUrbanoPrimario(Context context) {
        final SharedPreferences preferences = context.getSharedPreferences(PREFERENCE,
                Context.MODE_PRIVATE);
        return preferences.getFloat(context.getString(R.string.consumoUrbanoPrimario), -1);
    }

    public static void setMarca(Long id, Context context) {
        SharedPreferences.Editor editor = getSharedPreference(context);
        editor.putLong(context.getString(R.string.marcaDoCarro), id);
        editor.commit();
    }

    public static Marca getMarca(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE,Context.MODE_PRIVATE);
        Long id = preferences.getLong(context.getString(R.string.marcaDoCarro), -1L);
        return new Datastore(context).get()
                .select(Marca.class)
                .where(Marca.ID.eq(id))
                .get().firstOrNull();
    }

    public static void setCarro(Long id, Context context) {
        SharedPreferences.Editor editor = getSharedPreference(context);
        editor.putLong(context.getString(R.string.modeloDoCarro), id);
        editor.commit();
    }

    public static Carro getCarro(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
        Long id = preferences.getLong(context.getString(R.string.modeloDoCarro), -1L);
        return new Datastore(context).get()
                .select(Carro.class)
                .where(Carro.ID.eq(id))
                .get().firstOrNull();
    }

    public static void setModelo(Long id, Context context) {
        SharedPreferences.Editor editor = getSharedPreference(context);
        editor.putLong(context.getString(R.string.versaoDoCarro), id);
        editor.commit();
    }

    public static Modelo getModelo(Context context) {
        final SharedPreferences preferences = context.getSharedPreferences(PREFERENCE,
                Context.MODE_PRIVATE);
        Long id = preferences.getLong(context.getString(R.string.versaoDoCarro), -1L);
        return new Datastore(context).get()
                .select(Modelo.class)
                .where(Modelo.ID.eq(id))
                .get().firstOrNull();
    }

    public static void setConsumoUrbanoSecundario(float consumo, Context context) {
        SharedPreferences.Editor editor = getSharedPreference(context);
        editor.putFloat(context.getString(R.string.consumoUrbanoSecundario), consumo);
        editor.commit();
    }



    public static float getConsumoUrbanoSecundario(Context context) {
        final SharedPreferences preferences = context.getSharedPreferences(PREFERENCE,
                Context.MODE_PRIVATE);
        return preferences.getFloat(context.getString(R.string.consumoUrbanoSecundario), -1);
    }

    public static void setConsumoRodoviarioPrimario(float consumo, Context context) {
        SharedPreferences.Editor editor = getSharedPreference(context);
        editor.putFloat(context.getString(R.string.consumoRodoviarioPrimario), consumo);
        editor.commit();
    }

    public static float getConsumoRodoviarioPrimario(Context context) {
        final SharedPreferences preferences = context.getSharedPreferences(PREFERENCE,
                Context.MODE_PRIVATE);
        return preferences.getFloat(context.getString(R.string.consumoRodoviarioPrimario), -1);
    }

    public static void setConsumoRodoviarioSecundario(float consumo, Context context) {
        SharedPreferences.Editor editor = getSharedPreference(context);
        editor.putFloat(context.getString(R.string.consumoRodoviarioSecundario), consumo);
        editor.commit();
    }



    public static float getConsumoRodoviarioSecundario(Context context) {
        final SharedPreferences preferences = context.getSharedPreferences(PREFERENCE,
                Context.MODE_PRIVATE);
        return preferences.getFloat(context.getString(R.string.consumoRodoviarioSecundario), -1);
    }

    public static void setDone(boolean done, Context context) {
        SharedPreferences.Editor editor = getSharedPreference(context);
        editor.putBoolean("done", done);
        editor.commit();
    }

    public static boolean getDone(Context context) {
        final SharedPreferences preferences = context.getSharedPreferences(PREFERENCE,
                Context.MODE_PRIVATE);
        return preferences.getBoolean("done", false);
    }

    public static void setDistanciaTotal(float distancia, Context context) {
        SharedPreferences.Editor editor = getSharedPreference(context);
        editor.putFloat(context.getString(R.string.distancia_total), distancia);
        editor.commit();
    }

    public static float getDistanciaTotal(Context context) {
        final SharedPreferences preferences = context.getSharedPreferences(PREFERENCE,
                Context.MODE_PRIVATE);
        return preferences.getFloat(context.getString(R.string.distancia_total), 0.0f);
    }

    public static void putValorMaximoParaGastar(float valor, Context context) {
        SharedPreferences.Editor editor = getSharedPreference(context);
        editor.putFloat(context.getString(R.string.shared_valor_maximo_gastar), valor);
        editor.commit();
    }

    public static float getValorMaximoParaGastar(Context context) {
        final SharedPreferences preferences = context.getSharedPreferences(PREFERENCE,
                Context.MODE_PRIVATE);
        return preferences.getFloat(context.getString(R.string.shared_valor_maximo_gastar), Float.MAX_VALUE);
    }

    public static void putCapacidadeDoTanque(float valor, Context context) {
        SharedPreferences.Editor editor = getSharedPreference(context);
        editor.putFloat(context.getString(R.string.shared_capacidade_tanque), valor);
        editor.commit();
    }

    public static float getCapacidadeDoTanque(Context context) {
        final SharedPreferences preferences = context.getSharedPreferences(PREFERENCE,
                Context.MODE_PRIVATE);
        return preferences.getFloat(context.getString(R.string.shared_capacidade_tanque), (float)-1.0);
    }

    public static void putPrecoSecundario(float valor, Context context) {
        SharedPreferences.Editor editor = getSharedPreference(context);
        editor.putFloat(context.getString(R.string.shared_preco_secundario), valor);
        editor.commit();
    }

    public static float getPrecoSecundario(Context context) {
        final SharedPreferences preferences = context.getSharedPreferences(PREFERENCE,
                Context.MODE_PRIVATE);
        return preferences.getFloat(context.getString(R.string.shared_preco_secundario), (float) - 1.0);
    }
}
