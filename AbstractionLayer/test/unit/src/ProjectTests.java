package unit.src;

import de.uos.ide.Project;
import com.intellij.testFramework.fixtures.LightCodeInsightFixtureTestCase;

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

    public void testListener(){
        //Open project
        Project project = de.uos.intellij.Project.openProject("/test/data/SpezialProjekt.iml");
        //Still todo
    }
}