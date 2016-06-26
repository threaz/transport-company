/**
 * Place that occur in many different vehicles. Each is designated by its ID,
 * floor on which it is situated and position in m_col'th place i m_row.
 */
public class Place {

    private int     m_ID;
    private int     m_floor;
    private int     m_row;
    private int     m_col;

    /**
     * Defaault constructor.
     * @param ID place ID
     * @param floor floor on which place is situated
     * @param row row in which place is situated
     * @param col position in row
     */
    public Place(int ID, int floor, int row, int col)
    {
        m_ID = ID;
        m_floor  = floor;
        m_row = row;
        m_col = col;
    }

    /**
     * Return place ID.
     * @return place ID
     */
    public int getID() { return m_ID; }

}
