package pro1.reports.report3;

import com.google.gson.Gson;
import pro1.DataSource;
import pro1.apiDataModel.ActionsList;
import pro1.reports.report3.reportDataModel.WeekdayCount;
import java.util.ArrayList;
import java.util.List;

public class DepartmentWeekdaysReporting {
    public static WeekdayCount[] GetReport(DataSource dataSource, String rok, String katedra, String[] days) {
        String json = dataSource.getRozvrhByKatedra(rok, katedra);
        ActionsList actionsList = new Gson().fromJson(json, ActionsList.class);

        List<WeekdayCount> result = new ArrayList<>();
        if (days != null) {
            for (String day : days) {
                long count = 0;
                if (actionsList != null && actionsList.items != null) {
                    for (var action : actionsList.items) {
                        if (action.dayShortcut != null && day.equals(action.dayShortcut.trim())) {
                            count++;
                        }
                    }
                }
                result.add(new WeekdayCount(day, count));
            }
        }
        return result.toArray(new WeekdayCount[0]);
    }
}