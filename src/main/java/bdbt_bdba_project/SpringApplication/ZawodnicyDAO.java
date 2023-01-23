package bdbt_bdba_project.SpringApplication;


import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ZawodnicyDAO {
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    @Autowired
    private JdbcTemplate jdbcTemplate1;

    public ZawodnicyDAO(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate1 = jdbcTemplate;
    }

    public List<Zawodnicy> list(int name) {
        String sql = "SELECT * FROM ZAWODNICY WHERE NR_ZAWODNIKA = "+ name;
        List<Zawodnicy> listZawodnicy = jdbcTemplate1.query(sql,
                BeanPropertyRowMapper.newInstance(Zawodnicy.class));
        return listZawodnicy;
    }
    public List<Zawodnicy> allList() {
        String sql = "SELECT * FROM ZAWODNICY ORDER BY NR_ZAWODNIKA";
        List<Zawodnicy> listZawodnicy = jdbcTemplate1.query(sql,
                BeanPropertyRowMapper.newInstance(Zawodnicy.class));
        return listZawodnicy;
    }
    /* Update – aktualizacja danych */
    public int update2(Zawodnicy zawodnicy) {
        String sql = "UPDATE ZAWODNICY SET NR_ZAWODNIKA=:nr_zawodnika, DATA_DOLACZENIA=:data_dolaczenia, NAZWISKO=:nazwisko, IMIE=:imie, DATA_URODZENIA=:data_urodzenia, PESEL=:pesel, NR_KONTA=:nr_konta WHERE NR_ZAWODNIKA=:nr_zawodnika";
        if (( String.valueOf(zawodnicy.getPesel()).length() != 11 )| (String.valueOf(zawodnicy.getNr_konta()).length() != 26 | !isNumeric(zawodnicy.getNr_konta()))| zawodnicy.getImie()=="" | zawodnicy.getNazwisko()=="") {
            return 0;
        } else {
            BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(zawodnicy);
            NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate1);
            template.update(sql,param);
            return 1;
        }

    }


    public void save2(Zawodnicy zawodnicy) {
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate1);
        insertActor.withTableName("zawodnicy").usingColumns("nr_zawodnika","data_dolaczenia", "nazwisko","imie", "data_urodzenia","pesel", "nr_konta","nr_klubu");
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(zawodnicy);
        insertActor.execute(param);

    }
    /* Read – odczytywanie danych z bazy */
    public Zawodnicy get2(int nr_zawodnika) {
        String sql = "SELECT * FROM ZAWODNICY WHERE NR_ZAWODNIKA = " +nr_zawodnika;
        Zawodnicy zawodnicy = jdbcTemplate1.queryForObject(sql, BeanPropertyRowMapper.newInstance(Zawodnicy.class));
        return zawodnicy;
    }
}
