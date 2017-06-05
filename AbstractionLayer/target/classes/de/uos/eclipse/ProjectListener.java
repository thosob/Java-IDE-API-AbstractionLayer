package de.uos.eclipse;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.runtime.CoreException;

public class ProjectListener implements IResourceChangeListener{

	@Override
	public void resourceChanged(IResourceChangeEvent event) {
		// TODO Auto-generated method stub
		if (event == null || event.getDelta() == null)
			return;
    	try {
			event.getDelta().accept(new IResourceDeltaVisitor() {
				public boolean visit(IResourceDelta delta) throws CoreException {
			        if (delta.getKind() == IResourceDelta.OPEN){
			            final IResource resource = delta.getResource();
			            if (!(resource instanceof IProject))
			            	return false;
			            System.out.println("not saved");
			           //do your stuff and check the project is opened or closed
			            return true;
					}
					return false;
				}	
			});
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}
}
