package co.edu.umanizales.tads.model;

import lombok.Data;

@Data
public class NodeDE {
    private NodeDE previous;
    private NodeDE next;
    private Pet data;
    public NodeDE(Pet data) {this.data = data;}
}
