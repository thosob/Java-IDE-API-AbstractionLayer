package de.uos.eclipse;

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
		de.uos.ide.Project project = de.uos.ide.Project.openProject("SimpleTest");
		project.closeProject();
		
		
		
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		//clean up the listeners after shutdown to avoid memory leaks
		Workspace.removeResourceChangeListener(ProjectListener);		
	}
	



}
