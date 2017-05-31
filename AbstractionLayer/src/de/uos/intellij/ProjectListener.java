package de.uos.intellij;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManagerListener;

/**
 * @brief Listens if a project, was opened or closed. An open project event is thrown on startup
 */
public class ProjectListener implements ProjectManagerListener {

    @Override
    public void projectOpened(Project project){
        System.out.println("Project Opened");
    }
    @Override
    public void projectClosed(Project project) {
        System.out.println("Project Closed");
    }
}
