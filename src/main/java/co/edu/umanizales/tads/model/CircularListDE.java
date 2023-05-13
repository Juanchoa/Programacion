package co.edu.umanizales.tads.model;

import co.edu.umanizales.tads.errors.Validation;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data
public class CircularListDE {
    private NodeDE head;
    private int size;

    public boolean checkId(int id){
        boolean exist=false;
        if(head.getData().getIdentification()==id){
            //el pelao ya existe;
             exist=true;
        }
        else{
            NodeDE temp = head.getNext();
            while(temp!=head){

                if(temp.getData().getIdentification()==id){
                    //el pelao ya existe;
                     exist=true;
                     return exist;
                }
                temp=temp.getNext();
            }
        }
        return exist;
    }

    public void addPetToStart(Pet pet) throws Validation{
        NodeDE newNodeDE=new NodeDE(pet);
        if(head!=null){
            if(checkId(pet.getIdentification())){
                throw new Validation("Ya existe una mascota con esa dientificación");
            }
            NodeDE headPrev =head.getPrevious();
            newNodeDE.setNext(head);
            head.setPrevious(newNodeDE);
            newNodeDE.setPrevious(headPrev);
            headPrev.setNext(newNodeDE);
            head=newNodeDE;
        }
        else{
            head=newNodeDE;
            newNodeDE.setNext(head);
            newNodeDE.setPrevious(head);
        }
        size++;
    }
    public void addPetToStartWithOutVerification(Pet pet){
        NodeDE newNodeDE=new NodeDE(pet);
        if(head!=null){
            NodeDE headPrev =head.getPrevious();
            newNodeDE.setNext(head);
            head.setPrevious(newNodeDE);
            newNodeDE.setPrevious(headPrev);
            headPrev.setNext(newNodeDE);
            head=newNodeDE;
        }
        else{
            head=newNodeDE;
            newNodeDE.setNext(head);
            newNodeDE.setPrevious(head);
        }
        size++;
    }
    public void addPetToEnd(Pet pet)throws Validation{
        NodeDE newNodeDE=new NodeDE(pet);
        if(head!=null){
            if(checkId(pet.getIdentification())){
                throw new Validation("Ya existe una mascota con esa dientificación");
            }
            NodeDE headPrev =head.getPrevious();
            newNodeDE.setNext(head);
            head.setPrevious(newNodeDE);
            newNodeDE.setPrevious(headPrev);
            headPrev.setNext(newNodeDE);
        }
        else{
            head=newNodeDE;
            newNodeDE.setNext(head);
            newNodeDE.setPrevious(head);
        }
        size++;
    }
    public void addPetInPosition(int position, Pet pet)throws Validation{
        if(checkId(pet.getIdentification())){
            throw new Validation("Ya existe una mascota con esa dientificación");
        }
        if(position<=size){
            if(position==1){
                addPetToStartWithOutVerification(pet); //lo hago con el metodo sin verificar ya que arriba ya hice la validacion
            }
            else{
            NodeDE newNode = new NodeDE(pet);
            int count=1;
            NodeDE temp= head;
            while(count<position){
                temp=temp.getNext();
                count++;
            }
            NodeDE temp2 = temp.getPrevious();
            temp2.setNext(newNode);
            newNode.setPrevious(temp2);
            newNode.setNext(temp);
            temp.setPrevious(newNode);
            }
        }else{
            throw new Validation("No hay suficientes elementos para añadir a la mascota en esa posición.");
        }
    }
    public List<Pet> seePets(){
        List<Pet> pets = new ArrayList<>();
        if(head!=null){
            NodeDE temp = head;
            pets.add(temp.getData());
            temp=temp.getNext();
            while(temp!=head){
                pets.add(temp.getData());
                temp=temp.getNext();
            }
        }
        return pets;
    }
    public void cleanRandomPet(String sideToTurn)throws Validation{
        if(head!=null){
            if(sideToTurn.equals("derecha")){
                NodeDE temp =head;
                int count=1;
                Random random = new Random();
                int randomNumber = random.nextInt(100)+1;
                while (count<randomNumber){
                    temp=temp.getNext();
                    count++;
                }
                if(!temp.getData().isClean()){
                    temp.getData().setClean(true);
                }
                else{
                    throw new Validation("La mascota ya está limpia, no se puede bañar.");
                }
            }
            if(sideToTurn.equals("izquierda")){
                NodeDE temp =head;
                int count=1;
                Random random = new Random();
                int randomNumber = random.nextInt(100)+1;
                while (count<randomNumber){
                    temp=temp.getPrevious();
                    count++;
                }
                if(!temp.getData().isClean()){
                    temp.getData().setClean(true);
                }
                else{
                    throw new Validation("La mascota ya está limpia, no se puede bañar.");
                }
            }
        }
        else{
            throw new Validation("No existen mascotas para bañar.");
        }
    }

}
