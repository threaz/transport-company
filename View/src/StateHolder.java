import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is meant to be extended by other classes. StateHolder class can notify all its subscribers
 * that particular action took place. It's used in observer design pattern.
 */
public class StateHolder {

    protected List<ActionListener> actionListeners = new ArrayList<ActionListener>();

    /**
     * Subscribe to this class. Subscriber will be notified on actions perfermed on objects of
     * StateHolder class.
     * @param actionListener object to receive notification
     */
    public void subscribe(ActionListener actionListener)
    {
        actionListeners.add(actionListener);
    }

    /**
     * Resign from subscription.
     * @param actionListener subscriber to be removed
     */
    public void unsubscribe(ActionListener actionListener)
    {
        if(actionListeners.contains(actionListener))
            actionListeners.remove(actionListener);
    }
}
