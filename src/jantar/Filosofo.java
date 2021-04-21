/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jantar;

import java.util.concurrent.Semaphore;

/**
 *
 * @author André
 */
public class Filosofo extends Thread { //Criação da classe Filosofo, herdando da classe Thread.

    Semaphore semaforo = new Semaphore(1);//Criação de um semáforo para controle das threads.
    int garfoComFilosofo[] = new int[5];//Criação de um vetor garfo para controlar qual filosofo está com o garfo.
    int tempomax = 3000;//Variavel que armazena o tempo máximo que o Math.random pode gerar.
    int tempo = 0;//Variavel que recebe o tempo aleatório gerado.

    Thread filosofo1 = new Thread(new Runnable() { //Criação da thread filosofo1 e implementação dela.
        public void run() {
            while (true) {//Estrutura de repetição que irá se repetir para sempre.

                try { //Try que irá tentar executar o bloco de código e caso exista alguma exceção, irá para o catch.
                    tempo = (int) (Math.round(Math.random() * tempomax)); //Geração de um número aleátorio dentro dos limites da variavel tempomax e mandando para a variavel tempo.
                    pensar(tempo);//Aqui o tempo gerado é enviado via parametro de entrada para o método pensar, onde o filosofo irá pensar durante este tempo.

                    if (semaforo.availablePermits() > 0) { //if que verifica se o semáforo está desligado ou não.
                        semaforo.acquire();//Aqui o semáforo será "ligado" caso ele esteja "desligado".
                        if (garfoComFilosofo[0] == 0 & garfoComFilosofo[1] == 0) { //Se os dois garfos ao seu lado estiverem livres, ele pegará os dois ao mesmo tempo.
                            garfoComFilosofo[0] = 1;//O Filosofo pega o garfo para comer.
                            garfoComFilosofo[1] = 1;//O Filosofo pega o garfo para comer.
                            System.out.println(garfoComFilosofo[0] + " " + garfoComFilosofo[1] + " " + garfoComFilosofo[2] + " " + garfoComFilosofo[3] + " " + garfoComFilosofo[4]);//Mostra com qual filosofo estão os garfos.
                            semaforo.release(); //Após pegar os garfos, o semáforo é "liberado".
                            tempo = (int) (Math.round(Math.random() * tempomax));//Geração de um número aleátorio dentro dos limites da variavel tempomax e mandando para a variavel tempo.
                            while (semaforo.availablePermits() > 0) {//Enquanto não bloquear o semáforo, esse bloco será repetido.
                                comer(tempo); //Aqui o tempo gerado acima é enviado via parametro de entrada para o método comer, onde o filósofo irá comer durante este tempo.
                                semaforo.acquire();//Aqui o semáforo será "ligado" caso ele esteja "desligado".
                            }
                            garfoComFilosofo[0] = 0;//Filosofo larga o garfo após terminar de comer.
                            garfoComFilosofo[1] = 0;//Filosofo larga o garfo após terminar de comer.
                            System.out.println(garfoComFilosofo[0] + " " + garfoComFilosofo[1] + " " + garfoComFilosofo[2] + " " + garfoComFilosofo[3] + " " + garfoComFilosofo[4]);//Mostra os garfos que estão livres.
                            semaforo.release();//Aqui o semáforo será "desligado" logo após o filosofo largar os garfos.
                        } else {
                            semaforo.release();//Caso os garfos não estejam livres, o semáforo é desligado.
                            tempo = (int) (Math.round(Math.random() * tempomax));//Geração de um número aleátorio dentro dos limites da variavel tempomax e mandando para a variavel tempo.
                            pensar(tempo);//Aqui, o tempo gerado é enviado via parametro de entrada para o método pensar, onde o filosofo irá pensar durante este tempo.
                            System.out.println(garfoComFilosofo[0] + " " + garfoComFilosofo[1] + " " + garfoComFilosofo[2] + " " + garfoComFilosofo[3] + " " + garfoComFilosofo[4]);//Mostra na tela os garfos que estão lvires.
                        }
                    } else {
                        semaforo.release();//Caso o semáforo esteja "ligado", a executação pula para esta linha e "desliga" o semáforo.

                    }
                } catch (Exception ex) { //Tratamentode exceções, se existirem.

                }

            }

        }
    });
    
    Thread filosofo2 = new Thread(new Runnable() { //Criação da thread filosofo2 e implementação dela.
        public void run() {
            while (true) {//Estrutura de repetição que irá se repetir para sempre.

                try { //Try que irá tentar executar o bloco de código e caso exista alguma exceção, irá para o catch.
                    tempo = (int) (Math.round(Math.random() * tempomax)); //Geração de um número aleátorio dentro dos limites da variavel tempomax e mandando para a variavel tempo.
                    pensar(tempo);//Aqui o tempo gerado é enviado via parametro de entrada para o método pensar, onde o filosofo irá pensar durante este tempo.

                    if (semaforo.availablePermits() > 0) { //if que verifica se o semáforo está desligado ou não.
                        semaforo.acquire();//Aqui o semáforo será "ligado" caso ele esteja "desligado".
                        if (garfoComFilosofo[1] == 0 & garfoComFilosofo[2] == 0) { //Se os dois garfos ao seu lado estiverem livres, ele pegará os dois ao mesmo tempo.
                            garfoComFilosofo[1] = 2;//O Filosofo pega o garfo para comer.
                            garfoComFilosofo[2] = 2;//O Filosofo pega o garfo para comer.
                            System.out.println(garfoComFilosofo[0] + " " + garfoComFilosofo[1] + " " + garfoComFilosofo[2] + " " + garfoComFilosofo[3] + " " + garfoComFilosofo[4]);//Mostra com qual filosofo estão os garfos.
                            semaforo.release(); //Após pegar os garfos, o semáforo é "liberado".
                            tempo = (int) (Math.round(Math.random() * tempomax));//Geração de um número aleátorio dentro dos limites da variavel tempomax e mandando para a variavel tempo.
                            while (semaforo.availablePermits() > 0) {//Enquanto não bloquear o semáforo, esse bloco será repetido.
                                comer(tempo); //Aqui o tempo gerado acima é enviado via parametro de entrada para o método comer, onde o filósofo irá comer durante este tempo.
                                semaforo.acquire();//Aqui o semáforo será "ligado" caso ele esteja "desligado".
                            }
                            garfoComFilosofo[1] = 0;//Filosofo larga o garfo após terminar de comer.
                            garfoComFilosofo[2] = 0;//Filosofo larga o garfo após terminar de comer.
                            System.out.println(garfoComFilosofo[0] + " " + garfoComFilosofo[1] + " " + garfoComFilosofo[2] + " " + garfoComFilosofo[3] + " " + garfoComFilosofo[4]);//Mostra os garfos que estão livres.
                            semaforo.release();//Aqui o semáforo será "desligado" logo após o filosofo largar os garfos.
                        } else {
                            semaforo.release();//Caso os garfos não estejam livres, o semáforo é desligado.
                            tempo = (int) (Math.round(Math.random() * tempomax));//Geração de um número aleátorio dentro dos limites da variavel tempomax e mandando para a variavel tempo.
                            pensar(tempo);//Aqui, o tempo gerado é enviado via parametro de entrada para o método pensar, onde o filosofo irá pensar durante este tempo.
                            System.out.println(garfoComFilosofo[0] + " " + garfoComFilosofo[1] + " " + garfoComFilosofo[2] + " " + garfoComFilosofo[3] + " " + garfoComFilosofo[4]);//Mostra na tela os garfos que estão lvires.
                        }
                    } else {
                        semaforo.release();//Caso o semáforo esteja "ligado", a executação pula para esta linha e "desliga" o semáforo.

                    }
                } catch (Exception ex) { //Tratamentode exceções, se existirem.

                }

            }

        }
    });
    
    Thread filosofo3 = new Thread(new Runnable() { //Criação da thread filosofo3 e implementação dela.
        public void run() {
            while (true) {//Estrutura de repetição que irá se repetir para sempre.

                try { //Try que irá tentar executar o bloco de código e caso exista alguma exceção, irá para o catch.
                    tempo = (int) (Math.round(Math.random() * tempomax)); //Geração de um número aleátorio dentro dos limites da variavel tempomax e mandando para a variavel tempo.
                    pensar(tempo);//Aqui o tempo gerado é enviado via parametro de entrada para o método pensar, onde o filosofo irá pensar durante este tempo.

                    if (semaforo.availablePermits() > 0) { //if que verifica se o semáforo está desligado ou não.
                        semaforo.acquire();//Aqui o semáforo será "ligado" caso ele esteja "desligado".
                        if (garfoComFilosofo[2] == 0 & garfoComFilosofo[3] == 0) { //Se os dois garfos ao seu lado estiverem livres, ele pegará os dois ao mesmo tempo.
                            garfoComFilosofo[2] = 3;//O Filosofo pega o garfo para comer.
                            garfoComFilosofo[3] = 3;//O Filosofo pega o garfo para comer.
                            System.out.println(garfoComFilosofo[0] + " " + garfoComFilosofo[1] + " " + garfoComFilosofo[2] + " " + garfoComFilosofo[3] + " " + garfoComFilosofo[4]);//Mostra com qual filosofo estão os garfos.
                            semaforo.release(); //Após pegar os garfos, o semáforo é "liberado".
                            tempo = (int) (Math.round(Math.random() * tempomax));//Geração de um número aleátorio dentro dos limites da variavel tempomax e mandando para a variavel tempo.
                            while (semaforo.availablePermits() > 0) {//Enquanto não bloquear o semáforo, esse bloco será repetido.
                                comer(tempo); //Aqui o tempo gerado acima é enviado via parametro de entrada para o método comer, onde o filósofo irá comer durante este tempo.
                                semaforo.acquire();//Aqui o semáforo será "ligado" caso ele esteja "desligado".
                            }
                            garfoComFilosofo[2] = 0;//Filosofo larga o garfo após terminar de comer.
                            garfoComFilosofo[3] = 0;//Filosofo larga o garfo após terminar de comer.
                            System.out.println(garfoComFilosofo[0] + " " + garfoComFilosofo[1] + " " + garfoComFilosofo[2] + " " + garfoComFilosofo[3] + " " + garfoComFilosofo[4]);//Mostra os garfos que estão livres.
                            semaforo.release();//Aqui o semáforo será "desligado" logo após o filosofo largar os garfos.
                        } else {
                            semaforo.release();//Caso os garfos não estejam livres, o semáforo é desligado.
                            tempo = (int) (Math.round(Math.random() * tempomax));//Geração de um número aleátorio dentro dos limites da variavel tempomax e mandando para a variavel tempo.
                            pensar(tempo);//Aqui, o tempo gerado é enviado via parametro de entrada para o método pensar, onde o filosofo irá pensar durante este tempo.
                            System.out.println(garfoComFilosofo[0] + " " + garfoComFilosofo[1] + " " + garfoComFilosofo[2] + " " + garfoComFilosofo[3] + " " + garfoComFilosofo[4]);//Mostra na tela os garfos que estão lvires.
                        }
                    } else {
                        semaforo.release();//Caso o semáforo esteja "ligado", a executação pula para esta linha e "desliga" o semáforo.

                    }
                } catch (Exception ex) { //Tratamentode exceções, se existirem.

                }

            }

        }
    });

    Thread filosofo4 = new Thread(new Runnable() { //Criação da thread filosofo4 e implementação dela.
        public void run() {
            while (true) {//Estrutura de repetição que irá se repetir para sempre.

                try { //Try que irá tentar executar o bloco de código e caso exista alguma exceção, irá para o catch.
                    tempo = (int) (Math.round(Math.random() * tempomax)); //Geração de um número aleátorio dentro dos limites da variavel tempomax e mandando para a variavel tempo.
                    pensar(tempo);//Aqui o tempo gerado é enviado via parametro de entrada para o método pensar, onde o filosofo irá pensar durante este tempo.

                    if (semaforo.availablePermits() > 0) { //if que verifica se o semáforo está desligado ou não.
                        semaforo.acquire();//Aqui o semáforo será "ligado" caso ele esteja "desligado".
                        if (garfoComFilosofo[3] == 0 & garfoComFilosofo[4] == 0) { //Se os dois garfos ao seu lado estiverem livres, ele pegará os dois ao mesmo tempo.
                            garfoComFilosofo[3] = 4;//O Filosofo pega o garfo para comer.
                            garfoComFilosofo[4] = 4;//O Filosofo pega o garfo para comer.
                            System.out.println(garfoComFilosofo[0] + " " + garfoComFilosofo[1] + " " + garfoComFilosofo[2] + " " + garfoComFilosofo[3] + " " + garfoComFilosofo[4]);//Mostra com qual filosofo estão os garfos.
                            semaforo.release(); //Após pegar os garfos, o semáforo é "liberado".
                            tempo = (int) (Math.round(Math.random() * tempomax));//Geração de um número aleátorio dentro dos limites da variavel tempomax e mandando para a variavel tempo.
                            while (semaforo.availablePermits() > 0) {//Enquanto não bloquear o semáforo, esse bloco será repetido.
                                comer(tempo); //Aqui o tempo gerado acima é enviado via parametro de entrada para o método comer, onde o filósofo irá comer durante este tempo.
                                semaforo.acquire();//Aqui o semáforo será "ligado" caso ele esteja "desligado".
                            }
                            garfoComFilosofo[3] = 0;//Filosofo larga o garfo após terminar de comer.
                            garfoComFilosofo[4] = 0;//Filosofo larga o garfo após terminar de comer.
                            System.out.println(garfoComFilosofo[0] + " " + garfoComFilosofo[1] + " " + garfoComFilosofo[2] + " " + garfoComFilosofo[3] + " " + garfoComFilosofo[4]);//Mostra os garfos que estão livres.
                            semaforo.release();//Aqui o semáforo será "desligado" logo após o filosofo largar os garfos.
                        } else {
                            semaforo.release();//Caso os garfos não estejam livres, o semáforo é desligado.
                            tempo = (int) (Math.round(Math.random() * tempomax));//Geração de um número aleátorio dentro dos limites da variavel tempomax e mandando para a variavel tempo.
                            pensar(tempo);//Aqui, o tempo gerado é enviado via parametro de entrada para o método pensar, onde o filosofo irá pensar durante este tempo.
                            System.out.println(garfoComFilosofo[0] + " " + garfoComFilosofo[1] + " " + garfoComFilosofo[2] + " " + garfoComFilosofo[3] + " " + garfoComFilosofo[4]);//Mostra na tela os garfos que estão lvires.
                        }
                    } else {
                        semaforo.release();//Caso o semáforo esteja "ligado", a executação pula para esta linha e "desliga" o semáforo.

                    }
                } catch (Exception ex) { //Tratamentode exceções, se existirem.

                }

            }

        }
    });

Thread filosofo5 = new Thread(new Runnable() { //Criação da thread filosofo5 e implementação dela.
        public void run() {
            while (true) {//Estrutura de repetição que irá se repetir para sempre.

                try { //Try que irá tentar executar o bloco de código e caso exista alguma exceção, irá para o catch.
                    tempo = (int) (Math.round(Math.random() * tempomax)); //Geração de um número aleátorio dentro dos limites da variavel tempomax e mandando para a variavel tempo.
                    pensar(tempo);//Aqui o tempo gerado é enviado via parametro de entrada para o método pensar, onde o filosofo irá pensar durante este tempo.

                    if (semaforo.availablePermits() > 0) { //if que verifica se o semáforo está desligado ou não.
                        semaforo.acquire();//Aqui o semáforo será "ligado" caso ele esteja "desligado".
                        if (garfoComFilosofo[4] == 0 & garfoComFilosofo[0] == 0) { //Se os dois garfos ao seu lado estiverem livres, ele pegará os dois ao mesmo tempo.
                            garfoComFilosofo[4] = 5;//O Filosofo pega o garfo para comer.
                            garfoComFilosofo[0] = 5;//O Filosofo pega o garfo para comer.
                            System.out.println(garfoComFilosofo[0] + " " + garfoComFilosofo[1] + " " + garfoComFilosofo[2] + " " + garfoComFilosofo[3] + " " + garfoComFilosofo[4]);//Mostra com qual filosofo estão os garfos.
                            semaforo.release(); //Após pegar os garfos, o semáforo é "liberado".
                            tempo = (int) (Math.round(Math.random() * tempomax));//Geração de um número aleátorio dentro dos limites da variavel tempomax e mandando para a variavel tempo.
                            while (semaforo.availablePermits() > 0) {//Enquanto não bloquear o semáforo, esse bloco será repetido.
                                comer(tempo); //Aqui o tempo gerado acima é enviado via parametro de entrada para o método comer, onde o filósofo irá comer durante este tempo.
                                semaforo.acquire();//Aqui o semáforo será "ligado" caso ele esteja "desligado".
                            }
                            garfoComFilosofo[4] = 0;//Filosofo larga o garfo após terminar de comer.
                            garfoComFilosofo[0] = 0;//Filosofo larga o garfo após terminar de comer.
                            System.out.println(garfoComFilosofo[0] + " " + garfoComFilosofo[1] + " " + garfoComFilosofo[2] + " " + garfoComFilosofo[3] + " " + garfoComFilosofo[4]);//Mostra os garfos que estão livres.
                            semaforo.release();//Aqui o semáforo será "desligado" logo após o filosofo largar os garfos.
                        } else {
                            semaforo.release();//Caso os garfos não estejam livres, o semáforo é desligado.
                            tempo = (int) (Math.round(Math.random() * tempomax));//Geração de um número aleátorio dentro dos limites da variavel tempomax e mandando para a variavel tempo.
                            pensar(tempo);//Aqui, o tempo gerado é enviado via parametro de entrada para o método pensar, onde o filosofo irá pensar durante este tempo.
                            System.out.println(garfoComFilosofo[0] + " " + garfoComFilosofo[1] + " " + garfoComFilosofo[2] + " " + garfoComFilosofo[3] + " " + garfoComFilosofo[4]);//Mostra na tela os garfos que estão lvires.
                        }
                    } else {
                        semaforo.release();//Caso o semáforo esteja "ligado", a executação pula para esta linha e "desliga" o semáforo.

                    }
                } catch (Exception ex) { //Tratamentode exceções, se existirem.

                }

            }

        }
    });
  
    public void pensar(int tempo) { //Método pensar que recebe a variável tempo por parametro.
        try {//Try que tenta executar o código e caso haja exceções, pula para o catch tratá-la.
            sleep(tempo);//Aqui o filósofo que "chamou" o método irá pensar durante o tempo aleatório enviado via parametro de entrada.
        } catch (InterruptedException e) {//Tratamento das exceções que acabarem por aparecer.

        }
    }

    public void comer(int tempo) {//Método comer que recebe a variável tempo por parametro.
        try {//Try que tenta executar o código e caso haja exceções, pula para o catch tratá-la.
            sleep(tempo);//Aqui o filósofo que "chamou" o método irá comer durante o tempo aleatório enviado via parametro de entrada.
        } catch (InterruptedException e) {//Tratamento das exceções que acabarem por aparecer.

        }
    }
}
