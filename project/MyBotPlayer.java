import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.concurrent.TimeUnit;
/*
if(this.getPosition().getY() != food.getPosition().getY()){
            if(this.getPosition().getY() > food.getPosition().getY()) {
                moveUp();
                Thread.sleep(1000L);
                eat();
            }else if (this.getPosition().getY() < food.getPosition().getY()){
                moveDown();
                Thread.sleep(1000L);
                eat();
            }
        }else if (this.getPosition().getX() != food.getPosition().getX()){
            if(this.getPosition().getX() < food.getPosition().getX()) {
                moveRight();
                Thread.sleep(1000L);
                eat();
            }else if (this.getPosition().getX() > food.getPosition().getX()){
                moveLeft();
                Thread.sleep(1000L);
                eat();
            }
        }
 */

public class MyBotPlayer implements BotPlayer {
    private final Position position;
    private final Circle ball;
    private final Map map;
    private Food food;


    @Override
    public void feed(Food f) {
        this.food = f;
    }

    @Override
    public void eat() throws InterruptedException {
        (new Thread(new Runnable() {
            public void run() {
                while(true) {
                    int var1 = MyBotPlayer.this.food.getPosition().getX();
                    int var2 = MyBotPlayer.this.food.getPosition().getY();
                    int var3 = MyBotPlayer.this.getPosition().getX();
                    int var4 = MyBotPlayer.this.getPosition().getY();
                    if (var1 > var3) {
                        MyBotPlayer.this.moveRight();
                    } else if (var1 < var3) {
                        MyBotPlayer.this.moveLeft();
                    } else if (var1 == var3) {
                        if (var2 > var4) {
                            MyBotPlayer.this.moveDown();
                        } else if (var2 < var4) {
                            MyBotPlayer.this.moveUp();
                        }
                    }

                    try {
                        Thread.sleep(250L);
                    } catch (InterruptedException var6) {
                        
                    }
                }
            }
        })).start();
    }


    @Override
    public void find() {
        // TODO
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

    public MyBotPlayer(Map map ){
        this.map = map;
        position = map.getStartPosition();
        ball = new Circle(map.getStartPosition().getX()*map.getUnit()+map.getUnit()/2,
                map.getStartPosition().getY()*map.getUnit()+map.getUnit()/2, map.getUnit()/2-1);
        ball.setFill(Color.BROWN);

        map.getChildren().add(ball);
    }
}
