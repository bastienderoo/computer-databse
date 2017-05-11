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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((company == null) ? 0 : company.hashCode());
        result = prime * result + ((dateDiscontinued == null) ? 0 : dateDiscontinued.hashCode());
        result = prime * result + ((dateIntroduced == null) ? 0 : dateIntroduced.hashCode());
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + (int) (idCompany ^ (idCompany >>> 32));
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ComputerDTO other = (ComputerDTO) obj;
        if (company == null) {
            if (other.company != null) {
                return false;
            }
        } else if (!company.equals(other.company)) {
            return false;
        }
        if (dateDiscontinued == null) {
            if (other.dateDiscontinued != null) {
                return false;
            }
        } else if (!dateDiscontinued.equals(other.dateDiscontinued)) {
            return false;
        }
        if (dateIntroduced == null) {
            if (other.dateIntroduced != null) {
                return false;
            }
        } else if (!dateIntroduced.equals(other.dateIntroduced)) {
            return false;
        }
        if (id != other.id) {
            return false;
        }
        if (idCompany != other.idCompany) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
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

    /**
     * .
     * @param name name
     */
    public ComputerDTO(String name) {
        this.name = name;
    }

    /**
     * .
     *
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

    /**
     * Builder.
     */
    public static class Builder {
        private long id;
        private String name;
        private String dateIntroduced;
        private String dateDiscontinued;
        private String company;
        private long idCompany;

        /**
         * .
         *
         * @param name name
         */
        public Builder(String name) {
            this.name = name;
        }

        /**
         * .
         *
         * @param id id
         * @return id
         */
        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        /**
         * .
         *
         * @param dateIntroduced dateIntroduced
         * @return dateIntroduced
         */
        public Builder dateIntroduced(String dateIntroduced) {
            this.dateIntroduced = dateIntroduced;
            return this;
        }

        /**
         * .
         *
         * @param dateDiscontinued dateDiscontinued
         * @return dateDiscontinued
         */
        public Builder dateDiscontinued(String dateDiscontinued) {
            this.dateDiscontinued = dateDiscontinued;
            return this;
        }

        /**
         * .
         *
         * @param company company
         * @return company
         */
        public Builder company(String company) {
            this.company = company;
            return this;
        }

        /**
         * builder id company.
         * @param idCompany id company
         * @return builder
         */
        public Builder idCompany(long idCompany) {
            this.idCompany = idCompany;
            return this;
        }

        /**
         * .
         *
         * @return computer
         */
        public ComputerDTO build() {
            return new ComputerDTO(this);
        }


    }
}
