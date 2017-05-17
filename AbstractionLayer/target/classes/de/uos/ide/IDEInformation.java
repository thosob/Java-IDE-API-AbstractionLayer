package de.uos.ide;

/**
 * @brief Gets the IDE Information of the package
 * @author Thomas Sobieroy
 */
public class IDEInformation {
    
    /**
     * @brief stores IDE information
     */
    private static String IDE;
    
    /**
     * @brief gets the IDE
     * @return 
     */
    public static String getIDE(){
        return IDE;
    }
    
    /**
     * @brief set the IDE direct from start and ask that with getIDE()
     * @param IDEName Name of the IDE e.g. eclipse, intellij, netbeans
     * @return if setting the ide did work
     */
    public static boolean setIDE(String IDEName){
        if(IDEName.equalsIgnoreCase("netbeans")){
            IDE = IDEName.toLowerCase();
            return true;
        }
        if(IDEName.equalsIgnoreCase("eclipse")){
            IDE = IDEName.toLowerCase();
            return true;
        }
        if(IDEName.equalsIgnoreCase("intellij")){
            IDE = IDEName.toLowerCase();
            return true;
        }        
        return false;
    }
    
}
