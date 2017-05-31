package de.uos.intellij;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManagerListener;
import de.uos.ide.ProjectState;

/**
 * @brief Listens if a project, was opened or closed. An open project event is thrown on startup
 */
public class ProjectListener implements ProjectManagerListener {
    /**
     * @brief if project was opened
     * @param project and new state will be sent to observable
     */
    @Override
    public void projectOpened(Project project){
        de.uos.ide.Project ideProject = new de.uos.ide.Project(project);
        ideProject.setProjectState(ProjectState.opened);
        de.uos.ide.ProjectListener.getInstance().changeProject(ideProject);
    }
    /**
     * @brief if project was closed
     * @param project and new state will be sent to observable
     */
    @Override
    public void projectClosed(Project project) {
        de.uos.ide.Project ideProject = new de.uos.ide.Project(project);
        ideProject.setProjectState(ProjectState.closed);
        de.uos.ide.ProjectListener.getInstance().changeProject(ideProject);
    }
    /**
     * @brief if project was closing
     * @param project and new state will be sent to observable
     */
    @Override
    public void projectClosing(Project project) {
        de.uos.ide.Project ideProject = new de.uos.ide.Project(project);
        ideProject.setProjectState(ProjectState.closing);
        de.uos.ide.ProjectListener.getInstance().changeProject(ideProject);
    }
}
