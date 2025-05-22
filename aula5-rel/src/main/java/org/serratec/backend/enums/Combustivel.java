package org.serratec.backend.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.serratec.backend.infra.EnumException;

public enum Combustivel {
    DIESEL(1, "Diesel"),
    ALCOOL(2, "Alcool"),
    FLEX(3, "Flex"),
    GASOLINA(4, "Gasolina");

    private Integer codigo;
    private String tipo;

    Combustivel(Integer codigo, String tipo) {
        this.codigo = codigo;
        this.tipo = tipo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @JsonCreator
    public static Combustivel validarCombustivel(Integer valor){
        for(Combustivel c : Combustivel.values()){
            if(valor.equals(c.getCodigo())){
                return c;
            }
        }
        throw new EnumException("Tipo de combustivel invalido!");
    }
}
