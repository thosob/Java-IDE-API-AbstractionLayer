package de.uos.ide;

import java.util.Observable;

/**
 * @brief the project listeners
 */
public class ProjectListener extends Observable {

    private Project Project = null;
    private static ProjectListener Listener = null;

    /**
     * @brief on change project observers will be addressed
     * @param project
     */
    public void changeProject(Project project){
        Project = project;
        setChanged();
        notifyObservers(project);
    }

    /**
     * @brief returns the project assigned
     * @return
     */
    public Project getProject(){
        return this.Project;
    }

    /**
     * @brief returns the project listener
     * @return
     */
    public static ProjectListener getInstance(){
        if(Listener == null){
            Listener = new ProjectListener();
        }
        return Listener;
    }
}
