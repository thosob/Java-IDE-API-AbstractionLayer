package de.uos.netbeans;

import de.uos.ide.IDEInformation;
import org.openide.modules.OnStart;

/**
 * @brief starting point of module and push all to another
 * @author Thomas Sobieroy
 */
@OnStart
public class Startable implements Runnable {

    @Override
    public void run() {   
       //setting netbeans IDE as default
       IDEInformation.setIDE("netbeans"); 
       //jumping into our main access point. The main method
       de.uos.application.Main.Main(null, 0);
       System.out.println("test");
    }
    
}
