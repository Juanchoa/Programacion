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

                    assistant.getNext().getNext().setPrevious(assistant);
                    assistant.setNext(assistant.getNext().getNext());

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



}
