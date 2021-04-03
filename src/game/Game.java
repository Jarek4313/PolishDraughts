package game;

import game.field.Field;
import game.utils.GameConstValue;



public class Game {
    private Field[][] chessBoard = new Field[GameConstValue.numberOfFields.getValue()][GameConstValue.numberOfFields.getValue()];

    public Game() {
        for (int i = 0; i< chessBoard.length; i++) {
            for (int j = 0; j < chessBoard[i].length; j++) {
                chessBoard[i][j] = new Field();
            }
        }

        this.initialChessBoard();
    }

    public void initialChessBoard() {
        int id = 0;
        int offSet = 0;
        //this.chessBoard[0][0].setField("BLC");
        for (int i = 0; i < this.chessBoard.length; i++) {
            for (int j = 0; j < this.chessBoard[i].length; j++) {

                if ((id+offSet) % 2 == 0)
                    chessBoard[i][j].setField("BLC");
                else
                    chessBoard[i][j].setField("WHI");


                id++;
            }
            offSet++;
        }
    }

    public void drawChessBoard() {


        for (int i = 0; i < chessBoard.length; i++) {
            for (int j = 0; j < chessBoard[i].length; j++) {
                System.out.print(this.chessBoard[i][j].getField() + " ");
            }
            System.out.println();
        }
    }

}
