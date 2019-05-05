import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
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
    private static int count = 1;


    @Override
    public void feed(Food f) {
        this.food = f;
    }

    @Override
    public void eat() throws InterruptedException {
        new Thread(new Runnable() {
            public void run() {
                while(true) {
                    int foodX = MyBotPlayer.this.food.getPosition().getX();
                    int foodY = MyBotPlayer.this.food.getPosition().getY();
                    int botX = MyBotPlayer.this.getPosition().getX();
                    int botY = MyBotPlayer.this.getPosition().getY();
                    if (foodX > botX) {
                        MyBotPlayer.this.moveRight();
                    } else if (foodX < botX) {
                        MyBotPlayer.this.moveLeft();
                    } else if (foodX == botX) {
                        if (foodY > botY) {
                            MyBotPlayer.this.moveDown();
                        } else if (foodY < botY) {
                            MyBotPlayer.this.moveUp();
                        }
                    }

                    try {
                        Thread.sleep(250L);
                    } catch (Exception e) {

                    }
                }
            }
        }).start();
    }


    @Override
    public void find() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList var1 = new ArrayList();
                ArrayList var2 = new ArrayList();
                var1.add(new Safe(0, "", new Position(MyBotPlayer.this.getPosition().getX(), MyBotPlayer.this.getPosition().getY())));
                int[][] var3 = MyBotPlayer.this.map.getMap();
                boolean var4 = true;

                while(true) {
                    int var5;
                    int var6;
                    while(!var4) {
                        for(var5 = 0; var5 < var1.size(); ++var5) {
                            if (((Safe)var1.get(var5)).getPosition().equals(MyBotPlayer.this.food.getPosition())) {
                                var6 = ((Safe)var1.get(var5)).getPosition().getX();
                                int var7 = ((Safe)var1.get(var5)).getPosition().getY();

                                for(int var8 = ((Safe)var1.get(var5)).getN(); var8 > 0; --var8) {
                                    short var9 = 0;

                                    for(int var10 = 0; var10 < var5 + 1; ++var10) {
                                        if (var8 == ((Safe)var1.get(var10)).getN() && (Math.abs(((Safe)var1.get(var10)).getPosition().getX() - var6) < 2 && Math.abs(((Safe)var1.get(var10)).getPosition().getY() - var7) < 1 || Math.abs(((Safe)var1.get(var10)).getPosition().getX() - var6) < 1 && Math.abs(((Safe)var1.get(var10)).getPosition().getY() - var7) < 2) && var9 == 0) {
                                            var2.add((Safe)var1.get(var10));
                                            var6 = ((Safe)var1.get(var10)).getPosition().getX();
                                            var7 = ((Safe)var1.get(var10)).getPosition().getY();
                                            var9 = 500;
                                        }
                                    }
                                }
                            }
                        }

                        for(var5 = var2.size() - 1; var5 > -1; --var5) {
                            if (((Safe)var2.get(var5)).getSide() == "r") {
                                MyBotPlayer.this.moveRight();
                            } else if (((Safe)var2.get(var5)).getSide() == "l") {
                                MyBotPlayer.this.moveLeft();
                            } else if (((Safe)var2.get(var5)).getSide() == "u") {
                                MyBotPlayer.this.moveUp();
                            } else if (((Safe)var2.get(var5)).getSide() == "d") {
                                MyBotPlayer.this.moveDown();
                            }

                            try {
                                Thread.sleep(250L);
                            } catch (InterruptedException var11) {
                                var11.printStackTrace();
                            }
                        }

                        var1 = new ArrayList();
                        var1.add(new Safe(0, "", new Position(MyBotPlayer.this.getPosition().getX(), MyBotPlayer.this.getPosition().getY())));
                        var2 = new ArrayList();
                        var4 = true;
                        MyBotPlayer.count = 1;

                        for(var5 = 0; var5 < MyBotPlayer.this.map.getSize(); ++var5) {
                            for(var6 = 0; var6 < MyBotPlayer.this.map.getSize(); ++var6) {
                                if (var3[var5][var6] == 5) {
                                    var3[var5][var6] = 0;
                                }
                            }
                        }
                    }

                    var5 = var1.size();

                    for(var6 = 0; var6 < var5; ++var6) {
                        if (!((Safe)var1.get(var6)).getPosition().equals(MyBotPlayer.this.food.getPosition())) {
                            if (MyBotPlayer.this.map.getSize() > ((Safe)var1.get(var6)).getPosition().getX() + 1 && var3[((Safe)var1.get(var6)).getPosition().getX() + 1][((Safe)var1.get(var6)).getPosition().getY()] == 0) {
                                var1.add(new Safe(MyBotPlayer.count, "r", new Position(((Safe)var1.get(var6)).getPosition().getX() + 1, ((Safe)var1.get(var6)).getPosition().getY())));
                                var3[((Safe)var1.get(var6)).getPosition().getX() + 1][((Safe)var1.get(var6)).getPosition().getY()] = 5;
                            }

                            if (((Safe)var1.get(var6)).getPosition().getX() > 0 && var3[((Safe)var1.get(var6)).getPosition().getX() - 1][((Safe)var1.get(var6)).getPosition().getY()] == 0) {
                                var1.add(new Safe(MyBotPlayer.count, "l", new Position(((Safe)var1.get(var6)).getPosition().getX() - 1, ((Safe)var1.get(var6)).getPosition().getY())));
                                var3[((Safe)var1.get(var6)).getPosition().getX() - 1][((Safe)var1.get(var6)).getPosition().getY()] = 5;
                            }

                            if (MyBotPlayer.this.map.getSize() > ((Safe)var1.get(var6)).getPosition().getY() + 1 && var3[((Safe)var1.get(var6)).getPosition().getX()][((Safe)var1.get(var6)).getPosition().getY() + 1] == 0) {
                                var1.add(new Safe(MyBotPlayer.count, "d", new Position(((Safe)var1.get(var6)).getPosition().getX(), ((Safe)var1.get(var6)).getPosition().getY() + 1)));
                                var3[((Safe)var1.get(var6)).getPosition().getX()][((Safe)var1.get(var6)).getPosition().getY() + 1] = 5;
                            }

                            if (((Safe)var1.get(var6)).getPosition().getY() > 0 && var3[((Safe)var1.get(var6)).getPosition().getX()][((Safe)var1.get(var6)).getPosition().getY() - 1] == 0) {
                                var1.add(new Safe(MyBotPlayer.count, "u", new Position(((Safe)var1.get(var6)).getPosition().getX(), ((Safe)var1.get(var6)).getPosition().getY() - 1)));
                                var3[((Safe)var1.get(var6)).getPosition().getX()][((Safe)var1.get(var6)).getPosition().getY() - 1] = 5;
                            }
                        } else if (((Safe)var1.get(var6)).getPosition().equals(MyBotPlayer.this.food.getPosition())) {
                            var4 = false;
                        }
                    }

                    MyBotPlayer.count++;
                }
            }
        }).start();
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
