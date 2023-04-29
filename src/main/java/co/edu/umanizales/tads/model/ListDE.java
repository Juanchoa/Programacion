package co.edu.umanizales.tads.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class ListDE {
    private NodeDE head;
    private int size;

     public void addPetToEnd(Pet pet){

         NodeDE newNodeDE=new NodeDE(pet);
         if(head!=null){
             NodeDE temp=this.head;
             while(temp!=null){

                 temp=temp.getNext();
             }
             temp.setNext(newNodeDE);
             newNodeDE.setPrevious(temp);
         }
         else{
             head=newNodeDE;
         }
         size++;
     }
     public void addPetToStart(Pet pet){
         NodeDE newNodeDE=new NodeDE(pet);
         if(head!=null){
             newNodeDE.setNext(head);
             head.setPrevious(newNodeDE);
             head=newNodeDE;
         }
         else{
             head=newNodeDE;
         }
         size++;
     }
    public void addPetInPosition(int position,Pet pet){
        NodeDE node= new NodeDE(pet);
        if(head!=null){
            if(position>size){
                addPetToEnd(pet);
            }
            if(position==1){
                addPetToStart(pet);
            }
            if(position!=1){
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
            head=node;
        }
        size++;
    }
    public void deletePetById(int identification){
        if(head!=null){
            NodeDE assistant=head;
            if(assistant.getData().getIdentification()==identification){

                head=assistant.getNext();
                head.setPrevious(null);
                size--;
                return;
            }
            while(assistant!=null){

                if(assistant.getNext().getData().getIdentification()==identification){

                    assistant.setNext(assistant.getNext().getNext());
                    assistant.getNext().getNext().setPrevious(assistant);

                    size--;
                }
                assistant=assistant.getNext();
            }
            //no lo encontró si se sale del while
        }
    }
    public void mixKids(){
        if(head!=null){
            ListDE copyList=new ListDE();
            NodeDE temp=this.head;
            int positionBoy=0;
            int positionGirl=0;
            while(temp!=null) {

                if (temp.getData().getGender().getGender()==('M')){

                    copyList.addPetInPosition(1+positionBoy,temp.getData());

                    positionBoy=positionBoy+2;

                }
                if(temp.getData().getGender().getGender()==('F')){
                    copyList.addPetInPosition(2+positionGirl,temp.getData());

                    positionGirl=positionGirl+2;
                }
                temp=temp.getNext();
            }
            head=copyList.getHead();
        }
        else{

        }
    }
    public NodeDE copyPet(NodeDE temp){
        return new NodeDE(temp.getData());
    }

    public void losePositions(int identification,int positionsToLose){

        if(head!=null){
            if(positionsToLose<size){
                if(head.getData().getIdentification()==identification){
                    NodeDE node= copyPet(head);
                    addPetInPosition(positionsToLose+1,node.getData());
                    head=head.getNext();
                }
                else{
                    int count=1;
                    NodeDE temp = head;
                    while(temp.getNext().getData().getIdentification()!=identification){

                        temp=temp.getNext();
                        count++;

                        if(temp.getNext()!=null){
                            //el pelao no existe
                            return;
                        }
                    }
                    NodeDE temp2=copyPet(temp.getNext());
                    temp.getNext().getNext().setPrevious(temp);
                    temp.setNext(temp.getNext().getNext());
                    addPetInPosition(count+1+positionsToLose,temp2.getData());

                }
            }
            else{
                //no se puede añadir en esa posición
                return;
            }
        }
    }
    public void winPositions(int identification,int positionsToWin){
        if(head!=null){
            if(positionsToWin<size){
                if(head.getData().getIdentification()==identification){
                    //no puede ganar posiciones
                }
                else{
                    int count=1;
                    NodeDE temp = head;
                    while(temp.getNext().getData().getIdentification()!=identification){
                        temp=temp.getNext();
                        count++;
                        if(temp.getNext()!=null){
                            //el pelao no existe
                            return;
                        }
                    }
                    NodeDE temp2=copyPet(temp.getNext());
                    temp.getNext().getNext().setPrevious(temp);
                    temp.setNext(temp.getNext().getNext());
                    if(positionsToWin>=count+1){
                        addPetToStart(temp2.getData());
                    }
                    else {
                        addPetInPosition((count + 1) - positionsToWin, temp2.getData());
                    }
                }
            }
            else{
                //no se puede añadir en esa posición
                return;
            }
        }
    }
    public void invert(){
        if(this.head !=null){
            ListDE listCp = new ListDE();
            NodeDE temp = this.head;
            while(temp != null){
                listCp.addPetToStart(temp.getData());
                temp = temp.getNext();
            }
            this.head = listCp.getHead();
        }
    }
    public void orderBoysToStart(){
        if(this.head !=null){
            ListDE listCp = new ListDE();
            NodeDE temp = this.head;
            while(temp != null){

                if(temp.getData().getGender().getGender()=='M')
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
    }
    public void changeExtremes(){
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

    }
    public void deletePetByAge(int age){
        NodeDE temp = head;
        while(temp!=null){

            if(temp.getData().getAge()==age){
                deletePetById(temp.getData().getIdentification());
            }
            temp=temp.getNext();
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
    public void addPetAtTheEndByInicialName(String letter){
        ListDE listCp = new ListDE();
        NodeDE temp = head;
        while(temp!=null){
            if(temp.getData().getName().startsWith(letter)){
                listCp.addPetToEnd(temp.getData());
            }
            else{
                listCp.addPetToStart(temp.getData());
            }
            temp=temp.getNext();
        }
        head=listCp.getHead();
    }
    public int getPetsRangeAge(int min, int max) {
        NodeDE temp = head;
        int count = 0;
        while (temp !=  null) {
            if (temp.getData().getAge() >= min && temp.getData().getAge() <= max) {
                count++;
            }
            temp= temp.getNext();
        }
        return count;
    }


    public int getCountKidsMunByLocationCode(String code){
        int count =0;
        if( this.head!=null){
            Node temp = this.head;
            while(temp != null){
                if(temp.getData().getLocationMun().getCode().equals(code)){
                    count++;
                }
                temp = temp.getNext();
            }
        }
        return count;
    }




}
