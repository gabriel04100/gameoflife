package gameoflife.main;
import java.awt.event.*;
import java.awt.event.KeyAdapter;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.event.MouseInputListener;


public class GamePanel  extends JPanel implements ActionListener {
    
    static final int SCREEN_WIDHT=600;
    static final int SCREEN_HEIGHT=600;
    static final int UNIT_SIZE=25;
    static final int GAME_UNITS= (SCREEN_WIDHT*SCREEN_HEIGHT)/UNIT_SIZE;
    static final int DELAY=0;
    int grid[][] = new int[GAME_UNITS][GAME_UNITS];
    int indexWhite[][] = new int[GAME_UNITS][2];
    public boolean running =false;
    Timer timer;
    Random random = new Random();


    GamePanel(){
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.setPreferredSize(new Dimension(SCREEN_WIDHT,SCREEN_HEIGHT));
        this.addMouseListener(new MyMouse());
        this.addKeyListener(new MyKeyAdapter());
        startGame();
        System.out.println(GAME_UNITS);
        
        
    }
    public void startGame(){
        initializeGrid(); 
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
        
        
    }
    public void initializeGrid(){
        
        int max =10;
        for(int i =0; i<GAME_UNITS;i++){
            for(int j =0; j<GAME_UNITS;j++){
                grid[i][j]=0;
            }
        }

        for(int i =0; i<max;i++){
            for(int j =0; j<max;j++){
                int xRand=random.nextInt((int)SCREEN_WIDHT/UNIT_SIZE);
                int yRand=random.nextInt((int)SCREEN_HEIGHT/UNIT_SIZE);
                grid[xRand][yRand]=1;
        }
    } 
        }
     
        
        
    
    public void updateGrid(){
        if(running==true){
        for(int i =0; i<GAME_UNITS-1;i++){
            for(int j =0; j<GAME_UNITS-1;j++){
                int cnt=countNeighborsAlive(i, j);
                if(grid[i][j]==0 &&(cnt==3)){
                    grid[i][j]=1;
                }

                else if(grid[i][j]==1 && !(cnt==2 || cnt==3)){
                    grid[i][j]=0;

                }
            }
        } 
    }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }
    
    public void draw(Graphics g){
        
        for(int i = 0; i <SCREEN_HEIGHT/UNIT_SIZE;i++){
            g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
            g.drawLine(0, i*UNIT_SIZE, SCREEN_WIDHT, i*UNIT_SIZE);
        }
        for(int i =0; i<GAME_UNITS;i++){
            for (int j =0; j <GAME_UNITS;j++){
                if(grid[i][j]==1){
                    g.setColor(Color.WHITE);
                    g.fillRect(i*UNIT_SIZE, j*UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
                }

            }

        }
        updateGrid();

    }
            
    public void cleanGrid(){
        for(int i =0; i<GAME_UNITS;i++){
            for(int j =0; j<GAME_UNITS;j++){
                grid[i][j]=0;
            }
        }

    }
    public int  countNeighborsAlive(int x,int y){
        int count=0;

        if((x>0) &&(y>0))
        {
            count += grid[x-1][y-1];
            count += grid[x-1][y];
            count += grid[x-1][y+1];

            count += grid[x][y-1];
            count += grid[x][y+1];

            count +=grid[x+1][y-1];
            count +=grid[x+1][y];
            count +=grid[x+1][y+1];
                                            
            }
    
    
    
    
        
        
        return count;

    }  
      
      
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        repaint();
        
        
    }
    public class MyMouse implements MouseInputListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            int xClicked =e.getX();
            int yClicked=e.getY();
            int caseX= ((int)xClicked/UNIT_SIZE);
            int caseY =((int)yClicked/UNIT_SIZE);
            if(grid[caseX][caseY]==1){
                grid[caseX][caseY]=0;

            }
            else{
                grid[caseX][caseY]=1;
            }
                
        }

        @Override
        public void mousePressed(MouseEvent e) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void mouseReleased(MouseEvent e) {
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

        @Override
        public void mouseDragged(MouseEvent e) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            // TODO Auto-generated method stub
            
        }

    }
    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
         switch(e.getKeyCode()){
             case KeyEvent.VK_ESCAPE :
             if(running == true){
                running=false;
             }
             else{
                running=true;
             }
             break; 
             case KeyEvent.VK_ENTER:
             initializeGrid();
             break;
             case KeyEvent.VK_0:
             cleanGrid();
             break;

             
         }
        }
    }
   
           
    
}
