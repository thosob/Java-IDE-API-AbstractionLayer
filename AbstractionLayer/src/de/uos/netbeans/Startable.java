package de.uos.netbeans;

import de.uos.ide.IDEInformation;
import org.openide.modules.OnStart;
import de.uos.ide.Project;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * @brief starting point of module and push all to another
 * @author Thomas Sobieroy
 */
@OnStart
public class Startable implements Runnable {

    @Override
    public void run() {                 
        try {
            //setting netbeans IDE as default
            IDEInformation.setIDE("netbeans");
            //jumping into our main access point. The main method
            //this has to be improved
            Class<?> cls = Class.forName(de.uos.util.Settings.startClass);
            Method meth = cls.getMethod(de.uos.util.Settings.startMethod, String[].class);     
            meth.invoke((Object)de.uos.util.Settings.startParametersCount, (Object[])de.uos.util.Settings.startParameters); // static method doesn't have an instance
            
        } catch (Exception ex) { //having 6 catches doesn't look good
            Logger.getLogger(Startable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}
