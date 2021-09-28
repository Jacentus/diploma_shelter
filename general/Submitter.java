package pl.com.jmotyka.general;

public abstract class Submitter implements Uploadable {

    protected int SubmitterID;

    public abstract String createCheckForDuplicateStatement(Submitter submitter);

    public abstract String showContactData();

////////////////////////////////////////// auto generated Getters & Setters /////////////////////////////////////////////

    public abstract int getSubmitterID();

    public abstract void setSubmitterID(int submitterID);

    public abstract String getName();
}
