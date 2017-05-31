package de.uos.application;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import de.uos.ide.Project;

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

        System.out.println("Main Method");

        //de.uos.ide.Project.createProject("new Project", "newProject");
        Project proj = Project.openProject("C:\\Users\\thoma\\SpezialProjekt.iml");
        System.out.println(proj.closeProject());
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
