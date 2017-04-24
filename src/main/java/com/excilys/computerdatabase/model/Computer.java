package com.excilys.computerdatabase.model;

import java.time.LocalDate;

public class Computer {
<<<<<<< HEAD
    /**
     * .
     */
    private long id;
    private String name;
    private LocalDate dateIntroduced;
    private LocalDate dateDiscontinued;
    private Company company;
    /**
     * .
     * @param name name
     */
    public Computer(String name) {
        this.name = name;
    }
    /**
     * .
     * @param builder builder
     */
    private Computer(Builder builder) {

        this.id = builder.id;
        this.name = builder.name;
        this.dateIntroduced = builder.dateIntroduced;
        this.dateDiscontinued = builder.dateDiscontinued;
        this.company = builder.company;
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
     * @return company company
     */
    public Company getcompany() {
        return company;
    }
    /**
     * .
     * @param company company
     */
    public void setcompany(Company company) {
        this.company = company;
    }
    /**
     * .
     * @author excilys
     */
    public static class Builder {
        private long id;
        private String name;
        private LocalDate dateIntroduced;
        private LocalDate dateDiscontinued;
        private Company company;
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
        public Builder dateIntroduced(LocalDate dateIntroduced) {
            this.dateIntroduced = dateIntroduced;
            return this;
        }
        /**
         * .
         * @param dateDiscontinued dateDiscontinued
         * @return dateDiscontinued
         */
        public Builder dateDiscontinued(LocalDate dateDiscontinued) {
            this.dateDiscontinued = dateDiscontinued;
            return this;
        }
        /**
         * .
         * @param company company
         * @return company
         */
        public Builder company(Company company) {
            this.company = company;
            return this;
        }
        /**
         * .
         * @return computer
         */
        public Computer build() {
            return new Computer(this);
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
=======

	private long id;
	private String name;
	private LocalDate dateIntroduced;
	private LocalDate dateDiscontinued;
	private Company company;

	public Computer(String name) {
		this.name = name;
	}

	private Computer(Builder builder) {

		this.id = builder.id;
		this.name = builder.name;
		this.dateIntroduced = builder.dateIntroduced;
		this.dateDiscontinued = builder.dateDiscontinued;
		this.company = builder.company;
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

	public Company getcompany() {
		return company;
	}

	public void setcompany(Company company) {
		this.company = company;
	}

	public static class Builder {
		private long id;
		private String name;
		private LocalDate dateIntroduced;
		private LocalDate dateDiscontinued;
		private Company company;

		public Builder(String name) {

			this.name = name;
		}

		public Builder id(Long id) {
			this.id = id;
			return this;
		}

		public Builder dateIntroduced(LocalDate dateIntroduced) {
			this.dateIntroduced = dateIntroduced;
			return this;
		}

		public Builder dateDiscontinued(LocalDate dateDiscontinued) {
			this.dateDiscontinued = dateDiscontinued;
			return this;
		}

		public Builder company(Company company) {
			this.company = company;
			return this;
		}

		public Computer build() {
			return new Computer(this);
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
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Computer other = (Computer) obj;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (dateDiscontinued == null) {
			if (other.dateDiscontinued != null)
				return false;
		} else if (!dateDiscontinued.equals(other.dateDiscontinued))
			return false;
		if (dateIntroduced == null) {
			if (other.dateIntroduced != null)
				return false;
		} else if (!dateIntroduced.equals(other.dateIntroduced))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Computer [id=" + id + ", name=" + name + ", dateIntroduced=" + dateIntroduced + ", dateDiscontinued="
				+ dateDiscontinued + ", company=" + company + "]";
	}
>>>>>>> 73e0817a3d35b4bced2c74c09de8d96d764a7303

}
