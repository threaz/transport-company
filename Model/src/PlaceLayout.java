import java.util.ArrayList;
import java.util.List;

/**
 * Models the layout of places in vehicle. It can represent multi-floor place layout.
 */
public class PlaceLayout {

    // level, posInRow, posInColumn
    private List<Place[][]> m_places = new ArrayList<>();
    private int             m_floors = 0;

    /**
     * Default constructor.
     */
    public PlaceLayout() { }

    /**
     * Add floor to the layout.
     * @param rows number of rows
     * @param cols number of places in each row
     */
    public void addFloor(int rows, int cols)
    {
        m_places.add(new Place[rows][cols]);
        ++m_floors;
    }

    /**
     * Add place to the specific position in layout.
     * @param place place to be added
     * @param floor floor on which place will be added
     * @param row row in which place will be added
     * @param col position in row in which place will be added
     * @throws IndexOutOfBoundsException
     */
    public void addPlace(Place place, int floor, int row, int col) throws IndexOutOfBoundsException
    {
        (m_places.get(floor))[row][col] = place;
    }

    /**
     * Return the number of rows on specific floor.
     * @param floor number of floor
     * @return number of rows on floor
     */
    public int getRowsOnFloor(int floor)
    {
        return m_places.get(floor).length;
    }

    /**
     * Return the number of places in row on specific floor.
     * @param floor number of floor
     * @return number of places in row on floor.
     */
    public int getColsOnFloor(int floor)
    {
        return m_places.get(floor)[0].length;
    }

    /**
     * Return place at specific position.
     * @param nr place number
     * @return selected place if success, false otherwise
     */
    public Place getPlaceAtNumber(int nr) {

        Place p;

        for(int i = 0; i < m_floors; ++i)
        {
            for(int j = 0; j < m_places.get(i).length; ++j)
                for(int k = 0; k < m_places.get(i)[0].length; ++k)
                    if(( p = m_places.get(i)[j][k]).getID() == nr)
                        return p;
        }

        return null;
    }

    /**
     * Return total number of places in layout.
     * @return total number of places
     */
    public int getTotalPlacesNumber() {

        int res = 0;
        for(Place[][] p : m_places)
            res += p.length * p[0].length;

        return res;
    }

    /**
     * Return number of floors in the layout.
     * @return number of floors in layout
     */
    public int getFloorsNumber() {
        return m_floors;
    }
}
