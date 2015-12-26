/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.application.properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * properties wrapper
 *
 * @author andre
 */
public class Mapping {

    // keyword to find path
    private final static String MAPPING = "Mapping";
    // singleton instance
    private static final Mapping instance = new Mapping();
    // wrapped properties
    private final Properties props = new Properties();

    private Mapping() {
        if (instance != null) {
            throw new UnsupportedOperationException("can't instantiate");
        }
        updateInstance();
    }

    /**
     * return singleton instance of Properties object
     *
     * @return singleton instance
     */
    final public static Mapping getInstance() {
        return instance;
    }

    final public Properties getProperties() {
        return props;
    }

    /**
     * searches for updating file and updates singleton instance
     */
    final public void updateInstance() {
        try (InputStream istream = Paths.getInstance().getStreamFor(MAPPING)) {
            props.load(istream);
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
