package com.minhagasosa.dao;

import android.content.Context;

import java.util.List;

import io.requery.Persistable;
import io.requery.android.BuildConfig;
import io.requery.android.sqlite.DatabaseSource;
import io.requery.reactivex.ReactiveEntityStore;
import io.requery.reactivex.ReactiveSupport;
import io.requery.sql.Configuration;
import io.requery.sql.EntityDataStore;
import io.requery.sql.TableCreationMode;

public class Datastore {
    private ReactiveEntityStore<Persistable> dataStore;

    public Datastore(Context context) {
        DatabaseSource source = new DatabaseSource(context, Models.DEFAULT, 1);

        if (BuildConfig.DEBUG)
            source.setTableCreationMode(TableCreationMode.DROP_CREATE);

        Configuration configuration = source.getConfiguration();
        dataStore = ReactiveSupport.toReactiveStore(new EntityDataStore<Persistable>(configuration));
    }

    public ReactiveEntityStore<Persistable> get() {
        return dataStore;
    }

    public List<Carro> getCarros(Marca marca) {
        return dataStore.select(Carro.class).where(Carro.MARCA.eq(marca)).get().toList();
    }
}
