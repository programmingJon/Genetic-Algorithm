
public class Algorithm {

	int generation;
	
	void evolvePopulation(Population pop){
		pop.evaluateAllFitness();
		System.out.println(pop.getFittest(pop.individuals).nodeColours);
		pop.individuals = pop.selection();
		pop.crossBreed(0.2);
		pop.mutation(0.05);
	}
	
}
