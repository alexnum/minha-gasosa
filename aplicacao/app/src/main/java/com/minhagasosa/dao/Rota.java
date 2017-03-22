package com.minhagasosa.dao;

import java.util.Date;

import io.requery.*;

@Entity
abstract class AbstractRota {

    @Key @Generated
    Long id;
    String nome;
    Boolean idaEVolta;
    Double distanciaIda;
    Double distanciaVolta;
    Boolean rotineira;
    Boolean repeteSemana;
    Integer repeticoes;
    Date data;
    long i;
}
