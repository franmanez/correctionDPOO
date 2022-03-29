package edu.uoc.correction;

import edu.uoc.correction.model.Configuration;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class MainCopy {
    public static void main(String[] args) throws IOException {

        Configuration configuration = new Configuration();

        File folder = new File(configuration.getDirectoryStudentsPACs());

        File[] listExercisesDirectory = folder.listFiles();
        assert listExercisesDirectory != null;
        Arrays.sort(listExercisesDirectory, Comparator.comparing(File::getName));

        for (File studentDirectory : listExercisesDirectory) {
            String studentName = studentDirectory.getName().substring(0, studentDirectory.getName().indexOf("_PEC_2"));
            System.out.println(studentName);

            File srcDir = new File(studentDirectory.getAbsolutePath()+"/PAC2Ex4/src/main/java/edu/uoc/pac2/DNA.java");
            File destDir = new File("/home/fran/uoc/antiplagio/DNA-"+studentName+".java");

            FileUtils.copyFile(srcDir, destDir);
        }


    }

}
