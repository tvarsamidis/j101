package gr.codehub.j101.p02collection;

import java.util.Comparator;

public class PersonComparatorByLastLetter implements Comparator<Person> {
    @Override
    public int compare(Person o1, Person o2) {
        String last1 = o1.getName().substring(o1.getName().length() - 1);
        String last2 = o2.getName().substring(o2.getName().length() - 1);
        return last1.compareTo(last2);
    }
}
