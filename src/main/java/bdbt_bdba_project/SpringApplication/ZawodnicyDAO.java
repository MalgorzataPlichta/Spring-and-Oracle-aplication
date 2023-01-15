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
    private JdbcTemplate jdbcTemplate;

    public ZawodnicyDAO(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
    }
    //httpServletRequest.remoteUser
    public Zawodnicy get(int id){
        Object[] args = {id};
        String sql = "SELECT * FROM ZAWODNICY WHERE NR_ZAWODNIKA="+args[0];
        Zawodnicy zawodnicy = jdbcTemplate.queryForObject(sql, args, BeanPropertyRowMapper.newInstance(Zawodnicy.class));
        return zawodnicy;
    }
}
