package de.uos.ide;

import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * @brief Project-Class
 */
public class Project {

    private String ProjectName;
    private String ProjectPath;
    private ProjectState ProjectState;


    /**
     * @brief creates a project with a name and a path
     * @param projectName name of the project
     * @param projectPath path of the new project
     * @return a project object if successfully, else null
     */
    public static Project createProject(String projectName, String projectPath){
        if(IDEInformation.getIDE().equalsIgnoreCase("intellij")){
          //  return de.uos.intellij.Project.createProject(projectName, projectPath);
        }

        return null;
    }

    /**
     * @brief opens a project with it's project name
     * @param projectName
     * @return the opened project
     */
    public static Project openProject(String projectName){
        if(IDEInformation.getIDE().equalsIgnoreCase("intellij")){
            try {
                //Getting class
                Class cls = Class.forName("de.uos.intellij.Project");
                //Getting method
                Method method =  cls.getDeclaredMethod("openProject", String.class);
                //Get one instance of that class
                Object obj = cls.newInstance();
                //define it as accessible
                method.setAccessible(true);
                //use reflection to invoke static method and cast to bool
                return (Project) method.invoke(obj, projectName);
                //using generic exception, because list of possible exceptions is long, even for multi catch
            } catch (Exception ex) {
                Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    /**
     * @brief close project
     * @return true if closed
     */
    public boolean closeProject(){
        if(IDEInformation.getIDE().equalsIgnoreCase("intellij")){
            try {
                //Getting class
                Class cls = Class.forName("de.uos.intellij.Project");
                //Getting method
                Method method =  cls.getDeclaredMethod("closeProject", String.class);
                //Get one instance of that class
                Object obj = cls.newInstance();
                //define it as accessible
                method.setAccessible(true);
                //use reflection to invoke static method and cast to bool
                return (boolean)method.invoke(obj, this.ProjectName);
                //using generic exception, because list of possible exceptions is long, even for multi catch
            } catch (Exception ex) {
                Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    /**
     * @brief gets the project name
     * @return name of the project
     */
    public String getProjectName() {
        return ProjectName;
    }

    /**
     * @brief sets up the project name - does not change the name in the ides
     * @param projectName name of the project
     */
    public void setProjectName(String projectName) {
        ProjectName = projectName;
    }

    /**
     * @brief gets the path to the project
     * @return project path
     */
    public String getProjectPath() {
        return ProjectPath;
    }

    /**
     * @brief sets up the path to the projects - does not change the path on the local file system
     * @param projectPath
     */
    public void setProjectPath(String projectPath) {
        ProjectPath = projectPath;
    }

    public ProjectState getProjectState() {
        return ProjectState;
    }

    public void setProjectState(ProjectState projectState) {
        ProjectState = projectState;
    }
}
