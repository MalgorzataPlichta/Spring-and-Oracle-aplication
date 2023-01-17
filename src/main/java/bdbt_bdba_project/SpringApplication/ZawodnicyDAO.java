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
    //httpServletRequest.remoteUser
    public Zawodnicy getZ(int nr_zawodnika){

        String sql = "SELECT * FROM ZAWODNICY WHERE NR_ZAWODNIKA = ?";
        Object[] args = {nr_zawodnika};
        Zawodnicy zawodnicy = jdbcTemplate1.queryForObject(sql, args, BeanPropertyRowMapper.newInstance(Zawodnicy.class));
        System.out.println(zawodnicy);
        return zawodnicy;
    }
}
