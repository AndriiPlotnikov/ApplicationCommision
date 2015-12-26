/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.application.properties;

import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author andre
 */
final public class Paths {

    // filepath to pathholder
    private final static String PATHS = "Paths.properties";
    // singleton instance
    private static final Paths instance = new Paths();

    private Paths() {
        if (instance != null) {
            throw new UnsupportedOperationException("no can do");
        }
    }

    public static Paths getInstance() {
        return instance;
    }

    InputStream getStreamFor(String path) throws Exception {
        Properties prop = new Properties();
        try (InputStream istream = getClass().getResourceAsStream(PATHS)) {
            if (istream == null) {
                throw new Exception("paths not found");
            }
            prop.load(istream);
        }

        String filename = prop.getProperty(path);
        if (filename == null) {
            throw new Exception("no such path in pathlist");
        }
        return getClass().getResourceAsStream(filename);
    }

}
