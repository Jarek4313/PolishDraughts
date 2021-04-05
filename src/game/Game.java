package game;

import game.field.Field;
import game.utils.GameConstValue;
import game.utils.GameError;

import java.util.Scanner;

public class Game {
    private Field[][] chessBoard = new Field[GameConstValue.numberOfFields.getValue()][GameConstValue.numberOfFields.getValue()];
    private Scanner scanner;
    private String input;
    private int options;

    public Game() {
        scanner = new Scanner(System.in);

        this.initialChessBoard();
    }

    public void initialChessBoard() {
        int id = 1;
        int offSet = 0;

        for (int i = 0; i< chessBoard.length; i++) {
            for (int j = 0; j < chessBoard[i].length; j++) {
                chessBoard[i][j] = new Field(id);
                id++;
            }
        }
        id = 0;
        for (int i = 0; i < this.chessBoard.length; i++) {
            for (int j = 0; j < this.chessBoard[i].length; j++) {
                if ((id + offSet) % 2 == 0) {
                    if (id < 20) {
                        chessBoard[i][j].setPawn(GameConstValue.pawnBlack);
                    } else if (id >= 80 ) {
                        chessBoard[i][j].setPawn(GameConstValue.pawnWhite);
                    } else {
                        chessBoard[i][j].setField("...");
                    }
                } else {
                    chessBoard[i][j].setField("***");
                }
                id++;
            }
            offSet++;
        }
    }

    public void drawChessBoard() {
        for (int i = 0; i < chessBoard.length; i++) {
            for (int j = 0; j < chessBoard[i].length; j++) {
                if (chessBoard[i][j].getPawn() != null ) {
                    System.out.printf("%s%2d%3d ", this.chessBoard[i][j].getPawnColor(), this.chessBoard[i][j].getPawnId(), this.chessBoard[i][j].getId());
                } else {
                    System.out.printf("%3s%3d ", this.chessBoard[i][j].getField(), this.chessBoard[i][j].getId());
                }

            }
            System.out.println();
        }
        //System.out.println(this.chessBoard[0][0].getPawnId());
    }
    private void validateTypeOptions() {
        try {
            this.options = Integer.parseInt(this.input);
            GameError.errorOfType=true;
        } catch (NumberFormatException e) {
            System.out.println("Error - enter an integer value.");
        }
        GameError.errorOfType=false;
    }
    private boolean validateValueOptions() {
        return true;
    }
    private void inputGameControl(GameConstValue gameConstValue) {
        switch (gameConstValue) {
            case pawnSelection:
                input = scanner.nextLine();
                this.validateTypeOptions();
                break;
            default:
                System.out.println("Incorect input");
        }
    }
    private void writeMessage(GameConstValue gameConstValue) {
        switch(gameConstValue) {
            case pawnSelection:
                System.out.println("Wyznacz figurę: ");
                break;
            default:
                System.out.println("Incorect input");
        }
    }

    public void runGame() {
            this.writeMessage(GameConstValue.pawnSelection);
            this.inputGameControl(GameConstValue.pawnSelection);

            if (GameError.errorOfType && GameError.errorOfValue) {
                //jeśli nie ma błędu typu i wartości, gracz wyznacza figurę oraz teraz może wyznaczyć ruch
            }
            System.out.println(this.options);

    }

}
