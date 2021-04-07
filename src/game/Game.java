package game;

import game.field.Field;
import game.field.Pawn;
import game.utils.GameConstValue;
import game.utils.GameError;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private Field[][] chessBoard = new Field[GameConstValue.numberOfFields.getValue()][GameConstValue.numberOfFields.getValue()];
    private ArrayList<Pawn> whitePawns; //listy przechowujące id pionków obecnych na szachownicy,
    private ArrayList<Pawn> blackPawns; //gdy któryś zostanie strącony to zostanie też usunięty z tych list
    private ArrayList<Integer> fieldsChessboard;

    private Scanner scanner;
    private String input;
    private int options;
    private Integer choosenPawn;
    private GameConstValue actualTurn;

    public Game() {
        scanner = new Scanner(System.in);
        whitePawns = new ArrayList<>();
        blackPawns = new ArrayList<>();
        fieldsChessboard = new ArrayList<>();
        actualTurn = GameConstValue.whiteTurn;
        choosenPawn = null;
        this.initialChessBoard();
    }

    private void addPawnToList(GameConstValue gameConstValue, Pawn pawn) {
        switch (gameConstValue) {
            case pawnBlack:
                blackPawns.add(pawn);
                break;
            case pawnWhite:
                whitePawns.add(pawn);
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



    private void validateValueOptionsofField() {
        //if
    }



    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void runGame() {
        //tmp info
        this.writeMessage(GameConstValue.writePawnsList);
        this.writeMessage(GameConstValue.writeFieldList);

        this.writeMessage(actualTurn);
        this.writeMessage(GameConstValue.pawnSelection);
        this.inputGameControl(GameConstValue.pawnSelection);

        if (GameError.errorOfType && GameError.errorOfValue) {
            this.inputGameControl(GameConstValue.fieldSelection);
        }

        System.out.println(this.options);

    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void initalPawns(int id, int i, int j) {
        if (id < 20) {
            chessBoard[i][j].setPawn(GameConstValue.pawnBlack);
            this.addPawnToList(GameConstValue.pawnBlack, chessBoard[i][j].getPawn());
        } else if (id >= 80 ) {
            chessBoard[i][j].setPawn(GameConstValue.pawnWhite);
            this.addPawnToList(GameConstValue.pawnWhite, chessBoard[i][j].getPawn());
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
        if ( blackPawns.contains(this.options)) {///
            GameError.errorOfValue = true;
        } else {
            System.out.println("Error - you choose unidentfied pawn");
            GameError.errorOfValue = false;
        }
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
                    //black
                    chessBoard[i][j].setField("-------");
                    this.initalPawns(id, i, j); //<---
                    this.fieldsChessboard.add(id);
                } else {
                    //white
                    chessBoard[i][j].setField("#######");
                }
                id++;
            }
            offSet++;
        }
    }

    private String actualTurnPawnId(Field field) {
        Pawn pawn = field.getPawn();

        if (actualTurn==GameConstValue.whiteTurn && pawn.getColor().equals("W")) {
            return String.valueOf(field.getPawnId());
        } else if (actualTurn==GameConstValue.blackTurn && pawn.getColor().equals("B")) {
            return String.valueOf(field.getPawnId());
        } else {
            return "--";
        }
    }
    public void drawChessBoard() {
        for (int i = 0; i < chessBoard.length; i++) {
            for (int q = 0; q<3; q++) {
                for (int j = 0; j < chessBoard[i].length; j++) {
                    if (chessBoard[i][j].getPawn() != null) {
                        //pawn
                        if ( q == 1) {

                            System.out.printf("--" + "%s" + "%2s" + "--",this.chessBoard[i][j].getPawnColor(),this.actualTurnPawnId(this.chessBoard[i][j]));
                        } else {
                            System.out.printf("%5s", this.chessBoard[i][j].getField());
                        }
                    } else {
                        //field

                        System.out.printf("%5s", this.chessBoard[i][j].getField());
                    }
                }System.out.println();
            }
        }
    }

    private void writeMessage(GameConstValue gameConstValue) {
        switch(gameConstValue) {
            case pawnSelection:
                System.out.print("Wyznacz figurę: ");
                break;
            case errorAddPawnToList:
                System.out.println("Błąd dodania pionka do listy: ");
                break;
            case errorRemovePawnFromList:
                System.out.println("Błąd usunięcia pionka z listy: ");
                break;
            case writePawnsList:
                System.out.println("Black Pawns on board: " + blackPawns);
                System.out.println("White Pawns on board: " + whitePawns);
                break;
            case writeFieldList:
                System.out.println("Field list: " + fieldsChessboard);
                break;
            case whiteTurn:
                System.out.println("Actual turn: WHITE");
                break;
            case blackTurn:
                System.out.println("Actual turn: BLACK");
                break;
            default:
                System.out.println("Incorect input");
        }
    }

}
