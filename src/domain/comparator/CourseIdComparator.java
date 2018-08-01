package domain.comparator;

import domain.Course;

import java.util.Comparator;

public class CourseIdComparator implements Comparator<Course> {
    @Override
    public int compare(Course o1, Course o2) {
        int id1 = o1.getId();
        int id2 = o2.getId();
        return -Integer.compare(id1, id2);
    }
}
