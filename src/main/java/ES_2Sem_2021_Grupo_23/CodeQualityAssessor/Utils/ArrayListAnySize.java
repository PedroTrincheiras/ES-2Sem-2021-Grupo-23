package ES_2Sem_2021_Grupo_23.CodeQualityAssessor.Utils;

import java.util.ArrayList;

/**
* A array list without a specific size
*/
public class ArrayListAnySize<E> extends ArrayList<E>{
   
	private static final long serialVersionUID = 1L;

	 /**
	  * add a element to the specific index, if in the previous index's doesn't exist elements then that index's will be null
	  * 
	  * @param index -> index where the element will be added
	  * @param element -> element to be added
	  * 
	  */
	
	@Override
    public void add(int index, E element){
        if(index >= 0 && index <= size()){
            super.add(index, element);
            return;
        }
        int insertNulls = index - size();
        for(int i = 0; i < insertNulls; i++){
            super.add(null);
        }
        super.add(element);
    }
}	