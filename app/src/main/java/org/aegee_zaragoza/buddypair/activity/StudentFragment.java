package org.aegee_zaragoza.buddypair.activity;

import org.aegee_zaragoza.buddypair.data.Student;

import java.util.Comparator;

public interface StudentFragment {
    void sortBy(Comparator<Student> comparator);
}
