package task;
import java.util.Date;
import java.util.Locale;
import java.util.ArrayList;
import java.text.SimpleDateFormat;

import javax.sql.rowset.CachedRowSet;

public class ToDo {
	  private String name = "";
	  private ArrayList<ToDo> toDoList;
	  private String  dueDate;
	  private String description="";
	  private String id;  
	 
	  public ToDo(){
	  }
	  
	  public ToDo(String ID,String todo,  String strdesc, String dtDueDate){
	      name=todo;
	      dueDate = dtDueDate;
	      description = strdesc;
	      id=ID;
	  }
	public String getId() {  
	    return id;  
	}  
	public void setId(String id) {  
	    this.id = id;  
	}  
	public String getName(){    
	    return name;
	}
	  public String getDate(){
	      return dueDate;
	  }
	  
	  public String getDescription(){    
	    return description;
	} 
	public void setName(String strname){  
		
	    name = strname;
	}
	  public void setDate(String dtdate){
			String pattern = "MM-dd-yyyy";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			dueDate= simpleDateFormat.format(dtdate);
	  }
	  
	  public void setDescription(String strdesc){    
	    description = strdesc;
	}

	  public void setList(ArrayList<ToDo> td){
	     toDoList = td; 
	  }

	  public void addToDo() throws Exception{
	      if(toDoList==null){
	          toDoList = new ArrayList<ToDo>();
	      }
	      toDoList.add(this);
	      try {

				//String pattern = "yyyy-MM-dd";
				//SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, Locale.US);
				//String strdueDate= simpleDateFormat.format(dueDate);
	    	 // @SuppressWarnings("deprecation")
			//Date date = new SimpleDateFormat("yyyy-MM-dd")
               //       .format(new Date.parse(dueDate));
		      MySQLAccess mysql = new MySQLAccess();
	      String query = "INSERT INTO tasks "
		        		+ "(task_name,task_desc,due_date) "
		        		+ " VALUES ('" + name + "','" + description + "', '" + dueDate + "');";
		                
		      mysql.exec(query);
	      }
	      catch(Exception e) {
	    	  throw e;
	      }

          //ToDo td = new ToDo(getName(),getDescription(),getDate());
	      if(toDoList==null){
	          toDoList = new ArrayList<ToDo>();
	      }
	      toDoList.add(this);
          
	      
	  }
	  public ArrayList<ToDo> getToDoList() throws Exception{
	      //return toDoList;
		  ArrayList<ToDo> myArray = new ArrayList<ToDo>();
	      try {
		      MySQLAccess mysql = new MySQLAccess();
		      String query = "SELECT * from tasks;";
		      CachedRowSet resultSet = mysql.execResults(query);
		     
		      while(resultSet.next()){                  
		             String task_name=resultSet.getString("task_name");
		             String task_desc=resultSet.getString("task_desc");
		             String due_date=resultSet.getString("due_date");
		             String id=resultSet.getString("id");
		             ToDo td = new ToDo(id,task_name,task_desc,due_date);
		             myArray.add(td);
		             
		      }
	      }
	      catch(Exception e) {
	    	  throw e;
	      }
	      return myArray;
	      
	  }
	  public String Hello() {
		  return "Hello";
	  }
	  public void DeleteToDo() throws Exception{
		  String query ="";
	      try {

				
		      MySQLAccess mysql = new MySQLAccess();
	       query = "DELETE FROM  todo.tasks WHERE id = " +  getId() + ";";
		                
		      mysql.exec(query);
	      }
	      catch(Exception e) {
	    	  throw e;
	      }
	      	//return query;
	      //toDoList.remove(this);
	  }
	  public String toString(){
	      return this.getDate() + " | " + this.getName() + " | " + this.getDescription() ;
	  }
	      public ToDo fromString(String string)
	    {
	        ToDo td = null;
	 
	        if (string == null)
	        {
	            return td;
	        }
	 
	        int commaIndex = string.indexOf(" | ");
	 
	        if (commaIndex == -1)
	        {
	            td = new ToDo(string, null,null);
	        }
	        else
	        {
	            String tdDate = string.substring(commaIndex );
	            String Name = string.substring(0, commaIndex);
	            commaIndex = tdDate.indexOf(" | ");            
	            String tdDesc = string.substring(commaIndex );
	            td = new ToDo(Name, tdDate, tdDesc);
	        }
	 
	        return td;
	    }
	}

