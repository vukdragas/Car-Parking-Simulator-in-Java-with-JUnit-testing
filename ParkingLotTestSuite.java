import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

//javac -cp "junit5all.jar;checkthat.jar;." Size*.java Space*.java Car*.java Gate*.java Parking*.java vehicle/*.java parking/facility/*.java parking/*.java
//java -jar junit5all.jar execute -c ParkingLotTestSuite  -cp . -cp checkthat.jar 

import parking.facility.GateTest;
import parking.ParkingLotTest;

@SelectClasses({
    ParkingLotTestSuite.StructuralTests.class,
    ParkingLotTestSuite.FunctionalTests.class,
})
@Suite public class ParkingLotTestSuite {
    @SelectClasses({
        ParkingLotStructureTest.class,
        ParkingLotTestStructureTest.class,

        SpaceStructureTest.class,
        GateStructureTest.class,
        GateTestStructureTest.class,

        SizeStructureTest.class,
        CarStructureTest.class,

    })
    @Suite public static class StructuralTests {}

    @SelectClasses({
        GateTest.class,
        ParkingLotTest.class,
    })
    @Suite public static class FunctionalTests {}
}

