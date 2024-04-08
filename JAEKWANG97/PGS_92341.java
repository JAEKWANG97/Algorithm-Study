
import java.util.*;

class Solution {
    static class ParkingLot {
        int defaultTime;
        int defaultFee;
        int unitTime;
        int unitFee;

        public ParkingLot(int defaultTime, int defaultFee, int unitTime, int unitFee) {
            this.defaultTime = defaultTime;
            this.defaultFee = defaultFee;
            this.unitTime = unitTime;
            this.unitFee = unitFee;
        }

        public int calculateFee(int totalTime) {
            if (totalTime <= defaultTime) {
                return defaultFee;
            } else {
                int extraTime = totalTime - defaultTime;
                int extraFee = (int) Math.ceil((double) extraTime / unitTime) * unitFee;
                return defaultFee + extraFee;
            }
        }
    }

    static class Car {
        String carNumber;
        String inTime;
        int totalTime = 0;

        public Car(String carNumber, String inTime) {
            this.carNumber = carNumber;
            this.inTime = inTime;
        }

        public void setOutTime(String outTime) {
            this.totalTime += calculateTime(this.inTime, outTime);
            this.inTime = null; // 차량이 출차한 후에는 입차 시간을 null로 설정
        }

        public void calculateTotalTime() {
            if (this.inTime != null) { // 출차 기록이 없는 경우, "23:59"로 설정하여 시간 계산
                this.totalTime += calculateTime(this.inTime, "23:59");
            }
        }

        private int calculateTime(String inTime, String outTime) {
            int inHour = Integer.parseInt(inTime.split(":")[0]);
            int inMin = Integer.parseInt(inTime.split(":")[1]);
            int outHour = Integer.parseInt(outTime.split(":")[0]);
            int outMin = Integer.parseInt(outTime.split(":")[1]);

            return (outHour * 60 + outMin) - (inHour * 60 + inMin);
        }
    }

    public int[] solution(int[] fees, String[] records) {
        ParkingLot parkingLot = new ParkingLot(fees[0], fees[1], fees[2], fees[3]);
        Map<String, Car> carMap = new HashMap<>();

        for (String record : records) {
            String[] parts = record.split(" ");
            String time = parts[0];
            String carNumber = parts[1];
            String direction = parts[2];

            Car car = carMap.getOrDefault(carNumber, new Car(carNumber, null));
            if (direction.equals("IN")) {
                car.inTime = time;
            } else {
                car.setOutTime(time);
            }
            carMap.put(carNumber, car);
        }

        // 출차되지 않은 차량 처리
        for (Car car : carMap.values()) {
            car.calculateTotalTime();
        }

        // 요금 계산 및 정렬
        return carMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .mapToInt(entry -> parkingLot.calculateFee(entry.getValue().totalTime))
                .toArray();
    }
}
