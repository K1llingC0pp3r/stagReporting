package pro1.reports.report5;

import com.google.gson.Gson;
import pro1.DataSource;
import pro1.apiDataModel.ExamsList;
import pro1.reports.report5.reportDataModel.DepartmentExamsStats;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class DepartmentExamsStatsReporting {

    public static DepartmentExamsStats GetReport(DataSource dataSource, String katedra) {
        String json = dataSource.getTerminyZkousek2(katedra);
        ExamsList examsList = new Gson().fromJson(json, ExamsList.class);

        long realizedCount = 0;
        Set<String> rooms = new HashSet<>();

        if (examsList != null && examsList.items != null) {
            for (var exam : examsList.items) {
                if (exam.obsazeni != null) {
                    try {
                        int count = Integer.parseInt(exam.obsazeni);
                        if (count > 0) realizedCount++;
                    } catch (NumberFormatException ignored) {}
                }

                if (exam.mistnost != null && !exam.mistnost.isBlank()) {
                    rooms.add(exam.mistnost.trim());
                }
            }
        }

        var sortedRooms = new ArrayList<>(rooms);
        Collections.sort(sortedRooms);
        return new DepartmentExamsStats(realizedCount, sortedRooms);
    }
}