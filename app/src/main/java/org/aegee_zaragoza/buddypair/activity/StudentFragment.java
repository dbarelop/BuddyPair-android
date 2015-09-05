package org.aegee_zaragoza.buddypair.activity;

import org.aegee_zaragoza.buddypair.data.Student;

import java.util.Comparator;
import java.util.List;

public interface StudentFragment {
    void sortBy(Comparator<Student> comparator);
    void filter(String text);
}
