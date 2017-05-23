package com.excilys.computerdatabase.persistence.implementation;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.model.QCompany;
import com.excilys.computerdatabase.model.QComputer;
import com.excilys.computerdatabase.persistence.CompanyDAO;
import com.excilys.computerdatabase.persistence.ComputerDAO;
import com.querydsl.jpa.hibernate.HibernateQueryFactory;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Supplier;

@Repository
@Transactional
public class CompanyDAOImp implements CompanyDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyDAOImp.class.getName());

    @Autowired
    ComputerDAO computerDAOImp;

    QComputer qComputer = QComputer.computer;
    QCompany qCompany = QCompany.company;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    private SessionFactory sessionFactory;

    private Supplier<HibernateQueryFactory> queryFactory = () -> new HibernateQueryFactory(
            sessionFactory.getCurrentSession());

    @Override
    public List<Company> getList() {
        LOGGER.info("CompanyDAO.getList is running");
        List<Company> companysResult = queryFactory.get()
                .select(qCompany)
                .from(qCompany)
                .fetch();
        return companysResult;
    }

    @Override
    public Company getCompanyById(long id) {
        LOGGER.info("CompanyDAO.getCompanyById is running");
        Company companyResult = queryFactory.get().select(qCompany)
                .from(qCompany)
                .where(qCompany.id.eq(id))
                .fetchOne();
        return companyResult;
    }

    @Override
    public Company getCompanyByName(String name) {
        LOGGER.info("CompanyDAO.getCompanyByName is running");
        Company companyResult = queryFactory.get()
                .select(qCompany)
                .from(qCompany)
                .where(qCompany.name.like(name))
                .fetchOne();
        
        return companyResult;
    }

    @Override
    public Company delete(long id) {
        LOGGER.info("Company.delete is running");
        Company company = getCompanyById(id);
        List<Computer> computersResult = queryFactory.get()
                .select(qComputer)
                .from(qComputer)
                .where(qComputer.company.id.eq(id))
                .fetch();
        for (Computer computer : computersResult) {
            computerDAOImp.delete(computer.getId());
        }
        queryFactory.get().delete(qCompany).where(qCompany.id.eq(id)).execute();
        return company;

    }
}
