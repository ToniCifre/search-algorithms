package com.ToniC;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AlgorismesInformats extends AlgorismeCommons {

    ArrayList<NinePuzzle> HillClimbing(NinePuzzle start, NinePuzzle goal){
        initialize(start);

        while (LNO.size() > 0) {
            NinePuzzle isSol;
            if (( isSol = setAllMovesInLNO(goal)) != null){
                drawTree();
                return getCamiSol(isSol);
            }

            LNO.sort(Comparator.comparingInt(r -> manhattan(r, goal)));
            if(LNO.size()!=0) LNO = LNO.subList(0,1);
        }

        drawTree();
        return getCamiSol(null);
    }

    ArrayList<NinePuzzle> BestFirst(NinePuzzle start, NinePuzzle goal){
        initialize(start);

        while (LNO.size() > 0) {
            NinePuzzle isSol;
            if (( isSol = setAllMovesInLNO(goal)) != null){
                drawTree();
                return getCamiSol(isSol);
            }

            LNO.sort(Comparator.comparingInt(r -> manhattan(r, goal)));
        }

        drawTree();
        return getCamiSol(null);
    }


    ArrayList<NinePuzzle> N_Profunditat(NinePuzzle start, NinePuzzle goal, int maxListSize){
        initialize(start);

        while (LNO.size() > 0) {
            NinePuzzle isSol;
            if (( isSol = setAllMovesInLNO(goal)) != null){
                drawTree();
                return getCamiSol(isSol);
            }

            LNO.sort(Comparator.comparingInt(r -> manhattan(r, goal)));
            LNO = LNO.subList(0, Math.min(LNO.size(), maxListSize));
        }

        drawTree();
        return getCamiSol(null);
    }


    ArrayList<NinePuzzle> A(NinePuzzle start, NinePuzzle goal){
       initialize(start);

        while (LNO.size() > 0) {
            NinePuzzle isSol;
            if (( isSol = setAllMovesInLNO(goal)) != null){
                drawTree();
                return getCamiSol(isSol);
            }

            LNO.sort(Comparator.comparingInt(r -> manhattan(r, goal)+r.getDepth()));
        }

        drawTree();
        return getCamiSol(null);
    }

}
