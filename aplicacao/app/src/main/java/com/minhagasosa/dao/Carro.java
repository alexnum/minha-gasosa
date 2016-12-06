package com.minhagasosa.dao;

import com.minhagasosa.dao.DaoSession;
import de.greenrobot.dao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "CARRO".
 */
public class Carro {

    private Long id;
    private String marca;
    private String ano;
    private Float consumoUrbanoGasolina;
    private Float consumoRodoviarioGasolina;
    private Float consumoUrbanoAlcool;
    private Float consumoRodoviarioAlcool;
    private Boolean isFlex;
    private String version;
    private Long modeloId;
    private String EntityDetached = "Entity is detached from DAO context";

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient CarroDao myDao;

    private Modelo modelo;
    private Long modelo__resolvedKey;


    public Carro() {
    }

    public Carro(Long id) {
        this.id = id;
    }

    public Carro(Long id, String marca, String ano, Float consumoUrbanoGasolina, Float consumoRodoviarioGasolina, Float consumoUrbanoAlcool, Float consumoRodoviarioAlcool, Boolean isFlex, String version, Long modeloId) {
        this.id = id;
        this.marca = marca;
        this.ano = ano;
        this.consumoUrbanoGasolina = consumoUrbanoGasolina;
        this.consumoRodoviarioGasolina = consumoRodoviarioGasolina;
        this.consumoUrbanoAlcool = consumoUrbanoAlcool;
        this.consumoRodoviarioAlcool = consumoRodoviarioAlcool;
        this.isFlex = isFlex;
        this.version = version;
        this.modeloId = modeloId;
    }

    /** called by internal mechanisms, do not call yourself. */
    public final void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getCarroDao() : null;
    }

    public final Long getId() {
        return id;
    }

    public final void setId(Long id) {
        this.id = id;
    }

    public final String getMarca() {
        return marca;
    }

    public final void setMarca(String marca) {
        this.marca = marca;
    }

    public final String getAno() {
        return ano;
    }

    public final void setAno(String ano) {
        this.ano = ano;
    }

    public final Float getConsumoUrbanoGasolina() {
        return consumoUrbanoGasolina;
    }

    public final void setConsumoUrbanoGasolina(Float consumoUrbanoGasolina) {
        this.consumoUrbanoGasolina = consumoUrbanoGasolina;
    }

    public final Float getConsumoRodoviarioGasolina() {
        return consumoRodoviarioGasolina;
    }

    public final void setConsumoRodoviarioGasolina(Float consumoRodoviarioGasolina) {
        this.consumoRodoviarioGasolina = consumoRodoviarioGasolina;
    }

    public final Float getConsumoUrbanoAlcool() {
        return consumoUrbanoAlcool;
    }

    public final void setConsumoUrbanoAlcool(Float consumoUrbanoAlcool) {
        this.consumoUrbanoAlcool = consumoUrbanoAlcool;
    }

    public final Float getConsumoRodoviarioAlcool() {
        return consumoRodoviarioAlcool;
    }

    public final void setConsumoRodoviarioAlcool(Float consumoRodoviarioAlcool) {
        this.consumoRodoviarioAlcool = consumoRodoviarioAlcool;
    }

    public final Boolean getIsFlex() {
        return isFlex;
    }

    public final void setIsFlex(Boolean isFlex) {
        this.isFlex = isFlex;
    }

    public final String getVersion() {
        return version;
    }

    public final void setVersion(String version) {
        this.version = version;
    }

    public final Long getModeloId() {
        return modeloId;
    }

    public final void setModeloId(Long modeloId) {
        this.modeloId = modeloId;
    }

    /** To-one relationship, resolved on first access. */
    public final Modelo getModelo() {
        Long __key = this.modeloId;
        if (modelo__resolvedKey == null || !modelo__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException(EntityDetached);
            }
            ModeloDao targetDao = daoSession.getModeloDao();
            Modelo modeloNew = targetDao.load(__key);
            synchronized (this) {
                modelo = modeloNew;
            	modelo__resolvedKey = __key;
            }
        }
        return modelo;
    }

    public final void setModelo(Modelo modelo) {
        synchronized (this) {
            this.modelo = modelo;
            modeloId = modelo == null ? null : modelo.getId();
            modelo__resolvedKey = modeloId;
        }
    }

    /** Convenient call for {@link AbstractDao#delete(Object)}. Entity must attached to an entity context. */
    public final void delete() {
        if (myDao == null) {
            throw new DaoException(EntityDetached);
        }    
        myDao.delete(this);
    }

    /** Convenient call for {@link AbstractDao#update(Object)}. Entity must attached to an entity context. */
    public final void update() {
        if (myDao == null) {
            throw new DaoException(EntityDetached);
        }    
        myDao.update(this);
    }

    /** Convenient call for {@link AbstractDao#refresh(Object)}. Entity must attached to an entity context. */
    public final void refresh() {
        if (myDao == null) {
            throw new DaoException(EntityDetached);
        }    
        myDao.refresh(this);
    }

    @Override
    public String toString(){
        return getVersion();
    }

}
