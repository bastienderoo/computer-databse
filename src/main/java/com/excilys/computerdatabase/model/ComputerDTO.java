package com.excilys.computerdatabase.model;

public class ComputerDTO {
    private long id;
    private String name;
    private String dateIntroduced;
    private String dateDiscontinued;
    private String company;
    private long idCompany;
    

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDateIntroduced() {
        return dateIntroduced;
    }
    public void setDateIntroduced(String dateIntroduced) {
        this.dateIntroduced = dateIntroduced;
    }
    public String getDateDiscontinued() {
        return dateDiscontinued;
    }
    public void setDateDiscontinued(String dateDiscontinued) {
        this.dateDiscontinued = dateDiscontinued;
    }
    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company = company;
    }
    public long getIdCompany() {
        return idCompany;
    }
    public void setIdCompany(long idCompany) {
        this.idCompany = idCompany;
    }
    public ComputerDTO(String name) {
        this.name = name;
    }
    /**
     * .
     * @param builder builder
     */
    private ComputerDTO(Builder builder) {

        this.id = builder.id;
        this.name = builder.name;
        this.dateIntroduced = builder.dateIntroduced;
        this.dateDiscontinued = builder.dateDiscontinued;
        this.company = builder.company;
        this.idCompany = builder.idCompany;
    }
    public static class Builder {
        private long id;
        private String name;
        private String dateIntroduced;
        private String dateDiscontinued;
        private String company;
        private long idCompany;
        /**
         * .
         * @param name name
         */
        public Builder(String name) {
            this.name = name;
        }
        /**
         * .
         * @param id id
         * @return id
         */
        public Builder id(Long id) {
            this.id = id;
            return this;
        }
        /**
         * .
         * @param dateIntroduced dateIntroduced
         * @return dateIntroduced
         */
        public Builder dateIntroduced(String dateIntroduced) {
            this.dateIntroduced = dateIntroduced;
            return this;
        }
        /**
         * .
         * @param dateDiscontinued dateDiscontinued
         * @return dateDiscontinued
         */
        public Builder dateDiscontinued(String dateDiscontinued) {
            this.dateDiscontinued = dateDiscontinued;
            return this;
        }
        /**
         * .
         * @param company company
         * @return company
         */
        public Builder company(String company) {
            this.company = company;
            return this;
        }
        
        public Builder idCompany(long idCompany) {
            this.idCompany = idCompany;
            return this;
        }

        /**
         * .
         * @return computer
         */
        public ComputerDTO build() {
            return new ComputerDTO(this);
        }
        
      
    }
}
