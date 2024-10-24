package vip.fairy;


import org.junit.experimental.ParallelComputer;
import org.junit.jupiter.api.Test;
import org.junit.runner.Computer;
import org.junit.runner.JUnitCore;

public class ParallelTests {

    @Test
    void executeTwoInParallel() {
        final Class<?>[] classes = {IntegrationOneTest.class, IntegrationTwoTest.class};
        JUnitCore.runClasses(new ParallelComputer(true, true), classes);
    }

    @Test
    void executeTwoLinear() {
        final Class<?>[] classes = {IntegrationOneTest.class, IntegrationTwoTest.class};
        JUnitCore.runClasses(new Computer(), classes);
    }

}
