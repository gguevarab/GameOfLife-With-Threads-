package com.gameoflife;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;


//Main class
public class Main {

    private int mapSize;
    private int nGenerations;
    private char[][] gameMap;
    private CellThread[][] cellMap;



    private void initialize(int nGen){

        this.loadData();

        CellThread.initializeBarrier(this.mapSize * this.mapSize);

        this.cellMap = new CellThread[this.mapSize][this.mapSize];
        this.nGenerations = nGen;
        //this.makeMap();

        //A cycle to iterate through the generations
        for(int x = 0; x < nGenerations; x++){
            System.out.println("========================== Gen " + x + " ==========================");
            this.runNextGen();
        }
    }



    private void loadData(){

        try(Scanner scanner = new Scanner(new File("docs/n2.txt"))){

            int size = Integer.parseInt(scanner.nextLine());
            this.gameMap = new char[size][size];
            this.mapSize = size;

            for(int x = 0; x < this.mapSize; x++){
                String[] tempValuesRow = scanner.nextLine().split(",");
                for(int y = 0; y < this.mapSize; y++){
                    if(tempValuesRow[y].equals("true")){
                        this.gameMap[x][y] = '#';
                    } else {
                        this.gameMap[x][y] = '-';
                    }
                }
            }

        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }

    }



    private void runNextGen (){

        this.printMap();        

        // We create each cell and set the 'alive status' on each one
        for(int x = 0; x < this.mapSize; x++){
            for(int y = 0; y < this.mapSize; y++){
                if(gameMap[x][y] == '#'){
                    this.cellMap[x][y] = new CellThread(new int[]{x,y}, true, gameMap.length);
                } else {
                    this.cellMap[x][y] = new CellThread(new int[]{x,y}, false, gameMap.length);
                }
            }
        }

        // We then send a copy of the map to the cells so they can use it
        for(int x = 0; x < this.mapSize; x++){
            for(int y = 0; y < this.mapSize; y++){
                this.cellMap[x][y].setMap(cellMap);
                this.cellMap[x][y].start();
            }
        }

        //Main waits for the threads to finish. It not necessary to wait for all of them since all threads have a common cyclicbarrier
        try {
            this.cellMap[0][0].join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        this.refreshMap();


    }



    private void refreshMap(){
        for(int x = 0; x < this.mapSize; x++){
            for(int y = 0; y < this.mapSize; y++){
                if(this.cellMap[x][y].getStatus()){
                    this.gameMap[x][y] = '#';
                } else {
                    this.gameMap[x][y] = '-';
                }
                
            }
        }
    }



    private void makeMap(){

        Random rand = new Random();

        for(int x = 0; x < mapSize; x++){
            for(int y = 0; y < mapSize; y++){
                int randomNumber = rand.nextInt(2);
                if(randomNumber == 1){
                    this.gameMap[y][x] = '#';
                } else {
                    this.gameMap[y][x] = '-';
                }
                
            }
        }


    }



    private void printMap(){
        for(int x = 0; x < mapSize; x++){
            String tempRowData = "";
            for(int y = 0; y < mapSize; y++){
                tempRowData += this.gameMap[x][y] + " ";
            }
            System.out.println(tempRowData);
        }
    }



    public static void main(String[] args) {
        System.out.println("\nWelcome to the game of life!\n");
        System.out.print("Please choose the number of generations: ");
        Scanner input = new Scanner(System.in);
        int number = input.nextInt();

        Main mainExecution = new Main();
        mainExecution.initialize(number);

    }
}