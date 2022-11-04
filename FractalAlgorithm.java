import java.awt.*;
import java.awt.geom.*;

public class FractalAlgorithm {

    static int counterLimit = 10;

    Graphics2D gtd;
    FractalAlgorithm(Graphics2D gtd) {
        this.gtd = gtd;
        this.gtd.setStroke(new BasicStroke(3));
    }
    
    Point startCoordinates;
    ComponentMaker maker;
    long fullTime = 0;
    void drawCreatedFractal(ComponentMaker maker){
        this.maker = maker;
        Point start = maker.originPoint.returnStartPoint();
        this.startCoordinates = start;
        //gtd.translate(start.x, start.y);
        findFurthestPoints(maker);
        gtd.setPaint(Color.white);
        long startTime = System.nanoTime();
        algorithm(start.x, start.y, 0, maxLength, 0);
        System.out.println("FullTime: " + (System.nanoTime()-startTime) + " drawTime: " + fullTime + " percent: " + ((double)fullTime/(System.nanoTime()-startTime)));
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
    int R = 255;
    int G = 255;
    int B = 255;
    int returnStreak = 0;
    void algorithm(double x, double y, int counter, double sizeDivider, double cpangle){

        double divideAmount = Math.pow(maxLength/sizeDivider, counter);
        double translationX = x;
        double translationY = y;
        gtd.translate(translationX,translationY);
        gtd.rotate(cpangle);

        if(counter>counterLimit /* || sizeDivider-5>maxLength */) return;
        for(int i=0; i<maker.fractalLines.size(); i++){
            //Coordinates relative to the starting point
            FractalLine fl = maker.fractalLines.get(i);
            int x1 = fl.x1-(int)startCoordinates.getX();
            int y1 = fl.y1-(int)startCoordinates.getY();
            int x2 = fl.x2-(int)startCoordinates.getX();
            int y2 = fl.y2-(int)startCoordinates.getY();
                
            x1/=divideAmount;
            y1/=divideAmount;
            x2/=divideAmount;
            y2/=divideAmount;
            /* if((int)Math.round(Math.sqrt( (x2 - x1)*(x2 - x1) + (y2 - y1)*(y2 - y1)))<1){
                returnStreak++;
                if(returnStreak>5){
                    returnStreak--;
                    return;
                }
            } else returnStreak=0; */
            if(x1==x2 && y1==y2){
                returnStreak++;
                if(returnStreak>5){
                    returnStreak--;
                    return;
                }
            } else {
                returnStreak=0;
            }
            long startTime = System.nanoTime();
            gtd.drawLine(x1, y1, x2, y2); //Outside of else, so that it draws points as well
            fullTime += System.nanoTime()-startTime;
        }

        for(int i=0; i<maker.continuationPoints.size(); i++){
            ContinuationPoint cp = maker.continuationPoints.get(i);
            int cplength = (int)Math.round(Math.sqrt( (cp.x2 - cp.x1)*(cp.x2 - cp.x1) + (cp.y2 - cp.y1)*(cp.y2 - cp.y1)));
            cpangle = angleBetween2Lines(cp.x1,cp.y1,cp.x2,cp.y2);
            int sentX = cp.x1-(int)startCoordinates.getX();
            int sentY = cp.y1-(int)startCoordinates.getY();
            //double translationDivideAmount = Math.pow(maxLength/sizeDivider, counter);
            double translationDivideAmount = divideAmount;
            translationX = (sentX/translationDivideAmount);
            translationY = (sentY/translationDivideAmount);

            B-=5;
            if(B<0){
                B=255;
                G-=5;
                if(G<0){
                    G=255;
                    R-=5;
                    if(R<0){
                        R=200;
                        G=200;
                        B=200;
                    }
                }
            }
            gtd.setPaint(new Color(R,G,B));
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
