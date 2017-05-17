/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.uos.netbeans;

import java.util.Observable;
import org.netbeans.api.project.Project;
import org.netbeans.spi.project.LookupProvider;
import org.netbeans.spi.project.ui.ProjectOpenedHook;
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

    //private final Project project;
    @Override
    public Lookup createAdditionalLookup(Lookup baseContext) {

        Project p = baseContext.lookup(Project.class);
        final String name;
        name = p.getProjectDirectory().getName();

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
