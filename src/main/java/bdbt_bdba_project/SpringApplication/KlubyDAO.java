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
public class KlubyDAO {

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

    public static boolean isValidEmail(String email) {
    // create the EmailValidator instance
    EmailValidator validator = EmailValidator.getInstance();
    // check for valid email addresses using isValid method
    return validator.isValid(email);
}

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public KlubyDAO(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Kluby> list(){
        String sql = "SELECT * FROM KLUBY ORDER BY NR_KLUBU";
        List<Kluby> listKluby = jdbcTemplate.query(sql,
                BeanPropertyRowMapper.newInstance(Kluby.class));
        return listKluby;
    }
    /* Insert – wstawianie nowego wiersza do bazy */
    public void save(Kluby kluby) {
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("kluby").usingColumns("nr_klubu", "nazwa", "data_otwarcia","nr_telefonu","adres_email", "nr_adresu");
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(kluby);
        insertActor.execute(param);

    }
    /* Read – odczytywanie danych z bazy */
    public Kluby get(int nr_klubu) {
        Object[] args = {nr_klubu};
        String sql = "SELECT * FROM KLUBY WHERE NR_KLUBU = " + args[0];
        Kluby kluby = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Kluby.class));
        return kluby;
    }

    /* Update – aktualizacja danych */
    public int update(Kluby kluby) {
        String sql = "UPDATE KLUBY SET NR_KLUBU=:nr_klubu, NAZWA=:nazwa, DATA_OTWARCIA=:data_otwarcia, NR_TELEFONU=:nr_telefonu, ADRES_EMAIL=:adres_email, NR_ADRESU=:nr_adresu WHERE NR_KLUBU=:nr_klubu";
        if (!isValidEmail(kluby.getAdres_email()) | !(kluby.getNr_adresu() >= 1) | kluby.getNazwa() == "" | String.valueOf(kluby.getNr_telefonu()).length() != 9) {
            return 0;
        } else {
            BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(kluby);
            NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
            template.update(sql, param);
            return 1;
        }
    }

    /* Delete – wybrany rekord z danym id */
    public void delete(int nr_klubu) {
        String sql = "DELETE FROM KLUBY WHERE NR_KLUBU = ?";
        jdbcTemplate.update(sql,nr_klubu);
    }
}
