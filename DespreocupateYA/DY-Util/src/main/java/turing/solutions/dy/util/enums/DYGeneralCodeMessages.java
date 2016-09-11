/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turing.solutions.dy.util.enums;

/**
 *
 * @author Alan
 */
public enum DYGeneralCodeMessages {

    SUCCESS("200", "Success"),
    ERROR_GENERAL("500", "Error interno sel servidor/negocios"),
    ERROR_PARAMETROS_INSUFICIENTES("1001", "Parámetros insuficientes"),
    ERROR_404("404", "Recurso no disponible"),
    ERROR_RC_EMPTY("50", "El objeto usuario está vacio"),
    ERROR_RC_EXISTE_USUARIO("51", "El usuario ya existe"),
    ERROR_RC_SIN_ROL("52", "No no fue posible encontrar el Rol"),
    ERROR_RP_EMPTY("60", "El objeto usuario está vacio"),
    ERROR_RP_EXISTE_PROVEEDOR("61", "El usuario ya existe"),
    ERROR_RP_SIN_ROL("62", "No no fue posible encontrar el Rol"),
    ERROR_RP_RFC_DUPLICADO("63","Ya existe un proveedor con el RFC:"),
    PROVEEDOR_ID("idProveedor","Id del proveedor");

    private final String codigo;
    private final String descripcion;

    private DYGeneralCodeMessages(String codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;

    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public static DYGeneralCodeMessages getEnumFromString(String str) {
        DYGeneralCodeMessages enu = null;
        if (str == null) {
            enu = ERROR_GENERAL;
        } else {
            String code = str.split(":")[0];
            for (DYGeneralCodeMessages en : values()) {
                if (en.getCodigo().equals(code)) {
                    enu = en;
                    break;
                }
            }
        }
        return enu;
    }

    @Override
    public String toString() {
        return codigo+":"+descripcion;
    }
    

}
