package dev.arvato.model;

public class Customer {
	 
    private long id;
    private String name;
    private CustomerGroup group;
     
    public Customer(){
        id=0;
    }
     
    public Customer(long id, String name, CustomerGroup group) {
        this.id = id;
        this.name = name;
        this.group = group;
    }
 
    public long getId() {
        return id;
    }
 
    public void setId(long id) {
        this.id = id;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
    
    public void setGroup(CustomerGroup group) {
    	this.group = group;
    }
    
    public CustomerGroup getGroup() {
    	return group;
    }
 
 
    @Override public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }
 
    @Override public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Customer))
            return false;
        Customer other = (Customer) obj;
        if (id != other.id)
            return false;
        return true;
    }
 
    @Override public String toString() {
        return "Customer [id=" + id + ", name=" + name + ", group=" + group + "]";
    }
 
     
}