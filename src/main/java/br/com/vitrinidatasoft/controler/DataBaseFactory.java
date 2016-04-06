/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vitrinidatasoft.controler;

import br.com.vitrinidatasoft.utils.Constantes;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author mrhell
 */
public class DataBaseFactory  {
    private static EntityManagerFactory dataBaseFactory; 
    
    public DataBaseFactory(){        
    }
    
    public static EntityManagerFactory getInstance(){
        if (dataBaseFactory == null)
            dataBaseFactory = Persistence.createEntityManagerFactory(
                    Constantes.PERSISTENCE_UNIT_NAME);
        
        return dataBaseFactory;
                
    }
            
}
