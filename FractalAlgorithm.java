import java.awt.*;
import java.awt.geom.*;

public class FractalAlgorithm {

    final int counterLimit = 14;

    Graphics2D gtd;
    FractalAlgorithm(Graphics2D gtd) {
        this.gtd = gtd;
    }
    
    Point startCoordinates;
    ComponentMaker maker;
    void drawCreatedFractal(ComponentMaker maker){
        this.maker = maker;
        Point start = maker.originPoint.returnStartPoint();
        this.startCoordinates = start;
        //gtd.translate(start.x, start.y);
        findFurthestPoints(maker);
        algorithm(start.x, start.y, 0, maxLength, 0);
    }
    int maxLength = 0;
    void findFurthestPoints(ComponentMaker maker){
        //Finds the point that's furthest from the origin point, so that it can then take into account the length of the continuation point and make a size multiplier
        Point origin = maker.originPoint.returnStartPoint();
        for(FractalLine fl : maker.fractalLines){
            int length = (int)Math.round(Math.sqrt( (origin.x - fl.x1)*(origin.x - fl.x1) + (origin.y - fl.y1)*(origin.y - fl.y1)));
            if(length > maxLength){
                maxLength = length;
            }
            length = (int)Math.round(Math.sqrt( (origin.x - fl.x2)*(origin.x - fl.x2) + (origin.y - fl.y2)*(origin.y - fl.y2)));
            if(length > maxLength){
                maxLength = length;
            }
        }
    }
    void algorithm(double x, double y, int counter, double sizeDivider, double cpangle){

        double divideAmount = Math.pow(maxLength/sizeDivider, counter);
        double translationX = x;
        double translationY = y;
        gtd.translate(translationX,translationY);
        gtd.rotate(cpangle);

        if(counter>counterLimit || sizeDivider>maxLength) return;

        /* AffineTransform at = gtd.getTransform();
        int translateX = (int)at.getTranslateX();
        int translateY = (int)at.getTranslateY(); */

        gtd.setPaint(Color.white);
        for(int i=0; i<maker.fractalLines.size(); i++){
            FractalLine fl = maker.fractalLines.get(i);
            int x1 = fl.x1-(int)startCoordinates.getX();
            int y1 = fl.y1-(int)startCoordinates.getY();
            int x2 = fl.x2-(int)startCoordinates.getX();
            int y2 = fl.y2-(int)startCoordinates.getY();
                
            x1/=divideAmount;
            y1/=divideAmount;
            x2/=divideAmount;
            y2/=divideAmount;
            gtd.drawLine(x1, y1, x2, y2);
        }
        for(int i=0; i<maker.continuationPoints.size(); i++){
            ContinuationPoint cp = maker.continuationPoints.get(i);
            int cplength = (int)Math.round(Math.sqrt( (cp.x2 - cp.x1)*(cp.x2 - cp.x1) + (cp.y2 - cp.y1)*(cp.y2 - cp.y1)));
            cpangle = angleBetween2Lines(cp.x1,cp.y1,cp.x2,cp.y2);
            int sentX = cp.x1-(int)startCoordinates.getX();
            int sentY = cp.y1-(int)startCoordinates.getY();
            double translationDivideAmount = Math.pow(maxLength/sizeDivider, counter);
            translationX = (sentX/translationDivideAmount);
            translationY = (sentY/translationDivideAmount);

            algorithm(translationX,translationY, counter+1,cplength, cpangle);

            gtd.rotate(-cpangle);
            //divideAmount = Math.pow(maxLength/(double)cplength, counter);
            gtd.translate(-translationX,-translationY);
        }
        
    }
    public static double angleBetween2Lines(int x1, int y1, int x2, int y2) {
        //Find angle in radians
        double angle1 = Math.atan2(y1-y2,x1-x2);
        angle1-=Math.PI/2;
        return angle1;
    }
}
