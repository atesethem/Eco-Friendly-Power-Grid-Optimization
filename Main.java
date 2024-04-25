import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Main class
 */
// FREE CODE HERE
public class Main {
    public static void main(String[] args) throws IOException {

        /** MISSION POWER GRID OPTIMIZATION BELOW **/

        System.out.println("##MISSION POWER GRID OPTIMIZATION##");

        // Enerji talebi dosyasını oku ve diziye dönüştür
        String[] energyDemandsString = args[0].split(" ");
        ArrayList<Integer> energyDemands = new ArrayList<>();
        for (String demand : energyDemandsString) {
            energyDemands.add(Integer.parseInt(demand));
        }

        // PowerGridOptimization nesnesi oluştur ve çözümü al
        PowerGridOptimization powerGridOptimization = new PowerGridOptimization(energyDemands);
        OptimalPowerGridSolution powerGridSolution = powerGridOptimization.getOptimalPowerGridSolutionDP();

        // Çıktıyı yazdır
        System.out.println("The total number of demanded gigawatts: " + powerGridSolution.getmaxNumberOfSatisfiedDemands());
        System.out.println("Maximum number of satisfied gigawatts: " + powerGridSolution.getmaxNumberOfSatisfiedDemands());
        System.out.println("Hours at which the battery bank should be discharged: " + powerGridSolution.getHoursToDischargeBatteriesForMaxEfficiency());
        System.out.println("The number of unsatisfied gigawatts: " + (energyDemands.stream().mapToInt(Integer::intValue).sum() - powerGridSolution.getmaxNumberOfSatisfiedDemands()));

        System.out.println("##MISSION POWER GRID OPTIMIZATION COMPLETED##");

        /** MISSION ECO-MAINTENANCE BELOW **/

        System.out.println("##MISSION ECO-MAINTENANCE##");

        // Eskiden kullanılan arraylisti temizle
        energyDemands.clear();

        // ESVMaintenance dosyasını oku ve diziye dönüştür
        String[] maintenanceTasksString = args[1].split(" ");
        int maxNumberOfAvailableESVs = Integer.parseInt(maintenanceTasksString[0]);
        int maxESVCapacity = Integer.parseInt(maintenanceTasksString[1]);
        for (int i = 2; i < maintenanceTasksString.length; i++) {
            energyDemands.add(Integer.parseInt(maintenanceTasksString[i]));
        }

        // OptimalESVDeploymentGP nesnesi oluştur ve çözümü al
        OptimalESVDeploymentGP optimalESVDeploymentGP = new OptimalESVDeploymentGP(energyDemands);
        int minNumESVsToDeploy = optimalESVDeploymentGP.getMinNumESVsToDeploy(maxNumberOfAvailableESVs, maxESVCapacity);

        // Çıktıyı yazdır
        System.out.println("The minimum number of ESVs to deploy: " + minNumESVsToDeploy);
        ArrayList<ArrayList<Integer>> tasksAssignedToESVs = optimalESVDeploymentGP.getMaintenanceTasksAssignedToESVs();
        for (int i = 0; i < tasksAssignedToESVs.size(); i++) {
            System.out.println("ESV " + (i + 1) + " tasks: " + tasksAssignedToESVs.get(i));
        }

        System.out.println("##MISSION ECO-MAINTENANCE COMPLETED##");
    }
}
