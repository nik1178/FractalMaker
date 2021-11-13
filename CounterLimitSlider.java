import javax.swing.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class CounterLimitSlider extends JSlider {
    
    CounterLimitSlider(){
        this.setMaximum(15);
        this.setFocusable(false);
        this.setBackground(new Color(20,20,20));
    }
    int counterLimit = 15;
}
