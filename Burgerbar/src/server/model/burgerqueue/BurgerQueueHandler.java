package server.model.burgerqueue;

import server.persistence.ArrayList;
import server.persistence.ListADT;
import shared.Burger;
import shared.sout;

public class BurgerQueueHandler implements BurgerQueue {

    private ListADT<Burger> arrayList = new ArrayList<>();

    @Override
    public synchronized void addBurger(Burger burger){

        while(arrayList.size()>=10){
            try{
                sout.write(this,"The queue for burger is full...");
                wait();
            }catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
        arrayList.add(burger);
        sout.write(this,"Burger has been added to queue...");
        notifyAll();
    }

    @Override
    public synchronized Burger removeBurger()  {
        while(arrayList.isEmpty()){
            try {
                wait();
                sout.write(this,"There are no burgers to eat...");
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        sout.write(this,"Customer eats a " + arrayList.get(0).getName());
        notifyAll();
        return arrayList.remove(0);

    }

}
