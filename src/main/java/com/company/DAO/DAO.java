package com.company.DAO;

import java.io.FileNotFoundException;

public interface DAO {

    void readFile(String filename) throws FileNotFoundException;
    void writeFile(String filename);


}
