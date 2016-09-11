/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turing.solutions.dy.business.micuenta;

import java.util.List;
import turing.solutions.dy.persistence.model.Domicilios;

/**
 *
 * @author Alan
 */
public interface DireccionesService {

    List<Domicilios> getDomicilios(String idUsuario);
    
}
