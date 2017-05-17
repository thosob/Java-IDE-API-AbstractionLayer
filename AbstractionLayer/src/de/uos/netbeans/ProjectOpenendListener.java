/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.uos.netbeans;

import java.util.Observable;
import javax.swing.Icon;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ProjectInformation;
import org.netbeans.api.project.ProjectUtils;
import org.netbeans.spi.project.LookupProvider;
import org.netbeans.spi.project.ui.ProjectOpenedHook;
import org.openide.filesystems.FileObject;
import org.openide.util.Lookup;
import org.openide.util.lookup.Lookups;

/**
 * @brief defines service provider listening to opening and closing projects
 * @author Thomas Sobieroy
 */
@LookupProvider.Registration(projectType = {
    "org-netbeans-modules-web-project",
    "org-netbeans-modules-java-j2seproject",
    "org-netbeans-modules-apisupport-project",
    "org-netbeans-modules-apisupport-project-suite"
})
public class ProjectOpenendListener extends Observable implements LookupProvider{

    private Project project;
    //name from project - here I am not quite sure, if it is the human
    //readable version, the functional name or something else
    private String name;
    //main directory of the project, with that we can access all other directories
    //and all files
    private FileObject directory;
    //general Project-Information object
    //on this we have to register event handlers, if we want to know 
    //if display name, name or icon was changed
    private ProjectInformation info;
    //human readable version of the name
    private String displayName;
    //Swing component - icon
    private Icon icon;
    //programmatical name of the project
    private String functionalName;
    
    @Override
    public Lookup createAdditionalLookup(Lookup baseContext) {

        project = baseContext.lookup(Project.class);
        directory = project.getProjectDirectory();
        name = project.getProjectDirectory().getName();
        info = ProjectUtils.getInformation(project);
        functionalName = info.getName();
        info = ProjectUtils.getInformation(project);
        displayName = info.getDisplayName();
        icon = info.getIcon();
        
        
        
        return Lookups.fixed(new ProjectOpenedHook() {

            @Override
            protected void projectOpened() {
                System.out.println("test");
            }

            @Override
            protected void projectClosed() {
                System.out.println("test2");
            }
        });

    }
}
