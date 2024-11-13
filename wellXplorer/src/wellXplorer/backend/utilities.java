package wellXplorer.backend;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class utilities {

    /**
     * Constructor de utilidades
     */
    public utilities()
    {

    }

    /**
     * Creates a trajectory from Execel Survey List File
     * @return
     */
    public trajectory surveyListExcel(String fileName)
    {
        return null;
    }

    /**
     * Creates a trajectory from Notebook Survey List File
     * @return Trajectory object with the parameters from text file
     */
    public trajectory surveyListText(String fileName)
    {
        int stations = 0;
        trajectory trajectoria = new trajectory(fileName);
        /*
         * Routine to open and Read text file
         */
        try {
            File myObj = new File(fileName);
            Scanner myReader = new Scanner(myObj);

            while(myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String md = data.split("\t")[0];
                String inc = data.split("\t")[1];
                String azm = data.split("\t")[2];
                survey nSurvey = new survey();
                nSurvey.setMeasureDistance(Float.parseFloat(md));
                nSurvey.setRawInclination(Float.parseFloat(inc));
                nSurvey.setMagAzimuth(Float.parseFloat(azm));
                trajectoria.addSurvey(nSurvey);
                stations++;
            }
            System.out.println("Numero de registros insertados: " + stations + "");
            myReader.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        if(stations > 0) {
            return trajectoria;
        }
        else {
            return null;
        }
    }
}