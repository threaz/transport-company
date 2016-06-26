import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

// SINGLETON

/**
 * System that store available travels that can be reserved at the time.
 * Furthermore, it enables keeping track of all past orders using OrderDatabase.
 */
public class ReservationSystem
{
    private static ReservationSystem m_instance         = new ReservationSystem();
    private static int               m_nextTravelID;
    private List<Travel>             m_availableTravels = new ArrayList<>();
    private OrderDatabase            m_database         = new OrderMapDatabase();

    /**
     * Return instance of class. Note that because of singleton design pattern
     * there can only be one such instance in the program.
     * @return instance of ReservationSystem class
     */
    public static ReservationSystem getInstance()
    {
        return m_instance;
    }

    /**
     * Reserve place temporarily in given travel.
     * @param travelID unique ID of travel
     * @param placeNumber number of place to be reserved
     * @return true if success, false otherwise
     * @throws RuntimeException
     */
    public boolean temporarilyReservePlace(int travelID, int placeNumber) throws  RuntimeException
    {
        Travel tr = null;
        if((tr = findTravel(travelID)) == null)
            throw new RuntimeException("No such travel.");

        return tr.temporarilyReservePlace(placeNumber);
    }

    /**
     * Remove temporarily reservation of place.
     * @param travelID unique ID of travel
     * @param placeID number of place to remove reservation from
     * @throws RuntimeException
     */
    public void removeTemporarilyReservedPlace(int travelID, int placeID) throws RuntimeException
    {
        Travel tr = null;
        if((tr = findTravel(travelID)) == null)
            throw new RuntimeException("No such travel.");

        tr.removeTemporarilyReservedPlace(placeID);
    }

    /**
     * Reserve place in given travel.
     * @param travelID unique ID of travel
     * @param placeNumber number of place to be reserved
     * @return true if success, false otherwise
     * @throws RuntimeException
     */
    public boolean reservePlace(int travelID, int placeNumber) throws RuntimeException
    {
        Travel tr = null;
        if((tr = findTravel(travelID)) == null)
            throw new RuntimeException("No such travel.");

        return tr.reservePlace(placeNumber);
    }

    /**
     * Add new travel to the system. From now on there is a possibility to reserve place
     * on this travel.
     * @param mot vehicle that provides travel
     * @param from travel's starting place
     * @param to travel's destination
     * @param departure departure date
     * @param arriving arrival date
     * @param costs list of costs, where costs[i] is the cost of the i-th place
     * @throws RuntimeException
     */
    public void addTravel(MeanOfTransport mot, String from, String to,
                          Date departure, Date arriving,
                          List<Integer> costs) throws RuntimeException
    {
        int id = getNextTravelID();
        Travel tr = new Travel(id, from, to,  departure, arriving, mot);

        m_availableTravels.add(tr);

        if(mot.getTotalSeatsNumber() != costs.size())
            throw new RuntimeException("Wrong mapping Places -> Costs");

        for(int i = 0; i < mot.getTotalSeatsNumber(); ++i)
            tr.setPlaceCost(i, costs.get(i));
    }

    /**
     * Return currently available travels provided by airplanes.
     * @return available airplane travels
     */
    public List<Travel> getAvailableAirplaneTravels()
    {
        List<Travel> list = new ArrayList<>();

        for(Travel tr : m_availableTravels)
        {
            if(tr.getMeanOfTransport() instanceof Airplane)
                list.add(tr);
        }

        return list;
    }

    /**
     * Return currently available travels provided by coaches.
     * @return available coach travels
     */
    public List<Travel> getAvailableCoachTravels()
    {
        List<Travel> list = new ArrayList<>();

        for(Travel tr : m_availableTravels)
        {
            if(tr.getMeanOfTransport() instanceof Coach)
                list.add(tr);
        }

        return list;
    }

    /**
     * Return travel with given ID.
     * @param travelID ID of travel
     * @return selected travel on success, null otherise
     */
    public Travel getSelectedTravel(int travelID)
    {
        for(Travel at : m_availableTravels)
            if(at.getID() == travelID)
                return at;

        return null;
    }

    /**
     * Return all travels that match criteria given in selectedTravels parameter.
     * @param selectedTravels criteria to match against available travels
     *                        format: [from, to, day, month, year, hour, min, mean_of_transport]
     * @return list of travels that match given criteria
     */
    public List<Travel> getSelectedTravels(String selectedTravels) {

        String[] details = selectedTravels.split("\\s+");

        String from  = details[0];
        String to    = details[1];
        int    day   = Integer.parseInt(details[2]);
        int    month = Integer.parseInt(details[3]);
        int    year  = Integer.parseInt(details[4]);
        int    hour  = Integer.parseInt(details[5]);
        int    mins  = Integer.parseInt(details[6]);
        String mean  = (details.length == 8) ? details[7] : "both";

        List<Travel> result = new ArrayList<>();

        for(Travel t : m_availableTravels)
            if(! doesTravelMatchCriteria(t, mean, from, to, day, month, year, hour, mins))
                continue;
            else
                result.add(t);

        return result;
    }

    /**
     * Reserve places(not temporarily) on selected travel and add orders
     * to database
     * @param orders
     */
    public void placeOrders(List<Order> orders) {

        for(Order o : orders) {
            m_database.addOrder(o);
            reservePlace(o.getTravelID(), o.getPlaceID());
        }

    }

    // PRIVATE

    private boolean doesTravelMatchCriteria(Travel tr, String mean, String from, String to, int day, int month,
                                            int year, int hour, int mins)
    {
        if(!tr.getFrom().equals(from) || !tr.getTo().equals(to))
            return false;
        if(!(mean.equals("airplane") && tr.getMeanOfTransport() instanceof Airplane) &&
                !(mean.equals("coach") && tr.getMeanOfTransport() instanceof Coach) &&
                !mean.equals("both"))
            return false;

        Date planned_departure = tr.getDeparture();

        final Calendar cal = Calendar.getInstance();
        final DateFormat df = new SimpleDateFormat("MM/dd/yyyy");

        try
        {
            cal.setTime(df.parse(Integer.toString(month) + "/" +
                    Integer.toString(day) + "/" + Integer.toString(year)));
            cal.set(Calendar.HOUR, hour);
            cal.set(Calendar.MINUTE, mins);
        }
        catch (ParseException e)
        {
            throw new NotImplementedException();
        }

        Date user_departure = cal.getTime();

        System.out.println(user_departure);
        System.out.println(planned_departure);

        return user_departure.before(planned_departure) ||
                (! user_departure.before(planned_departure) && ! user_departure.after(planned_departure));
    }

    private ReservationSystem() { }

    private int getNextTravelID()
    { return m_nextTravelID++; }

    private Travel findTravel(int travelID)
    {
        Travel travel = null;

        for(Travel tr : m_availableTravels)
            if(tr.getID() == travelID)
                return tr;

        return travel;
    }
}



