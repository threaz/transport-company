import java.util.ArrayList;
import java.util.List;

/**
 * Company class models a firm which is in possession of vehicles.
 */

public class Company {

    private static int            m_nextVehicleID             = 0;
    private List<MeanOfTransport> m_availableMeansOfTransport = new ArrayList<>();

    /**
     * Add mean of transport to the company's resources.
     * @param mot vehicle to be added
     */
    public void addMeanOfTransport(MeanOfTransport mot)
    {
        mot.setID(getNextID());
        m_availableMeansOfTransport.add(mot);
    }

    /**
     * Return particular vehicle from company's resources.
     * @param ID vehicle ID
     * @return selected vehicle on success, null otherwise
     */
    public MeanOfTransport getMeanOfTransport(int ID)
    {
        for(MeanOfTransport mot : m_availableMeansOfTransport)
            if(mot.getID() == ID)
                return mot;

        return null;
    }

    // PRIVATE

    private int getNextID()
    {
        return m_nextVehicleID++;
    }

}
