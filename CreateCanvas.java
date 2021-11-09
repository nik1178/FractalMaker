import javax.swing.*;
import javax.swing.Timer;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class CreateCanvas extends JPanel implements MouseListener, ActionListener {
    int fps = 1000/60;
    CreateCanvas(){
        this.setBackground(Color.black);
        this.addMouseListener(this);
        Timer timer = new Timer(fps, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        repaint();
    }

    ComponentMaker maker = new ComponentMaker();
    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D gtd = (Graphics2D) g;
        
        for(FractalComponent fc : maker.components){
            COMPONENTNAMES cn = COMPONENTNAMES.valueOf(fc.getClass().getSimpleName());
            switch(cn){
                case OriginPoint:
                    drawOriginPoint(fc, gtd);
                    break;
                case FractalLine:
                    drawFractalLine(fc, gtd);
                    break;
                case ContinuationPoint:
                    drawContinuationPoint(fc, gtd);
                    break;
            }
        }
    }

    //Draw instructions for each component-----------------------------
    void drawOriginPoint(FractalComponent fc, Graphics2D gtd) {
        int dotSize = 5;
        int circleSize = 15;
        int width = 1;
        gtd.setStroke(new BasicStroke(width));
        gtd.setPaint(Color.red);
        Point start = fc.returnStartPoint();
        gtd.fillOval(start.x-dotSize/2, start.y-dotSize/2, dotSize, dotSize);
        gtd.drawOval(start.x-circleSize/2, start.y-circleSize/2, circleSize, circleSize);
    }
    void drawFractalLine(FractalComponent fc, Graphics2D gtd){
        int width = 4;
        gtd.setPaint(Color.white);
        gtd.setStroke(new BasicStroke(width));
        Point start = fc.returnStartPoint();
        Point end = fc.returnEndPoint();
        gtd.drawLine(start.x, start.y, end.x, end.y);
    }
    void drawContinuationPoint(FractalComponent fc, Graphics2D gtd) {
        int dotSize = 15;
        int lineWidth = 3;
        gtd.setPaint(Color.blue);
        Point start = fc.returnStartPoint();
        Point end = fc.returnEndPoint();
        gtd.fillOval(start.x-dotSize/2, start.y-dotSize/2, dotSize, dotSize);
        gtd.setStroke(new BasicStroke(lineWidth));
        gtd.drawLine(start.x, start.y, end.x, end.y);
    }

    //Mouse controls----------------------------------------------------
    int mouseStartX;
    int mouseStartY;
    @Override
    public void mousePressed(MouseEvent e) {
        mouseStartX = e.getX();
        mouseStartY = e.getY();
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        int mouseEndX = e.getX();
        int mouseEndY = e.getY();
        maker.addComponent(mouseStartX, mouseStartY, mouseEndX, mouseEndY);
        ComponentMaker.currentComponent++;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }


    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
}