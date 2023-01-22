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

public class ZawodnicyDAOTest {
    private ZawodnicyDAO dao1;

    @BeforeEach
    void setUp() throws Exception {
        DriverManagerDataSource datasource = new DriverManagerDataSource();
        datasource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
        datasource.setUsername("OKRASA7");
        datasource.setPassword("OKRASA3");
        datasource.setDriverClassName("oracle.jdbc.OracleDriver");

        dao1 = new ZawodnicyDAO(new JdbcTemplate(datasource));
    }
    @Test
    void testSave() throws ParseException {

        Zawodnicy zawodnicy = new Zawodnicy(3,"2022-01-12", "Bóbr","Tomek","2002-01-12",11232345767L,"123456789098765676787676",1);
        dao1.save2(zawodnicy);
    }

    @Test
    void testUpdate() {
        Zawodnicy zawodnicy = new Zawodnicy();
        zawodnicy.setNr_klubu(1);
        zawodnicy.setNr_konta("123456789098765676787676");
        zawodnicy.setImie("Mirek");

    }
    @Test
    void testGet(){
        int id = 1;
        Zawodnicy zawodnicy = dao1.get2(id);
        assertNotNull(zawodnicy);
    }
}
