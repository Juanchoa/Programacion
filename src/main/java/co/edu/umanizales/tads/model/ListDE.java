package co.edu.umanizales.tads.model;

import co.edu.umanizales.tads.errors.Validation;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ListDE {
    private NodeDE head;
    private int size;


    public int checkIdentificationPet(int identificaion) {
        int numero = 0;
        if (head != null){
            NodeDE temp = head;
            while (temp!= null) {
                if (temp.getData().getIdentification() == identificaion) {

                    //pelao existe
                    numero = 1;

                    return numero;
                }
                temp = temp.getNext();
            }
            //si retorna 0 no existe
        }
        return numero;
    }

    public List<Pet> seePets()throws Validation{
        List<Pet> newListDE= new ArrayList<>();
        if(head!=null){
            NodeDE temp = head;
            while(temp!=null){

                newListDE.add(temp.getData());
                temp=temp.getNext();
            }
            return newListDE;
        }
        else{
            throw new Validation("No hay datos que listar");
        }
    }

     public void addPetToEnd(Pet pet)throws Validation{

         NodeDE newNodeDE=new NodeDE(pet);
         if(head!=null){
             NodeDE temp=this.head;
             while(temp.getNext()!=null){

                    if(temp.getData().getIdentification()==pet.getIdentification()){
                        throw new Validation("Ya existe una mascota con esa identificación.");
                    }
                 temp=temp.getNext();
             }
             if(temp.getData().getIdentification()==pet.getIdentification()){
                 throw new Validation("Ya existe una mascota con esa identificación.");
             }
             temp.setNext(newNodeDE);
             newNodeDE.setPrevious(temp);
         }
         else{
             head=newNodeDE;
         }
         size++;
     }
     public void addPetToStart(Pet pet) throws Validation{

         NodeDE newNodeDE=new NodeDE(pet);
         if(head!=null){
             if(checkIdentificationPet(pet.getIdentification())==1){
                 throw new Validation("Ya existe una mascota con esa dientificación");
             }
             newNodeDE.setNext(head);
             head.setPrevious(newNodeDE);
             head=newNodeDE;
         }
         else{
             head=newNodeDE;
         }
         size++;
     }
    public void addPetInPosition(int position,Pet pet) throws Validation{
        if(checkIdentificationPet(pet.getIdentification())==1){
            throw new Validation("La mascota ya existe.");
        }
        NodeDE node= new NodeDE(pet);
        if(head!=null){
            if(position-1>size){
                throw new Validation("La cantidad de mascotas no es suficiente para añadir en esa posición");
            }
            if(position==1){
                addPetToStart(pet);
                return;
            }
            else{
                int accumulator=0;
                NodeDE assistant=this.head;
                while(accumulator<position-2 && assistant.getNext()!=null){

                    assistant=assistant.getNext();
                    accumulator=accumulator+1;
                }
                node.setNext(assistant.getNext());
                assistant.getNext().setPrevious(node);
                assistant.setNext(node);
                node.setPrevious(assistant);
            }
        }
        else{
            if(position==1){
                addPetToStart(pet);
            }
            else{
                throw new Validation("La lista está vacia, solo se puede añadir en la primera posición.");
            }
        }
        size++;
    }
    public void deletePetById (int id)throws Validation{
        if(head==null){
            throw new Validation("La lista está vacia.");
        }
        NodeDE temp = head;
        while (temp != null){
            if (temp.getData().getIdentification()==id){
                NodeDE prev = temp.getPrevious();
                NodeDE next = temp.getNext();
                if (prev == null){
                    head = next;
                }else{
                    prev.setNext(next);
                }
                if (next != null){
                    next.setPrevious(prev);
                }
                size--;
                return;
            }
            temp = temp.getNext();
        }
            throw new Validation("No existe una mascota con esa identificación.");
    }
    public void mixPets()throws Exception{
        if(head!=null){
            ListDE copyList=new ListDE();
            NodeDE temp=this.head;
            int positionBoy=0;
            int positionGirl=0;
            while(temp!=null) {

                if (temp.getData().getGender().equals("M")){

                    copyList.addPetInPosition(1+positionBoy,temp.getData());

                    positionBoy=positionBoy+2;

                }
                if(temp.getData().getGender().equals("F")){
                    copyList.addPetInPosition(2+positionGirl,temp.getData());

                    positionGirl=positionGirl+2;
                }
                temp=temp.getNext();
            }
            head=copyList.getHead();
        }
        else{
                throw new Validation("La lista está vacia, no hay datos que mezclar");
        }
    }
    public NodeDE copyPet(NodeDE temp){
        return new NodeDE(temp.getData());
    }

    public void losePositions(int identification,int positionsToLose)throws Validation{

        if(head!=null){
            if(positionsToLose<size){
                if(head.getData().getIdentification()==identification){
                    NodeDE node= copyPet(head);
                    head=head.getNext();
                    addPetInPosition(positionsToLose+1,node.getData());
                }
                else{
                    int count=1;
                    NodeDE temp = head;
                    while(temp.getNext().getData().getIdentification()!=identification){

                        temp=temp.getNext();
                        count++;

                        if(temp.getNext()==null){
                            throw new Validation("La mascota no existe.");
                        }
                    }
                    if(temp.getNext().getNext()==null){
                        throw new Validation("La mascota no puede perder posiciones ya que está en el ultimo lugar de la lista.");
                    }
                    NodeDE temp2=copyPet(temp.getNext());
                    deletePetById(temp.getNext().getData().getIdentification());
                    addPetInPosition((count+1+positionsToLose),temp2.getData());
                }
            }
            else{
                throw new Validation("No hay datos suficientes para que la mascota pueda perder esa cantidad de posiciones.");
            }
        }
        else {
            throw new Validation("La lista está vacia");}
    }
    public void winPositions(int identification,int positionsToWin)throws Validation{
        if(head!=null){
            if(positionsToWin<size){
                if(head.getData().getIdentification()==identification){
                    throw new Validation("La mascota no puede ganar posiciones ya que está en el primer lugar de la lista.");
                }
                else{
                    int count=1;
                    NodeDE temp = head;
                    while(temp.getNext().getData().getIdentification()!=identification){
                        temp=temp.getNext();
                        count++;
                        if(temp.getNext()==null){
                            throw new Validation("La mascota no existe.");
                        }
                    }
                    //copiamos los datos del dato que va a ganar las posiciones
                    NodeDE temp2=copyPet(temp.getNext());
                    //eliminamos al nodo que contiene los datos en la lsita
                    deletePetById(temp.getNext().getData().getIdentification());
                    //añadimos la copia del dato en la lista
                    if(positionsToWin>=count+1){
                        addPetToStart(temp2.getData());
                    }
                    else {
                        addPetInPosition(((count + 1) - positionsToWin), temp2.getData());
                    }
                }

            }
            else{throw new Validation("No hay datos suficientes para que la mascota pueda ganar esa cantidad de posiciones.");}
        }
        else {throw new Validation("La lista está vacia");}
    }
    public void invert()throws Validation{
        if(this.head !=null){
            ListDE listCp = new ListDE();
            NodeDE temp = this.head;
            while(temp != null){
                listCp.addPetToStart(temp.getData());
                temp = temp.getNext();
            }
            this.head = listCp.getHead();
        }
        else{
            throw new Validation("La lista está vacia");
        }
    }
    public void orderBoysToStart()throws Validation{
        if(this.head !=null){
            ListDE listCp = new ListDE();
            NodeDE temp = this.head;
            while(temp != null){

                if(temp.getData().getGender().equals("M"))
                {
                    listCp.addPetToStart(temp.getData());
                }
                else{
                    listCp.addPetToEnd(temp.getData());
                }
                temp = temp.getNext();
            }
            this.head = listCp.getHead();
        }
        else{
            throw new Validation("La lista está vacia.");
        }
    }
    public void changeExtremes()throws Validation{
        if(this.head !=null && this.head.getNext() !=null)
        {
            NodeDE temp = this.head;
            while(temp.getNext()!=null)
            {
                temp = temp.getNext();
            }
            //temp está en el último
            Pet copy = this.head.getData();
            this.head.setData(temp.getData());
            temp.setData(copy);
        }
        else{
            throw new Validation("No hay datos suficientes para cambiar los extremos");
        }
    }
    public void deletePetByAge(int age)throws Validation{
        NodeDE temp = head;
        int count =0;
        while(temp!=null){

            if(temp.getData().getAge()==age){
                deletePetById(temp.getData().getIdentification());
                count++;
            }
            temp=temp.getNext();
        }
        if(count<1){
            throw new Validation("No hay mascotas con esa edad.");
        }
    }
    public float averagePetsAge(){
        if (head != null){
            NodeDE temp = head;
            int count = 0;
            int ages = 0;
            while(temp != null) {
                count++;
                ages = ages + temp.getData().getAge();
                temp = temp.getNext();
            }
            float average=ages/(float)count;

            return average;
        }
        else{
            return 0;
        }
    }
    public void addPetAtTheEndByInicialName(String letter)throws Validation{
        if(head!=null) {
            ListDE listCp = new ListDE();
            NodeDE temp = head;
            while (temp != null) {
                if (temp.getData().getName().startsWith(letter)) {
                    listCp.addPetToEnd(temp.getData());
                } else {
                    listCp.addPetToStart(temp.getData());
                }
                temp = temp.getNext();
            }
            if (listCp.size<1){
                throw new Validation("No hay mascotas que su nombre inicie por la letra dada");
            }
            head = listCp.getHead();
        }
        else{
            throw new Validation("La lista está vacia");
        }
    }
    public int getPetsRangeAge(int min, int max)throws Validation {
        if(head!=null) {
            NodeDE temp = head;
            int count = 0;
            while (temp != null) {
                if (temp.getData().getAge() >= min && temp.getData().getAge() <= max) {
                    count++;
                }
                temp = temp.getNext();
            }
            return count;
        }
        else{
            throw new Validation("La lista está vacia");
        }
    }

    public int getCountPetsSpicesByCode(String code){
        int count =0;
        if( this.head!=null){
            NodeDE temp = this.head;
            while(temp != null){
                if(temp.getData().getSpecies().getCode()==code){
                    count++;
                }
                temp = temp.getNext();
            }
        }
        return count;
    }

    /*
    algoritmo metodo para eliminar parado en la posición a eliminar

    necesito saber que mascota voy a eliminar, asi que le pido al usario una id por parametro
    ¿hay datos?
    si- creo un temp y le digo que se pare en la cabeza, mientras su id sea diferente a la que dió el usario
        va a ser igual a su siguiente.
        si temp es igual a nulo, la mascota no exite y se lo digo al usario.
         Cuando la id de temp sea igual a la del parametro:
          creo 2 temporales, uno será el anterio de temp y el otro su siguiente.
          ¿ambos son nulos?
          significa que solo está el elemento a eliminar en la lista
          entonces hago que la cabeza sea nula.
          ¿temp anterior es nulo?
           si-nombramos a la cabeza como temp siguiente.
          ¿temp siguiente es nulo?
          si-el siguiente de temp anterior será nulo
         si ninguna de esas 2 condiciones se cumple:
          el siguiente de temp anterior será temp siguiente y
          el anterior de temp siguiente será temp anterior
   no- digo que no hay datos

      fin.
     */
    public void deletePetInPositionById(int id){

        if(this.head != null){

           NodeDE temp= this.head;
           while (temp.getData().getIdentification()!= id){
               temp=temp.getNext();
               if(temp==null){
                   return;
                   //la mascota no existe
               }
           }
           NodeDE tempNext= temp.getNext();
           NodeDE tempPrev= temp.getPrevious();

           if(tempNext==null && tempPrev==null ){
               this.head=null;
               size--;
               return;
           }
           if(tempPrev==null){
               head=tempNext;
               size--;
               return;
           }
           if(tempNext==null){
               tempPrev.setNext(null);
               size--;
               return;
           }
           tempPrev.setNext(tempNext);
           tempNext.setPrevious(tempPrev);
           size--;
        }
    }
}
