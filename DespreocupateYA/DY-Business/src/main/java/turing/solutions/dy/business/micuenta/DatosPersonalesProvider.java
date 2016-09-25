/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turing.solutions.dy.business.micuenta;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import turing.solutions.dy.business.common.CommonService;
import turing.solutions.dy.persistence.dao.usuarios.UsuariosDAO;
import turing.solutions.dy.persistence.model.Telefonos;
import turing.solutions.dy.persistence.model.Usuarios;
import turing.solutions.dy.util.exceptions.DYRuntimeException;

/**
 *
 * @author Alan
 */
@Service
public class DatosPersonalesProvider implements DatosPersonalesService {

    @Autowired
    private UsuariosDAO<Usuarios> usuriosDAO;

    @Autowired
    private CommonService commonService;

    @Override
    @Transactional(readOnly = true)
    public Usuarios getDatosPersonales(Integer idUsuario) {
        if (idUsuario == null || idUsuario == 0) {
            throw new DYRuntimeException("El id usuario viene nulo");
        }
        Usuarios usuario = this.usuriosDAO.findById(idUsuario);

        return usuario;
    }

    @Override
    @Transactional
    public String actualizaDatos(String idUsuario, String nombres, String apellidos, String email, String telefono) {
        String estatus = null;
        if (idUsuario == null || idUsuario.isEmpty()) {
            throw new DYRuntimeException("El id del usuario está nulo/no existe");
        }

        Usuarios usuario = this.usuriosDAO.findById(Integer.valueOf(idUsuario));

        usuario.setNombres(nombres);
        usuario.setApellidos(apellidos);
        usuario.setEmail(email);

        //usuario.getTelefonosList();
        List<Telefonos> listaTelefonos = usuario.getTelefonosList();

        if (listaTelefonos != null && !listaTelefonos.isEmpty()) {
            for (Telefonos tel : listaTelefonos) {
                if (tel.getTipoTelefonoIdTipotelefono().getDescripcion().equals("Movil")) {
                    tel.setTelefono(telefono);
                    break;
                }
            }
            this.usuriosDAO.update(usuario);
        }

        return estatus;
    }

    @Override
    @Transactional
    public Map<String, Object> acualizaPassword(Map<String, String> elements) {
        Map<String, Object> resultado = new HashMap<>();
        Usuarios currentUser = this.commonService.currentUser();
        if (!currentUser.getPassword().equals(elements.get("password_actual"))) {
            resultado.put("message", "Tu antiguio password es inválido, favor de verificarlo");
            return resultado;
        }
        
        if (!elements.get("password").equals(elements.get("another_password"))) {
            resultado.put("message", "No coincide la confirmación de tu nuevo password, verificalo nuevamente");
            return resultado;
        }
        
        if (currentUser.getPassword().equals(elements.get("password"))) {
            resultado.put("message", "Tu nuevo password no debe de ser igual al password anterior");
            return resultado;
        }
        
        currentUser.setPassword(elements.get("password"));
        this.usuriosDAO.update(currentUser);
        
        return resultado;
    }

}
