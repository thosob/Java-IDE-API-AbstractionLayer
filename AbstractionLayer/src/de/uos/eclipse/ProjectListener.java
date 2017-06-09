package de.uos.eclipse;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;

import de.uos.ide.ProjectState;

/**
 * @brief The project listener implements a resource change listener to be
 *        notified about open or close events
 * @author Thomas Sobieroy
 */
public class ProjectListener implements IResourceChangeListener {
	/**
	 * @brief resourceChange listener method
	 */
	@Override
	public void resourceChanged(IResourceChangeEvent event) {

		if (event == null || event.getDelta() == null) {
			return;
		}

		try {
			event.getDelta().accept(new IResourceDeltaVisitor() {
				public boolean visit(final IResourceDelta delta) throws CoreException {
					IResource resource = delta.getResource();
					if (((resource.getType() & IResource.PROJECT) != 0)
							&& delta.getKind() == IResourceDelta.CHANGED
							&& ((delta.getFlags() & IResourceDelta.OPEN) != 0)) {

						IProject iproject = (IProject) resource;
						if (iproject.isOpen()) {
							// if open transform project to ide project
							de.uos.ide.Project project = de.uos.eclipse.Project.transformProject(iproject);
							// mark it as open
							project.setProjectState(ProjectState.opened);
							// and notify observable
							de.uos.ide.ProjectListener.getInstance().changeProject(project);
							System.out.println("Project opening was notifiyed.");
						} else {
							de.uos.ide.Project project = de.uos.eclipse.Project.transformProject(iproject);
							project.setProjectState(ProjectState.closed);
							de.uos.ide.ProjectListener.getInstance().changeProject(project);
							System.out.println("Project was closed.");
						}
					}
					return true;
				}
			});
		} catch (CoreException e) {
			e.printStackTrace();

		}
	}
}
