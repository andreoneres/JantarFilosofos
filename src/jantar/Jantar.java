/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jantar;



/**
 *
 * @author André
 */
public class Jantar { //Classe Jantar que irá inicializar as threads.
   public static void main (String[] args) {
      
       Filosofo iniciar = new Filosofo();
       iniciar.filosofo1.start();//Inicialização da thread filosofo1.
       iniciar.filosofo2.start();//Inicialização da thread filosofo2.
       iniciar.filosofo3.start();//Inicialização da thread filosofo3.
       iniciar.filosofo4.start();//Inicialização da thread filosofo4.
       iniciar.filosofo5.start();//Inicialização da thread filosofo5.  
       
   }
}