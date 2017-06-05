/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.uos.netbeans;

import de.uos.ide.ProjectState;
import java.io.File;
import java.io.IOException;
import org.netbeans.api.project.ProjectInformation;
import org.netbeans.api.project.ProjectManager;
import org.netbeans.api.project.ProjectUtils;
import org.netbeans.api.project.ui.OpenProjects;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;


/**
 * @brief Netbeans Project
 * @author Thomas Sobieroy
 */
public class Project {


    /**
     * @brief converts netbeans project to abstraction layer project
     * @param project
     * @return an ide project or null
     */
    public static de.uos.ide.Project transformProject(org.netbeans.api.project.Project project){
        de.uos.ide.Project ideProject = null;
        if(project != null){
            //Getting project information over project utils
            ProjectInformation projectInformation = ProjectUtils.getInformation(project);
            //create new abstraction layer project
            ideProject = new de.uos.ide.Project();          
            if(projectInformation != null){
                //get name from project information and set it
                ideProject.setProjectName(projectInformation.getName());
                //get path from netbeans project and set it
                ideProject.setProjectPath(project.getProjectDirectory().getPath());
            }
        }
        return ideProject;
    }
    /**
     * @throws java.io.IOException if path is wrong
     * @brief open project from netbeans perspective
     * @param pathToProjectFile path of the project, that has to be opened
     * @return ide project, that is accessible
     */
    public static de.uos.ide.Project openProject(String pathToProjectFile) throws IOException{
        File file = new File(pathToProjectFile);
        file = FileUtil.normalizeFile(file);
        //Get project from path
        FileObject projectFile = FileUtil.toFileObject(file);
        ProjectManager PM = ProjectManager.getDefault();
        //find project in path
        org.netbeans.api.project.Project project = PM.findProject(projectFile);                   
        //put it to an array
        org.netbeans.api.project.Project[] projects = { project };
        //open all projects in the array
        OpenProjects.getDefault().open(projects, false);
        de.uos.ide.Project ideProject = transformProject(project);
        ideProject.setProjectState(ProjectState.opened);
        //give back our ide instance
        return ideProject;
    }
    /**
     * @param projectPath path to project
     * @brief closes project from within netbeans
     * @param projectName name of the project
     * @return true if really closed
     */
    public static boolean closeProject(String projectName, String projectPath) throws IOException{
        File file = new File(projectPath);
        file = FileUtil.normalizeFile(file);
        //Get project from path
        FileObject projectFile = FileUtil.toFileObject(file);
        //find project in path
        org.netbeans.api.project.Project project = ProjectManager.getDefault().findProject(projectFile);    
        //put it to an array
        org.netbeans.api.project.Project[] projects = { project };
        //open all projects in the array
        OpenProjects.getDefault().close(projects);
        return true;
    }
}
