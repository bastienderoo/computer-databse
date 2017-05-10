package com.excilys.computerdatabase.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by excilys on 10/05/17.
 */
public class Page {

    int numberPage;
    int elementByPage;
    List<Computer> listElements = new ArrayList();

    public Page(int numberPage, int elementByPage) {
        this.numberPage = numberPage;
        this.elementByPage = elementByPage; 

    }


}
