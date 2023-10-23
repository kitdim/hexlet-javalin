package org.example.hexlet;

import io.javalin.Javalin;
import net.datafaker.Faker;
import org.example.hexlet.dto.courses.CoursePage;
import org.example.hexlet.dto.courses.CoursesPage;
import org.example.hexlet.model.Course;

import java.util.Collections;
import java.util.List;

public class HelloWorld {
    private static final List<Course> COURSES = new Faker().collection()
            .suppliers(
                    () -> new Faker().number().numberBetween(1, 100),
                    () -> new Faker().name().firstName(),
                    () -> new Faker().animal().scientificName())
            .minLen(5)
            .maxLen(20)
            .build().get();

    public static void main(String[] args) {
        // Создаем приложение
        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });
        app.get("/", ctx -> ctx.render("index.jte"));
        app.get("/courses", ctx -> {
            var id = ctx.formParamAsClass("id", Integer.class);
            var courses = COURSES;
            var header = "Курсы по программированию";
            var page = new CoursesPage(courses, header);
            ctx.render("courses/index.jte", Collections.singletonMap("page", page));
        });
        app.get("/courses/{id}", ctx -> {
            var id = ctx.formParamAsClass("id", Integer.class);
            var course = COURSES.get(id.get());
            var page = new CoursePage(course);
            ctx.render("courses/show.jte", Collections.singletonMap("page", page));
        });

        app.get("/hello", ctx -> {
            var name = ctx.queryParam("name");
            if (name != null) {
                ctx.result("Hello ".concat(name));
            } else {
                ctx.result("Hello world");
            }
        });
        app.get("/users/{id}/post/{postId}", ctx -> {
            ctx.result("User ID: " + ctx.pathParam("id"));
        });
        app.start(7070);
    }
}
