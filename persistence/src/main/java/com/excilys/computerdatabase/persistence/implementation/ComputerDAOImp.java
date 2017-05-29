package com.excilys.computerdatabase.persistence.implementation;

import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.model.QCompany;
import com.excilys.computerdatabase.model.QComputer;
import com.excilys.computerdatabase.persistence.ComputerDAO;
import com.querydsl.jpa.hibernate.HibernateQueryFactory;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Supplier;

/**
 * Classe contenant les méthodes permettant d'effectuer les différentes actions
 * sur la list d'ordinateurs.
 *
 * @author excilys
 */
@Repository
@Transactional
public class ComputerDAOImp implements ComputerDAO {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyDAOImp.class);

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
    public Computer delete(long id) {
        LOGGER.info("Computer.delete is running");
        Computer computer = getComputerById(id);
        queryFactory.get().delete(qComputer).where(qComputer.id.eq(id)).execute();
        return computer;
    }

    @Override
    public List<Computer> getList(int page, int nbrElements) {
        LOGGER.info("Computer.getList is running");
        List<Computer> computersResult = queryFactory.get().select(qComputer).from(qComputer).leftJoin(qCompany)
                .on(qCompany.id.eq(qComputer.company.id)).limit(nbrElements).offset(nbrElements * page).fetch();
        return computersResult;

    }

    @Override
    public long add(Computer computer) {
        LOGGER.info("Computer.add is running");
        sessionFactory.getCurrentSession().save(computer);
        return computer.getId();
    }

    @Override
    public Computer update(Computer computer) {
        LOGGER.info("Computer.update is running");
        queryFactory.get().update(qComputer).where(qComputer.id.eq(computer.getId()))
                .set(qComputer.name, computer.getName()).set(qComputer.dateIntroduced, computer.getDateIntroduced())
                .set(qComputer.dateDiscontinued, computer.getDateDiscontinued())
                .set(qComputer.company, computer.getcompany()).execute();
        return computer;
    }

    @Override
    public Computer getComputerById(long id) {
        LOGGER.info("Computer.getComputerById is running");
        Computer computerResult = queryFactory.get().select(qComputer).from(qComputer).leftJoin(qCompany)
                .on(qComputer.company.id.eq(qCompany.id)).where(qComputer.id.eq(id)).fetchOne();
        return computerResult;

    }

    @Override
    public List<Computer> getComputerByName(String name) {
        LOGGER.info("Computer.getComputerByName is running");
        List<Computer> computersResult = queryFactory.get().select(qComputer).from(qComputer).leftJoin(qCompany)
                .on(qCompany.id.eq(qComputer.company.id)).where(qComputer.name.like("%" + name + "%")).fetch();
        return computersResult;
    }

    @Override
    public int numberComputer() {
        LOGGER.info("Computer.number is running is running");
        return (int) queryFactory.get().from(qComputer).fetchCount();
    }
}