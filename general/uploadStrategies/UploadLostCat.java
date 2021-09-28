package pl.com.jmotyka.general.uploadStrategies;

import pl.com.jmotyka.animals.Animal;
import pl.com.jmotyka.animals.Cat;
import pl.com.jmotyka.dbConnectvity.MySQLCon;
import pl.com.jmotyka.general.Uploadable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class UploadLostCat implements UploadStrategy {

    @Override
    public void upload(Animal animal) {
        Cat cat = (Cat)animal;
        String sqlStatement = "INSERT INTO " + cat.getSubmittedBy().getTableName() + animal.getAllParams();
        MySQLCon conn = new MySQLCon();
        conn.sendToDB(sqlStatement);
    }
}


