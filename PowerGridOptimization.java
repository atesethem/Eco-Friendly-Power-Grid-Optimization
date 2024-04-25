import java.util.ArrayList;

public class PowerGridOptimization {
    private ArrayList<Integer> amountOfEnergyDemandsArrivingPerHour;

    public PowerGridOptimization(ArrayList<Integer> amountOfEnergyDemandsArrivingPerHour){
        this.amountOfEnergyDemandsArrivingPerHour = amountOfEnergyDemandsArrivingPerHour;
    }

    public ArrayList<Integer> getAmountOfEnergyDemandsArrivingPerHour() {
        return amountOfEnergyDemandsArrivingPerHour;
    }

    public OptimalPowerGridSolution getOptimalPowerGridSolutionDP(){
        int N = amountOfEnergyDemandsArrivingPerHour.size();
        int[] SOL = new int[N + 1];
        ArrayList<ArrayList<Integer>> HOURS = new ArrayList<>(N + 1);

        SOL[0] = 0;
        HOURS.add(new ArrayList<>());

        for (int j = 1; j <= N; j++) {
            int max = 0;
            int bestI = -1;

            for (int i = 0; i < j; i++) {
                int minDemandOrEfficiency = Math.min(amountOfEnergyDemandsArrivingPerHour.get(j - 1),
                        getBatteryEfficiency(j - i));
                int current = SOL[i] + minDemandOrEfficiency;

                if (current > max) {
                    max = current;
                    bestI = i;
                }
            }

            SOL[j] = max;

            if (bestI != -1) {
                ArrayList<Integer> hours = new ArrayList<>(HOURS.get(bestI));
                hours.add(j);
                HOURS.add(hours);
            } else {
                HOURS.add(new ArrayList<>());
            }
        }

        ArrayList<Integer> bestHoursToDischarge = HOURS.get(N);

        return new OptimalPowerGridSolution(SOL[N], bestHoursToDischarge);
    }

    private int getBatteryEfficiency(int hours) {
        return hours * hours;
    }
}