/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.commands;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author andre
 */
public class CommandFactory {

    // singleton instance

    private static CommandFactory instance = new CommandFactory();

    //cache of commands
    private Map<String, Command> cache = new HashMap<>();

    /**
     *
     */
    private CommandFactory() {
        fillCache();
    }

    public static CommandFactory getInstance() {
        return instance;
    }

    public Command getCommand(String URL) {
        Command com = cache.get(URL);
        if (com == null) {
            //com = new Error404();
        }
        return com;

    }

    private void fillCache() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void refresh(){
        fillCache();
    }

}
