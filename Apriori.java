//I worked on this algorithm alone using materials taught in class, 
//the textbook provided in the syllabus, and other online materials 
//for the data structures and other implementations- Ramzi Daswani 

import java.io.*;
import java.util.*;

public class Apriori {

  
  String output;
  int item;
  int process;
  double min;
  String data;
  ArrayList<int[]> itemarr;
  ArrayList keep=new ArrayList();
  ArrayList countarr=new ArrayList<Integer>();
 
 public Apriori() {
     item= 0;
     process = 0;
 }
 
 public Apriori(String output) {
     item= 0;
     process = 0;
     this.output=output;
 }

 public void analyzer(String[] args) throws IOException {
     
	 data = args[0];
     if (args.length >= 2) {
    	 min = (Double.valueOf(args[1]));
     } 
     
     item = 0;
     process = 0;
     BufferedReader bufferedReader = new BufferedReader(new FileReader(data));
     while (bufferedReader.ready()) {
         String buffer = bufferedReader.readLine();
         if (buffer.matches("\\s*")) {
             continue;
         }
         
         StringTokenizer token = new StringTokenizer(buffer, " ");
         while (token.hasMoreTokens()) {
             int items = Integer.parseInt(token.nextToken());
             if (items + 1 > item) {
                 item = items + 1;
             }
         }
         process++;
     }
   
 }

 public void calculate() throws Exception {

	 itemarr = new ArrayList<int[]>();
     for (int i = 0; i < item; i++) {
         int[] itemSet = {i};
         itemarr.add(itemSet);
     }
     int current = 1; 
     int count = 0;

     while (itemarr.size() > 0) {
    	 
         ArrayList<int[]> frequent = new ArrayList<int[]>(); 

         boolean select; 
         int counter[] = new int[itemarr.size()]; 

         
         BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(data)));

         boolean[] transaction = new boolean[item];

         
         for (int i = 0; i < process; i++) {

             String line = bufferedReader.readLine();
           
             Arrays.fill(transaction, false);
             StringTokenizer stringTokenizer = new StringTokenizer(line, " ");
             
             while (stringTokenizer.hasMoreTokens()) {

                 int value = Integer.parseInt(stringTokenizer.nextToken());
                 transaction[value] = true; 
             }

             
             for (int j = 0; j < itemarr.size(); j++) {
            	 select = true; 
               
                 int[] cand = itemarr.get(j);
                
                 for (int k : cand) {
                     if (!transaction[k]) {
                    	 select = false;
                         break;
                     }
                 }
                 if (select) { 
                     counter[j]++;
                 }
             }
         }

         bufferedReader.close();

         for (int i = 0; i < itemarr.size(); i++) {
             
             if ((counter[i] / (double) (process)) >= min) {
            	 
            	 keep.add(Arrays.toString(itemarr.get(i)));
            	 countarr.add((int)counter[i]);
            	 System.out.println(Arrays.toString(itemarr.get(i))  + "("+  counter[i] + ")");
            	 	
            	 frequent.add(itemarr.get(i));
             }
         }
         
         itemarr = frequent;
         if (itemarr.size() > 0 ||itemarr.size()<0) {
        	 count += itemarr.size();
            
             int size = itemarr.get(0).length;
            
             HashMap<String, int[]> temp = new HashMap<String, int[]>();

            
             for (int i = 0; i < itemarr.size(); i++) {
                 for (int j = i + 1; j < itemarr.size(); j++) {
                     int[] test1 = itemarr.get(i);
                     int[] test2 = itemarr.get(j);

                   
                     int[] selected = new int[size + 1];
                     for (int s = 0; s < selected.length - 1; s++) {
                    	 selected[s] = test1[s];
                     }

                     int no = 0;
                   
                     for (int k = 0; k < test2.length; k++) {
                         boolean found = false;
                        
                         for (int x = 0; x < test1.length; x++) {
                             if (test1[x] == test2[k]) {
                                 found = true;
                                 break;
                             }
                         }
                         if (!found) {
                        	 	no++;
                             selected[selected.length - 1] = test2[k];
                         }
                     }

                     if (no== 1) {
                         Arrays.sort(selected);
                         temp.put(Arrays.toString(selected), selected);
                     }
                 }
             }

             
             itemarr = new ArrayList<int[]>(temp.values());
            
         }
         current++;
     }
     
     try (Writer writer = new BufferedWriter(new OutputStreamWriter(
             new FileOutputStream(output), "utf-8"))) {
 
  for(int i =0; i<keep.size(); i++) {
	  writer.write( (String) keep.get(i) + "("+(int) countarr.get(i) +")"+ "\n"); 
  }
  
  System.out.println("Output file created");
  
}
     System.out.println("Done");
 }


}