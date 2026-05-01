package pro1.reports.report1;

import com.google.gson.Gson;
import pro1.DataSource;
import pro1.apiDataModel.BooksList;
import pro1.apiDataModel.TeacherCoursesList;
import pro1.reports.report1.reportDataModel.CourseBook;

import java.util.ArrayList;
import java.util.List;

public class TeacherBooksReporting {

    public static List<CourseBook> GetReport(DataSource dataSource, String rok, int ucitIdno, String katedra) {
        var reportItems = new ArrayList<CourseBook>();
        Gson gson = new Gson();

        var coursesJson = dataSource.getPredmetyByUcitel(rok, ucitIdno, katedra);
        var coursesList = gson.fromJson(coursesJson, TeacherCoursesList.class);

        if (coursesList != null && coursesList.items != null) {
            for (var course : coursesList.items) {
                var booksJson = dataSource.getLiteraturaPredmetu(course.code, katedra);
                var booksList = gson.fromJson(booksJson, BooksList.class);

                if (booksList != null && booksList.items != null) {
                    for (var book : booksList.items) {
                        reportItems.add(new CourseBook(book.title, book.author, course.code));
                    }
                }
            }
        }
        return reportItems;
    }
}