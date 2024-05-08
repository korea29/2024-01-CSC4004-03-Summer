package com.dgu_csc.akomanager_back;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class DataService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DataService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertData(MyData data) {
        jdbcTemplate.update("INSERT INTO my_table (column1, column2) VALUES (?, ?)",
                data.getValue1(), data.getValue2());
    }

    public void deleteData(Long id) {
        jdbcTemplate.update("DELETE FROM my_table WHERE id = ?", id);
    }

    public MyData getData(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM my_table WHERE id = ?",
                new Object[]{id},
                (rs, rowNum) -> new MyData(rs.getLong("id"),
                        rs.getString("column1"),
                        rs.getString("column2")));
    }
}

