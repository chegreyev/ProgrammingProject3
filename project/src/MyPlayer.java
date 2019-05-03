import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class MyPlayer implements Player{
    private final Position position;
    private final Circle ball;
    private final Map map;


    MyPlayer(Map map){
        this.map =map;
        position = map.getStartPosition();
        ball = new Circle(map.getStartPosition().getX()*map.getUnit()+map.getUnit()/2,
                map.getStartPosition().getY()*map.getUnit()+map.getUnit()/2, map.getUnit()/2-1);
        ball.setFill(Color.BROWN);

        map.getChildren().add(ball);
    }


    @Override
    public void moveRight(){
        ball.setCenterX(ball.getCenterX()+map.getUnit());
    }
    @Override
    public void moveLeft(){
        ball.setCenterX(ball.getCenterX()-map.getUnit());
    }
    @Override
    public void moveUp(){
        ball.setCenterY(ball.getCenterY()-map.getUnit());
    }
    @Override
    public void moveDown(){
        ball.setCenterY(ball.getCenterY()+map.getUnit());
    }

    @Override
    public Position getPosition(){
        int x = (int)ball.getCenterX()/map.getUnit();
        int y = (int)ball.getCenterY()/map.getUnit();
        Position p = new Position(x,y);
        return p;
    }
}
