import javax.swing.*;
import javax.swing.Timer;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class CreateCanvas extends JPanel implements MouseListener, MouseMotionListener {
    int fps = 1000/60;
    Timer timer;
    Timer hoverTimer;

    CreateCanvas(){
        this.setBackground(Color.black);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        hoverTimer = new Timer(fps/4, f -> {
            repaint();
        });
        /* timer = new Timer(fps, e -> {
            repaint();
        });
        timer.start(); */
    }

    ComponentMaker maker = new ComponentMaker();
    Graphics2D gtd;
    int mouseX = -500; //For hovering
    int mouseY = -500; //For hovering
    int mouseX2 = -500; //For dragging
    int mouseY2 = -500; //For dragging
    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D gtd = (Graphics2D) g;
        this.gtd = gtd;
        gtd.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        /* for(FractalComponent fc : maker.components){
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
        } */

        if(MyFrame.editMode){
            drawHover(gtd, new FractalComponent(mouseX, mouseY, mouseX2, mouseY2));
        }

        if(maker.howManyComponents<1) return;
        if(MyFrame.editMode){
            drawEditLayout(gtd);
        } else {
            FractalAlgorithm fa = new FractalAlgorithm(gtd);
            hoverTimer.stop();
            fa.drawCreatedFractal(maker);
        }
    }
    void drawEditLayout(Graphics2D gtd){
        drawOriginPoint(maker.originPoint, gtd);
        for(int i=0; i<maker.fractalLines.size(); i++){
            drawFractalLine(maker.fractalLines.get(i), gtd);
        }
        for(int i=0; i<maker.continuationPoints.size(); i++){
            drawContinuationPoint(maker.continuationPoints.get(i), gtd);
        }
    }
    void drawHover(Graphics2D gtd, FractalComponent fc){
        switch(ComponentMaker.selectedComponent){
            case 1:
                drawOriginPoint(fc, gtd);
                break;
            case 2:
                drawFractalLine(fc, gtd);
                break;
            case 3:
                drawContinuationPoint(fc, gtd);
                break;
            
        }
    }

    //Draw instructions for each component-----------------------------
    void drawOriginPoint(FractalComponent fc, Graphics2D gtd) {
        int dotSize = 4;
        int circleSize = 14;
        int width = 2;
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
        int dotSize = 8;
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
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        System.out.println("Start");
        if(MyFrame.editMode){
            hoverTimer.start();
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        hoverTimer.stop();
        System.out.println("Stop");
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        if(MyFrame.editMode){
            repaint();
        }
        System.out.println("Movement");
        mouseX = e.getX();
        mouseY = e.getY();
        mouseX2 = mouseX;
        mouseY2 = mouseY;
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
        mouseX2 = e.getX();
        mouseY2 = e.getY();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
}