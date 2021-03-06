package com.github;

import com.github.model.Scheme;
import com.github.repository.DataRepository;
import com.github.repository.EsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SchemeTest {

    @Autowired
    private EsRepository esRepository;

    // @Autowired
    // private EsTransportClientRepository esTransportClientRepository;

    @Autowired
    private DataRepository dataRepository;

    @Sql({"classpath:sql/scheme.sql"})
    @Sql(value = {"classpath:sql/delete.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void test() {
        List<Scheme> schemeList = dataRepository.dbToEsScheme();

        boolean flag = esRepository.saveScheme(schemeList);
        if (flag) {
            esRepository.deleteScheme(schemeList);
        }

        // boolean flag = esTransportClientRepository.saveScheme(schemeList);
        // if (flag) {
        //     esTransportClientRepository.deleteScheme(schemeList);
        // }
    }
}
