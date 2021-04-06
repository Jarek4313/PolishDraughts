package game;

import game.field.Field;
import game.utils.GameConstValue;
import game.utils.GameError;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private Field[][] chessBoard = new Field[GameConstValue.numberOfFields.getValue()][GameConstValue.numberOfFields.getValue()];
    private ArrayList<Integer> whitePawns; //listy przechowujące id pionków obecnych na szachownicy,
    private ArrayList<Integer> blackPawns; //gdy któryś zostanie strącony to zostanie też usunięty z tych list
    private ArrayList<Integer> fieldsChessboard;

    private Scanner scanner;
    private String input;
    private int options;
    private int choosenPawn;

    public Game() {
        scanner = new Scanner(System.in);
        whitePawns = new ArrayList<>();
        blackPawns = new ArrayList<>();
        fieldsChessboard = new ArrayList<>();

        this.initialChessBoard();
    }
    private void addPawnToList(GameConstValue gameConstValue, int idPawn) {
        switch (gameConstValue) {
            case pawnBlack:
                blackPawns.add(idPawn);
                break;
            case pawnWhite:
                whitePawns.add(idPawn);
                break;
            default:
                this.writeMessage(GameConstValue.errorAddPawnToList);
        }
    }
    private void deletePawnFromList(GameConstValue gameConstValue, int idPawn) {
        switch (gameConstValue) {
            case pawnBlack:
                blackPawns.remove(idPawn);
                break;
            case pawnWhite:
                whitePawns.remove(idPawn);
                break;
            default:
                this.writeMessage(GameConstValue.errorRemovePawnFromList);
        }
    }


    private void validateTypeOptions() {

        try {
            this.options = Integer.parseInt(this.input);
            GameError.errorOfType=true;
        } catch (NumberFormatException e) {
            GameError.errorOfType=false;
            System.out.println("Error - enter an integer value.");
        }
    }
    private void validateValueOptionsofPawn() {
        if ( blackPawns.contains(this.options)) {
            GameError.errorOfValue = true;
        } else {
            System.out.println("Error - you choose unidentfied pawn");
            GameError.errorOfValue = false;
        }
    }
    private void validateValueOptionsofField() {
        //if
    }

    private void inputGameControl(GameConstValue gameConstValue) {
        switch (gameConstValue) {
            case pawnSelection:
                input = scanner.nextLine();
                this.validateTypeOptions();
                this.validateValueOptionsofPawn();
                break;
            case fieldSelection:
                input = scanner.nextLine();
                this.validateTypeOptions();
                this.validateValueOptionsofField();
            default:
                System.out.println("Incorect input");
        }
    }


    public void runGame() {
            this.writeMessage(GameConstValue.wirtePawnsList);
            this.writeMessage(GameConstValue.writeFieldLis);
            this.writeMessage(GameConstValue.pawnSelection);
            this.inputGameControl(GameConstValue.pawnSelection);

            if (GameError.errorOfType && GameError.errorOfValue) {
                this.inputGameControl(GameConstValue.fieldSelection);
            }

            System.out.println(this.options);

    }
    private void initalPawns(int id, int i, int j) {
        if (id < 20) {
            chessBoard[i][j].setPawn(GameConstValue.pawnBlack);
            this.addPawnToList(GameConstValue.pawnBlack, chessBoard[i][j].getPawn().getPawnId());
        } else if (id >= 80 ) {
            chessBoard[i][j].setPawn(GameConstValue.pawnWhite);
            this.addPawnToList(GameConstValue.pawnWhite, chessBoard[i][j].getPawn().getPawnId());
        }
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

                    chessBoard[i][j].setField("...");
                    this.initalPawns(id, i, j);
                    this.fieldsChessboard.add(id);
                } else {
                    chessBoard[i][j].setField("---");
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
                    System.out.printf("%s%2d%4s ", this.chessBoard[i][j].getPawnColor(), this.chessBoard[i][j].getPawnId(), "    ");
                } else {
                    System.out.printf("%3s%4s ", this.chessBoard[i][j].getField(), this.chessBoard[i][j].getStrId());
                }

            }
            System.out.println();
        }
        //System.out.println(this.chessBoard[0][0].getPawnId());
    }

    private void writeMessage(GameConstValue gameConstValue) {
        switch(gameConstValue) {
            case pawnSelection:
                System.out.println("Wyznacz figurę: ");
                break;
            case errorAddPawnToList:
                System.out.println("Błąd dodania pionka do listy: ");
                break;
            case errorRemovePawnFromList:
                System.out.println("Błąd usunięcia pionka z listy: ");
                break;
            case wirtePawnsList:
                System.out.println("Black Pawns on board: " + blackPawns);
                System.out.println("White Pawns on board: " + whitePawns);
                break;
            case writeFieldLis:
                System.out.println("Field list: " + fieldsChessboard);
                break;
            default:
                System.out.println("Incorect input");
        }
    }

}
