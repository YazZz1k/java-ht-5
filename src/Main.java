import bd_task.BD_PSQL;
import bd_task.BDtask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    static BD_PSQL askUserAboutDB()
    {
        Scanner in = new Scanner(System.in);

        System.out.print("PSQL username: ");
        String user = in.nextLine();

        System.out.print("user password: ");
        String userPassword = in.nextLine();

        System.out.print("DB URL: ");
        String URL = in.nextLine();

        return new BD_PSQL(user, userPassword, URL);
    }


    static String askUserAboutTable()
    {
        Scanner in = new Scanner(System.in);

        System.out.print("таблица: ");
        return in.nextLine();
    }

    static List<String> askUserAboutFields()
    {
        Scanner in = new Scanner(System.in);

        System.out.print("Поля: ");

        List<String> fields = Arrays.asList(in.nextLine().split(","));

        return fields;
    }



    public static void main(String[] args) {

        //Ввод из тела программы
        BD_PSQL bd1 = new BD_PSQL("postgres", "postgres", "jdbc:postgresql://localhost:5432/db1");
        BD_PSQL bd2 = new BD_PSQL("postgres", "postgres", "jdbc:postgresql://localhost:5432/db2");
        List<String> s = new ArrayList<String>();
        s.add("first_name");
        s.add("last_name");
        System.out.println(BDtask.isEqual(bd1, "users", bd2, "users", s));


        //Ввод с клавы
        /*System.out.println("Введите информацию о первой базе данных");
        BD_PSQL db1 = askUserAboutDB();
        String table1 = askUserAboutTable();

        System.out.println("Введите информацию о второй базе данных");
        BD_PSQL db2 = askUserAboutDB();
        String table2 = askUserAboutTable();

        System.out.println("Введите информацию о сравниваемых полях (через запятую)");
        List<String> fields = askUserAboutFields();


        if(BDtask.isEqual(db1, "users", db2, "users", fields)) {
            System.out.println("Таблицы одинаквые");
        }
        else {
            System.out.println("Таблицы не одинаковые");
        }*/
    }
}
