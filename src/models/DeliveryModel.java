package models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DeliveryModel{
	private int deliverystatus;
	private Date d1; //processed Date
	private Date d2; //estimated Date
	
	public DeliveryModel( int deliverystatus, Date d1, Date d2){
		this.deliverystatus = deliverystatus;
		this.d1 = d1;
		this.d2 = d2;
	}
	
	public int getDeliveryStatus(){
		return deliverystatus;
	}
	
	public void setDeliveryStatus(int deliverystatus){
		this.deliverystatus = deliverystatus;
	}
	
//    public String getProcessedDate(){
//        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
//        Date d1 = format.parse(d1String);
//        Date d1 = new Date(d1);
//        return d1;
//    }
    
    public void setProcessedDate(Date d1){
        this.d1 = d1;
    }
    
//    public String getEstimatedDate(){
//        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
//        Date d2 = format.parse(d2String);
//        Date d2 = new Date(d2);
//        return d2;
//    }
    
    public void setEstimatedDate(Date d2){
        this.d2 = d2;
    }
}