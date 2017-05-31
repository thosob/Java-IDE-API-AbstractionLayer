package de.uos.intellij;

import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.project.ex.ProjectManagerEx;
import org.jdom.JDOMException;

import java.io.IOException;

/**
 * Created by thoma on 31.05.2017.
 */
public class Project {
    /**
     * @brief transforms intellij project to abstraction layer project
     * @return Project for ide to work further with
     */
    private static de.uos.ide.Project setProjectData(com.intellij.openapi.project.Project intellijProject){

        de.uos.ide.Project ideProject = new de.uos.ide.Project();
        ideProject.setProjectName(intellijProject.getName());
        ideProject.setProjectPath(intellijProject.getProjectFilePath());

        return  ideProject;
    }

    /**
     * @brief creates a project with a name and checks
     * @param projectName name of the project
     * @param path path where the project shall be created
     * @return true if successfully done
     */
    public static de.uos.ide.Project createProject(String projectName, String path){

        ProjectManager projectManager = ProjectManager.getInstance();
        com.intellij.openapi.project.Project intellijProject =  projectManager.createProject(projectName, path);
        return setProjectData(intellijProject);
    }

    /**
     * @brief opens a project with it's project name
     * @param pathToProjectFile
     * @return the opened project
     */
    public static de.uos.ide.Project openProject(String pathToProjectFile){
        ProjectManagerEx projectManagerEx = ProjectManagerEx.getInstanceEx();
        com.intellij.openapi.project.Project project = null;
        try {
            project =  projectManagerEx.loadProject(pathToProjectFile);
            projectManagerEx.openProject(project);
        } catch (IOException e) {
            System.err.println("IOException: could not open project. " + e.getMessage());
        }
        finally {
            return setProjectData(project);
        }
    }

    /**
     * @brief closes projects
     * @param projectName name of the project
     * @return true if closed
     */
    public static boolean closeProject(String projectName){
        ProjectManagerEx projectManager = ProjectManagerEx.getInstanceEx();
        for(com.intellij.openapi.project.Project project : projectManager.getOpenProjects()){
            if(project.getName().equals(projectName)){
                projectManager.closeProject(project);
                return true;
            }
        }
        return false;

    }

}
