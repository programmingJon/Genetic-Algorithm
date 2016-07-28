import java.util.Collections;


public class Algorithm {

	int generation;
	
	void evolvePopulation(Population pop, int generation){
		pop.evaluateAllFitness();
		Collections.sort(pop.individuals);
		System.out.println(pop.getFittest(pop.individuals).genes);
		System.out.println(pop.getFittest(pop.individuals).fitness);
		if (generation == 0){
			System.out.println("Fitness eval: \n");
			for (Individual ind: pop.individuals){
				System.out.println(ind.genes);
				System.out.println(ind.fitness);
			}
		}
		//System.out.println("\nBefore Selection: \n");
		pop.individuals = pop.tournamentSelection(10);
		pop.individuals = pop.crossBreed(0.8);
		pop.mutation((double)1/pop.GENE.size());
		System.out.println("\nEvolution generation: " + generation + " completed!\n\n");
	}
	
}
