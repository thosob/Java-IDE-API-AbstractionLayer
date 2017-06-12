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
import java.lang.reflect.Method;

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
        try {
            //jumping into our main access point. The main method
            //this has to be improved
            Class<?> cls = Class.forName(de.uos.util.Settings.startClass);
            Method meth = cls.getMethod(de.uos.util.Settings.startMethod, String[].class);
            meth.invoke((Object) de.uos.util.Settings.startParametersCount, (Object[]) de.uos.util.Settings.startParameters); // static method doesn't have an instance

        } catch (Exception ex) { //having 6 catches doesn't look good
            System.err.println(ex);
        }
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
