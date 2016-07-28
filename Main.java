import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Main {

	public static void main(String[] args) {
		Integer[] targetArray = {1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0};
		List<Integer> targetList = Arrays.asList(targetArray);
		System.out.println(targetList.size());
		Population pop = new Population(new ArrayList<Individual>(), 100, 10, targetList);
		Algorithm alg = new Algorithm();
		pop.createPopulation();
		
		for (int i = 0; i < 200; i ++){
			alg.evolvePopulation(pop, i);
			/*for (Individual ind: pop.individuals){
				System.out.println(ind.getNodeColours());
				System.out.println(ind.fitness + "\n");
				
			}*/
		}
	}
}
