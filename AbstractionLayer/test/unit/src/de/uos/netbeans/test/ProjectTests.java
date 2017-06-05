/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.uos.netbeans.test;

import de.uos.ide.Project;
import de.uos.ide.ProjectListener;
import de.uos.ide.ProjectState;
import java.io.File;
import java.util.Observable;
import java.util.Observer;
import junit.framework.Test;
import org.netbeans.api.project.ProjectManager;
import org.netbeans.api.project.ProjectManager.Result;
import org.netbeans.junit.MockServices;
import org.netbeans.junit.NbModuleSuite;
import org.netbeans.junit.NbTestCase;
import org.netbeans.spi.project.LookupProvider;
import org.netbeans.spi.project.ui.ProjectOpenedHook;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.Lookup;
import org.openide.util.lookup.Lookups;

/**
 * @brief Netbeans tests
 * @author Thomas Sobieroy
 */
public class ProjectTests extends NbTestCase {

    private final static String projectPath = "C:\\Users\\thoma\\workspace\\Java-IDE-API-AbstractionLayer\\AbstractionLayer";
    private FileObject d;
    
    
    /**
     * @brief forced by nbtest case
     * @param name
     */
    public ProjectTests(String name) {
        super(name);
    }
    
  
    
    
    /**
     * @brief checks if loading of files is o.k, if not the masterfs dependency
     * is missing in unit test library
     * @throws Exception 
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        //Register the services for testing
        MockServices.setServices(ProjectListener.class);
      
        
        File file = new File(projectPath);
        file = FileUtil.normalizeFile(file);
        d = FileUtil.toFileObject(file);
        //here we check if masterfs is loaded
        assertNotNull(d);
        ProjectManager PM = ProjectManager.getDefault();        
        //try finding project
        org.netbeans.api.project.Project project = PM.findProject(d);          
        //assert that is loadable
        assertNotNull(project);        
    }

    /**
     * @brief sets up full netbeans suite to test the whole ide
     * @return
     */
    public static Test suite() {
        return NbModuleSuite.createConfiguration(ProjectTests.class).enableClasspathModules(true).suite();
    }

    public void testOpenProject() {
        de.uos.ide.Project project = de.uos.ide.Project.openProject(projectPath);
        assertNotNull(project);
        assertNotNull(project.getProjectName());
        assertNotNull(project.getProjectPath());
        assert (project.getProjectState() == ProjectState.opened);
    }

    public void testCloseProject() {
        de.uos.ide.Project project = de.uos.ide.Project.openProject(projectPath);
        assertNotNull(project.getProjectState() == ProjectState.opened);
        assertNotNull (project.closeProject());
    }
    /**
     * @brief this test fails sometimes, because opening and closing occur in 
     * isn't refleceted to the observer immeadiately.
     */
    public void testListener() {

        class TestObserver implements Observer {

            public ProjectState State;

            @Override
            public void update(Observable observable, Object o) {
                Project project = (Project) o;
                this.State = project.getProjectState();
            }
        }
        TestObserver observer = new TestObserver();
        ProjectListener.getInstance().addObserver(observer);
        //Open project
        Project project = de.uos.ide.Project.openProject(projectPath);
        //Assert, that it was opened
        assert(observer.State == ProjectState.opened);
        //Close project
        project.closeProject();
        //Assert, that it is closed
        assert(observer.State == ProjectState.closed);
    }
}
