package com.ToniC;

import edu.uci.ics.jung.graph.DelegateTree;

import java.util.ArrayList;
import java.util.List;

public class AlgorismesNoInformats extends AlgorismeCommons {



    ArrayList<NinePuzzle> BFS(NinePuzzle start, NinePuzzle goal){
        initialize(start);

        while (LNO.size() > 0) {
            NinePuzzle isSol;
            if (( isSol = setAllMovesInLNO(goal)) != null){
                drawTree();
                return getCamiSol(isSol);
            }
        }

        drawTree();
        return getCamiSol(null);
    }


    ArrayList<NinePuzzle> DFS(NinePuzzle start, NinePuzzle goal){
        initialize(start);

        while (LNO.size() > 0) {

            NinePuzzle parent = LNO.remove(0);
            List<Dir> dirs = parent.validMoves();

            int pos = 0;
            for (Dir d : dirs) {
                NinePuzzle child = parent.clone();
                child.move(d);

                if ( !isCycle(child, parent)) {
                    g.addChild(edge++, parent, child);
                    if (child.isSolution(goal)) {
                        drawTree();
                        return getCamiSol(child);
                    } else {
                        LNO.add(pos++, child);
                    }
                }
            }
        }

        drawTree();
        return getCamiSol(null);
    }

}
