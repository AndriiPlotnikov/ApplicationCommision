/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.application.properties;

import java.io.InputStream;
import java.util.Properties;

/**
 * wrapper for AccessRights.properties
 * 
 * @author andre
 */
public class AccessRights {

    // codename for access rights file
    private final static String ACCESS_RIGHTS = "AccessRights";
    // singleton instance
    private static final AccessRights instance = new AccessRights();
    // wrapped properties
    private final Properties props = new Properties();

    /**
     * constructs only sigleton instance.
     * 
     * throws UnsupportedOperationException if instance is already instanciated
     */
    private AccessRights() {
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
    public static AccessRights getInstance() {
        return instance;
    }

    /**
     * get saved properties
     * 
     * @return properties
     */
    final public Properties getProperties() {
        return props;
    }
    
    /**
     * searches for updating file and updates singleton instacne
     */
    public void updateInstance() {
        try (InputStream istream = Paths.getInstance().getStreamFor(ACCESS_RIGHTS)) {
            props.load(istream);
        } catch (Exception e) {
            //System.out.println(e);
            // log here
        }
    }
}
