package com.excilys.computerdatabase.model;

import java.util.List;

import javax.persistence.Entity;


public class Page<T> {
    private List<T> listElement;
    private int page;
    private int nbrElements;

    public Page(){
        
    }
    
    public Page(List<T> listElement, int page, int nbrElements) {
        this.listElement = listElement;
        this.page = page;
        this.nbrElements = nbrElements;
    }

    public Page(List<T> listElement) {
        this.listElement = listElement;
    }

    public List<T> getList() {
        return listElement;
    }

    public void setList(List<T> listComputer) {
        this.listElement = listComputer;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getNbrElements() {
        return nbrElements;
    }

    public void setNbrElements(int nbrElements) {
        this.nbrElements = nbrElements;
    }

}
