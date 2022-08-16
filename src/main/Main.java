package main;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        List<RealTimePosition> rtPositions = new ArrayList<>();
        List<AggregatePosition> aggPosition;

        //Sample input to run the program (Custom input can be given here)
        rtPositions.add(new RealTimePosition(1234,1,"XYZ",100,"BUY","ACC-1234","NEW"));
        rtPositions.add(new RealTimePosition(1234,2,"XYZ",150,"BUY","ACC-1234","NEW"));
        rtPositions.add(new RealTimePosition(1234,1,"JKL",50,"SELL","ACC-1234","NEW"));

        aggPosition = createAggregatePos(rtPositions);
        System.out.println("The aggregated position list  is" + aggPosition);

    }

    /**
     * The method has the core logic on how to create the aggregate position based on the Real time position Data
     * @param rtPos (RealTimePosition List)
     * @return AggregatePosition List
     */
    public static List<AggregatePosition> createAggregatePos(List<RealTimePosition> rtPos) {
        List<AggregatePosition> aggPositionList = new ArrayList<>();

        //Forming a composite key to use group by function on the input based on tradeId,securityId,AccNo
        Function<RealTimePosition, List<Object>> compositeKey = rtPosition ->
                Arrays.asList(rtPosition.getTrade_id(), rtPosition.getSecurity_id(), rtPosition.getAcc_no());

        //Creating a Map object with group by function
        Map<Object, List<RealTimePosition>> grpMap =
                rtPos.stream().collect(Collectors.groupingBy(compositeKey, Collectors.toList()));

        //Creating a list from the Map object's value. This list will have only single entry of the latest version
        List<RealTimePosition> latestVerList = new ArrayList<>();
        for (Map.Entry<Object, List<RealTimePosition>> entry : grpMap.entrySet()) {
            List<RealTimePosition> posList = entry.getValue();
            int latestVersion = posList.stream().map(RealTimePosition::getTrade_version).max(Integer::compare).get();
            latestVerList.add(posList.stream().filter(rtp -> rtp.getTrade_version() == latestVersion).findFirst().get());
        }

        //Forming a composite key to use group by function based securityId,AccNo
        Function<RealTimePosition, List<Object>> compositeKey1 = rtPosition ->Arrays.asList(rtPosition.getSecurity_id(), rtPosition.getAcc_no());

        //Creating a map object on the list with the latest version  using the group by function
        Map<Object, List<RealTimePosition>> aggregateMap = latestVerList.stream().collect(Collectors.groupingBy(compositeKey1, Collectors.toList()));

        /* Implementing the final condition to aggregate the values
        The position quantity will be incremented when a trade is processed with the following attributes:
            a. Direction = BUY, Operation = NEW or AMEND
            b. Direction = SELL, Operation = CANCEL
        The position quantity will be decremented when a trade is processed with the following attributes:
            a. Direction = SELL, Operation = NEW or AMEND
            b. Direction = BUY, Operation = CANCEL
         */
        for (Map.Entry<Object, List<RealTimePosition>> entry : aggregateMap.entrySet()) {
            List<RealTimePosition> posList = entry.getValue();
            if (posList.size() == 1) {
                List<Integer> tradeIds = new ArrayList<>();
                tradeIds.add(posList.get(0).getTrade_id());
                aggPositionList.add(new AggregatePosition(posList.get(0).getAcc_no(), posList.get(0).getSecurity_id(), posList.get(0).getQuantity(), tradeIds));
            } else {
                int QuanSum = 0;
                List<Integer> tradeIds = new ArrayList<>();

                for (RealTimePosition realTimePosition : posList) {
                    if ((realTimePosition.getDirection().equals("BUY") && (realTimePosition.getOperation().equals("NEW") || realTimePosition.getOperation().equals("AMEND"))) || (realTimePosition.getDirection().equals("SELL") && (realTimePosition.getOperation().equals("CANCEL")))) {
                        QuanSum = QuanSum + realTimePosition.getQuantity();
                    }
                    if ((realTimePosition.getDirection().equals("SELL") && (realTimePosition.getOperation().equals("NEW") || realTimePosition.getOperation().equals("AMEND"))) || (realTimePosition.getDirection().equals("BUY") && (realTimePosition.getOperation().equals("CANCEL")))) {
                        QuanSum = QuanSum - realTimePosition.getQuantity();
                    }
                    tradeIds.add(realTimePosition.getTrade_id());
                }
                aggPositionList.add(new AggregatePosition(posList.get(0).getAcc_no(), posList.get(0).getSecurity_id(), QuanSum, tradeIds));
            }
        }
        return aggPositionList;
    }
}


