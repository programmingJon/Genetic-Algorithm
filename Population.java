import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class Population {

	ArrayList<Individual> individuals;
	int sizeOfPopulation;
	int noOfNodes;
	List<Integer> GENE;
	
	Population(List<Individual> individuals, int sizeOfPopulation, int noOfNodes, List<Integer> gene){
		this.individuals = (ArrayList<Individual>) individuals;
		this.sizeOfPopulation = sizeOfPopulation;
		this.noOfNodes = noOfNodes;
		this.GENE = gene;
	}
	
	public void createPopulation(){
		Individual ind;
		ArrayList<Integer> arrList;
		Random rand = new Random();
		for (int i = 0; i < this.sizeOfPopulation; i++){
			arrList = new ArrayList<Integer>();
			for (int j = 0; j < GENE.size(); j++){
				arrList.add((int) rand.nextInt(10));
			}
			ind = new Individual(arrList);
			this.individuals.add(ind);
		}
	}
	
	public void evaluateAllFitness(){
		for (Individual ind: individuals){
			this.fitnessFunction(ind);
		}
	}
	
	public void fitnessFunction(Individual ind){
		int fitness = 0;
		
		for (int i = 0; i < GENE.size(); i++){
			if (ind.genes.get(i) == GENE.get(i)){
				fitness++;
			}
		}
		
		ind.fitness = fitness;
		
		/*
		ArrayList<Integer> colours = ind.nodeColours;
		int count = 0;
		System.out.println(colours);
		for (int i = 0; i < colours.size() - 1; i ++){
			System.out.println(colours.get(i + 1) - colours.get(i));
			System.out.println(Math.abs(colours.get(i + 1) - colours.get(i)) >= 4);
			System.out.println("Count: " + count);
			System.out.println("Fitness: " + fitness);
			if (Math.abs(colours.get(i + 1) - colours.get(i)) >= 4){
				count++;
				fitness += Math.pow(count, 2);
			} else {
				count = 0;
			}
			//fitness += Math.max(0, colours.get(i+1) - colours.get(i));
			//if (colours.get(i) < colours.get(i + 1)){
			//	fitness++;
			//}
		}
		*/
	}
	
	public Individual getFittest(ArrayList<Individual> individuals){
		Individual fittest = individuals.get(0);
		for (Individual ind: individuals){
			if (ind.fitness > fittest.fitness){
				fittest = ind;
			}
		}
		//System.out.println("Highest Fitness is: " + fittest.fitness);
		return fittest;
	}
	
	
	/*//Old Selection method. Wasn't biased enough to the fittest individuals.
	public ArrayList<Individual> selection(){
		ArrayList<Individual> list = new ArrayList<Individual>();
		int accFitness = 0;
		Random rand = new Random();
		
		Collections.sort(this.individuals);
		
		for (Individual ind: individuals){
			//System.out.println(ind.fitness);
			accFitness += ind.fitness;
			ind.accFitness = accFitness;
		}
		//System.out.println(accFitness + "\n");
		
		int randomInt;
		for (int i = 0; i < sizeOfPopulation; i++){
			randomInt = rand.nextInt(accFitness);
			Individual selectedInd = individuals.get(0);
			for (Individual ind: individuals){
				if (ind.accFitness >= randomInt){
					selectedInd = ind;
					break;
				}
			}
			list.add(selectedInd);
		}
		
		return list;
	}*/
	
	/*
	 * Elitism
	public ArrayList<Individual> selection(){
		ArrayList<Individual> list = new ArrayList<Individual>();
		int accFitness = 0;
		Random rand = new Random();
		
		Collections.sort(individuals);
		
		list.add(individuals.get(0));
		
		for (Individual ind: individuals){
			//System.out.println(ind.fitness);
			accFitness += ind.fitness;
			ind.accFitness = accFitness;
		}
		
		
		int randomInt;
		for (int i = 1; i < sizeOfPopulation; i++){
			randomInt = rand.nextInt(accFitness);
			Individual selectedInd = individuals.get(0);
			for (Individual ind: individuals){
				if (ind.accFitness >= randomInt){
					selectedInd = ind;
					break;
				}
			}
			list.add(selectedInd);
		}

		Collections.sort(list);
		
		System.out.println("Post-Selection: \n");
		for (Individual ind: list){
			System.out.println(ind.genes);
			System.out.println(ind.fitness);
		}
		
		return list;
	}
	*/
	
	public ArrayList<Individual> tournamentSelection(int tournamentSize){
		ArrayList<Individual> list = new ArrayList<Individual>();
		ArrayList<Individual> selected = new ArrayList<Individual>();
		
		while (selected.size() < individuals.size()){
			for (int i = 0; i < tournamentSize; i++){
				int random = (int) (Math.random() * individuals.size());
				list.add(individuals.get(random));
			}
			selected.add(getFittest(list));
			list.clear();
		}
		return selected;
	}
	
	/*
	 * Single Point Crossover
	 * Not effective
	 */
	/*public ArrayList<Individual> crossBreed(double crossbreedRate){
		Random rand = new Random();
		Individual indi;
		Individual indj;
		int i, j, crossoverPoint;
		ArrayList<Integer> iGenes, jGenes, newIGenes, newJGenes;
		ArrayList<Individual> individuals = this.individuals;
		
		for (int iter = 0; iter < crossbreedRate*sizeOfPopulation; iter++){
			i = rand.nextInt(sizeOfPopulation);
			j = rand.nextInt(sizeOfPopulation);
			
			while (j == i){
				j = rand.nextInt(sizeOfPopulation);
			}
			
			indi = individuals.get(i);
			indj = individuals.get(j);
			
			iGenes = indi.genes;
			jGenes = indj.genes;
			
			crossoverPoint = rand.nextInt(iGenes.size());
			newIGenes = new ArrayList<Integer>(iGenes.subList(0, crossoverPoint));
			newIGenes.addAll(new ArrayList<Integer>(jGenes.subList(crossoverPoint, jGenes.size())));
			newJGenes = new ArrayList<Integer>(jGenes.subList(0, crossoverPoint));
			newJGenes.addAll(new ArrayList<Integer>(iGenes.subList(crossoverPoint, iGenes.size())));
			
			indi.genes = newIGenes;
			indj.genes = newJGenes;
		}
		return individuals;
	}
	*/
	
	/*
	 * Uniform crossover
	 * Much more effective
	 */
	public ArrayList<Individual> crossBreed(double crossBreedRate){
        ArrayList<Integer> geneList = new ArrayList<Integer>();
        ArrayList<Individual> indList = new ArrayList<Individual>();
        Individual ind1, ind2;
        
        for (int index = 0; index < individuals.size(); index++){
        	if (Math.random() < crossBreedRate){
	        	ind1 = individuals.get(index);
	        	ind2 = individuals.get((index + 1)%individuals.size());
		        for (int i = 0; i < ind1.genes.size(); i++) {
		            if (Math.random() <= 0.5) {
		            	geneList.add(ind1.genes.get(i));
		            } else {
		            	geneList.add(ind2.genes.get(i));
		            }
		        }
		        ind1 = new Individual(geneList);
		        ind1.genes = geneList;
		        indList.add(ind1);
		        geneList = new ArrayList<Integer>();
        	} else {
        		indList.add(individuals.get(index));
        	}
        }
        return indList;
	}
	
	public void mutation(double mutationRate){
		Random rand = new Random();
		double[] doubles = rand.doubles((long) (sizeOfPopulation*GENE.size()), 0, 1).toArray();
		Individual ind;
		
		for (int i = 0; i < individuals.size(); i ++){
			ind = individuals.get(i);
			//System.out.println(ind.genes);
			for (int j = 0; j < GENE.size(); j++){
				//System.out.println("I : " + i + " J: " + j);
				if (doubles[i*GENE.size() + j] < mutationRate){
					//System.out.println("Mutation occurring to: " + ind + " at point " + j);
					//System.out.println("Current genes: " + ind.genes);
					ind.genes.set(j, rand.nextInt(6));
					//System.out.println("New genes:     " + ind.genes);
				}
			}
		
		}
	}

}
