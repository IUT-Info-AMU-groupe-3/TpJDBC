/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.amu.jdbc.dao;

import fr.amu.jdbc.dao.impl.DAOProfI;
import fr.amu.jdbc.model.Prof;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public abstract class DAOProf implements DAO<Prof> {
    final static private DAOProf instance = new DAOProfI();

    public static DAOProf getInstance() {
        return instance;
    }
}
