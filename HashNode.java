public class HashNode implements Comparable<HashNode>{

	// necessary parameters 
	char character;
	int frequency;
	boolean isLeaf;

	HashNode left, right;

	// constructor
	public HashNode(char c, int f){
		
		this.character = c;
		this.frequency = f;
		this.isLeaf = true;
		
	}

	public HashNode(int f, HashNode l, HashNode r){

			this.frequency = f;
			this.isLeaf = false;

			this.left = l;
			this.right = r;

	}

	// method to increase frequency parameter
	public void increaseFrequency(){
		frequency++;
	}

	public int returnFrequency(){
		return frequency;
	}

    public char returnCharacter(){
        return character;
    }

    public boolean isLeaf(){
        return isLeaf;
    }

    public HashNode leftChild(){
        return left;
    }

    public HashNode rightChild(){
        return right;
    }


	@Override
	public int compareTo(HashNode other){

		if (this.frequency > other.returnFrequency()){
			return 1;
		}
		else if (this.frequency < other.returnFrequency()){
			return -1;
		}
		else{
			return 0;
		}

	}


}// end of class
