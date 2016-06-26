import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Program
{
    public static void main(String[] args)
    {
        Company company                      = new Company();
        ReservationSystem reservationSystem  = ReservationSystem.getInstance();
        CustomerDatabase customerDatabase = new CustomerListDatabase();

        ViewController viewControler  = new ViewController(reservationSystem, customerDatabase);
        LoginView             loginWindow    = new LoginView();

        makeTravels(company, reservationSystem);

        loginWindow.subscribe(viewControler);
        loginWindow.showWindow();
    }

    private static void makeTravels(Company company, ReservationSystem reservationSystem)
    {
        Airplane plane1 = new Airplane( new int[][] { {20, 4} } );
        Airplane plane2 = new Airplane( new int[][] { {10, 4} } );
        Airplane plane3 = new Airplane( new int[][] { {20, 4} } );

        company.addMeanOfTransport(plane1);
        company.addMeanOfTransport(plane2);
        company.addMeanOfTransport(plane3);

        Coach bus1 = new Coach( new int[][] { {10, 4} } );
        company.addMeanOfTransport(bus1);

        final DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        final Calendar dep    = Calendar.getInstance();
        final Calendar arr    = Calendar.getInstance();

        try
        {
            dep.setTime(df.parse("03/11/2016"));
            dep.set(Calendar.HOUR, 12);
            dep.set(Calendar.MINUTE, 22);

            arr.setTime(df.parse("03/11/2016"));
            arr.set(Calendar.HOUR, 19);
            arr.set(Calendar.MINUTE, 20);

            List<Integer> costs = new ArrayList<>();
            for(int i = 0; i < plane1.getTotalSeatsNumber(); ++i)
                if(i % 2 == 0)
                    costs.add(100);
                else
                    costs.add(200);

            reservationSystem.addTravel(plane1, "Warsaw", "Moscow",
                    dep.getTime(), arr.getTime(), costs);

            dep.set(Calendar.HOUR, 9);
            dep.set(Calendar.MINUTE, 0);

            arr.set(Calendar.HOUR, 13);
            arr.set(Calendar.MINUTE, 20);

            costs.clear();
            for(int i = 0; i < plane2.getTotalSeatsNumber(); ++i)
                if(i % 2 == 0)
                    costs.add(100);
                else
                    costs.add(200);

            reservationSystem.addTravel(plane2, "Warsaw", "Moscow",
                    dep.getTime(), arr.getTime(), costs);

            costs.clear();
            for(int i = 0; i < bus1.getTotalSeatsNumber(); ++i)
                if(i % 2 == 0)
                    costs.add(100);
                else
                    costs.add(200);

            reservationSystem.addTravel(bus1, "Tokio", "Wroclaw",
                    dep.getTime(), arr.getTime(), costs);

        }
        catch (ParseException e)
        {

        }
    }
}
