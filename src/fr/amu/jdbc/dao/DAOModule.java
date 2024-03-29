/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.amu.jdbc.dao;

import fr.amu.jdbc.dao.impl.DAOModuleI;
import fr.amu.jdbc.model.Module;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public abstract class DAOModule implements DAO<Module>{
    final static private DAOModule instance = new DAOModuleI();
    
    abstract public Module getByCode(String code);

    public static DAOModule getInstance() {
        return instance;
    }
}
