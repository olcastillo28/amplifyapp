package wellXplorer.backend;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class trajectory {

    private String name;
    private int numberStations;
    private ArrayList<survey> surveys = new ArrayList<survey>(0);

    /**
     * Constructor trajectory by name
     */
    public trajectory(String nName)
    {
        name = nName;
    }

    /**
     * Constructor de la trayectoria por nombre
     */
    public trajectory()
    {
    }

    /**
     * Return trajectory name
     */
    public String getTrajectoryName()
    {
        return name;
    }

    /**
     * Insert Survey station
     */
    public boolean insertSurvey(int pos)
    {
        return false;
    }


    /**
     * Insert Survey Station to the last position
     */
    public boolean addSurvey(survey nS)
    {
        // System.out.println("Insertando Survey Numero:" + numberStations);
        surveys.add(nS);
        numberStations++;
        return true;
    }

    /**
     * Delete Survey station
     */
    public boolean deleteSurvey(int pos)
    {
        return false;
    }

    /**
     * Replace survey station
     */
    public boolean replaceSurvey(int pos, survey nSurvey)
    {
        return false;
    }

    /**
     * Interpolate survey position according to trajectory
     */
    public survey interpolateSurvey(survey nSurvey)
    {
        return null;
    }

    /**
     * Construir trayectoria utilizando el método tangencial para cada estación de Survey
     */
    public void tangencialMethod()
    {
        for(int i = 0 ; i < numberStations - 1 ; i++ )
        {
            double md1, md2, inc1, inc2, azm1, azm2;
            md1 = surveys.get(i).getMeasureDistance();
            md2 = surveys.get(i+1).getMeasureDistance();
            inc1 = Math.toRadians(surveys.get(i).getRawInclination());
            inc2 = Math.toRadians(surveys.get(i+1).getRawInclination());
            azm1 = Math.toRadians(surveys.get(i).getMagAzimuth());
            azm2 = Math.toRadians(surveys.get(i+1).getMagAzimuth());


            // Calcula y escribe los north posistion para cada survey
            double north = ((md2-md1)/2)*(Math.sin(inc2))*(Math.cos(azm2) + Math.sin(inc1))*(Math.cos(azm1));
            double east =  ((md2-md1)/2)*((Math.sin(inc1)*Math.sin(azm1)) + (Math.sin(inc2)*Math.sin(azm2)));
            double vd = ((md2-md1)/2)*(Math.cos(inc2) + Math.cos(inc1));
            //double cl = (md2-md1)*(Math.sin(inc2));

            // Devuelve el valor de la estación inmeditaamente anterior
            double n0 = surveys.get(i).getNorthing();
            double e0 = surveys.get(i).getEasting();
            double vd0 = surveys.get(i).getTrueVerticalDepth();

            // Actualiza el valor de la siguient estación de Survey
            surveys.get(i+1).setNorthing(north + n0);
            surveys.get(i+1).setEasting(e0 + east);
            surveys.get(i+1).setTrueVerticalDepth(vd0 + vd);
        }
    }


    /**
     * Construir trayectoria utilizando el método tangencial para cada estación de Survey
     */
    public void balancedTangencialMethod()
    {
        for(int i = 0 ; i < numberStations - 1 ; i++ )
        {
            double md1, md2, inc1, inc2, azm1, azm2;
            md1 = surveys.get(i).getMeasureDistance();
            md2 = surveys.get(i+1).getMeasureDistance();
            inc1 = Math.toRadians(surveys.get(i).getRawInclination());
            inc2 = Math.toRadians(surveys.get(i+1).getRawInclination());
            azm1 = Math.toRadians(surveys.get(i).getMagAzimuth());
            azm2 = Math.toRadians(surveys.get(i+1).getMagAzimuth());


            // Calcula y escribe los north posistion para cada survey
            double north = (md2-md1)*(Math.sin(inc2))*(Math.cos(azm2));
            double east =  (md2-md1)*(Math.sin(inc2))*(Math.sin(azm2));
            double vd = (md2-md1)*(Math.cos(inc2));
            double cl = (md2-md1)*(Math.sin(inc2));

            // Devuelve el valor de la estación inmeditaamente anterior
            double n0 = surveys.get(i).getNorthing();
            double e0 = surveys.get(i).getEasting();
            double vd0 = surveys.get(i).getTrueVerticalDepth();

            // Actualiza el valor de la siguient estación de Survey
            surveys.get(i+1).setNorthing(north + n0);
            surveys.get(i+1).setEasting(e0 + east);
            surveys.get(i+1).setTrueVerticalDepth(vd0 + vd);
        }
    }


    /**
     * Average angle method
     */
    public void averageAngleMethod()
    {
        for(int i = 0 ; i < numberStations - 1 ; i++ )
        {
            double md1, md2, inc1, inc2, azm1, azm2;
            md1 = surveys.get(i).getMeasureDistance();
            md2 = surveys.get(i+1).getMeasureDistance();
            inc1 = Math.toRadians(surveys.get(i).getRawInclination());
            inc2 = Math.toRadians(surveys.get(i+1).getRawInclination());
            azm1 = Math.toRadians(surveys.get(i).getMagAzimuth());
            azm2 = Math.toRadians(surveys.get(i+1).getMagAzimuth());


            // Calcula y escribe los north posistion para cada survey
            double north = (md2-md1)*(Math.sin((inc2+inc1)/2))*(Math.cos((azm2+azm1)/2));
            double east =  (md2-md1)*(Math.sin((inc2+inc1)/2))*(Math.sin((azm2+azm1)/2));
            double vd = (md2-md1)*(Math.cos((inc2+inc1)/2));
            double cl = (md2-md1)*(Math.sin((inc2+inc1)/2));

            // Devuelve el valor de la estación inmeditaamente anterior
            double n0 = surveys.get(i).getNorthing();
            double e0 = surveys.get(i).getEasting();
            double vd0 = surveys.get(i).getTrueVerticalDepth();

            // Actualiza el valor de la siguient estación de Survey
            surveys.get(i+1).setNorthing(north + n0);
            surveys.get(i+1).setEasting(e0 + east);
            surveys.get(i+1).setTrueVerticalDepth(vd0 + vd);
        }
    }


    /**
     * Radius of curvature
     */
    public void radiusOfCurvature()
    {
        for(int i = 0 ; i < numberStations - 1 ; i++ )
        {
            double md1, md2, inc1, inc2, azm1, azm2;
            md1 = surveys.get(i).getMeasureDistance();
            md2 = surveys.get(i+1).getMeasureDistance();
            inc1 = Math.toRadians(surveys.get(i).getRawInclination());
            inc2 = Math.toRadians(surveys.get(i+1).getRawInclination());
            azm1 = Math.toRadians(surveys.get(i).getMagAzimuth());
            azm2 = Math.toRadians(surveys.get(i+1).getMagAzimuth());

            // Calcula y escribe los north posistion para cada survey
            double north = (md2-md1)*(((Math.cos(inc1)*Math.cos(inc2)) + (Math.sin(azm1)*Math.sin(azm2)))/((inc2-inc1)*(azm2-azm1)));
            double east =  (md2-md1)*(((Math.cos(inc1)*Math.cos(inc2))+(Math.cos(azm1)*Math.cos(azm2)))/((inc2-inc1)*(azm2-azm1)));
            double vd = (md2-md1)*((Math.sin(inc2) - Math.sin(inc1))/(inc2-inc1));
            double cl = (md2-md1)*(Math.sin((inc2+inc1)/2));

            // Devuelve el valor de la estación inmeditaamente anterior
            double n0 = surveys.get(i).getNorthing();
            double e0 = surveys.get(i).getEasting();
            double vd0 = surveys.get(i).getTrueVerticalDepth();

            // Actualiza el valor de la siguient estación de Survey
            surveys.get(i+1).setNorthing(north + n0);
            surveys.get(i+1).setEasting(e0 + east);
            surveys.get(i+1).setTrueVerticalDepth(vd0 + vd);
        }
    }


    /**
     * Minimum curvature
     */
    public void minimumCurvature()
    {
        for(int i = 0 ; i < numberStations - 1 ; i++ )
        {
            double md1, md2, inc1, inc2, azm1, azm2, dls, a, b, alpha, s;
            md1 = surveys.get(i).getMeasureDistance();
            md2 = surveys.get(i+1).getMeasureDistance();
            inc1 = Math.toRadians(surveys.get(i).getRawInclination());
            inc2 = Math.toRadians(surveys.get(i+1).getRawInclination());
            azm1 = Math.toRadians(surveys.get(i).getMagAzimuth());
            azm2 = Math.toRadians(surveys.get(i+1).getMagAzimuth());

            // Compute alpha and alpha_star, S and Start
            a = Math.pow(Math.sin(((inc2-inc1)/2.0)),2);
            b = Math.sin(inc1)*Math.sin(inc2)*Math.pow((Math.sin(azm2-azm1)/2.0),2);
            double c = Math.sqrt(a + b);
            alpha = 2*Math.asin(c);
            s = (md2-md1);
            dls = ((18000 * alpha) / Math.PI)/s;

            // Calcula y escribe los north position para cada survey
            double t3 = (1-Math.cos(azm2-azm1));
            double theta = Math.acos(Math.cos(inc2-inc1) - (Math.sin(inc1)*Math.sin(inc2)*t3));
            double rf = (2/theta)*(Math.tan((theta/2)));

            double north = ((md2-md1)/2)*((Math.sin(inc1)*Math.cos(azm1))+(Math.sin(inc2)*Math.cos(azm2)))*rf;
            double east =  ((md2-md1)/2)*((Math.sin(inc1)*Math.sin(azm1)) + (Math.sin(inc2)*Math.sin(azm2)))*rf;
            double vd = ((md2-md1)/2)*(Math.cos(inc1) + Math.cos(inc2))*rf;
            double cl = (md2-md1)*(Math.sin((inc2+inc1)/2));

            // Devuelve el valor de la estación inmediatamente anterior
            double n0 = surveys.get(i).getNorthing();
            double e0 = surveys.get(i).getEasting();
            double vd0 = surveys.get(i).getTrueVerticalDepth();

            // Actualiza el valor de la siguiente estación de Survey
            surveys.get(i+1).setDogleg(dls);
            surveys.get(i+1).setNorthing(north + n0);
            surveys.get(i+1).setEasting(e0 + east);
            surveys.get(i+1).setTrueVerticalDepth(vd0 + vd);
            surveys.get(i+1).calVerSec();
        }
        computeProperties();
    }

    /**
     * Mercury with STL Survey Tool Length
     */
    public void mercurySTL(double stl)
    {
        for(int i = 0 ; i < numberStations - 1 ; i++ )
        {
            double md1, md2, inc1, inc2, azm1, azm2;
            md1 = surveys.get(i).getMeasureDistance();
            md2 = surveys.get(i+1).getMeasureDistance();
            inc1 = Math.toRadians(surveys.get(i).getRawInclination());
            inc2 = Math.toRadians(surveys.get(i+1).getRawInclination());
            azm1 = Math.toRadians(surveys.get(i).getMagAzimuth());
            azm2 = Math.toRadians(surveys.get(i+1).getMagAzimuth());

            double north = ((md2-md1-stl)/2)*((Math.sin(inc1)*Math.cos(azm1))+(Math.sin(inc2)*Math.cos(azm2))) + (stl*Math.sin(inc2)*Math.cos(azm2));
            double east =  ((md2-md1-stl)/2)*((Math.sin(inc1)*Math.sin(azm1)) + (Math.sin(inc2)*Math.sin(azm2))) + (stl*Math.sin(inc2)*Math.sin(azm2));
            double vd = ((md2-md1-stl)/2)*(Math.cos(azm2) + Math.cos(azm1)) + stl*Math.cos(azm2);
            double cl = (md2-md1)*(Math.sin((inc2+inc1)/2));

            // Devuelve el valor de la estación inmeditaamente anterior
            double n0 = surveys.get(i).getNorthing();
            double e0 = surveys.get(i).getEasting();
            double vd0 = surveys.get(i).getTrueVerticalDepth();

            // Actualiza el valor de la siguient estación de Survey
            surveys.get(i+1).setNorthing(north + n0);
            surveys.get(i+1).setEasting(e0 + east);
            surveys.get(i+1).setTrueVerticalDepth(vd0 + vd);
        }
    }

    /**
     * This function computes the Toolface angles fro every station along the trajectory.
     */
    public void computeProperties()
    {
        for(int i = 0; i < (numberStations-1) ; i++)
        {
            double md1, md2, inc1, inc2, azm1, azm2, num, den, tf, br, tr;
            md1 = surveys.get(i).getMeasureDistance();
            md2 = surveys.get(i+1).getMeasureDistance();
            inc1 = Math.toRadians(surveys.get(i).getRawInclination());
            inc2 = Math.toRadians(surveys.get(i+1).getRawInclination());
            azm1 = Math.toRadians(surveys.get(i).getMagAzimuth());
            azm2 = Math.toRadians(surveys.get(i+1).getMagAzimuth());

             // Compute Course Length for every station
            surveys.get(i+1).setCourseLength(md2-md1);

            // Compute Toolface for every station
            num = Math.sin(inc2)*Math.sin((azm2-azm1));
            den = (Math.sin(inc2)*Math.cos(inc1)*Math.cos(azm2-azm1)) - (Math.sin(inc1)*Math.cos(inc2));
            tf = Math.atan(num/den);
            if(inc1 > inc2){ tf = tf + (Math.PI/2.0);};
            surveys.get(i+1).setToolFace(Math.toDegrees(tf));

            // Compute Build Rate and Turn Rate for each station
            tr = (azm2-azm1) / (md2-md1);
            br = (inc2-inc1) / (md2-md1);
            surveys.get(i+1).setBuildRate(br);
            surveys.get(i+1).setTurnRate(tr);
        }
    }

    /**
     * Retorna Estaciones de Survey n y n+1 dada una profundidad
     */
    public ArrayList<survey> darSurveysProfundidad(double depth)
    {
        ArrayList<survey> retorno = new ArrayList<>();
        survey nS = new survey();
        survey pS = new survey();

        // Buscar el Survey cercano por profundidad
        for(int i = 0; i<numberStations; i++)
        {
            if(surveys.get(i).getMeasureDistance() > depth)
            {
                nS = surveys.get(i-1);
                pS = surveys.get(i);
            }
        }
        retorno.add(nS);
        retorno.add(pS);
        return retorno;
    }

    /**
     * This method loads a file with all the depths to be interpolated using the current trajectory
     */
    public trajectory interFile(String fileName)
    {
        /*
         * Routine to open and Read text file
         */
        trajectory newTra = new trajectory();
        try {
            File myObj = new File(fileName);
            Scanner myReader = new Scanner(myObj);
            double depthMD = 0.0;
            double stations = 0.0;
            survey nSurvey = null;

            while(myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String md = data.split("\t")[0];
                depthMD = Float.parseFloat(md);
                nSurvey = interpolarMD(depthMD);
                newTra.addSurvey(nSurvey);
                stations++;
            }
            System.out.println("Numero de registros insertados: " + stations + "");
            myReader.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        newTra.computeProperties();
        return newTra;
    }


    /**
     * Interpolacion de una profundidad en MD sobre la trayectoria de Surveys existentes
     * Existen varios tipos de interpolacion
     * Project ahead
     * Survey intermedio
     */

    public survey interpolarMD(double depthMD)
    {
        // New survey object to be returned as interpolated station
        survey nSurvey = new survey();
        survey svyBefore = new survey();
        survey svyAfter = new survey();

        // Find interval in which Svy can be interpolated
        ArrayList<Integer> indexes = new ArrayList<>();
        indexes = findIndexInterval(depthMD);
        svyBefore = surveys.get(indexes.get(0));
        svyAfter = surveys.get(indexes.get(1));

        // Create the new Interpolation as a Survey Station
        double md1, md2, inc1, inc2, azm1, azm2;
        md1 = svyBefore.getMeasureDistance();
        md2 = svyAfter.getMeasureDistance();
        inc1 = Math.toRadians(svyBefore.getRawInclination());
        inc2 = Math.toRadians(svyAfter.getRawInclination());
        azm1 = Math.toRadians(svyBefore.getMagAzimuth());
        azm2 = Math.toRadians(svyAfter.getMagAzimuth());

        // Check if the current depth if equals to the survey station
        if(depthMD == md1)
        {
            return svyBefore;
        }

        if(depthMD == md2)
        {
            return svyAfter;
        }

        // Formulae for DLS
        double alpha = 0;
        double alpha_star = 0;
        double s, s_star = 0;
        double a=0.0,b=0.0,c=0.0,f=0.0, g=0.0, h= 0.0;

        // Compute alpha and alpha_star, S and Start
        a = Math.pow(Math.sin(((inc2-inc1)/2.0)),2);
        h = Math.sin((azm2-azm1)/2.0);
        b = Math.sin(inc1)*Math.sin(inc2)*Math.pow(h,2);
        c = Math.sqrt(a + b);
        alpha = 2*Math.asin(c);
        s = (md2-md1);
        s_star = (depthMD - md1);
        alpha_star = ((depthMD-md1)/(md2-md1))*alpha;
        double dls = ((18000 * alpha) / Math.PI)/s;

        // Compute coefficients a and b
        f = Math.sin((alpha-alpha_star)) / Math.sin(alpha);
        g = Math.sin(alpha_star) / Math.sin(alpha);

        // Compute vectors t1 and t2
        double t1x = Math.sin(inc1)*Math.cos(azm1);
        double t1y = Math.sin(inc1)*Math.sin(azm1);
        double t1z = Math.cos(inc1);

        double t2x = Math.sin(inc2)*Math.cos(azm2);
        double t2y = Math.sin(inc2)*Math.sin(azm2);
        double t2z = Math.cos(inc2);

        // Calculate t_star
        double t3x = (t1x * f) + (t2x * g);
        double t3y = (t1y * f) + (t2y * g);
        double t3z = (t1z * f) + (t2z * g);
        //System.out.println("tx: " + t3x + "ty: " + t3y + "tz: " + t3z);

        // Compute Inclination for the intended Depth
        double nInc = Math.acos(t3z);
        double r = t3y / Math.sin(nInc);
        double nAzm = Math.asin(r);

        // If AZM Avg > 180 Substract 180 deg
        if(((azm1+azm2)/2.0 > Math.PI / 2.0)){ nAzm = (Math.PI) - nAzm;}

        //If nAzm is Negative add 360 deg
        if(nAzm < 0.0){ nAzm = 2*Math.PI + nAzm;}

        // Compute degrees and N-E coordinates for new survey
        // Compute alpha and alpha_star, S and Start
        a = Math.pow(Math.sin(((nInc-inc1)/2.0)),2);
        b = Math.sin(inc1)*Math.sin(nInc)*Math.pow((Math.sin(nAzm-azm1)/2.0),2);
        c = Math.sqrt(a + b);
        alpha = 2*Math.asin(c);
        s = (depthMD-md1);
        dls = ((18000 * alpha) / Math.PI)/s;

        // Calcula y escribe los north position para cada survey
        double t3 = (1-Math.cos(nAzm-azm1));
        double theta = Math.acos(Math.cos(nInc-inc1) - (Math.sin(inc1)*Math.sin(nInc)*t3));
        double rf = (2/theta)*(Math.tan((theta/2)));

        double north = ((depthMD-md1)/2)*((Math.sin(inc1)*Math.cos(azm1))+(Math.sin(nInc)*Math.cos(nAzm)))*rf;
        double east =  ((depthMD-md1)/2)*((Math.sin(inc1)*Math.sin(azm1)) + (Math.sin(nInc)*Math.sin(nAzm)))*rf;
        double vd = ((depthMD-md1)/2)*(Math.cos(inc1) + Math.cos(nInc))*rf;
        double cl = (depthMD-md1)*(Math.sin((nInc+inc1)/2));

        // Devuelve el valor de la estación inmediatamente anterior
        double n0 = svyBefore.getNorthing();
        double e0 = svyBefore.getEasting();
        double vd0 = svyBefore.getTrueVerticalDepth();

        // Actualiza el valor de la siguiente estación de Survey
        nSurvey.setDogleg(dls);
        nSurvey.setNorthing(north + n0);
        nSurvey.setEasting(e0 + east);
        nSurvey.setTrueVerticalDepth(vd0 + vd);
        nSurvey.calVerSec();

        nInc = Math.toDegrees(nInc);
        nAzm = Math.toDegrees(nAzm);
        nSurvey.setMeasureDistance(depthMD);
        nSurvey.setRawInclination(nInc);
        nSurvey.setMagAzimuth(nAzm);
        nSurvey.setDogleg(dls);
        //System.out.println("******************************************************************************");
        //System.out.println("Upper Survey:");
        //svyBefore.imprimir();
        //System.out.println("Lower Survey:");
        //svyAfter.imprimir();
        //System.out.println("Interpolated Survey:");
        //nSurvey.imprimir();
        return nSurvey;
    }

    /**
     * Interpolacion de una profundidad en MD sobre la trayectoria de Surveys existentes
     * Existen varios tipos de interpolacion
     * Project ahead
     * Survey intermedio
     */

    public ArrayList<Integer> findIndexInterval(double depthMD)
    {
        // New survey object to be returned as interpolated station
        int indexBefore = 0;
        int indexAfter = 0;
        ArrayList<Integer> indexes = new ArrayList<>();

        // Find index across the SurveyList Array
        for (int i =0; i < numberStations; i++)
        {
            if (surveys.get(i).getMeasureDistance()>depthMD)
            {
                indexBefore = i-1;
                indexAfter = i;
                break;
            }
        }
        indexes.add(indexBefore);
        indexes.add(indexAfter);
        // Find interval in which Svy can be interpolated
        return indexes;
    }

    /**
     * Imprime el listado de surveys de la trayectoria
     */
    public void imprimirSurveys ()
    {
        for (int i = 0 ; i < surveys.size()  ; i++)
        {
            surveys.get(i).imprimir();
        }
    }
}