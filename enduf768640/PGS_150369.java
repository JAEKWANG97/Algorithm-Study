import java.util.Arrays;

public class PGS_150369 {

    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;

        int deliveryCount = Arrays.stream(deliveries).sum();
        int pickupCount = Arrays.stream(pickups).sum();

        int destination = n - 1;

        int deliveryDestination = setDeliveryDestination(n, deliveries);
        int pickupDestination = setPickupDestination(n, pickups);

        while (deliveryDestination >= 0 || pickupDestination >= 0) {
            int capacity = cap;

            while (capacity > 0 && deliveryCount > 0) {
                deliveries[deliveryDestination]--;
                deliveryCount--;
                capacity--;

                while (deliveryDestination >= 0 && deliveries[deliveryDestination] == 0) {
                    deliveryDestination--;
                }
            }

            capacity = cap;

            while (capacity > 0 && pickupCount > 0) {
                pickups[pickupDestination]--;
                pickupCount--;
                capacity--;

                while (pickupDestination >= 0 && pickups[pickupDestination] == 0) {
                    pickupDestination--;
                }
            }

            answer += (destination + 1) * 2L;

            destination = Math.max(deliveryDestination, pickupDestination);
        }

        return answer;
    }

    private static int setDeliveryDestination(int n, int[] deliveries) {
        int deliveryDestination = n - 1;

        for (int i = n - 1; i >= 0; i--) {
            if (deliveries[i] != 0) {
                break;
            }

            deliveryDestination--;
        }

        return deliveryDestination;
    }

    private static int setPickupDestination(int n, int[] pickups) {
        int pickupDestination = n - 1;

        for (int i = n - 1; i >= 0; i--) {
            if (pickups[i] != 0) {
                break;
            }

            pickupDestination--;
        }

        return pickupDestination;
    }
}