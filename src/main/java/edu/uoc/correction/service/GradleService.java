package edu.uoc.correction.service;
import edu.uoc.correction.model.Configuration;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Fran Manez
 * @version 1.0
 */
public class GradleService {

    public static String GRADLEW_EXECUTABLE;
    Configuration configuration;

    public GradleService(Configuration configuration){
        this.configuration = configuration;
    }

    private void setGradlewExecutableOS(){
        if(System.getProperty("os.name").startsWith("Windows")){
            GRADLEW_EXECUTABLE = System.getProperty("user.dir")+"/gradlew.bat";
        }else{
            GRADLEW_EXECUTABLE = System.getProperty("user.dir")+"/gradlew";
        }
    }

    private void copyOriginalTests(String exerciseName, String url) throws IOException{
        exerciseName = exerciseName.endsWith("_sol") ? exerciseName.split("_")[0] : exerciseName;
        File srcDir = new File(configuration.getDirectoryTestsPACs() + "/" + exerciseName);
        File destDir = new File(url);
        FileUtils.copyDirectory(srcDir, destDir);
    }

    private void myExecRuntime(String command) throws IOException {
        long init = System.currentTimeMillis();
        Process p = Runtime.getRuntime().exec(command);
        try {
            p.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println(" Duration: " + (double) ((end - init)/1000) +" seconds");
        //System.out.println("OK");
        p.destroy();
    }

    public void run() throws IOException{

        setGradlewExecutableOS();

        File folder = new File(configuration.getDirectoryStudentsPACs());
        int cont = 1;

        //TODO maybe performance problem
        File[] listExercisesDirectory = folder.listFiles();
        assert listExercisesDirectory != null;
        Arrays.sort(listExercisesDirectory, Comparator.comparing(File::getName));

        for (File studentDirectory : listExercisesDirectory) {
            if(studentDirectory.isDirectory()) {

                String studentName = studentDirectory.getName().split("_")[0]+"_"+studentDirectory.getName().split("_")[1];
                System.out.println(System.lineSeparator() + "_______________________ " + cont + " - " + studentName + " _______________________");

                //TODO maybe performance problem
                File[] listExercises = studentDirectory.listFiles();
                assert listExercises != null;
                Arrays.sort(listExercises, Comparator.comparing(File::getName));

                for (File exercise : listExercises) {
                    System.out.println(exercise.getName());
                    boolean existGradleFile = new File(exercise.getAbsolutePath()+"/build.gradle").exists();
                    if(existGradleFile){

                        //Copy test files
                        copyOriginalTests(exercise.getName(), exercise.getAbsolutePath());

                        //Gradle clean
                        String commandClean = GRADLEW_EXECUTABLE + " -b " + exercise.getAbsolutePath() + "/build.gradle clean";
                        System.out.print("\tExecuting gradle CLEAN: ");
                        myExecRuntime(commandClean);

                        //check is package not void (Windows problem)
                        File dirPackage = new File(exercise.getAbsolutePath()+"/src/main/java/edu/uoc/"+configuration.getName().toLowerCase());
                        if(dirPackage.listFiles().length > 0){
                            //Gradle test
                            String commandTest = GRADLEW_EXECUTABLE + " -b " + exercise.getAbsolutePath() + "/build.gradle test";
                            System.out.print("\tExecuting gradle  TEST: ");
                            myExecRuntime(commandTest);
                        }
                    }
                }
            }
            cont++;
        }
    }
}

