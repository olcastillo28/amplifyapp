package wellXplorer.backend;

public class survey {

    private int stationNumber = 0 ;
    private double courseLength;
    private double measureDistance;
    private double rawInclination;
    private double magAzimuth;
    private double dogleg;
    private double trueVerticalDepth;
    private double northing;
    private double easting;
    private double verticalSec;
    private double toolFace;
    private double buildRate;
    private double turnRate;
    private double distancetoPlan;
    private double yHS;
    private double xHS;
    private double date;
    private double time;
    private double toolCode;


    public int getStationNumber() {
        return stationNumber;
    }
    public void setStationNumber(int stationNumber) {
        this.stationNumber = stationNumber;
    }
    public double getMeasureDistance() {
        return measureDistance;
    }
    public void setBuildRate(double nBuildRate) {
        this.buildRate = nBuildRate;
    }

    public void setTurnRate(double nTurnRate) {
        this.turnRate = nTurnRate;
    }
    public void setToolFace(double nToolFace) {
        this.toolFace = nToolFace;
    }
    public void setCourseLength(double nCourseLength) {
        this.courseLength = nCourseLength;
    }
    public void setMeasureDistance(double measureDistance) {
        this.measureDistance = measureDistance;
    }
    public double getRawInclination() {
        return rawInclination;
    }
    public void setRawInclination(double rawInclination) {
        this.rawInclination = rawInclination;
    }
    public double getMagAzimuth() {
        return magAzimuth;
    }
    public void setMagAzimuth(double magAzimuth) {
        this.magAzimuth = magAzimuth;
    }
    public double getTrueVerticalDepth() {
        return trueVerticalDepth;
    }
    public void setTrueVerticalDepth(double trueVerticalDepth) {
        this.trueVerticalDepth = trueVerticalDepth;
    }
    public double getEasting() {
        return easting;
    }
    public void setEasting(double easting) {
        this.easting = easting;
    }
    public double getNorthing() {
        return northing;
    }
    public void setNorthing(double northing) {
        this.northing = northing;
    }
    public double getverticalSec() {
        return verticalSec;
    }
    public void setverticalSec(double vSection) {
        this.verticalSec = vSection;}
    public void setDogleg(double nDogleg){this.dogleg = nDogleg; }
    public void imprimir() {
        double md = this.measureDistance;
        double inc = this.rawInclination;
        double azm = this.magAzimuth;
        double tvd = this.trueVerticalDepth;
        double north = this.northing;
        double east = this.easting;
        double dls = this.dogleg;
        double vSec = this.verticalSec;
        System.out.println("Survey: " + md + "\t " + inc + "\t " + azm + "\t" + dls + "\t " + tvd + "\t" + north + "\t" + east +  "\t" + vSec + "\t" + toolFace);
    }
    public double calVerSec() {
        this.verticalSec = Math.sqrt(Math.pow(this.northing,2) + (Math.pow(this.easting,2)));
        return this.verticalSec;
    }
}