/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turing.solutions.dy.util.enums;

import java.util.EnumSet;

/**
 *
 * @author Alan
 */
public enum DYGeneralEnums {

    USER_INFO("userSessionInfo"),
    USUARIO("usuario"),
    EMAIL("email"),
    ROL("roles_usuario"),
    ID_USER("id_usuario"),
    NOMBRES("nombres"),
    ROL_CLIENTE("Cliente"),
    ROL_PROVEEDOR("Proveedor"),
    ROL_ADMINISTRADOR("Administrador"),
    CAT_HOGAR("Hogar"),
    ESTATUS_PROVEEDOR_NUEVO("Nuevo"),
    ESTATUS_PROVEEDOR_PENDIENTE("Pendiente"),
    ESTATUS_PROVEEDOR_ACEPTADO("Aceptado"),
    ESTATUS_PROVEEDOR_RECHAZADO("Rechazado"),
    NOMBRE_IMAGEN_SUCURSAL("MainImage.jpg"),
    EDO_CUENTA("EdoCuenta"),
    COMPROBANTE_DOMICILIO("ComprobanteDomicilio"),
    IFE("IFE"),
    FILE("Init");

    private final String value;
    
    

    private DYGeneralEnums(String value) {
        this.value = value;
    }
    

    public String getValue() {
        return value;
    }

    public static DYGeneralEnums getEnumFromValue(String val) {
        for (DYGeneralEnums en : DYGeneralEnums.values()) {
            
            if (val.equals(en.getValue())) {
                return en;

            }
        }
        return null;
    }

}
