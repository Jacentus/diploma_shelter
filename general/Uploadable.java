package pl.com.jmotyka.general;

public interface Uploadable {

    String getAllParams(); //method returns String to be added to SQL statement containing all fields of the given object together with respective suitable fieldnames from the database.
                           // Construction: 'INSERT INTO [....}+getAllParams

    String createUploadStatement(Uploadable uploadableObject);

    String createGetIDStatement(Uploadable uploadableObject);

}
