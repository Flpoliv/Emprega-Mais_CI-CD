package com.empregamais;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * Teste básico que carrega o contexto com o profile de teste.
 */
@SpringBootTest
@ActiveProfiles("test")
class EmpregaMaisApplicationTests {

    @Test
    void contextLoads() {
        // Se a aplicação subir sem erros, o teste passa.
    }
}
