package test;

import main.AggregatePosition;
import main.RealTimePosition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static main.Main.createAggregatePos;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @org.junit.jupiter.api.Test
    void createAggregatePosTest() {
        List<RealTimePosition> rtPositions = new ArrayList<>();
        rtPositions.add(new RealTimePosition(1234,1,"XYZ",100,"BUY","ACC-1234","NEW"));
        rtPositions.add(new RealTimePosition(1234,2,"XYZ",150,"BUY","ACC-1234","AMEND"));
        rtPositions.add(new RealTimePosition(5678,1,"QED",200,"BUY","ACC-2345","NEW"));
        rtPositions.add(new RealTimePosition(5678,2,"QED",0,"BUY","ACC-2345","CANCEL"));
        rtPositions.add(new RealTimePosition(2233,1,"RET",100,"SELL","ACC-3456","NEW"));
        rtPositions.add(new RealTimePosition(2233,2,"RET",400,"SELL","ACC-3456","AMEND"));
        rtPositions.add(new RealTimePosition(2233,3,"RET",0,"SELL","ACC-3456","CANCEL"));
        rtPositions.add(new RealTimePosition(8896,1,"YUI",300,"BUY","ACC-4567","NEW"));
        rtPositions.add(new RealTimePosition(6638,1,"YUI",100,"SELL","ACC-4567","NEW"));
        rtPositions.add(new RealTimePosition(6363,1,"HJK",200,"BUY","ACC-5678","NEW"));
        rtPositions.add(new RealTimePosition(7666,1,"HJK",	200,"BUY","ACC-5678","NEW"));
        rtPositions.add(new RealTimePosition(6363,2,"HJK",100,"BUY","ACC-5678","AMEND"));
        rtPositions.add(new RealTimePosition(7666,2,"HJK",50,"SELL","ACC-5678","AMEND"));
        rtPositions.add(new RealTimePosition(1025,1,"JKL",100,"BUY","ACC-7789","NEW"));
        rtPositions.add(new RealTimePosition(1036,1,"JKL",100,"BUY","ACC-7789","NEW"));
        rtPositions.add(new RealTimePosition(1025,2,"JKL",100,"SELL","ACC-8877","AMEND"));
        rtPositions.add(new RealTimePosition(8686,1,"FVB",100,"BUY","ACC-6789","NEW"));
        rtPositions.add(new RealTimePosition(8686,2,"GBN",100,"BUY","ACC-6789","AMEND"));
        rtPositions.add(new RealTimePosition(9654,1,"FVB", 200,"BUY","ACC-6789","NEW"));
        rtPositions.add(new RealTimePosition(1122,1,"KLO",100,"BUY","ACC-9045","NEW"));
        rtPositions.add(new RealTimePosition(1122,2,"HJK",100,"SELL","ACC-9045","AMEND"));
        rtPositions.add(new RealTimePosition(1122,3,"KLO",100,"SELL","ACC-9045","AMEND"));
        rtPositions.add(new RealTimePosition(1144,1,"KLO",300,"BUY","ACC-9045","NEW"));
        rtPositions.add(new RealTimePosition(1144,2,"KLO",400,"BUY","ACC-9045","AMEND"));
        rtPositions.add(new RealTimePosition(1155,1,"KLO",600,"SELL","ACC-9045","NEW"));
        rtPositions.add(new RealTimePosition(1155,2,"KLO",0,"BUY","ACC-9045","CANCEL"));

        List<AggregatePosition> expectedAggPos = new ArrayList<>();
        expectedAggPos.add(new AggregatePosition("ACC-5678","HJK",50, Arrays.asList(6363,7666)));
        expectedAggPos.add(new AggregatePosition("ACC-3456","RET",0, List.of(2233)));
        expectedAggPos.add(new AggregatePosition("ACC-9045","HJK",100, List.of(1122)));
        expectedAggPos.add(new AggregatePosition("ACC-2345","QED",0, List.of(5678)));
        expectedAggPos.add(new AggregatePosition("ACC-6789","FVB",300, Arrays.asList(9654, 8686)));
        expectedAggPos.add(new AggregatePosition("ACC-7789","JKL",200, Arrays.asList(1036, 1025)));
        expectedAggPos.add(new AggregatePosition("ACC-6789","GBN",100, List.of(8686)));
        expectedAggPos.add(new AggregatePosition("ACC-1234","XYZ",150, List.of(1234)));
        expectedAggPos.add(new AggregatePosition("ACC-8877","JKL",100, List.of(1025)));
        expectedAggPos.add(new AggregatePosition("ACC-4567","YUI",200, Arrays.asList(8896, 6638)));
        expectedAggPos.add(new AggregatePosition("ACC-9045","KLO",300, Arrays.asList(1144, 1155, 1122)));
        List<AggregatePosition> actualAggPos = createAggregatePos(rtPositions);
        assertEquals(expectedAggPos.toString(),actualAggPos.toString());

    }
}