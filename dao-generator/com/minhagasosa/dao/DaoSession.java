package com.minhagasosa.dao;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import com.minhagasosa.dao.Modelo;
import com.minhagasosa.dao.Carro;
import com.minhagasosa.dao.Rota;

import com.minhagasosa.dao.ModeloDao;
import com.minhagasosa.dao.CarroDao;
import com.minhagasosa.dao.RotaDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig modeloDaoConfig;
    private final DaoConfig carroDaoConfig;
    private final DaoConfig rotaDaoConfig;

    private final ModeloDao modeloDao;
    private final CarroDao carroDao;
    private final RotaDao rotaDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        modeloDaoConfig = daoConfigMap.get(ModeloDao.class).clone();
        modeloDaoConfig.initIdentityScope(type);

        carroDaoConfig = daoConfigMap.get(CarroDao.class).clone();
        carroDaoConfig.initIdentityScope(type);

        rotaDaoConfig = daoConfigMap.get(RotaDao.class).clone();
        rotaDaoConfig.initIdentityScope(type);

        modeloDao = new ModeloDao(modeloDaoConfig, this);
        carroDao = new CarroDao(carroDaoConfig, this);
        rotaDao = new RotaDao(rotaDaoConfig, this);

        registerDao(Modelo.class, modeloDao);
        registerDao(Carro.class, carroDao);
        registerDao(Rota.class, rotaDao);
    }
    
    public void clear() {
        modeloDaoConfig.getIdentityScope().clear();
        carroDaoConfig.getIdentityScope().clear();
        rotaDaoConfig.getIdentityScope().clear();
    }

    public ModeloDao getModeloDao() {
        return modeloDao;
    }

    public CarroDao getCarroDao() {
        return carroDao;
    }

    public RotaDao getRotaDao() {
        return rotaDao;
    }

}
