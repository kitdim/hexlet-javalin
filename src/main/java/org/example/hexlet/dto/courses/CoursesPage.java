package org.example.hexlet.dto.courses;

import java.util.List;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.hexlet.model.Course;

import lombok.AllArgsConstructor;
import lombok.Getter;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
public class CoursesPage {
    private List<Course> courses;
    private String header;
}
