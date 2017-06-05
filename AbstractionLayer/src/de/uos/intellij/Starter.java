/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.uos.intellij;

import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.DataConstants;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import org.jetbrains.annotations.NotNull;
import de.uos.ide.IDEInformation;

/**
 * @brief implements the application start of intellij
 * @author Thomas Sobieroy
 */
public class Starter implements ApplicationComponent {

    @Override
    public void initComponent() {
        //Set up intellij as globally recognized IDE
        IDEInformation.setIDE("intellij");
        System.out.println("de.uos.intellij.starter.initComponent");

        ProjectManager projectManager = ProjectManager.getInstance();
        projectManager.addProjectManagerListener(new ProjectListener());
        
        //jump into the application
        de.uos.application.Main.Main(null, 0);

    }

    @Override
    public void disposeComponent() {

    }

    @NotNull
    @Override
    public String getComponentName() {
        return "JavaIDE-API-Abstraction-Layer";
    }
}
