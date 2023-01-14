package bdbt_bdba_project.SpringApplication;

import lombok.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KlubyDAOTest extends Object {

    private KlubyDAO dao;

    @BeforeEach
    void setUp() throws Exception {
        DriverManagerDataSource datasource = new DriverManagerDataSource();
        datasource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
        datasource.setUsername("MPLICHTA1");
        datasource.setPassword("MPLICHTA1");
        datasource.setDriverClassName("oracle.jdbc.OracleDriver");

        dao = new KlubyDAO(new JdbcTemplate(datasource));
    }

    @Test
    void testList() {
        List<Kluby> klubyList = dao.list();
        assertTrue(!klubyList.isEmpty());
    }

    @Test
    void testSave() throws ParseException {
        Kluby klub = new Kluby(9,"Lekkoaltetyczny 1","2022-11-24",434234444,"marian@gmail.com",2);
        dao.save(klub);
    }

    @Test
    void testGet() {
        int nr_klubu = 5;
        Kluby kluby = dao.get(nr_klubu);
        assertNotNull(kluby);
    }

    @Test
    void testUpdate() {
        Kluby kluby = new Kluby();
        kluby.setNr_klubu(5);
        kluby.setAdres_email("testupdate@o2.pl");
        kluby.setNazwa("testUpadte");
        kluby.setNr_adresu(2);
    }

    @Test
    void testDelete() {
        int nr_klubu =2;
        dao.delete(nr_klubu);
    }
}