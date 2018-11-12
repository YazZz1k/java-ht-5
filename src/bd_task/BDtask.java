package bd_task;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class BDtask {

    private static int getResultSetRowCount(ResultSet rs) {
        int size = 0;
        try {
            rs.last();
            size = rs.getRow();
            rs.beforeFirst();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return size;
    }

    private static List<Map<String, String>> getData(BD_PSQL bd, String table, List<String> fields)
    {
        Connection conn = bd.getConnection();

        List<Map<String, String>> retData = new ArrayList<Map<String, String>>();
        try {
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + table);

            int rowCount = getResultSetRowCount(rs);

            IntStream a = IntStream.rangeClosed(1, rowCount);

            a.forEach(el1->{
                try {
                    rs.next();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                Map addedMap = new HashMap<String, String>();

                fields.forEach(el2->{
                    try {
                        addedMap.put(el2, rs.getString(el2));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });

                retData.add(addedMap);

            });
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return retData;
    }

    public static boolean isEqual(BD_PSQL bd1, String table1, BD_PSQL bd2, String table2, List<String> fields)
    {
        List<Map<String, String>> data1 = getData(bd1, table1, fields);
        List<Map<String, String>> data2 = getData(bd2, table2, fields);

        if(data1.size() != data2.size())
        {
            return false;
        }


        if(data2.size() == 0) {
            return false;
        }
        else {
            data1.forEach(el -> {
                if (!data2.contains(el)) {
                    data2.clear();
                }
            });

            return data2.size() != 0;
        }
    }
}
