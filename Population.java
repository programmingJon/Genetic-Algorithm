import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class Population {

	ArrayList<Individual> individuals;
	int sizeOfPopulation;
	int noOfNodes;
	
	Population(ArrayList<Individual> individuals, int sizeOfPopulation, int noOfNodes){
		this.individuals = individuals;
		this.sizeOfPopulation = sizeOfPopulation;
		this.noOfNodes = noOfNodes;
	}
	
	public void createPopulation(){
		Individual ind;
		for (int i = 0; i < this.sizeOfPopulation; i++){
			ind = new Individual(noOfNodes, new ArrayList<Integer>());
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
		ind.fitness = fitness;
	}
	
	public Individual getFittest(ArrayList<Individual> individuals){
		Individual fittest = individuals.get(0);
		for (Individual ind: individuals){
			if (ind.fitness > fittest.fitness){
				fittest = ind;
			}
		}
		System.out.println("Highest Fitness is: " + fittest.fitness);
		return fittest;
	}
	
	/*
	 * Old Selection method. Wasn't biased enough to the fittest individuals.
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
	}
	*/
	
	/*
	 * Elitism
	 */
	public ArrayList<Individual> selection(){
		ArrayList<Individual> list = new ArrayList<Individual>();
		int accFitness = 0;
		Random rand = new Random();
		
		Collections.sort(individuals);
		
		for (int index = 0; index < individuals.size() / 2; index++){
			list.add(individuals.get(index));
		}
		
		for (Individual ind: individuals){
			//System.out.println(ind.fitness);
			accFitness = ind.fitness;
			ind.accFitness = accFitness;
		}
		
		
		int randomInt;
		for (int i = 0; i < sizeOfPopulation / 2; i++){
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
	}
	
	
	public void crossBreed(double crossbreedRate){
		Random rand = new Random();
		Individual indi;
		Individual indj;
		int i, j, crossoverPoint;
		ArrayList<Integer> iColours, jColours, newIColours, newJColours;
		
		for (int iter = 0; iter < crossbreedRate*sizeOfPopulation; iter++){
			i = rand.nextInt(sizeOfPopulation);
			j = rand.nextInt(sizeOfPopulation);
			
			while (j == i){
				j = rand.nextInt(sizeOfPopulation);
			}
			
			indi = individuals.get(i);
			indj = individuals.get(j);
			
			iColours = indi.nodeColours;
			jColours = indj.nodeColours;
			
			crossoverPoint = rand.nextInt(iColours.size());
			newIColours = new ArrayList<Integer>(iColours.subList(0, crossoverPoint));
			newIColours.addAll(new ArrayList<Integer>(jColours.subList(crossoverPoint, jColours.size())));
			newJColours = new ArrayList<Integer>(jColours.subList(0, crossoverPoint));
			newJColours.addAll(new ArrayList<Integer>(iColours.subList(crossoverPoint, iColours.size())));
			
			indi.nodeColours = newIColours;
			indj.nodeColours = newJColours;
		}
	}
	
	public void mutation(double mutationRate){
		Random rand = new Random();
		double[] doubles = rand.doubles((long) (sizeOfPopulation*noOfNodes), 0, 1).toArray();
		Individual ind;
		
		//for (Individual ind: individuals){
		for (int i = 0; i < individuals.size(); i ++){
			ind = individuals.get(i);
			for (int j = 0; j < noOfNodes; j++){
				if (doubles[i*noOfNodes + j] < mutationRate){
					ind.nodeColours.set(j, rand.nextInt(10));
				}
			}
		
		}
	}

}
