package unit.src.de.uos.intellij.test;

import de.uos.ide.Project;
import com.intellij.testFramework.fixtures.LightCodeInsightFixtureTestCase;
import de.uos.ide.ProjectListener;
import de.uos.ide.ProjectState;

import java.util.Observable;
import java.util.Observer;

/**
 * @brief tests if project could be closed or opened
 */
public class ProjectTests extends LightCodeInsightFixtureTestCase {

    public void testCloseProject(){
        //Open project
        Project project = de.uos.intellij.Project.openProject("/test/data/SpezialProjekt.iml");
        //if not null it's ok
        assertNotNull(project);
        //check if closing was successful
        assertTrue(project.closeProject());
    }

    public void testOpenProject(){
        //Open project
        Project project = de.uos.intellij.Project.openProject("/test/data/SpezialProjekt.iml");
        //make sure it's not null
        assertNotNull(project);
    }

    /**
     * @brief this test checks, if the reflections working correctly in ide package
     */
    public void testIDEFunctions(){
        Project project = de.uos.ide.Project.openProject("/test/data/SpezialProjekt.iml");
        assertNotNull(project);
        //Test reflection in ide function
        boolean closed = project.closeProject();
        assertTrue(closed);
    }

    public void testListener(){

        class TestObserver implements Observer{
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
        Project project = de.uos.intellij.Project.openProject("/test/data/SpezialProjekt.iml");
        //Assert, that it was opened
        assertEquals(observer.State, ProjectState.opened);
        //Close project
        project.closeProject();
        //Assert, that it is closed
        assertEquals(observer.State, ProjectState.closed);



    }
}