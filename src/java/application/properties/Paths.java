/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.properties;

import java.io.FileInputStream;

/**
 *
 * @author andre
 */
final public class Paths {
    // filepath to pathholder
    private final static String PATHS = "application\\properties\\Paths.properties";
    
    /**
     * wtf? modifiers and wtf am i doing
     */
    private static Paths instance = new Paths();

    private Paths() {
        if(instance != null) {
           throw new UnsupportedOperationException("no can do");
        }
    }
    
    public static Paths getInstance(){
        return instance;
    }

    FileInputStream getStreamFor(String MAPPING) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
