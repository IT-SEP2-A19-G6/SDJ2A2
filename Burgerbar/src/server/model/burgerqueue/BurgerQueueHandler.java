package server.model.burgerqueue;

import Shared.Burger;
import server.persistence.ArrayList;
import server.persistence.ListADT;

public class BurgerQueueHandler implements BurgerQueue {

    private ListADT<Burger> arrayList = new ArrayList<>();

    @Override
    public synchronized void addBurger(Burger burger){

        while(arrayList.size()>=10){
            try{
                System.out.println("The queue for burger is full...");
                wait();
            }catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
        arrayList.add(burger);
        System.out.println("Burger has been added to queue...");
        notifyAll();
    }

    @Override
    public synchronized Burger removeBurger()  {
        while(arrayList.isEmpty()){
            try {
                wait();
                System.out.println("There are no burgers to eat...");
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.println(arrayList.get(0).toString());
        notifyAll();
        return arrayList.remove(0);

    }

}
