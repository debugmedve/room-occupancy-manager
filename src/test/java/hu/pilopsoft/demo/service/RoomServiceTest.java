package hu.pilopsoft.demo.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@SpringBootTest(classes = RoomServiceImpl.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
class RoomServiceTest {

    @Autowired
    private RoomService roomService;

    @Test
    void test() {
        Assertions.assertThrows(UnsupportedOperationException.class, () -> roomService.calculate(null));
    }

    @Configuration
    public static class ContextConfiguration {

    }
}
