package day60;

public class Elvis  
{  
    private static boolean flag = false;  
    String name="张三";
    private Elvis(){  
    }  
  
    private  static class SingletonHolder{  
        private static final Elvis INSTANCE = new Elvis();  
    }  
  
    public static Elvis getInstance()  
    {  
        return SingletonHolder.INSTANCE;  
    }  
  
    public void doSomethingElse()  
    {  
  
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}  
    
}  