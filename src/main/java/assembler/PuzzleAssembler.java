package assembler;

import assembler.templateMatcher.Match;
import com.google.common.collect.Maps;
import org.opencv.core.*;
import utillities.Utilities;
import utillities.ValueFromFuture;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.opencv.core.Core.putText;

public enum PuzzleAssembler
{
    instance;

    private static final int numberOfCPUCores = 4;

    public Map<Integer, Match> assemblePieces(Map<Integer, Mat> puzzlePieces, Mat puzzle, double[] backgroundColor) throws Exception
    {
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfCPUCores);

        Map<Integer, Future<Match>> futures = new HashMap<>();

        for (Map.Entry<Integer, Mat> puzzlePiece : puzzlePieces.entrySet())
        {
            futures.put
                    (
                            puzzlePiece.getKey(),

                            executorService.submit
                                    (
                                            PuzzlePieceAssembler.createAssembler(puzzlePiece.getKey(), puzzlePiece.getValue(), puzzle, backgroundColor)
                                    )
                    );
        }

        executorService.shutdown();

        return Maps.transformValues(futures, ValueFromFuture.<Match>create());
    }
}
