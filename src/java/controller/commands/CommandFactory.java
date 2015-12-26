/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.commands;

import controller.application.properties.Mapping;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andre
 */
public class CommandFactory {
    private boolean debug = false;
    
    // singleton instance
    final private static CommandFactory instance = new CommandFactory();

    //cache of commands
    private Map<String, Command> cache = new HashMap<>();

    /**
     *
     */
    private CommandFactory() {
        refresh();
    }

    public static CommandFactory getInstance() {
        return instance;
    }

    public Command getCommand(String URL) {
        refresh();
        Command com = cache.get(URL);
        if (com == null) {

            // if you see this - delete test command and create proper error
            com = new TestCommand();
        }
        if (debug) {
            System.out.println("WARNING!!!!!!!!!!!!!!!!!!!!!!");

            for (Entry<String, Command> entry : cache.entrySet()) {
                System.out.println(entry.getKey() + " is key");
            }
            System.out.println("EnD Warning!");
        }
        return com;

    }

    private void fillCache() {

        Map<String, Command> temp = new HashMap<>();
        if(debug){
            System.out.println("starting fill cache");
        }
        for (Map.Entry<Object, Object> entry : Mapping.getInstance().getProperties().entrySet()) {
            if(debug){
                System.out.println("we start fillling in cache object");
            }
            try {
                Command com = (Command) Class.forName((String) entry.getValue()).newInstance();
                temp.put((String) entry.getKey(), com);
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                Logger.getLogger(CommandFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(debug){
            System.out.println("cache filled, exiting");
        }
        cache = temp;
    }

    public void refresh() {
        fillCache();
    }

}
