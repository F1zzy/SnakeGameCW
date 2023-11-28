package example;

import javax.swing.JPanel;
import java.util.Observable;
import java.util.Observer;

public class View extends JPanel implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }

    public View(Model model) {
        model.addObserver(this);
    }

}
