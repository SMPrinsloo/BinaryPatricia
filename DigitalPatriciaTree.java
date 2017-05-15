*====================================NODE===============================================*/
	public class patriciaNode
	{

		String[] data = {"#","0","1"};

		public Boolean isLeaf;
		public Boolean EOW;
		public String leafString;

		public patriciaNode[] pointers={null,null,null};
		public int[] next;

		public  patriciaNode(){
			isLeaf = false;
			leafString = "";
		}

		public  patriciaNode(String l){
			leafString = l;
			isLeaf = true;
		}	

		public int indexOf(String s){
			if (s.compareTo("#")==0)return 0;
			if (s.compareTo("0")==0)return 1;
			if (s.compareTo("1")==0)return 2;
			return 0;
		}

		private boolean isLeaf() {
			return isLeaf;
		} 

		private String _toString(){
			String returnString;
			returnString="";
			int index;
			if (isLeaf) return leafString;
			for (index = 0;index<=2;index++){
				if (pointers[index]!= null) {
					returnString+="["+data[index]+"]";
				}
			}

			return returnString;
		}

		private boolean isEmpty(){
			if ((pointers[0]== null && pointers[1] == null) && pointers[2]==null)
				return true;
			return false;

		}

		private boolean hasLeafs()
		{	
			int count=0;
			for (int index =0;index<=2;index++){
				if(pointers[index]!= null) count++;
			}
			if (count>=2) return true;
			else return false;
		}
	}
	
	/*====================================NODE===============================================*/
	
	//private patriciaNode parentOfLeaf;
	public patriciaNode root;
	
	private boolean isAlpha(String name) {
	    return name.matches("[a-zA-Z]+");
	}
	
	private String convertToBinary(String word){
		String binaryString = "";
		//Convert word to binary representation
		for (int i = 0; i< word.length();i++){
			char c = word.charAt(i);
			switch (Character.toUpperCase(c)) {
				case 'A': binaryString+="00000";
				case 'B': binaryString+="00001";
				case 'C': binaryString+="00010";
				case 'D': binaryString+="00011";
				case 'E': binaryString+="00100";
				case 'F': binaryString+="00101";
				case 'G': binaryString+="00110";
				case 'H': binaryString+="00111";
				case 'I': binaryString+="01000";
				case 'J': binaryString+="01001";
				case 'K': binaryString+="01010";
				case 'L': binaryString+="01011";
				case 'M': binaryString+="01100";
				case 'N': binaryString+="01101";
				case 'O': binaryString+="01110";
				case 'P': binaryString+="01111";
				case 'Q': binaryString+="10000";
				case 'R': binaryString+="10001";
				case 'S': binaryString+="10010";
				case 'T': binaryString+="10011";
				case 'U': binaryString+="10100";
				case 'V': binaryString+="10101";
				case 'W': binaryString+="10110";
				case 'X': binaryString+="10111";
				case 'Y': binaryString+="11000";
				case 'Z': binaryString+="11001";
			}
		}
		return binaryString;
		
	}
	
	public static void print(String s)
	{
		System.out.println(s);
	}


	public DigitalPatriciaTree()
	{
		/*You may add any initialization which your
		require for your trie here.  Your default constructor
		will be used to create your tree for marking*/

		root = new patriciaNode();
	}
	
	public boolean insert(String word)
	{
		
		
		
		if (!isAlpha(word)) return false;
		
		
		String _word = convertToBinary(word)+"#";
		
		String TheString = search(_word);
		char KlopDisselDassieBalsak = TheString.charAt(TheString.length()-1);
		if (KlopDisselDassieBalsak!= '!') return false;
		
		
		
		/*Insert the word passed as a parameter into the tree.
		No duplicates are allowed.  If the insert fails for 
		whatever reason, you should return false. You must return 
		true to indicate a successful insert.*/
		
		patriciaNode p = root;
		Integer index = 0;
		while(!false) {
			if (index == _word.length()-1){	
				patriciaNode newLeaf = new patriciaNode(word);
				p.pointers[0] = newLeaf;
				return true;
			}
			
			else if (p.pointers[Character.getNumericValue(_word.charAt(index))+1] == null) {	
				patriciaNode newLeaf = new patriciaNode(word);
				p.pointers[Character.getNumericValue(_word.charAt(index))+1] = newLeaf;
				return true;
			}
			
			else if(p.pointers[Character.getNumericValue(_word.charAt(index))+1] != null && p.pointers[Character.getNumericValue(_word.charAt(index))+1].isLeaf() == true ){	
				
				String KL = p.pointers[Character.getNumericValue(_word.charAt(index))+1].leafString ;
				String binKL = convertToBinary(KL)+"#";
				
				char[] char_KL = binKL.toCharArray();
				char[] char_Word = _word.toCharArray(); 
				
				while (char_Word[index] == char_KL[index]){
					patriciaNode nonLeaf = new patriciaNode();
					p.pointers[Character.getNumericValue(_word.charAt(index))+1] = nonLeaf;
					p = nonLeaf;
					index++;
				}
				
				patriciaNode aLeaf = new patriciaNode(word);
				p.pointers[Character.getNumericValue(_word.charAt(index))+1] = aLeaf;
				
				if (index == binKL.length()-2){
					print(KL);
					p.pointers[0] = new patriciaNode(KL);
					print(p._toString());  
				}
				else{
					patriciaNode leLeaf = new patriciaNode(KL);
					p.pointers[Character.getNumericValue(binKL.charAt(index))+1]= leLeaf;
				}
				
				return true;
			}
			
			else {
				p = p.pointers[Character.getNumericValue(_word.charAt(index++))+1];
			}
		}
	}
	
	public String search(String word){
		
		String _word = convertToBinary(word);
	
		if (root.isEmpty()) return"!";
		patriciaNode tmpNode = root;
		String _str="";
		int index = 0;
		while ( tmpNode!=null && !tmpNode.isLeaf()){
			if (tmpNode == root) _str = tmpNode._toString();
			if (tmpNode != root && tmpNode.hasLeafs()) _str = _str+","+tmpNode._toString();
			if (index == _word.length())
				tmpNode = tmpNode.pointers[0];
				else
				tmpNode = tmpNode.pointers[Character.getNumericValue(_word.charAt(index))+1];
			index++;
		}
		
		if (tmpNode == null) {
			_str = _str+",!";
		}
		else if(tmpNode == root){
			return _str; 
		}
		else	
		{
			if (tmpNode!= null && tmpNode.leafString.compareTo(word) == 0) 
				_str = _str+","+tmpNode._toString();	
			else 
				_str = _str+",!";
		}
		return _str;
	}
	

}
 
