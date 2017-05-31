package de.uos.ide;

/**
 * @brief Project-Class
 */
public class Project {

    private String ProjectName;
    private String ProjectPath;

    public Project(){

    }

    /**
     * @brief creates a project with a name and a path
     * @param projectName name of the project
     * @param projectPath path of the new project
     * @return a project object if successfully, else null
     */
    public static Project createProject(String projectName, String projectPath){
        if(IDEInformation.getIDE().equalsIgnoreCase("intellij")){
            return de.uos.intellij.Project.createProject(projectName, projectPath);
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
            return de.uos.intellij.Project.openProject(projectName);
        }
        return null;
    }

    /**
     * @brief close project
     * @return true if closed
     */
    public boolean closeProject(){
        if(IDEInformation.getIDE().equalsIgnoreCase("intellij")){
            return de.uos.intellij.Project.closeProject(ProjectName);
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
}
