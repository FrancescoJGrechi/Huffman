import java.io.*;
import java.util.HashMap;
import java.util.Arrays;
import java.util.Map;
import java.util.ArrayList;
import java.util.PriorityQueue;


public class Mainer {
	
	///// ENTRY POINT /////
	public static void main (String[] args) throws IOException {

		////////////////////////////////////////
		///// WE START BY HASHING THE FILE /////
		////////////////////////////////////////
		HashMap<Character, HashNode> map = new HashMap<>();

		// we start by reading in the file
		String file = args[0];
		String fileTo = args[1];
		String line;
		
		// we try reading in this file name
		try{
			// create stuff to read in file
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			// we store the string as a char array
			char[] arrayString;

			
			// cycle through line by line and input into the hashmap
			while ((line = bufferedReader.readLine()) != null){

				
				arrayString = line.toCharArray();
				//System.out.println(Arrays.toString(arrayString));
				
				// cycle through the line
				for (int i = 0; i < arrayString.length; ++i){

					// System.out.println("Main Check #3: " + arrayString[i]);
					
					if (map.containsKey(arrayString[i]))
						map.get(arrayString[i]).increaseFrequency();
					
					else
						map.put(arrayString[i], new HashNode(arrayString[i], 1));
			
				}// end of for loop
				
			}
			
		}
		catch(IOException e){
			 System.err.println("Caught IOException: " + e.getMessage());
		}

		// we check that the map has worked properly
		// System.out.println("Check #3: ")
		//printNodeMap(map);



		////////////////////////////////////////
		/////// WE PUSH NODES TO THE HEAP //////
		////////////////////////////////////////
		PriorityQueue<HashNode> heap = new PriorityQueue<>(map.size());

		for (Map.Entry<Character, HashNode> entry : map.entrySet()){
    		heap.add(entry.getValue());
		}// tested & works

		
/*
		// clone priority queue and print it out
		PriorityQueue<HashNode> heapClone = new PriorityQueue<>(heap);

		for (int i = 0; heapClone.size() > 0; i++){

			HashNode tmp = heapClone.poll();
			System.out.println( tmp.returnCharacter() + " = " + tmp.returnFrequency() );

		}
*/

		////////////////////////////////////////
		/////// WE POP TOP TWO AND MAKE NEW ////
		////////////////////////////////////////

		while (heap.size() > 1){

			// we pop the top two elements
			HashNode tmp1 = heap.poll();
			HashNode tmp2 = heap.poll();

			// evaluate their sum
			int frequencySum = tmp1.returnFrequency() + tmp2.returnFrequency();

			// input new node that has these two as children
			heap.offer(new HashNode(frequencySum, tmp1, tmp2));

		}// tested & works

/*
		// clone priority queue and print it out
		PriorityQueue<HashNode> heapClone = new PriorityQueue<>(heap);

		for (int i = 0; heapClone.size() > 0; i++){

			HashNode tmp = heapClone.poll();
			System.out.println( tmp.returnCharacter() + " = " + tmp.returnFrequency() );

		}
*/

		////////////////////////////////////////
		// WE WORK WITH THE RESULTING HTREE ////
		////////////////////////////////////////
		HashNode root = heap.poll();
        ArrayList<Integer> codes = new ArrayList<>();
        HashMap<Character, String> finalMap = new HashMap<>(map.size());

        collectCodes(root, codes, finalMap, 0);

        
		////////////////////////////////////////
		// WRITE THE RESULTING HTREE TO FILE ///
		////////////////////////////////////////        
        FileWriter fileWriter = new FileWriter(fileTo);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        bufferedWriter.write("{ ");

        for (Map.Entry<Character, String> entry : finalMap.entrySet()){
        	bufferedWriter.write("\"" + entry.getKey() + "\" : \"" + entry.getValue() + "\", ");
        }

        bufferedWriter.write("}");
        bufferedWriter.close();


	}// end of main


	//// WE DEFINE AUXILLIARY STATICS /////

	// method to print out the hashmap
	public static void printNodeMap(Map<Character, HashNode> map) {

		System.out.println("Check #1");

		for (Map.Entry<Character, HashNode> entry : map.entrySet()){
    		System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue().frequency);
    		System.out.println("Check #2");
		}
		
	}

	// comment about functionality eventually
    public static void collectCodes(HashNode root, ArrayList<Integer> codes, HashMap<Character, String> map, int top){

    	// Since HTrees are complete, we only have to deal with cases of left children & right children
        if (!root.isLeaf()){

        	// we work down the left half of the tree
            codes.add(top, new Integer(0));
            collectCodes(root.leftChild(), codes, map, top+1);        

        	// then then work down the right half of the tree
            codes.add(top, new Integer(1));        
            collectCodes(root.rightChild(), codes, map, top+1);

        }

        // we deal with the case of hitting a leaf
        else {

        	// we conatenate the value of the edges hit to reach the character
            String tmp = "";
            for (int i = 0; i < top; i++){
                tmp += Integer.toString(codes.get(i));
            }

            // we store the pair (character, tmp) in the hashmap
            map.put(root.returnCharacter(), tmp);

        }

    }


}

