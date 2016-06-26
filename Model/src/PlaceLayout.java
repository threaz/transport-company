import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class PlaceLayout {

    // level, posInRow, posInColumn
    private List<Place[][]> m_places = new ArrayList<>();
    private int             m_floors = 0;


    public PlaceLayout() { }

    public void addFloor(int rows, int cols)
    {
        m_places.add(new Place[rows][cols]);
        ++m_floors;
    }

    public void addPlace(Place place, int floor, int row, int col) throws IndexOutOfBoundsException
    {
        (m_places.get(floor))[row][col] = place;
    }

    public int getRowsOnFloor(int floor)
    {
        return m_places.get(floor).length;
    }

    public int getColsOnFloor(int floor)
    {
        return m_places.get(floor)[0].length;
    }

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

    public int getTotalSeatsNumber() {

        int res = 0;
        for(Place[][] p : m_places)
            res += p.length * p[0].length;

        return res;
    }

    public int getFloorsNumber() {
        return m_floors;
    }
}
