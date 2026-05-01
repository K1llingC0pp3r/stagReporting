package pro1.reports.report4;

import com.google.gson.Gson;
import pro1.DataSource;
import pro1.apiDataModel.ThesisList;
import pro1.reports.report4.reportDataModel.YearDuration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class ThesisDurationReporting {
    public static YearDuration[] GetReport(DataSource dataSource, String katedra, String[] years) {
        List<YearDuration> result = new ArrayList<>();
        Gson gson = new Gson();

        for (String year : years) {
            String json = dataSource.getKvalifikacniPrace(year, katedra);
            ThesisList list = gson.fromJson(json, ThesisList.class);

            double totalDays = 0;
            int count = 0;

            if (list != null && list.items != null) {
                for (var thesis : list.items) {
                    if (thesis.datumZadani != null && thesis.datumZadani.isValid() &&
                            thesis.datumOdevzdani != null && thesis.datumOdevzdani.isValid()) {

                        long days = ChronoUnit.DAYS.between(
                                thesis.datumZadani.toLocalDate(),
                                thesis.datumOdevzdani.toLocalDate()
                        );
                        totalDays += days;
                        count++;
                    }
                }
            }

            long average = (count > 0) ? Math.round(totalDays / count) : 0;
            result.add(new YearDuration(year, average));
        }

        return result.toArray(new YearDuration[0]);
    }
}