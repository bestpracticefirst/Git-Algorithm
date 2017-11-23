package day60;

public class Employee {
    private Employee() {}  
    private String emString="zhangsan";
    private static Employee single=null;  
    //静态工厂方法   
    public static Employee getInstance() {  
         if (single == null) {    
             single = new Employee();  
         }    
        return single;  
    }
	public String getEmString() {
		return emString;
	}
	public void setEmString(String emString) {
		this.emString = emString;
	} 
    
}
