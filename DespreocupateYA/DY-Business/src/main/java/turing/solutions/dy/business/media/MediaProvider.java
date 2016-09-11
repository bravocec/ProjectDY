/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turing.solutions.dy.business.media;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import turing.solutions.dy.persistence.dao.categorias.CategoriasServiciosDAO;
import turing.solutions.dy.persistence.model.CategoriasServicios;
import turing.solutions.dy.util.Base64;

/**
 *
 * @author Alan
 */
@Service
public class MediaProvider implements MediaService{
    
    @Autowired
    private CategoriasServiciosDAO<CategoriasServicios> catServiciosDAO;
    
    private final Logger log = Logger.getLogger(MediaProvider.class);
    
    @Override
    @Transactional(readOnly = true)
    public Map<String,Object> getImagenesIndex(){
        Map<String,Object> map = null;
        
        List<CategoriasServicios> categorias = this.catServiciosDAO.findAll(CategoriasServicios.class);
        
        if(categorias != null && !categorias.isEmpty()){
            map = new HashMap<String,Object>();
            for(CategoriasServicios cat : categorias){
                String img = "N/A";
                File file = new File(cat.getRuta());
                if(file.exists()){
                    try{
                        FileInputStream fInput = new FileInputStream(file);
                        byte[] array = new byte[Long.valueOf(file.length()).intValue()];
                        fInput.read(array);
                        img = Base64.encodeToString(array, false);
                    }catch(Exception e){
                        log.error("Error al obtener la imagen en Base64: "+e.getMessage());
                    }
                }
                map.put(cat.getDescripcion(),img);
            }
        }
        return map;
    }
    
}
