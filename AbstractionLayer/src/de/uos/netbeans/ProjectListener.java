/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.uos.netbeans;

import de.uos.ide.ProjectState;
import org.netbeans.api.project.Project;
import org.netbeans.spi.project.ProjectServiceProvider;
import org.netbeans.spi.project.ui.ProjectOpenedHook;

/**
 * @brief Project Listener 
 * @author Thomas Sobieroy
 */
@ProjectServiceProvider(
    service=ProjectOpenedHook.class,
    projectType= {"org-netbeans-modules-web-project",
    "org-netbeans-modules-java-j2seproject",
    "org-netbeans-modules-apisupport-project",
    "org-netbeans-modules-apisupport-project-suite",
    "org-netbeans-modules-maven"}
)
public class ProjectListener extends ProjectOpenedHook {
    
    private final Project project;

    public ProjectListener(Project project) {
        this.project = project;
    }
    
    @Override
    protected void projectOpened() {
        if(this.project != null){
            de.uos.ide.Project ideProject =  de.uos.netbeans.Project.transformProject(project);   
            ideProject.setProjectState(ProjectState.opened);
            de.uos.ide.ProjectListener.getInstance().changeProject(ideProject);
          
        }
    }

    @Override
    protected void projectClosed() {
        if(this.project != null){
            de.uos.ide.Project ideProject =  de.uos.netbeans.Project.transformProject(project);            
            ideProject.setProjectState(ProjectState.closed);
            de.uos.ide.ProjectListener.getInstance().changeProject(ideProject);
        }
    }    
}
