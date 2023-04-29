package co.edu.umanizales.tads.model;

import co.edu.umanizales.tads.service.ListSEService;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class ListSE {
    private Node head;
    private int size;
    public int checkIdentification(int identificaion) {
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
        size++;
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
        size++;
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
        size++;
    }
    public void addInPosition(int position,Kid kid){
        Node node= new Node(kid);
        if(head!=null){
            if(position>size){
                addToEnd(kid);
            }
            if(position==1){
                addToStart(kid);
            }
            if(position!=1){
                int accumulator=0;
                Node assistant=this.head;
                while(accumulator<position-2 && assistant.getNext()!=null){

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
            size++;
    }

    public void deleteKidByIdentification(int identification){
        if(head!=null){
            Node assistant=head;
            if(assistant.getData().getIdentification()==identification){

                head=assistant.getNext();
                size--;
                return;
            }
            while(assistant!=null){

                if(assistant.getNext().getData().getIdentification()==identification){
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
            ListSE copyList=new ListSE();
            Node temp=this.head;
            int positionBoy=0;
            int positionGirl=0;
            while(temp!=null) {

                if (temp.getData().getGender().getGender()==('M')){

                    copyList.addInPosition(1+positionBoy,temp.getData());

                    positionBoy=positionBoy+2;

                }
                if(temp.getData().getGender().getGender()==('F')){
                    copyList.addInPosition(2+positionGirl,temp.getData());

                    positionGirl=positionGirl+2;
                }
                temp=temp.getNext();
            }
            head=copyList.getHead();
        }

    }
    public Node copyKid(Node temp){
        return new Node(temp.getData());
    }
    public void losePositions(int identification,int positionsToLose){

        if(head!=null){
            if(positionsToLose<size){
                if(head.getData().getIdentification()==identification){
                    Node node= copyKid(head);
                    addInPosition(positionsToLose+1,node.getData());
                    head=head.getNext();
                }
                else{
                    int count=1;
                    Node temp = head;
                    while(temp.getNext().getData().getIdentification()!=identification){

                        temp=temp.getNext();
                        count++;

                        if(temp.getNext()!=null){
                            //el pelao no existe
                            return;
                        }
                    }
                    Node temp2=copyKid(temp.getNext());
                    temp.setNext(temp.getNext().getNext());
                    addInPosition(count+1+positionsToLose,temp2.getData());
                }
            }
            else{
                //no se puede añadir en esa posición

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
                    Node temp = head;
                    while(temp.getNext().getData().getIdentification()!=identification){
                        temp=temp.getNext();
                        count++;
                        if(temp.getNext()!=null){
                            //el pelao no existe

                            return;
                        }
                    }
                    Node temp2=copyKid(temp.getNext());
                    temp.setNext(temp.getNext().getNext());
                    if(positionsToWin>=count+1){
                        addToStart(temp2.getData());
                    }
                    else {
                        addInPosition((count + 1) - positionsToWin, temp2.getData());
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
                if(temp.getData().getGender().getGender()=='M')
                {
                    listCp.addToStart(temp.getData());
                }
                else{
                    listCp.addToEnd(temp.getData());
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
    public void deleteKidByAge(int age){
        Node temp = head;
        while(temp!=null){

            if(temp.getData().getAge()==age){
                deleteKidByIdentification(temp.getData().getIdentification());
            }
            temp=temp.getNext();
        }
    }
    public float averageKidsAge(){
        if (head != null){
            Node temp = head;
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
    public void addAtTheEndByInicialName(String letter){
        ListSE listCp = new ListSE();
        Node temp = head;
        while(temp!=null){
            if(temp.getData().getName().startsWith(letter)){
                listCp.addToEnd(temp.getData());
            }
            else{
                listCp.addToStart(temp.getData());
            }
            temp=temp.getNext();
        }
        head=listCp.getHead();
    }
    public int getKidsRangeByAge(int min, int max) {
        Node temp = head;
        int count = 0;
        while (temp !=  null) {
            if (temp.getData().getAge() >= min && temp.getData().getAge() <= max) {
                count++;
            }
            temp= temp.getNext();
        }
        return count;
    }
}