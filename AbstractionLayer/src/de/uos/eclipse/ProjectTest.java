package de.uos.eclipse;

import java.util.Observable;
import java.util.Observer;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.uos.ide.Project;
import de.uos.eclipse.ProjectListener;
import de.uos.ide.ProjectState;

public class ProjectTest {

	private de.uos.ide.Project project;
	/**
	 * The test project has to be in the testing workspace
	 */
	private String testProject = "AbstractionLayer";
	/**
	 * @brief tests closing of projects
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		de.uos.ide.IDEInformation.setIDE("eclipse");
		IWorkspace Workspace = ResourcesPlugin.getWorkspace();
		// create new listener
		ProjectListener projectListener = new ProjectListener();
		// assign listener to workspace
		Workspace.addResourceChangeListener(projectListener);
		
	}
	/**
	 * @brief tests opening of projects
	 */
	@Test
	public void testOpenProject() {

		project = de.uos.ide.Project.openProject(testProject);
		assert (project != null);
		assert (project.getProjectName() != null);
		assert (project.getProjectState() == de.uos.ide.ProjectState.opened);

	}

	@Test
	public void testCloseProject() {
		project = de.uos.ide.Project.openProject(testProject);
		project.closeProject();
		assert (project.getProjectState() == de.uos.ide.ProjectState.closed);
	}
	/**
	 * @brief listening to opening and closing of projects
	 */
	@Test
	public void testProjectListener() {		
		
		class TestObserver implements Observer {
			public ProjectState State;

			@Override
			public void update(Observable observable, Object o) {
				Project project = (Project) o;
				this.State = project.getProjectState();
			}
		}
		TestObserver observer = new TestObserver();
		de.uos.ide.ProjectListener.getInstance().addObserver(observer);

		// Open project
		project = de.uos.ide.Project.openProject(testProject);
		// Assert, that it was opened
		assert (observer.State == ProjectState.opened);
		// Close project
		project.closeProject();
		// Assert, that it is closed
		assert (observer.State == ProjectState.closed);
	}
}
