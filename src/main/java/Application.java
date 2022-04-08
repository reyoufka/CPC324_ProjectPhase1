/*Names & IDs:
 * 1- Reyouf Alhaj, 1807118
 * 2- Joud Talaky,  1905537
 * 3- Layan Makki,  1905188
 *Subject: CPCS-324, Algorithms and Data Structures II
 *Doctor: Mai Fadel
 *Title: Group Project – Part I
 *Date: Thursday 7/4/2022
*/

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        //Create a scanner for user input for choices
        Scanner i = new Scanner(System.in);
        
        //Create variables for user choice where n = vertex and m = edges
        int n = 0;
        int m = 0;
        char c;
        
        //Printing the menu and prompting user to type input
        System.out.println("This program generates random graphs, and runs several algorithms\n");
        System.out.println("Please choose your algorithm:");
        System.out.println("A- Kruskal & Prim Priority Queue");
        System.out.println("B- Prim Min Heap & Prim Priority Queue");
        System.out.print("Please enter your choice: ");
        
        //Taking input from user
        c = i.next().charAt(0);
        c = Character.toLowerCase(c);
        System.out.println("\n");
        
        //If statement to check for invalid input
        if (c != 'a' && c != 'b')
            System.out.println("Sorry! Your input is invalid");
        
        //If statement to check for valid input, and printing statements
        if (c == 'a' || c == 'b') {
            System.out.println("Please choose your case");
            System.out.println(" A- n = 1000, m = 10000");
            System.out.println(" B- n = 1000, m = 15000");
            System.out.println(" C- n = 1000, m = 25000");
            System.out.println(" D- n = 5000, m = 15000");
            System.out.println(" E- n = 5000, m = 25000");
            System.out.println(" F- n = 10000, m = 15000");
            System.out.println(" G- n = 10000, m = 25000");
            System.out.println(" H- n = 20000, m = 200000");
            System.out.println(" I- n = 20000, m = 300000");
            System.out.println(" J- n = 50000, m = 1000000");
            System.out.print("\nPlease enter your choice: ");
            char letter = i.next().charAt(0);
            letter = Character.toLowerCase(letter);
            
            //Check if input is correct
            while (letter < 'a' || letter > 'j') {
                System.out.println("Sorry! Your input is invalid");
                System.out.print("Please try again: ");
                letter = i.next().charAt(0);
                letter = Character.toLowerCase(letter);
            }
            
            //Switch statement for choices
            switch (letter) {
                case 'a': {
                    n = 1000;
                    m = 10000;}
                break;
                case 'b': {
                    n = 1000;
                    m = 15000;}
                break;
                case 'c': {
                    n = 1000;
                    m = 25000;}
                break;
                case 'd': {
                    n = 5000;
                    m = 15000;}
                break;
                case 'e': {
                    n = 5000;
                    m = 25000;}
                break;
                case 'f': {
                    n = 10000;
                    m = 15000;}
                break;
                case 'g': {
                    n = 10000;
                    m = 25000;}
                break;
                case 'h': {
                    n = 20000;
                    m = 200000;}
                break;
                case 'i': {
                    n = 20000;
                    m = 300000;}
                break;
                case 'j': {
                    n = 50000;
                    m = 1000000;}
                break;
            }
            
            //Generate graph based on choices
            Graph graph = new Graph(n, m);
            graph.makeGraph(graph);
            
            //Perform kruskal and prim priority queue
            if (c == 'a') {
                new KruskalAlg().displayResultingMST(graph);
                new PQPrimAlg().displayResultingMST(graph);
            } 
            //Perform prim min heap and prim priority queue
            else if (c == 'b') {
                new MHPrimAlg().displayResultingMST(graph);
                new PQPrimAlg().displayResultingMST(graph);
            }
        }
    }

}
