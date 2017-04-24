package com.excilys.computerdatabase.model;

public class CompanyDTO {
    private long id;
    private String name;
/**
 * .
 * @param builder builder
 */
    private CompanyDTO(Builder builder) {
        this.name = builder.name;
        this.id = builder.id;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static class Builder {
        private long id;
        private String name;
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
        public Builder id(long id) {
            this.id = id;
            return this;
        }
/**
 * .
 * @return company
 */
        public CompanyDTO build() {
            return new CompanyDTO(this);
        }

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
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
        CompanyDTO other = (CompanyDTO) obj;
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
        return "Company [id=" + id + ", name=" + name + "]";
    }
}
