package com.minhagasosa.dao;

import io.requery.*;

@Entity
abstract class AbstractModelo {

    @Key @Generated
    Long id;
    Carro carro;
    String nome;
    String ano;
    Double consumoUrbanoGasolina;
    Double consumoRodoviarioGasolina;
    Double consumoUrbanoAlcool;
    Double consumoRodoviarioAlcool;
    Boolean flex;
}
