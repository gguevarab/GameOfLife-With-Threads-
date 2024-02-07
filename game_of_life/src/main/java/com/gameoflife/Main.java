package com.gameoflife;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;



public class Main {

    private int mapSize;
    private char[][] gameMap;



    private void start(int size){
        this.gameMap = new char[size][size];
        this.mapSize = size;
        //this.makeMap();
        this.loadData();
        this.printMap();
    }



    private void loadData(){

        try(Scanner scanner = new Scanner(new File("docs/test.txt"))){

            this.mapSize = Integer.parseInt(scanner.nextLine());

            for(int y = 0; y < this.mapSize; y++){
                String[] tempValuesRow = scanner.nextLine().split(",");
                for(int x = 0; x < this.mapSize; x++){
                    if(tempValuesRow[x].equals("true")){
                        this.gameMap[y][x] = '#';
                    } else {
                        this.gameMap[y][x] = '-';
                    }
                }
            }

        } catch(FileNotFoundException e) {
            e.printStackTrace();
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
        for(int y = 0; y < mapSize; y++){
            String tempRowData = "";
            for(int x = 0; x < mapSize; x++){
                tempRowData += this.gameMap[y][x] + " ";
            }
            System.out.println(tempRowData);
        }
    }



    public static void main(String[] args) {
        System.out.println("\nWelcome to the game of life!\n");

        Main mainExecution = new Main();
        mainExecution.start(3);

    }
}