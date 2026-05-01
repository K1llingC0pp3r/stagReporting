package pro1.reports.report5.reportDataModel;

import java.util.List;

public class DepartmentExamsStats {
    public long realizedExamsCount;
    public List<String> reservedRooms;

    public DepartmentExamsStats(long realizedExamsCount, List<String> reservedRooms) {
        this.realizedExamsCount = realizedExamsCount;
        this.reservedRooms = reservedRooms;
    }
}