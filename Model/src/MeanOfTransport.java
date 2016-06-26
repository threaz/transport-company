/**
 * Models the concept of vehicle that can be used as a mean of transport.
 * Such vehicle is characterized by unique ID number and contains places
 * which can be used by passengers.
 */
public class MeanOfTransport {

    private int         m_ID;
    private PlaceLayout m_placeLayout = new PlaceLayout();

    /**
     * Default constructor.
     * @param ID id that characterises the vehicle, should be unique
     * @param placeInfo specifies the place layout in the vehicle,
     *                  the pattern is: [[nr_of_rows, nr_of_places_in_row]].
     *
     */
    public MeanOfTransport(int ID, int[][] placeInfo)
    {
        m_ID = ID;

        int placeNumber = 0;
        int floors = placeInfo.length;

        for(int i = 0 ; i < floors; ++i)
        {
            int rows, cols;
            rows = placeInfo[i][0]; cols = placeInfo[i][1];

            m_placeLayout.addFloor(rows, cols);

            for(int j = 0; j < rows; ++j)
                for(int k = 0; k < cols; ++k)
                    m_placeLayout.addPlace(new Place(placeNumber++, i, j, k), i, j, k);
        }
    }

    /**
     * Overloaded constructor which sets the default ID of vehicle to 0.
     * @param placeInfo specifies the place layout in the vehicle,
     *                  the pattern is: [[nr_of_rows, nr_of_places_in_row]].
     */
    public MeanOfTransport(int[][] placeInfo)
    {
        this(0, placeInfo);
    }

    /**
     * Return information of the available places in vehicle.
     * Each entry characterises one floor of the vehicle.
     * @return place layout in vehicle
     */
    public int[][] getPlaceLayoutInfo()
    {
        int floors = getFloorsNumber();
        int[][] placeLayoutInfo = new int[floors][2];

        for(int i = 0 ; i < floors; ++i)
        {
            // get rows
            placeLayoutInfo[i][0] = m_placeLayout.getRowsOnFloor(i);
            placeLayoutInfo[i][1] = m_placeLayout.getColsOnFloor(i);
        }

        return placeLayoutInfo;
    }


    /**
     * Return the i-th place in the vehicle.
     * @param i number of place
     * @return i-th place in vehicle
     */
    public Place getPlace(int i) {
        return m_placeLayout.getPlaceAtNumber(i);
    }

    /**
     * Return total seats number in the vehicle.
     * @return total seats number in the vehicle
     */
    public int getTotalSeatsNumber()
    {
        return m_placeLayout.getTotalPlacesNumber();
    }

    /**
     * Return id of the vehicle
     * @return id of the vehicle
     */
    public int getID() {
        return m_ID;
    }

    /**
     * Sets the id of the vehicle
     * @param ID id of the vehicle to be set
     */
    public void setID(int ID) {
        this.m_ID = ID;
    }

    // PRIVATE

    private int getFloorsNumber()
    {
        return m_placeLayout.getFloorsNumber();
    }
}
