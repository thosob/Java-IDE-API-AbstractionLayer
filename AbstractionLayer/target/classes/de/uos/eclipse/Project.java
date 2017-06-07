package de.uos.eclipse;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;

public class Project {

	/**
	 * @brief transforms an eclipse project to an ide one
	 * @param project,
	 *            that is to be transformed
	 * @return an abstraction layer compatible project
	 */
	public static de.uos.ide.Project transformProject(IProject project) {
		de.uos.ide.Project ideProject = null;
		if (project != null) {
			// Getting project information over project utils
			IProjectDescription description;
			try {
				// getting description of the project
				description = project.getDescription();

				// create new abstraction layer project
				ideProject = new de.uos.ide.Project();
				if (description != null) {
					// get name from project information and set it
					ideProject.setProjectName(description.getName());
					// get path from eclipse project and set it
					ideProject.setProjectPath(description.getLocationURI().getPath());
				}
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		// null if not successful and an abstraction layer compatible module, if
		// successfully executed
		return ideProject;
	}

	/**
	 * @brief open a project from a path. The path must be in the workspace or
	 *        else the eclipse can't open the project.
	 * @param pathToProject
	 *            fully qualified path to a project
	 * @return if successful an abstraction layer project, if not null
	 */
	public static de.uos.ide.Project openProject(String projectName) {
		// Get root of the workspace
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		// get all project
		IProject eclipseProject = root.getProject(projectName);
		de.uos.ide.Project ideProject = null;
		
		// open and convert it to an abstraction layer project
		try {
			if (eclipseProject != null) {
				eclipseProject.open(null);
				ideProject = transformProject(eclipseProject);
			}
		} catch (CoreException e) {
			System.err.println("de.uos.eclipse.Project - error opening eclipse project");
		}
		// return null if not successfully executed else give back the project
		return ideProject;
	}

	/**
	 * @brief closes the project
	 * @param projectName
	 * @return true if closed, false if not
	 */
	public static boolean closeProject(String projectName) {
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		boolean retValue = false;

		try {
			root.getProject(projectName).close(null);
			retValue = true;
		} catch (CoreException e) {
			e.printStackTrace();
		}
		return retValue;
	}

}
