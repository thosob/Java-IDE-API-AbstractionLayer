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

		if (event == null || event.getDelta() == null)
			return;
		try {
			// get event data by using delta visitor
			event.getDelta().accept(new IResourceDeltaVisitor() {
				// using eclipse visitor pattern
				public boolean visit(final IResourceDelta delta) throws CoreException {
					IResource resource = delta.getResource();
					IResourceDelta[] children = delta.getAffectedChildren();
					IProject iproject = null;

					for (IResourceDelta child : children) {
						// Check if project and something has changed
						if (delta.getKind() == IResourceDelta.CHANGED) {
							/**
							 * IProject[] projects =
							 * resource.getWorkspace().getRoot().getProjects();
							 * 
							 * for (IProject tmpProject : projects) { if
							 * (tmpProject.getFullPath().toString().equals(path))
							 * { iproject = tmpProject; } }
							 **/
							if (child.getResource().getProject() != null) {
								iproject = child.getResource().getProject();

								// IResourceDelta.OPEN is the notifier of open
								// and
								// close events
								if (iproject.isOpen()) {

									// if open transform project to ide project
									de.uos.ide.Project project = de.uos.eclipse.Project.transformProject(iproject);
									// mark it as open
									project.setProjectState(ProjectState.opened);
									// and notify observable
									de.uos.ide.ProjectListener.getInstance().changeProject(project);
									break;
								} else {
									// if closed, get ide project
									de.uos.ide.Project project = de.uos.eclipse.Project.transformProject(iproject);
									// set state to closed
									project.setProjectState(ProjectState.closed);
									// notify observable
									de.uos.ide.ProjectListener.getInstance().changeProject(project);
									break;
								}							
							}
							// mark as successful visit
							return true;
						}
					}
					return false;

				}

			});

		} catch (CoreException e) {
			e.printStackTrace();
		}

	}
}
