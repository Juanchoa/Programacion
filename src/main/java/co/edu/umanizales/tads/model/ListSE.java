package co.edu.umanizales.tads.model;

import co.edu.umanizales.tads.errors.Validation;
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

                return numero;
            }
            temp = temp.getNext();
        }
        //si retorna 0 no existe
        }
        return numero;
    }

    public void addToStart(Kid kid)throws Validation {
        if(head !=null)
        {
            if(checkIdentification(kid.getIdentification())==1){
                throw new Validation("Ya existe un pelao con esa identificación");
            }
            Node newNode = new Node(kid);
            newNode.setNext(head);
            head = newNode;
            size++;
        }
        else {
            head = new Node(kid);
            size++;
        }
    }
    public void addToEnd(Kid kid)throws Validation{
        Node newNode = new Node(kid);
        if(head !=null)
        {
            Node temp=this.head;
            while(temp.getNext()!=null){

                if(temp.getData().getIdentification()==kid.getIdentification()){
                    throw new Validation("Ya existe un pelao con esa identificación.");
                }
                temp=temp.getNext();
            }
            if(temp.getData().getIdentification()==kid.getIdentification()){
                throw new Validation("Ya existe una pelao con esa identificación.");
            }
            temp.setNext(newNode);
            size++;
        }
        else{
            head= new Node(kid);
            size++;
        }
    }
    public void addInPosition(int position,Kid kid)throws Validation{
        if(checkIdentification(kid.getIdentification())==1){
            throw new Validation("el pelao ya existe.");
        }
        Node node= new Node(kid);
        if(head!=null){
            if(position-1>size){
                throw new Validation("La cantidad de niños no es suficiente para añadir en esa posición");
            }
            if(position==1){
                addToStart(kid);
            }
            else{
                int accumulator=0;
                Node assistant=this.head;
                while(accumulator<position-2 && assistant.getNext()!=null){

                    assistant=assistant.getNext();
                    accumulator=accumulator+1;
                }
                node.setNext(assistant.getNext());
                assistant.setNext(node);
                size++;
            }
        }
        else{
            if(position==1){
                addToStart(kid);
            }
            else{
                throw new Validation("La lista está vacia, solo se puede añadir en la primera posición.");
            }
        }
    }

    public void deleteKidByIdentification(int identification)throws Validation{
        if(head==null){
            throw new Validation("La lista está vacia.");
        }
        else{

            Node assistant=head;
            if(assistant.getData().getIdentification()==identification){

                head=assistant.getNext();
                size--;
            }
            else {
                    while (assistant != null) {

                        if (assistant.getNext().getData().getIdentification() == identification) {
                            assistant.setNext(assistant.getNext().getNext());
                            size--;
                            return;
                        }
                        assistant = assistant.getNext();
                    }
                throw new Validation("No existe un pelao con esa identificación.");
            }

        }
    }
    public void mixKids()throws Validation{
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
        else{
            throw new Validation("La lista está vacia.");
        }

    }
    public Node copyKid(Node temp){
        return new Node(temp.getData());
    }
    public void losePositions(int identification,int positionsToLose)throws Validation{
        if(head!=null){

            if(positionsToLose<size){
                if(head.getData().getIdentification()==identification){
                    Node node= copyKid(head);
                    head=head.getNext();
                    addInPosition(positionsToLose+1,node.getData());
                }
                else{
                    int count=1;
                    Node temp = head;
                    while(temp.getNext().getData().getIdentification()!=identification){

                        temp=temp.getNext();
                        count++;

                        if(temp.getNext()==null){
                            throw new Validation("El pelao no existe");
                        }
                    }
                    if(temp.getNext().getNext()==null){
                        throw new Validation("El niño está en la ultima posición, no puede perder mas posiciones");
                    }
                    Node temp2=copyKid(temp.getNext());
                    deleteKidByIdentification(temp.getNext().getData().getIdentification());
                    addInPosition((count+1+positionsToLose),temp2.getData());
                }
            }
            else{
                throw new Validation("No hay suficientes elementos en la lista para que el niño pierda esas posiciones.");
            }
        }
        else{
            throw new Validation("La lista está vacia..");
        }
    }
    public void winPositions(int identification,int positionsToWin)throws Validation{
        if(head!=null){
            if(positionsToWin<size){
                if(head.getData().getIdentification()==identification){
                    throw new Validation("El pelao está en la primera posición, no puede ganar posiciones.");
                }
                else{
                    int count=1;
                    Node temp = head;
                    while(temp.getNext().getData().getIdentification()!=identification){
                        temp=temp.getNext();
                        count++;
                        if(temp.getNext()==null){
                            throw new Validation("El pelao no existe");
                        }
                    }
                    Node temp2=copyKid(temp.getNext());
                    deleteKidByIdentification(temp.getNext().getData().getIdentification());
                    if(positionsToWin>=count+1){
                        addToStart(temp2.getData());
                    }
                    else {
                        addInPosition(((count + 1) - positionsToWin), temp2.getData());
                    }
                }
            }
            else{
                throw new Validation("No hay elementos suficientes para poder añadir al pelao en esa posicion.");

            }
        }
        else {
            throw new Validation("La lista está vacia:");
        }
    }

    public void invert()throws Validation{
        if(this.head !=null && head.getNext() != null){
            ListSE listCp = new ListSE();
            Node temp = this.head;
            while(temp != null){
                listCp.addToStart(temp.getData());
                temp = temp.getNext();
            }
            this.head = listCp.getHead();
        }
        else{
            throw new Validation("No hay elementos suficientes para poder invetir la lista.");
        }
    }
    public void orderBoysToStart()throws Validation{
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
        else{
            throw new Validation("La lista está vacia.");
        }
    }
    public void changeExtremes()throws Validation{
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
        else{
            throw new Validation("No hay elementos suficientes para poder cambiar los extremos de la lista.");
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

    public void deleteKidByAge(int age)throws Validation{
        Node temp = head;
        int count=0;
        while(temp!=null){

            if(temp.getData().getAge()==age){
                deleteKidByIdentification(temp.getData().getIdentification());
                count++;
            }
            temp=temp.getNext();
        }
        if(count<1){
            throw new Validation("No hay niños con esa edad.");
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
    public void addAtTheEndByInicialName(String letter)throws Validation{
        if(head!=null) {
            ListSE listCp = new ListSE();
            Node temp = head;
            int count = 0;
            while (temp != null) {
                if (temp.getData().getName().startsWith(letter)) {
                    listCp.addToEnd(temp.getData());
                    count++;
                } else {
                    listCp.addToStart(temp.getData());
                }
                temp = temp.getNext();
            }
            if (count < 1) {
                throw new Validation("No hay niños que tengan esa inicial.");
            } else {
                head = listCp.getHead();
            }
        }
        else{
            throw new Validation("La lista está vacia.");
        }
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