import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
public class MyFrame extends JFrame implements KeyListener{
    //Make program where you can create your own fractal then run the simulation which creates the final version
    
    Point canvasLocation = new Point(50,50);
    CreateCanvas canvas = new CreateCanvas();
    MyFrame(){

        //CreateCanvas settings
        canvas.setBounds(canvasLocation.x,canvasLocation.y,500,500);

        //Add components---------------------------------
        this.add(canvas);
        this.addKeyListener(this);

        //JFrame settings--------------------------------
        this.setLayout(null);
        this.getContentPane().setBackground(new Color(20,20,20));
        this.setSize(800,800);
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
        if(editMode && e.getKeyCode()>=48 && e.getKeyCode()<58){
            //Numbers 1-9
            ComponentMaker.selectedComponent = e.getKeyCode()-48;
        } else if(e.getKeyCode()==10){
            //Enter key, start the simulaton
            reverseEditMode();
        }
    }
    void reverseEditMode(){
        if(editMode) {
            editMode = false;
        }
        else {
            editMode = true;
            canvas.timer.start();
        }
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
