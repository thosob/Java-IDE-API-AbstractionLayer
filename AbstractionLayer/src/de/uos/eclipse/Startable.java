package de.uos.eclipse;

import java.lang.reflect.Method;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Startable implements BundleActivator {

    private IResourceChangeListener ProjectListener;
    private IWorkspace Workspace;

    @Override
    public void start(BundleContext context) throws Exception {
        de.uos.ide.IDEInformation.setIDE("eclipse");
        //here we register all eclipse listeners
        //get workspace
        Workspace = ResourcesPlugin.getWorkspace();
        //create new listener
        ProjectListener = new ProjectListener();
        //assign listener to workspace	
        Workspace.addResourceChangeListener(ProjectListener);
        try {
            //jumping into our main access point. The main method
            //this has to be improved
            Class<?> cls = Class.forName(de.uos.util.Settings.startClass);
            Method meth = cls.getMethod(de.uos.util.Settings.startMethod, String[].class);
            meth.invoke((Object) de.uos.util.Settings.startParametersCount, (Object[]) de.uos.util.Settings.startParameters); // static method doesn't have an instance

        } catch (Exception ex) { //having 6 catches doesn't look good
            System.err.println(ex);
        }

    }

    @Override
    public void stop(BundleContext context) throws Exception {
        //clean up the listeners after shutdown to avoid memory leaks
        Workspace.removeResourceChangeListener(ProjectListener);
    }

}
