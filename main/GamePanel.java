package main;
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
    
    static final int SCREEN_WIDHT=400;
    static final int SCREEN_HEIGHT=400;
    static final int UNIT_SIZE=10;
    static final int GAME_UNITS= (SCREEN_WIDHT*SCREEN_HEIGHT)/UNIT_SIZE;
    static final int DELAY=0;
    int grid[][] = new int[GAME_UNITS][GAME_UNITS];
    int indexWhite[][] = new int[GAME_UNITS][2];
    boolean running =false;
    Timer timer;
    Random random = new Random();


    GamePanel(){
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.setPreferredSize(new Dimension(SCREEN_WIDHT,SCREEN_HEIGHT));
        this.addMouseListener(new MyMouse());
        startGame();
        
        
    }
    public void startGame(){
        initializeGrid(); 
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
        
        
    }
    public void initializeGrid(){
        
        int max =30;
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

  

 

       
       
    
}
