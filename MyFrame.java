import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
public class MyFrame extends JFrame implements KeyListener, ChangeListener{
    //Make program where you can create your own fractal then run the simulation which creates the final version
    
    final int WINDOWS_WIDTH = 800;
    final Point CANVASLOCATION = new Point(10,30);
    final Point COUNTERSLIDERLOCATION = new Point(10,10);
    CreateCanvas canvas = new CreateCanvas();
    CounterLimitSlider counterSlider = new CounterLimitSlider();
    MyFrame(){

        //CreateCanvas settings
        canvas.setBounds(CANVASLOCATION.x,CANVASLOCATION.y,WINDOWS_WIDTH,WINDOWS_WIDTH);
        counterSlider.setBounds(COUNTERSLIDERLOCATION.x, COUNTERSLIDERLOCATION.y, WINDOWS_WIDTH, COUNTERSLIDERLOCATION.y);

        //Add components---------------------------------
        this.add(counterSlider);
        this.add(canvas);
        this.addKeyListener(this);
        counterSlider.addChangeListener(this);

        //JFrame settings--------------------------------
        this.setLayout(null);
        this.getContentPane().setBackground(new Color(20,20,20));
        this.setSize(WINDOWS_WIDTH + 40,WINDOWS_WIDTH + 80);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //sss
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    static boolean editMode = true;

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        System.out.println(e.getKeyCode());
        if(e.getKeyCode()>=48 && e.getKeyCode()<58){
            //Numbers 1-9
            ComponentMaker.selectedComponent = e.getKeyCode()-48;
        } else if(e.getKeyCode()==10){
            //Enter key, start the simulaton
            reverseEditMode();
        }
        if(editMode) canvas.repaint();
    }
    void reverseEditMode(){
        if(editMode) {
            editMode = false;
        }
        else {
            editMode = true;
            //canvas.timer.start();
        }
        canvas.repaint();
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        // TODO Auto-generated method stub
        counterSlider.counterLimit = counterSlider.getValue();
        System.out.println(counterSlider.counterLimit);
        FractalAlgorithm.counterLimit = counterSlider.counterLimit;
        canvas.repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }
}
