import java.util.ArrayList;
import java.util.List;

/**
 * Created by threaz on 31/05/16.
 */

public class Company {

    private static int            m_nextVehicleID             = 0;
    private List<MeanOfTransport> m_availableMeansOfTransport = new ArrayList<>();

    public void addMeanOfTransport(MeanOfTransport mot)
    {
        mot.setID(getNextID());
        m_availableMeansOfTransport.add(mot);
    }

    public MeanOfTransport getMeanOfTransport(int ID)
    {
        for(MeanOfTransport mot : m_availableMeansOfTransport)
            if(mot.getID() == ID)
                return mot;

        return null;
    }

    private int getNextID()
    {
        return m_nextVehicleID++;
    }

}
