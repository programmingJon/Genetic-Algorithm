import java.util.ArrayList;
import java.util.Random;
public class Individual implements Comparable<Individual>{
	//Note: this class has a natural ordering that is inconsistent with equals.

	static final int[] COLOURS = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
	ArrayList<Integer> nodeColours;
	int fitness;
	int accFitness;
	
	Individual(int noOfNodes, ArrayList<Integer> nodeColours){
		Random rand = new Random();
		this.nodeColours = nodeColours;
		
		for (int i = 0; i < noOfNodes; i++){
			this.nodeColours.add(rand.nextInt(COLOURS.length));
		}
	}

	public int compareTo(Individual arg0) {
		if (this.fitness > arg0.fitness){
			return -1;
		} else if (this.fitness == arg0.fitness){
			return 0;
		} else {
			return 1;
		}
	}
}
