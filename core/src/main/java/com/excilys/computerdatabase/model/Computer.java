package com.excilys.computerdatabase.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "computer")
public class Computer {

    /**
     * .
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "introduced")
    private LocalDate dateIntroduced;
    @Column(name = "discontinued")
    private LocalDate dateDiscontinued;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
    private Company company;

    /**
     * .
     *
     * @param name
     *            name
     */
    public Computer(String name) {
        this.name = name;
    }

    /**
     * .
     */
    public Computer() {

    }

    /**
     * .
     * @param id
     *            id
     * @param name
     *            name
     * @param dateIntroduced
     *            introduced
     * @param dateDiscontinued
     *            discontinued
     * @param company
     *            company
     */
    private Computer(long id, String name, LocalDate dateIntroduced, LocalDate dateDiscontinued, Company company) {
        this.id = id;
        this.name = name;
        this.dateIntroduced = dateIntroduced;
        this.dateDiscontinued = dateDiscontinued;
        this.company = company;
    }

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

    public LocalDate getDateIntroduced() {
        return dateIntroduced;
    }

    public void setDateIntroduced(LocalDate dateIntroduced) {
        this.dateIntroduced = dateIntroduced;
    }

    public LocalDate getDateDiscontinued() {
        return dateDiscontinued;
    }

    public void setDateDiscontinued(LocalDate dateDiscontinued) {
        this.dateDiscontinued = dateDiscontinued;
    }

    /**
     * .
     *
     * @return company company
     */
    public Company getcompany() {
        return company;
    }

    /**
     * .
     *
     * @param company
     *            company
     */
    public void setcompany(Company company) {
        this.company = company;
    }

    /**
     * .
     *
     * @author excilys
     */
    public static class Builder {
        private Computer computer = new Computer();

        /**
         * .
         *
         * @param name
         *            name
         */
        public Builder(String name) {
            computer.name = name;
        }

        /**
         * .
         *
         * @param id
         *            id
         * @return id
         */
        public Builder id(Long id) {
            computer.id = id;
            return this;
        }

        /**
         * .
         *
         * @param dateIntroduced
         *            dateIntroduced
         * @return dateIntroduced
         */
        public Builder dateIntroduced(LocalDate dateIntroduced) {
            computer.dateIntroduced = dateIntroduced;
            return this;
        }

        /**
         * .
         *
         * @param dateDiscontinued
         *            dateDiscontinued
         * @return dateDiscontinued
         */
        public Builder dateDiscontinued(LocalDate dateDiscontinued) {
            computer.dateDiscontinued = dateDiscontinued;
            return this;
        }

        /**
         * .
         *
         * @param company
         *            company
         * @return company
         */
        public Builder company(Company company) {
            computer.company = company;
            return this;
        }

        /**
         * .
         *
         * @return computer
         */
        public Computer build() {
            return computer;
        }

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((company == null) ? 0 : company.hashCode());
        result = prime * result + ((dateDiscontinued == null) ? 0 : dateDiscontinued.hashCode());
        result = prime * result + ((dateIntroduced == null) ? 0 : dateIntroduced.hashCode());
        result = prime * result + (int) (id ^ (id >>> 32));
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
        Computer other = (Computer) obj;
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
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Computer [id=" + id + ", name=" + name + ", dateIntroduced=" + dateIntroduced + ", dateDiscontinued="
                + dateDiscontinued + ", company=" + company + "]";
    }

}
