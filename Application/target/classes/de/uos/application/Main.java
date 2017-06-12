package de.uos.application;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * @brief Start method
 * @author Thomas Sobieroy
 */
public class Main implements BundleActivator {

    /**
     * @brief starting
     * @param args
     * @param argv
     */
    public static void Main(String[] args, int argv) {        
        //Open test file to ensure, that application can use the abstraction layer
        de.uos.ide.Project.openProject("C:\\Users\thoma\\eclipse-test-workspace\\SimpleTest\\", "");
        
    }

    /**
     * @brief important for eclipse start up
     * @param arg0
     * @throws Exception
     */
    @Override
    public void start(BundleContext arg0) throws Exception {
        Main(null, 0);
       
    }

    /**
     * @brief important for eclipse stopping
     * @param arg0
     * @throws Exception
     */
    @Override
    public void stop(BundleContext arg0) throws Exception {
    }

}
