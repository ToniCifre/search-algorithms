package com.ToniC;

import edu.uci.ics.jung.graph.DelegateTree;

import java.util.ArrayList;
import java.util.List;

/**
 * Fincions que utilitzen els algorismes de cerca
 */
class AlgorismeCommons {

    DelegateTree<NinePuzzle, Integer> g;
    List<NinePuzzle> LNO;
    int edge;
    List<Dir> dirs;

    void initialize(NinePuzzle start){
        NinePuzzle.resetIds();
        edge = 0;


        LNO = new ArrayList<>();
        LNO.add(start);

        g = new DelegateTree<>();
        g.addVertex(start);
    }

    NinePuzzle setAllMovesInLNO(NinePuzzle goal){
        NinePuzzle parent = LNO.remove(0);
        dirs = parent.validMoves();

        for (Dir d : dirs) {

            NinePuzzle child = parent.clone();
            child.move(d);

            if ( !isCycle(child, parent)) {
                g.addChild(edge++, parent, child);
                if (child.isSolution(goal)) {
                    return child;
                } else {
                    LNO.add(child);
                }
            }
        }
        return null;
    }
    /**
     * retorna e imprimeix el cami a segir per trobar la solució
     * @param solution node final igual a la solució
     * @return L'arrai amb els nodes del cami de la solució
     */
    ArrayList<NinePuzzle> getCamiSol(NinePuzzle solution){
        if (solution == null) {
            System.out.println("Sense solució.");
            return new ArrayList<>();
        } else {
            NinePuzzle node = solution;
            ArrayList<NinePuzzle> camiSolucio = new ArrayList<>();

            while (node != null) {
                camiSolucio.add(0, node);
                node = g.getParent(node);
            }

            System.out.println("------------- Solució -------------\n");
            System.out.println("Mida: " + camiSolucio.size());
            System.out.println("Id: " + camiSolucio.size());

            for (NinePuzzle cami : camiSolucio) {
                cami.print();
            }

            System.out.println("\n----------------------------------");
            return camiSolucio;
        }
    }

    /**
     * funció euristica basada en el nombre de caselles desordenades
     * @param child puzele actual
     * @param goal puzzle objectiu
     * @return nombre euristic
     */
    int getEuristic(NinePuzzle child, NinePuzzle goal){
        int euristic = 0;
        for (int i = 0; i<child.puzzle.length; i++) {
            for (int j = 0; j<child.puzzle[i].length; j++) {
                if(child.puzzle[i][j] != goal.puzzle[i][j] && child.puzzle[i][j] != 0){
                    euristic++;
                }
            }
        }
        return euristic;
    }

    /**
     * Funció euristica vasada amb la distancia de manhatan
     * @param child puzele actual
     * @param goal puzele actual
     * @return distancia de manhatan
     */
    int manhattan(NinePuzzle child, NinePuzzle goal) {
        int count = 0;

        for (int row = 0; row < child.puzzle.length; row++) {
            for (int col = 0; col < child.puzzle[row].length; col++) {
                int value = child.puzzle[row][col];
                if (value != 0) {
                    count += Math.abs(row
                            - getRow(goal.puzzle, value))
                            + Math.abs(col
                            - getCol(goal.puzzle, value));
                }
            }
        }

        if(count>=22) return 99999;
        return count;
    }

    private int getCol(int[][] a, int value) {
        for (int[] ints : a) {
            for (int col = 0; col < ints.length; col++) {
                if (ints[col] == value) {
                    return col;
                }
            }
        }
        return -1;
    }

    private int getRow(int[][] a, int value) {
        for (int row = 0; row < a.length; row++) {
            for (int col = 0; col < a[row].length; col++) {
                if (a[row][col] == value) {
                    return row;
                }
            }
        }
        return -1;
    }

    boolean isCycle(NinePuzzle child, NinePuzzle parent) {
        while (parent != null) {
            if (child.isSolution(parent)) {
                return true;
            }
            parent = g.getParent(parent);
        }
        return false;
    }

    void drawTree(){
        new ShowGraph(g);
    }

}
