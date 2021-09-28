package pl.com.jmotyka.general;

public interface Uploadable {

    String createUploadStatement(Uploadable uploadableObject);

    String createGetIDStatement(Uploadable uploadableObject);

}
