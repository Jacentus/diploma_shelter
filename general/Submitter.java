package pl.com.jmotyka.general;

import pl.com.jmotyka.general.uploadStrategies.UploadStrategy;

public abstract class Submitter implements Uploadable {

    protected int SubmitterID;

    //protected UploadStrategy uploadStrategy; TODO ZASTANOW SIE CZY MOZNA POLACZYC TO ZE STRATEGIA UPLOADOWANIA
    

    public abstract String createCheckForDuplicateStatement(Submitter submitter);

    public abstract String showContactData();

////////////////////////////////////////// auto generated Getters & Setters /////////////////////////////////////////////

    public abstract int getSubmitterID();

    public abstract void setSubmitterID(int submitterID);

    public abstract String getName();

    public abstract String getTableName();


}
