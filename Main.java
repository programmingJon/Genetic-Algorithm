import java.util.ArrayList;


public class Main {

	public static void main(String[] args) {
		Population pop = new Population(new ArrayList<Individual>(), 10, 10);
		Algorithm alg = new Algorithm();
		pop.createPopulation();
		
		for (int i = 0; i < 1; i ++){
			alg.evolvePopulation(pop);
			/*for (Individual ind: pop.individuals){
				System.out.println(ind.getNodeColours());
				System.out.println(ind.fitness + "\n");
				
			}*/
		}
	}
}
