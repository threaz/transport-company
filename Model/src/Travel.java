import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Travel describes a journey from one place to another which are denoted as 'm_from' and 'm_to' strings.
 * Each travel should have unique ID in order to be recognisable among other travels.
 * m_departure and m_arriving properties describes when the journey starts and ends.
 * To each instance of this class there is attached a MeanOfTransport instance which will be
 * responsible for transporting passengers. Places that are available to be booked are denoted
 * as two maps. m_temporarilyAvailablePlaces includes all places that customers have already
 * booked but maybe have not paid yet. m_availablePlaces tell which places have been booked accordingly.
 * Places in each travel may vary although the same MeanOfTransport can be used to deliver both.
 * Thus m_places holds information of pricing.
 */
public class Travel {
    private int    m_ID;
    private String m_from;
    private String m_to;
    private Date   m_departure;
    private Date   m_arriving;
    private MeanOfTransport m_transport;

    private HashMap<Integer, Boolean> m_temporarilyAvailablePlaces = new HashMap<>();
    private HashMap<Integer, Boolean> m_availablePlaces            = new HashMap<>();
    private HashMap<Integer, Integer> m_placesCost                 = new HashMap<>();

    /**
     * Defaut constructor.
     * @param id travel id
     * @param from place from which travel starts
     * @param to place in which travel ends
     * @param d departure date
     * @param a arrival data
     * @param mot vehicle that provides this travel
     */
    public Travel(int id, String from, String to, Date d, Date a, MeanOfTransport mot)
    {
        m_ID = id;
        m_from = from;
        m_to = to;
        m_transport = mot;
        m_departure = d;
        m_arriving = a;

        // make all seats available
        for(int i = 0; i < m_transport.getTotalSeatsNumber(); ++i) {
            m_availablePlaces.put(i, true);
            m_temporarilyAvailablePlaces.put(i, true);
        }
    }

    /**
     * Return place from which travel start.
     * @return place of start
     */
    public String getFrom()
    {
        return m_from;
    }

    /**
     * Return place in which travel end.
     * @return place of end
     */
    public String getTo()
    {
        return m_to;
    }

    /**
     * Return departure date.
     * @return departure date
     */
    public Date getDeparture()
    {
        return m_departure;
    }

    /**
     * Return arrival date.
     * @return arrival date
     */
    public Date getArrival()
    {
        return m_arriving;
    }

    /**
     * Return travel's ID.
     * @return ID of travel
     */
    public int getID() { return m_ID; }

    /**
     * Return mean of transport that provides travel.
     * @return mean of transport
     */
    public MeanOfTransport getMeanOfTransport()
    {
        return m_transport;
    }

    /**
     * Check whether the place of given number is available to be booked.
     * @param placeNumber
     * @return
     */
    public boolean isPlaceAvailable(int placeNumber)
    {
        return m_temporarilyAvailablePlaces.get(placeNumber);
    }

    /**
     * Reserve place temporarily.
     * @param placeNumber place number to be reserved
     * @return true if success, false otherwise
     */
    public boolean temporarilyReservePlace(int placeNumber)
    {
        return reservePlace(placeNumber, m_temporarilyAvailablePlaces);
    }

    /**
     * Make temporarily reserved place available again.
     * @param placeNumber place number to be freed
     */
    public void removeTemporarilyReservedPlace(int placeNumber)
    {
        removeReservedPlace(placeNumber, m_temporarilyAvailablePlaces);
    }

    /**
     * Reserve place(not temporarily).
     * @param placeNumber place number to be reserved.
     * @return true if success, false otherwise
     */
    public boolean reservePlace(int placeNumber)
    {
        return reservePlace(placeNumber, m_availablePlaces);
    }

    /**
     * Set cost of particular place.
     * @param placeNumber place number
     * @param cost place cost
     */
    public void setPlaceCost(int placeNumber, int cost)
    {
        m_placesCost.put(placeNumber, cost);
    }

    /**
     * Return cost of the particular place.
     * @param placeNumber place number
     * @return requested place cost
     */
    public int  getPlaceCost(int placeNumber) { return m_placesCost.get(placeNumber); }

    /**
     * Return number of places that have not yet been reserved(not temporarily).
     * @return number of places not reserved
     */
    public int getNumberOfAvailablePlaces()
    {
        return getNumberOfAvailablePlaces(m_availablePlaces);
    }

    /**
     * Return number of places that have not yet been reserved temporarily.
     * @return number of places not reserved temporarily
     */
    public int getNumberOfTemporarilyAvailablePlaces() {

        return getNumberOfAvailablePlaces(m_temporarilyAvailablePlaces);
    }

    // PRIVATE

    private int getNumberOfAvailablePlaces(Map<Integer, Boolean> map)
    {
        int res = 0;

        for(Map.Entry e : map.entrySet())
        if(e.getValue() == true)
            ++res;

        return res;
    }

    private void removeReservedPlace(int placeNumber, HashMap<Integer, Boolean> temporairlyAvailablePlaces)
    {
        for(Map.Entry<Integer, Boolean> entry : temporairlyAvailablePlaces.entrySet())
            if(entry.getKey() == placeNumber)
                entry.setValue(true);

    }

    private boolean isPlaceAvailable(int placeNumber, Map<Integer, Boolean> map)
    {
        return map.get(placeNumber);
    }

    private boolean reservePlace(int placeNumber, Map<Integer, Boolean> map)
    {
        if(isPlaceAvailable(placeNumber, map))
        {
            map.put(placeNumber, false);
            return true;
        }

        return false;
    }
}
