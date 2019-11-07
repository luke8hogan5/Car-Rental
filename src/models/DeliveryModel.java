package models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DeliveryModel{
	private int deliverystatus;
	private Date dateProcessed; //processed Date
	private Date estDate; //estimated Date
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	
	public DeliveryModel( int deliverystatus, Date dateProcessed, Date estDate){
		this.deliverystatus = deliverystatus;
		this.dateProcessed = dateProcessed;
		this.estDate = estDate;
	}
	
	public int getDeliveryStatus(){
		return deliverystatus;
	}
	
	public void setDeliveryStatus(int deliverystatus){
		this.deliverystatus = deliverystatus;
	}
	
    public String getProcessedDate(){
        return dateFormat.format(dateProcessed);
    }
    
    public void setProcessedDate(Date d1){
        this.dateProcessed = d1;
    }
    
    public String getEstimatedDate(){
		return  dateFormat.format(estDate);
    }
    
    public void setEstimatedDate(Date d2){
        this.estDate = d2;
    }
}