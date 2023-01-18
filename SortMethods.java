import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 *	SortMethods - Sorts a List of City Objects in ascending order.
 *
 * @author	Aarya Kulkarni
 * @since	January 09, 2023
 */
public class SortMethods {
	
	/*
	 * Used by mergeSort only
	 */
	private List<City> temp;
	/**
	 *	Bubble Sort algorithm - in ascending order
	 *	@param list		list of City objects to sort
	 * @param comparator comparator to compate two city objects, can be null
	 */
	public void bubbleSort(List<City> list, Comparator<City> comparator) {
		for (int outer = list.size() - 1; outer > 0; outer--)
			for (int inner = 0; inner < outer; inner++)
				if(compare(comparator, list.get(inner), list.get(inner + 1)) > 0)
				    swap(list, inner, inner + 1);
	}
	
	/**
	 *	Swaps two City objects in list list
	 *	@param list		list of City objects
	 *	@param x		index of first object to swap
	 *	@param y		index of second object to swap
	 */
	private void swap(List<City> list, int x, int y) {
		City temp = list.get(x);
		list.set(x, list.get(y));
		list.set(y, temp);
	}
	
	/**
	 *	Selection Sort algorithm - in ascending order (you implement)
	 * @param list		list of City objects to sort
	 * @param comparator comparator to compate two city objects, can be null
	 */
	public void selectionSort(List<City> list, Comparator<City> comparator) {
		for (int i = list.size(); i > 1; i--)
		{
			int maxIndex = 0;
			for (int j = 1; j < i; j++)
			{
				if(compare(comparator, list.get(j), list.get(maxIndex)) > 0)
					maxIndex = j;
			}
			swap(list, maxIndex, i-1);
		}
	}
	
	/**
	 * If comparator is not-null then uses comparator to compare two City objects
	 * If comparator is null then uses City's Comparable implementation to compare two City objects
	 * @param comparator comparator to compate two city objects, can be null
	 * @param a first city object
	 * @param b second city object
	 * 
	 */
	private int compare(Comparator<City> comparator, City a, City b) {
	    if (comparator != null)
	        return comparator.compare(a, b);
	    return a.compareTo(b);
	}
	
	/**
	 *	Insertion Sort algorithm - in ascending order (you implement)
	 *	@param list		list of City objects to sort
	 * @param comparator comparator to compate two city objects, can be null
	 */
	public void insertionSort(List<City> list, Comparator<City> comparator) {
		for (int i = 1; i < list.size(); i++)
		{
			City aTemp = list.get(i);
			int j = i;
			while (j > 0 && compare(comparator, aTemp, list.get(j-1)) < 0)
			{
				list.set(j, list.get(j - 1));
				j --;
			}
			list.set(j, aTemp);
		}
	}
	
	/**
	 *	Merge Sort algorithm - in ascending order (you implement)
	 *	@param list		list of City objects to sort
	 *  @param comparator comparator to compate two city objects, can be null
	 */
	public void mergeSort(List<City> list, Comparator<City> comparator) {
		temp = new ArrayList<City>(list);
		recursiveSort(list, 0, list.size() - 1, comparator);
	}
	
	private void recursiveSort(List<City> a, int from, int to, Comparator<City> comparator)
	{
	    if (to  -  from <  2)       // Base case : 1 or 2 elements
	    {
	        if  (to  > from && compare(comparator, a.get(to), a.get(from)) < 0)
	        {
	              //  swap  a[to] and  a[from]
	            swap(a, from, to);
	        }
	       
	    }
	    else                      //  Recursive case
	    {
	        int  middle = (from + to) / 2;
	        recursiveSort (a, from, middle, comparator);
	        recursiveSort (a, middle  + 1,to, comparator);
	        merge(a, from, middle, to, comparator);
	    }
	}
	
	//Merges  a[ from ]   a[middle]  and a [middle+1]   a [to]
	//  into one sorted list a[ from]   a[ to]
	private void merge(List<City> a, int from, int middle, int to, Comparator<City> comparator)
	{
	     int  i =  from,  j  =  middle  +  1,  k  =  from;
	     
	     // while both lists have elements left unprocessed:
	     while  (i  <=middle  &&  j  <=to)
	     {
	         if  (compare(comparator, a.get(i), a.get(j)) < 0)
	         {
	             temp.set(k, a.get(i));    //  or  simply  temp[k]  =  a[i++];
	             i++;
	             
	         }
	         else
	         {   temp.set(k, a.get(j));
	         j++;
	         }
	         k++;
	     }
	      
	     
	     //  Copy the tail of the first half, if any, into temp:
	     while  (i  <= middle)
	     {
	      
	         temp.set(k, a.get(i));    // Or simply temp[k++]  =  a[i++]
	         i++;
	         k++;
	        
	     }
	     
	       //  Copy the tail of the second half, if any, into temp:
	       while  (j  <=  to) 
	       {
	           temp.set(k, a.get(j));       //  Or simply temp[k++]  =  a[j++]
	           j++;
	           k++;
	           
	       }
	       
	          //  Copy temp back into a
	       for  (k  =  from; k  <=  to;  k++)
	           a.set(k, temp.get(k));
	       
	}
	
	/*****************************************************************/
	/************************* For Testing ***************************/
	/*****************************************************************/
	
	/**
	 *	Print an list of City objects to the screen
	 *	@param list		the list of City objects
	 */
	public void printList(List<City> list) {
		if (list.size() == 0) System.out.print("(");
		else System.out.printf("( %4d", list.get(0));
		for (int a = 1; a < list.size(); a++) {
			if (a % 10 == 0) System.out.printf(",\n  %4d", list.get(a));
			else System.out.printf(", %4d", list.get(a));
		}
		System.out.println(" )");
	}
}
