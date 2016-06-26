import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by threaz on 26/06/16.
 */
public class StateHolder {

    protected List<ActionListener> actionListeners = new ArrayList<ActionListener>();

    public void subscribe(ActionListener actionListener)
    {
        actionListeners.add(actionListener);
    }

    public void unsubscribe(ActionListener actionListener)
    {
        if(actionListeners.contains(actionListener))
            actionListeners.remove(actionListener);
    }
}
