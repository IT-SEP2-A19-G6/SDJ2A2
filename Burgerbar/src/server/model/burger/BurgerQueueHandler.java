package server.model.burger;

import client.domain.Burger;
import client.domain.Recipe;
import server.persistence.ArrayList;

public class BurgerQueueHandler implements BurgerQueue {

    private ArrayList arrayList;
    private Burger burger;

    @Override
    public void addBurger(Burger burger) {

        while(!(arrayList.isFull())){
            try{
                wait();
            }catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
        arrayList.add(burger);
        notifyAll();
    }

    @Override
    public void removeBurger() {
        while(arrayList.size()<=0){
            try {
                wait();
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        arrayList.remove(burger);
        notifyAll();
    }
}
