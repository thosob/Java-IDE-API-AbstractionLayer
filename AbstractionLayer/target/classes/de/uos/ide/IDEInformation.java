package de.uos.ide;

/**
 * @brief Gets the IDE Information of the package
 * @author Thomas Sobieroy
 */
public class IDEInformation {
    
    /**
     * @brief stores IDE information as kind of a global object
     */
    private static String IDE = null;
    
    /**
     * @brief gets the IDE, in case it wasn't set yet, tries to determine the actual running ide by the build of this
     * package
     * @return 
     */
    public static String getIDE(){

        if(IDE == null){
            return determineIDE();
        }
        else{
            return IDE;
        }
    }
    
    /**
     * @brief set the IDE direct from start and ask that with getIDE()
     * @param IDEName Name of the IDE e.g. eclipse, intellij, netbeans
     * @return false if setting up the ide string did work, else everything is fine and we get true
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

    /**
     * @brief we can check in which IDE we are if we know, if we check the packages put in the build
     * @return gets back the IDE as a string
     */
    public static String determineIDE() {
        //if intellij package is in jar we are in intellij environment
        if(Package.getPackage("intellij") != null){
            IDE = "intellij";
        }
        //if eclipse package is in jar we are in eclipse environment
        if(Package.getPackage("eclipse") != null){
            IDE = "eclipse";
        }
        //and last, if we are in netbeans environment, we have a netbeans package
        if(Package.getPackage("netbeans") != null){
            IDE = "netbeans";
        }
        return IDE;
    }
    
}
