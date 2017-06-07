package de.uos.eclipse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class ProjectTest {
	
	private de.uos.ide.Project project;
	/**
	 * The test project has to be in the testing workspace
	 */
	private String testProject = "AbstractionLayer";
	
	@Before
	public void setUp() throws Exception {
		de.uos.ide.IDEInformation.setIDE("eclipse");
	}
	
	@Test
	public void testOpenProject(){
		
		project = de.uos.ide.Project.openProject(testProject);
		assert(project != null);
		assert(project.getProjectName() != null);
		assert(project.getProjectState() == de.uos.ide.ProjectState.opened);
		
	}
	
	@Test
	public void testCloseProject(){
		project = de.uos.ide.Project.openProject(testProject);
		project.closeProject();		
		assert(project.getProjectState() == de.uos.ide.ProjectState.closed);
	}
	
	@Test
	public void testProjectListener(){
		
	}
}
