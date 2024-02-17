package com.gameoflife;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.CyclicBarrier;


//Main class
public class Main {

    private int mapSize;
    private int nGenerations;
    private char[][] gameMap;
    private CellThread[][] cellMap;



    private void start(int size, int nGen){

        this.gameMap = new char[size][size];
        this.mapSize = size;
        this.nGenerations = nGen;
        this.makeMap();
        //this.loadData();
        this.printMap();

        //A cycle to iterate through the generations
        for(int x = 0; x < nGenerations; x++){
            this.runNextGen();
        }

    }



    private void loadData(){

        try(Scanner scanner = new Scanner(new File("docs/test.txt"))){

            this.mapSize = Integer.parseInt(scanner.nextLine());

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

        // We create each cell and set the 'alive status' on each one
        for(int x = 0; x < this.mapSize; x++){
            for(int y = 0; y < this.mapSize; y++){
                if(gameMap[x][y] == '#'){
                    this.cellMap[x][y] = new CellThread(new int[]{x,y}, true);
                } else {
                    this.cellMap[x][y] = new CellThread(new int[]{x,y}, false);
                }
            }
        }

        // We then send a copy of the map to the cells so they can use it
        for(int x = 0; x < this.mapSize; x++){
            for(int y = 0; y < this.mapSize; y++){
                this.cellMap[x][y].setMap(cellMap);
            }
        }



    }



    private void makeMap(){

        Random rand = new Random();

        for(int y = 0; y < mapSize; y++){
            for(int x = 0; x < mapSize; x++){
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

        Main mainExecution = new Main();
        mainExecution.start(5, 4);

    }
}