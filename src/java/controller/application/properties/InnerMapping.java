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
 *
 * @author andre
 */
public class InnerMapping {

    private final static String INNER_MAPPING = "InnerMapping";
    // singleton instance
    private static final InnerMapping instance = new InnerMapping();
    // wrapped properties
    private final Properties props = new Properties();

    private InnerMapping() {
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
    public static InnerMapping getInstance() {
        return instance;
    }

    /**
     * searches for updating file and updates singleton instacne
     */
    public void updateInstance() {
        try (InputStream istream = Paths.getInstance().getStreamFor(INNER_MAPPING)) {
            props.load(istream);
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
