package com.epam.exercise.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.epam.exercise.entity.Company;
import com.epam.exercise.entity.Customer;
import com.epam.exercise.entity.ParkingEvent;
import com.epam.exercise.entity.PrivatePerson;

@Repository
public class DBDaoImpl implements DBDao {

    private List<ParkingEvent> parkings = new ArrayList<ParkingEvent>();
    private List<Customer> customers = new ArrayList<Customer>();
    private final String connCreateURL = "jdbc:derby:memory:memdatabase;create=true";
    private final String connURL = "jdbc:derby:memory:memdatabase";

    private static final String QUERY = "select startdate, enddate from parkingevent where startdate < ? and enddate > ?";

    public DBDaoImpl() {

        Customer customer1 = new Company("company1", 0);
        Customer customer2 = new Company("company2", 1);
        Customer customer3 = new PrivatePerson("person1first", "person1last");
        Customer customer4 = new PrivatePerson("person2first", "person2last");
        customers.add(customer1);
        customers.add(customer2);
        customers.add(customer3);
        customers.add(customer4);

        Date date = new Date(1429204288000L);
        Date date2 = new Date(1429204388000L);
        Date date3 = new Date(1429204408000L);
        Date date4 = new Date(1429208008000L);
        Date date5 = new Date(1429209008000L);
        Date date6 = new Date(1429628884000L);
        Date date7 = new Date(1429369684000L); // 4/18/2015, 5:08:04 PM
        Date date8 = new Date(1429373284000L); // 4/18/2015, 6:08:04 PM
        Date date9 = new Date(1429459684000L); // 4/19/2015, 6:08:04 PM

        final String sql_parkingevent = "CREATE TABLE parkingevent (id INT NOT NULL GENERATED ALWAYS AS IDENTITY, startdate DATE, enddate DATE, customerid INTEGER)";
        final String sql_customer = "CREATE TABLE customer (id INT, telephonenumber VARCHAR(20), streetname VARCHAR(40))";
        final String sql_company = "CREATE TABLE company (id INT NOT NULL GENERATED ALWAYS AS IDENTITY, companyname VARCHAR(20), taxid INTEGER, customerid INTEGER)";
        final String sql_privateperson = "CREATE TABLE privateperson (id INT NOT NULL GENERATED ALWAYS AS IDENTITY, firstname VARCHAR(20), lastname VARCHAR(20), customerid INTEGER)";

        final String insert_customer = "insert into customer(id, telephonenumber, streetname) values(?, ?, ?)";
        final String insert_company = "insert into company(companyname, taxid, customerid) values(?, ?, ?)";
        final String insert_privateperson = "insert into privateperson(firstname, lastname, customerid) values(?, ?, ?)";
        final String insert_parkingevent = "insert into parkingevent(startdate, enddate, customerid) values(?, ?, ?)";

        // Initializing DB
        // Creating database
        try (Connection conn = DriverManager.getConnection(connCreateURL);
                PreparedStatement ps1 = conn.prepareStatement(sql_parkingevent);
                PreparedStatement ps2 = conn.prepareStatement(sql_customer);
                PreparedStatement ps3 = conn.prepareStatement(sql_company);
                PreparedStatement ps4 = conn.prepareStatement(sql_privateperson);) {
            ps1.execute();
            ps2.execute();
            ps3.execute();
            ps4.execute();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Insert example data
        try (Connection conn = DriverManager.getConnection(connURL);
                PreparedStatement ps_customer = conn.prepareStatement(insert_customer);
                PreparedStatement ps_company = conn.prepareStatement(insert_company);
                PreparedStatement ps_privateperson = conn.prepareStatement(insert_privateperson);
                PreparedStatement ps_parkingevent = conn.prepareStatement(insert_parkingevent);) {
            ps_customer.setInt(1, 1);
            ps_customer.setString(1, "customer1telephoneNumber");
            ps_customer.setString(2, "customer1streetName");
            ps_customer.addBatch();
            ps_customer.setInt(1, 2);
            ps_customer.setString(1, "customer2telephoneNumber");
            ps_customer.setString(2, "customer2streetName");
            ps_customer.addBatch();
            ps_customer.executeBatch();

            ps_company.setString(1, "company1Name");
            ps_company.setInt(2, 1);
            ps_company.setInt(3, 1);
            ps_company.addBatch();
            ps_company.executeBatch();

            ps_privateperson.setString(1, "pp1firstName");
            ps_privateperson.setString(2, "pp1lastName");
            ps_privateperson.setInt(3, 2);
            ps_privateperson.addBatch();
            ps_privateperson.executeBatch();

            ps_parkingevent.setDate(1, new java.sql.Date(date7.getTime()));
            ps_parkingevent.setDate(2, new java.sql.Date(date8.getTime()));
            ps_parkingevent.setInt(3, 1);
            ps_parkingevent.addBatch();
            ps_parkingevent.setDate(1, new java.sql.Date(date8.getTime()));
            ps_parkingevent.setDate(2, new java.sql.Date(date9.getTime()));
            ps_parkingevent.setInt(3, 2);
            ps_parkingevent.addBatch();
            ps_parkingevent.executeBatch();

            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // try (Connection conn = DriverManager.getConnection(connURL);
        // PreparedStatement ps = conn.prepareStatement(QUERY);) {
        // ps.setDate(1, new java.sql.Date(date9.getTime()));
        // ps.setDate(2, new java.sql.Date(date.getTime()));
        // ps.execute();
        // ResultSet resultSet = ps.getResultSet();
        // System.out.println("Simple query result:");
        // System.out.println(resultSet);
        // if (resultSet != null) {
        // java.sql.Date date10 = resultSet.getDate("startdate");
        // System.out.println(date10);
        // }
        //
        // } catch (SQLException e) {
        // e.printStackTrace();
        // }

        parkings.add(new ParkingEvent(date, date3, customer1));
        parkings.add(new ParkingEvent(date, date4, customer2));
        parkings.add(new ParkingEvent(date2, date5, customer1));

        parkings.add(new ParkingEvent(date, date3, customer3));
        parkings.add(new ParkingEvent(date, date4, customer4));
        parkings.add(new ParkingEvent(date2, date5, customer4));

        parkings.add(new ParkingEvent(date7, date8, customer1));
        parkings.add(new ParkingEvent(date7, date8, customer3));

        parkings.add(new ParkingEvent(date7, date9, customer1));
        parkings.add(new ParkingEvent(date7, date9, customer3));
    }

    @Override
    public void addParking(ParkingEvent parkingEvent) {
        parkings.add(parkingEvent);
    }

    @Override
    public List<ParkingEvent> getParkings(Date from, Date to) {
        List<ParkingEvent> result = new ArrayList<ParkingEvent>();
        for (ParkingEvent e : parkings) {
            if (e.getStartDate().before(to) && e.getEndDate().after(from)) {
                result.add(e);
            }
        }
        return result;
        // TypedQuery<ParkingEvent> q = em.createQuery(QUERY, ParkingEvent.class);
        // q.setParameter("to", to);
        // q.setParameter("from", from);
        // return q.getResultList();

        // EntityManager manager = SessionManager.getEntityManager();
        // EntityTransaction tran = manager.getTransaction();
        // tran.begin();
        // Query query = manager.createQuery("select b from parkingevent b");
        // List<ParkingEvent> pe = query.getResultList();
        // tran.commit();
        // manager.close();
    }

    @Override
    public List<ParkingEvent> getParkings(Date from, Date to, Customer customer) {
        List<ParkingEvent> tmpResult = getParkings(from, to);
        List<ParkingEvent> result = new ArrayList<ParkingEvent>();

        for (ParkingEvent e : tmpResult) {
            if (e.getCustomer().getType().equals(customer.getType())) {
                result.add(e);
            }
        }
        return result;
    }

    @Override
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }
}