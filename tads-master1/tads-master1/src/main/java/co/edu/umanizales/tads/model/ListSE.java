package co.edu.umanizales.tads.model;

import co.edu.umanizales.tads.service.ListSEService;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class ListSE {
    private Node head;
    public int comprobarIdentificaion(int identificaion) {
        int numero = 0;
        if (head != null){
            Node temp = head;
        while (temp!= null) {

            if (temp.getData().getIdentification() == identificaion) {

                //pelao existe
                numero = 1;
            }
            temp = temp.getNext();
        }
        //si retorna 0 no existe
        }
        return numero;
    }




    public void add(Kid kid){

        if(head != null){
            Node temp = head;
            while(temp.getNext() !=null)
            {
                temp = temp.getNext();
            }
            /// Parado en el último
            Node newNode = new Node(kid);
            temp.setNext(newNode);
        }
        else {
            head = new Node(kid);
        }
    }
    public void addToStart(Kid kid){
        if(head !=null)
        {
            Node newNode = new Node(kid);
            newNode.setNext(head);
            head = newNode;
        }
        else {
            head = new Node(kid);
        }
    }
    public void addToEnd(Kid kid){
        Node newNode = new Node(kid);
        if(head !=null)
        {
            Node temp=this.head;
            while(temp.getNext()!=null){

                temp=temp.getNext();
            }
            temp.setNext(newNode);
        }
        else{
            head= new Node(kid);
        }
    }
    public void addInPosition(int posicion,Kid kid){
        Node node= new Node(kid);
        if(head!=null){
            if(posicion==1){
                addToStart(node.getData());
            }
            if(posicion!=1){
                int accumulator=0;
                Node assistant=this.head;
                while(accumulator<posicion-2 & assistant.getNext()!=null){

                    assistant=assistant.getNext();
                    accumulator=accumulator+1;
                }
                node.setNext(assistant.getNext());
                assistant.setNext(node);
            }
        }
        else{
            head=node;
        }

    }

    public void deleteKidByIdentification(int identification){

        if(head!=null){

            Node assistant=head;
            while(assistant.getNext()!=null && assistant.getNext().getData().getIdentification()!=identification){

                assistant=assistant.getNext();
            }

             assistant.setNext(assistant.getNext().getNext());
        }
        else{
            head=null;
        }
    }


    public void losePositions(int position,int identification){

        if(head!=null){

           Node temp=head;
           while(temp.getNext().getData().getIdentification()!=identification){

               temp= temp.getNext();
            }
           Node temp5=temp.getNext().getNext();
            int acumulator=0;
            Node temp2=temp.getNext();
            temp2.getData();
            Node temp3=temp.getNext();
            while (acumulator<position-1 &&temp3.getNext()!=null ){

                temp3=temp3.getNext();
                acumulator=acumulator+1;
            }
            Node temp6=temp3.getNext().getNext();
            Node temp4=temp3.getNext();
            temp4.getData();

            temp3.setNext(temp2);
            temp2.setNext(temp6);

            temp.setNext(temp4);
            temp4.setNext(temp5);
        }
        else{
            head=null;
        }
    }
    public void mixKids(){
        if(head!=null){
            ListSE copyList=new ListSE();
            Node temp=this.head;

            int positionBoy=0;
            int positionGirl=0;
            while(temp!=null) {

                if (temp.getData().getGender().equals('M')) {

                    copyList.addInPosition(1+positionBoy,temp.getData());

                    positionBoy=positionBoy+2;

                }

                if(temp.getData().getGender().equals('M')){
                    copyList.addInPosition(2+positionGirl,temp.getData());

                    positionGirl=positionGirl+2;
                }
                temp=temp.getNext();
            }
            head=copyList.getHead();
        }
        else{

        }
    }

    public void invert(){
        if(this.head !=null){
            ListSE listCp = new ListSE();
            Node temp = this.head;
            while(temp != null){
                listCp.addToStart(temp.getData());
                temp = temp.getNext();
            }
            this.head = listCp.getHead();
        }
    }

    public void orderBoysToStart(){
        if(this.head !=null){
            ListSE listCp = new ListSE();
            Node temp = this.head;
            while(temp != null){
                if(temp.getData().getGender().equals('M'))
                {
                    listCp.addToStart(temp.getData());
                }
                else{
                    listCp.add(temp.getData());
                }

                temp = temp.getNext();
            }
            this.head = listCp.getHead();
        }
    }

    public void changeExtremes(){
        if(this.head !=null && this.head.getNext() !=null)
        {
            Node temp = this.head;
            while(temp.getNext()!=null)
            {
                temp = temp.getNext();
            }
            //temp está en el último
            Kid copy = this.head.getData();
            this.head.setData(temp.getData());
            temp.setData(copy);
        }

    }

    public int getCountKidsDepByLocationCode(String code){
        int count =0;
        if( this.head!=null){
            Node temp = this.head;
            while(temp != null){
                if(temp.getData().getLocationDep().getCode().equals(code)){
                    count++;
                }
                temp = temp.getNext();
            }
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
    public int getCountKidsMunByLocationCodeAndAge(String code,int age){
        int count =0;
        if( this.head!=null){
            Node temp = this.head;
            while(temp != null){
                if(temp.getData().getLocationMun().getCode().equals(code)&&(temp.getData().getAge()>age)){

                    count++;
                }
                temp = temp.getNext();
            }
        }
        return count;
    }

    public int getCountKidsLocationByGenderAndAge(String code,String codeGender,int age){
        int count= 0;
        if(this.head!=null){
            Node temp = this.head;
            while (temp != null){

                if((temp.getData().getLocationMun().getCode().equals(code)) && (temp.getData().getGender().getCodeGender().equals(codeGender))){

                    if(temp.getData().getAge()>age){

                        count++;

                    }
                }
                temp = temp.getNext();
            }
        }
        return count;
    }



    public int getCountKidsByCodeGender(String codeGender,int age){
        int count= 0;
        if(this.head!=null){
            Node temp = this.head;
            while (temp != null){
                if(temp.getData().getGender().getCodeGender().equals(codeGender)&&(temp.getData().getAge()>age)){

                    count++;

                }
                temp = temp.getNext();
            }
        }
        return count;
    }




    /*public int getCountKidsByGender(char gender){
        int count= 0;
        if(this.head!=null){
            Node temp = this.head;
            while (temp != null){
                if(temp.getData().getGender().equals(gender)){

                    count++;

                }
                temp = temp.getNext();
            }
        }
        return count;
    }*/







}