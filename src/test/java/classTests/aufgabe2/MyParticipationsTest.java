package test.java.classTests.aufgabe2;

import main.java.aufgabe1.Participation;
import main.java.aufgabe2.Participations;
import test.java.miniTestSuite.MyClassTest;
import test.java.miniTestSuite.MyMiniTestSuite;
import test.java.miniTestSuite.MyTestInterface;

import java.util.Iterator;

public class MyParticipationsTest extends MyClassTest implements MyTestInterface {

    @Override
    public boolean run() {
        return testPrint()
            && testLookupRacer()
            && testModifiedConstructor()
            && testIterator();
    }

    private boolean testPrint() {
        print("testPrint()");

        Participations participations = new Participations(100);

        participations.add(new Participation("Kitzbühel", "Marcel Hirscher", 1));
        participations.add(new Participation("Kitzbühel", "Hermann Maier", 2));
        participations.add(new Participation("Kitzbühel", "Benni Raich", 3));

        MyMiniTestSuite.changeOutToFile();
        participations.print();
        return testPassed(MyMiniTestSuite.assertOutContent("1 Marcel Hirscher (Kitzbühel)\n" +
            "\n" +
            "2 Hermann Maier (Kitzbühel)\n" +
            "\n" +
            "3 Benni Raich (Kitzbühel)" +
            "\n"));
    }

    private boolean testLookupRacer() {
        print("testLookupRacer()");

        Participations participations = new Participations(100);
        String lookupName = "Marcel Hirscher";
        Participation lookupParticipation = new Participation("Kitzbühel", "Marcel Hirscher", 1);
        String lookupWrongName = "aldskjfhalsdhflajhsdf";

        participations.add(lookupParticipation);
        participations.add(new Participation("Kitzbühel", "Hermann Maier", 2));
        participations.add(new Participation("Kitzbühel", "Benni Raich", 3));

        return testPassed(MyMiniTestSuite.assertEquals(participations.lookupRacer(lookupName), lookupParticipation)
            && MyMiniTestSuite.assertEquals(participations.lookupRacer(lookupWrongName), null));
    }

    private boolean testModifiedConstructor() {
        print("testModifiedConstructor()");

        Participations participations = new Participations(100);

        participations.add(new Participation("Kitzbühel1", "Marcel Hirscher", 1));
        participations.add(new Participation("Kitzbühel2", "Hermann Maier", 2));
        participations.add(new Participation("Kitzbühel3", "Benni Raich", 3));

        Participations testParticipations = new Participations(participations, "Kitzbühel1", "Kitzbühel2");

        MyMiniTestSuite.changeOutToFile();
        testParticipations.print();
        return testPassed(MyMiniTestSuite.assertOutContent("1 Marcel Hirscher (Kitzbühel1)\n" +
            "\n" +
            "2 Hermann Maier (Kitzbühel2)\n"));
    }

    private boolean testIterator() {
        print("testIterator()");

        Participations participations = new Participations(100);

        Participation a = new Participation("Kitzbühel1", "Marcel Hirscher", 1);
        Participation b = new Participation("Kitzbühel2", "Hermann Maier", 2);
        Participation c = new Participation("Kitzbühel3", "Benni Raich", 3);

        participations.add(a);
        participations.add(b);
        participations.add(c);

        Iterator partIterator = participations.iterator();

        return testPassed(
            MyMiniTestSuite.assertTrue(partIterator.next().equals(a))
            && MyMiniTestSuite.assertTrue(partIterator.next().equals(b))
            && MyMiniTestSuite.assertTrue(partIterator.next().equals(c))
            && MyMiniTestSuite.assertFalse(partIterator.hasNext())
        );
    }
}
