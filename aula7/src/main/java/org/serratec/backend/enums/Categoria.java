package org.serratec.backend.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.serratec.backend.infra.EnumException;

public enum Categoria {
    SUV,HATCH,PICAPE,SEDAN,CONVERSIVEL,COUPE;

    @JsonCreator
    public static Categoria verificarCategoria(String valor){
        for(Categoria cat : Categoria.values()){
            if(valor.equals(cat.name())){
                return cat;
            }
        }
        throw new EnumException("Categoria de carro invalida!");
    }
}
